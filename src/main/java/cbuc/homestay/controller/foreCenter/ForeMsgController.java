package cbuc.homestay.controller.foreCenter;

import cbuc.homestay.CommonEnum.LevelEnum;
import cbuc.homestay.CommonEnum.StatusEnum;
import cbuc.homestay.base.Result;
import cbuc.homestay.bean.Merchant;
import cbuc.homestay.bean.Message;
import cbuc.homestay.bean.User;
import cbuc.homestay.service.MerchantService;
import cbuc.homestay.service.MessageService;
import cbuc.homestay.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Explain: 小程序端消息控制器
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/2/8
 */
@Slf4j
@Controller
@Api(value = "小程序端消息控制器", description = "处理消息相关内容")
public class ForeMsgController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Autowired
    private MerchantService merchantService;

    @ApiOperation("用户端获取消息列表")
    @ResponseBody
    @RequestMapping("getMsgList")
    public Object getMsgList(String openId) {
        List<Message> messages = null;
        try {
            User user = userService.queryDetail(openId);
            Message message = new Message();
            message.setReceiveId(user.getId());
            message.setReceiveType("USER");
            messages = messageService.getPullList(Message.builder().receiveId(user.getId()).receiveType(LevelEnum.USER.getValue()).build());
            if (messages.size() <= 0 || messages == null) {
                messages = messageService.getPushList(Message.builder().sendId(user.getId()).sendType(LevelEnum.USER.getValue()).build());
                messages.stream().forEach(msg -> {
                    Merchant merchant = merchantService.queryDetail(msg.getReceiveId());
                    msg.setSendName(merchant.getMname());
                    msg.setAvatarUrl(merchant.getAvatarUrl());
                    msg.setSendId(msg.getReceiveId());
                });
            } else {
                messages.stream().forEach(msg -> {
                    Merchant merchant = merchantService.queryDetail(msg.getSendId());
                    msg.setSendName(merchant.getMname());
                    msg.setAvatarUrl(merchant.getAvatarUrl());
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("获取消息列表异常");
        }
        return Result.success(messages);
    }

    @ApiOperation("商户端获取消息列表")
    @ResponseBody
    @RequestMapping("getMerMsgList")
    public Object getMerMsgList(Long mid) {
        List<Message> messages = null;
        try {
            messages = messageService.getPullList(Message.builder().receiveType(LevelEnum.MERCHANT.getValue()).receiveId(mid).sendType(LevelEnum.USER.getValue()).build());
            if (messages.size() > 0 || messages != null) {
                messages.stream().forEach(message -> {
                    User user = userService.queryDetail(message.getSendId());
                    message.setSendName(user.getUname());
                    message.setAvatarUrl(user.getAvatarUrl());
                    message.setSendId(user.getId());
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("获取消息列表异常");
        }
        return Result.success(messages);
    }

    @ApiOperation(("/拉取聊天详情"))
    @ResponseBody
    @RequestMapping("getChatList")
    public Object getChatList(String openId, Long mId) {
        List<Message> messageList = null;
        try {
            User user = userService.queryDetail(openId);
            messageList = messageService.queryChatList(user.getId(), mId);
            messageList.stream().forEach(ml -> {
                if (ml.getSendId().compareTo(user.getId()) == 0) {
                    ml.setIsSelf("true");
                    ml.setAvatarUrl(user.getAvatarUrl());
                } else {
                    ml.setIsSelf("false");
                    Merchant merchant = merchantService.queryDetail(ml.getSendId());
                    ml.setReadStatus("YR");
                    messageService.doEdit(ml);
                    ml.setAvatarUrl(merchant.getAvatarUrl());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            log.info("获取聊天详情异常");
        }
        return Result.success(messageList);
    }

    @ApiOperation(("/商家端拉取聊天详情"))
    @ResponseBody
    @RequestMapping("getMerChats")
    public Object getMerChats(Long uid, Long mid) {
        List<Message> merChatList = null;
        try {
            merChatList = getMerChatList(null, mid, uid);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("获取聊天详情异常");
        }
        return Result.success(merChatList);
    }

    @ApiOperation("聊天界面发送消息")
    @ResponseBody
    @RequestMapping("doSendMsg")
    public Object doSendMsg(String openId, String content, Long mId) {
        int res = 0;
        try {
            User user = userService.queryDetail(openId);
            Message message = Message
                    .builder()
                    .sendId(user.getId())
                    .sendType("USER")
                    .receiveId(mId)
                    .receiveType("MERCHANT")
                    .content(content).build();
            res = messageService.doAdd(message);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("发送消息异常");
        }
        return res > 0 ? Result.success() : Result.error();
    }

    @ApiOperation("移除聊天记录")
    @ResponseBody
    @RequestMapping("/removeMsg")
    public Object removeMsg(Long mid, String openId, Long uid) {
        int res = 0;
        try {
            Long id;
            if (openId != null) {
                User user = userService.queryDetail(openId);
                id = user.getId();
            } else {
                id = uid;
            }
            Message message = Message
                    .builder()
                    .sendId(id)
                    .sendType("USER")
                    .receiveId(mid)
                    .receiveType("MERCHANT")
                    .build();
            Message mi = Message.builder().status("D").build();
            res = messageService.doRemove(mi, message);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("移除聊天记录异常");
        }
        return res > 0 ? Result.success() : Result.error();
    }

    /**
     * ---------------------------------------------商户端操作------------------------------------------------------
     */

    @ApiOperation("跳转客服中心")
    @RequestMapping("/toKefuCenter")
    public String toKefuCenter(HttpSession session, Model model) {
        Merchant merchant = (Merchant) session.getAttribute("LOGIN_MERCHANT");
        List<Message> messages = messageService.getKefuList(merchant.getId());
        model.addAttribute("msgList", messages);
        return "merchant/kefu";
    }

    @ApiOperation("打开聊天界面")
    @RequestMapping("/showChat")
    public String showChat(Long uid, HttpSession session, Model model) {
        try {
            List<Message> messages = getMerChatList(session, null, uid);
            model.addAttribute("chatList", messages);
            model.addAttribute("uid", uid);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("获取聊天详情异常");
        }
        return "merchant/chat";
    }

    @ApiOperation("发送回复消息")
    @ResponseBody
    @RequestMapping("/doSendReply")
    public Object doSendReply(HttpSession session, Message message, Long mid) {
        int res = 0;
        try {
            Long id;
            Merchant merchant = (Merchant) session.getAttribute("LOGIN_MERCHANT");
            if (Objects.isNull(merchant)) {
                id = mid;
            } else {
                id = merchant.getId();
            }
            message.setSendId(id);
            message.setSendType(LevelEnum.MERCHANT.getValue());
            message.setReceiveType(LevelEnum.USER.getValue());
            res = messageService.doAdd(message);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("发送回复消息异常");
        }
        return res > 0 ? Result.success(message) : Result.error();
    }

    @ApiOperation("获取未读消息")
    @ResponseBody
    @RequestMapping("/home/getUnreadMsg")
    public Object getUnreadMsg(HttpSession session) {
        try {
            Merchant merchant = (Merchant) session.getAttribute("LOGIN_MERCHANT");
            List<Message> messages = messageService.queryList(Message.builder()
                    .receiveId(merchant.getId())
                    .receiveType("MERCHANT")
                    .sendType("USER")
                    .readStatus("WR").build());
            return Result.success(messages.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 商家端拉取聊天列表
     */
    public List<Message> getMerChatList(HttpSession session, Long mid, Long uid) {
        Merchant merchant;
        if (session == null) {
            merchant = merchantService.queryDetail(mid);
        } else {
            merchant = (Merchant) session.getAttribute("LOGIN_MERCHANT");
        }
        AtomicReference atomicReference = new AtomicReference(merchant);
        Merchant m = (Merchant) atomicReference.get();
        List<Message> messages = messageService.queryChatList(uid, m.getId());
        messages.stream().forEach(message1 -> {
            if (message1.getSendId() == m.getId()) {
                message1.setIsSelf("true");
                message1.setSendName(m.getMname());
                message1.setAvatarUrl(m.getAvatarUrl());
            } else {
                message1.setIsSelf("false");
                User user = userService.queryDetail(message1.getSendId());
                message1.setSendName(user.getUname());
                message1.setAvatarUrl(user.getAvatarUrl());
                messageService.doEdit(Message.builder().
                        id(message1.getId())
                        .readStatus(StatusEnum.YR.getValue()).build());
            }
        });
        return messages;
    }

}