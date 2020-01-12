package cbuc.homestay.controller;

import cbuc.homestay.base.Result;
import cbuc.homestay.bean.Merchant;
import cbuc.homestay.bean.Message;
import cbuc.homestay.service.MerchantService;
import cbuc.homestay.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Explain: 共用接口控制器
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/1/12
 */
@Slf4j
@Api(value = "共用接口控制器", description = "处理共用功能接口")
@Controller
public class BaseController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private MerchantService merchantService;

    @ApiOperation("弹出消息发送模态框")
    @GetMapping("/sendMsg")
    public String toSendMsg(Message message, String mName, Model model, HttpSession session) {
        Merchant merchant = (Merchant) session.getAttribute("LOGIN_MERCHANT");
        model.addAttribute("sendId", merchant.getId());
        model.addAttribute("receiveId", message.getReceiveId());
        model.addAttribute("sendType", message.getSendType());
        model.addAttribute("receiveType", message.getReceiveType());
        model.addAttribute("mName", mName);
        return "sendMsg";
    }

    @ApiOperation("消息发送操作")
    @ResponseBody
    @PutMapping("/sendMsg")
    public Object doSendMsg(Message message) {
        try {
            int res = messageService.doAdd(message);
            return res > 0 ? Result.success() : Result.error("发送消息失败");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("发送消息异常");
            return Result.error("发送功能异常");
        }
    }

    @ApiOperation("弹出消息历史模态框")
    @GetMapping("/toMsgHistory")
    public String toMsgHistory(Message message, Model model, HttpSession session) {
        Merchant merchant = (Merchant) session.getAttribute("LOGIN_MERCHANT");
        message.setSendId(merchant.getId());
        List<Message> messageList = messageService.queryList(message);
        model.addAttribute("messageList",messageList);
        return "msgHistory";
    }

}
