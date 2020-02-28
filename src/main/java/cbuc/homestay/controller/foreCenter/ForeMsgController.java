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
        User user = userService.queryDetail(openId);
        Message message = new Message();
        message.setSendId(user.getId());
        message.setSendType("USER");
        List<Message> messages = messageService.getList(message);
        messages.stream().forEach(msg -> {
            Merchant merchant = merchantService.queryDetail(msg.getReceiveId());
            msg.setReceiveName(merchant.getMname());
            msg.setAvatarUrl(merchant.getAvatarUrl());
        });
        return Result.success(messages);
    }

    @ApiOperation(("/拉取聊天详情"))
    @ResponseBody
    @RequestMapping("getChatList")
    public Object getChatList(String openId, Long mId) {
        User user = userService.queryDetail(openId);
        List<Message> messageList = messageService.queryChatList(user.getId(), mId);
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
        return Result.success(messageList);
    }

    @ResponseBody
    @RequestMapping("doSendMsg")
    public Object doSendMsg(String openId, String content, Long mId) {
        User user = userService.queryDetail(openId);
        Message message = Message
                .builder()
                .sendId(user.getId())
                .sendType("USER")
                .receiveId(mId)
                .receiveType("MERCHANT")
                .content(content).build();
        int res = messageService.doAdd(message);
        return res > 0 ? Result.success() : Result.error();
    }

    @ResponseBody
    @RequestMapping("/removeMsg")
    public Object removeMsg(Long mid, String openId) {
        User user = userService.queryDetail(openId);
        Message message = Message
                .builder()
                .sendId(user.getId())
                .sendType("USER")
                .receiveId(mid)
                .receiveType("MERCHANT")
                .build();
        Message mi = Message.builder().status("D").build();
        int res = messageService.doRemove(mi, message);
        return res > 0 ? Result.success() : Result.error();
    }

    /**
     * ---------------------------------------------商户端操作------------------------------------------------------
     */

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

    @RequestMapping("/showChat")
    public String showChat(Long uid, HttpSession session, Model model) {
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
        return "merchant/chat";
    }

    @ResponseBody
    @RequestMapping("/doSendReply")
    public Object doSendReply(HttpSession session, Message message) {
        Merchant merchant = (Merchant) session.getAttribute("LOGIN_MERCHANT");
        message.setSendId(merchant.getId());
        message.setSendType("MERCHANT");
        message.setReceiveType("USER");
        int res = messageService.doAdd(message);
        return res > 0 ? Result.success(message) : Result.error();
    }

}