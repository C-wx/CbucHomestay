package cbuc.homestay.controller.foreCenter;

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

    @ApiOperation("获取消息列表")
    @ResponseBody
    @RequestMapping("getMsgList")
    public Object getMsgList(String openId) {
        List<Message> messages = null;
        try {
            User user = userService.queryDetail(openId);
            Message message = new Message();
            message.setSendId(user.getId());
            message.setSendType("USER");
            messages = messageService.getList(message);
            messages.stream().forEach(msg -> {
                Merchant merchant = merchantService.queryDetail(msg.getReceiveId());
                msg.setReceiveName(merchant.getMname());
                msg.setAvatarUrl(merchant.getAvatarUrl());
            });
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
                    ml.setAvatarUrl(merchant.getAvatarUrl());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            log.info("获取聊天详情异常");
        }
        return Result.success(messageList);
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
    public Object removeMsg(Long mid, String openId) {
        int res = 0;
        try {
            User user = userService.queryDetail(openId);
            Message message = Message
                    .builder()
                    .sendId(user.getId())
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
        messages.stream().forEach(message -> {
            User user = userService.queryDetail(message.getSendId());
            message.setSendName(user.getUname());
        });
        model.addAttribute("msgList", messages);
        return "merchant/kefu";
    }

    @ApiOperation("打开聊天界面")
    @RequestMapping("/showChat")
    public String showChat(Long uid, HttpSession session, Model model) {
        try {
            Merchant merchant = (Merchant) session.getAttribute("LOGIN_MERCHANT");
            List<Message> messages = messageService.queryChatList(uid, merchant.getId());
            messages.stream().forEach(message1 -> {
                if (message1.getSendId() == merchant.getId()) {
                    message1.setIsSelf("true");
                    message1.setSendName(merchant.getMname());
                } else {
                    message1.setIsSelf("false");
                    User user = userService.queryDetail(message1.getSendId());
                    message1.setSendName(user.getUname());
                }
            });
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
    public Object doSendReply(HttpSession session, Message message) {
        int res = 0;
        try {
            Merchant merchant = (Merchant) session.getAttribute("LOGIN_MERCHANT");
            message.setSendId(merchant.getId());
            message.setSendType("MERCHANT");
            message.setReceiveType("USER");
            res = messageService.doAdd(message);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("发送回复消息异常");
        }
        return res > 0 ? Result.success(message) : Result.error();
    }

}