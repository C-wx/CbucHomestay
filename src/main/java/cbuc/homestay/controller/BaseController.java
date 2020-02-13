package cbuc.homestay.controller;

import cbuc.homestay.CommonEnum.LevelEnum;
import cbuc.homestay.base.Result;
import cbuc.homestay.bean.Merchant;
import cbuc.homestay.bean.Message;
import cbuc.homestay.service.MerchantService;
import cbuc.homestay.service.MessageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        message.setReceiveId(message.getReceiveId());
        message.setReceiveType("MERCHANT");
        List<Message> messageList = messageService.queryList(message);
        model.addAttribute("messageList",messageList);
        return "msgHistory";
    }

    @ApiOperation("跳转到消息中心页面")
    @GetMapping("/toMessageCenter")
    public String toMessageCenter() {
        return "msgCenter";
    }

    @ApiOperation("获取消息列表")
    @ResponseBody
    @GetMapping("/msgCenterPage")
    public Object msgCenterPage(@RequestParam(value = "current", defaultValue = "1") Integer pn,
                                    @RequestParam(value = "size", defaultValue = "10") Integer size,
                                    @RequestParam(value = "sort", defaultValue = "id") String sort,
                                    @RequestParam(value = "order", defaultValue = "desc") String order,
                                    HttpSession session,
                                    String content) {
        try {
            Merchant merchant = (Merchant) session.getAttribute("LOGIN_MERCHANT");
            String receiveType = merchant.getMlevel().equals(LevelEnum.ADMIN.getValue())?LevelEnum.ADMIN.getValue():"MERCHANT";
            Message message = Message.builder().receiveId(merchant.getId()).receiveType(receiveType).ifMerchant(true).build();
            if (StringUtils.isNotBlank(content)) {
                message.setContent(content);
            }
            PageHelper.startPage(pn, size, sort + " " + order);
            List<Message> messageList = messageService.queryList(message);
            messageList.stream().forEach(ml->{
                Merchant mc = merchantService.queryDetail(ml.getSendId());
                ml.setSendName(mc.getMname());
            });
            PageInfo pageInfo = new PageInfo(messageList, 10);
            return Result.layuiTable(pageInfo.getTotal(), pageInfo.getList());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询结果异常");
            return Result.error("查询结果异常");
        }
    }

    @ResponseBody
    @RequestMapping("/checkReadStatus")
    public Object checkReadStatus(Message message) {
        try {
            int res = messageService.doEdit(message);
            if (res > 0) {
                Message messagei = messageService.queryDetail(message.getId());
                messagei.setReadStatus("WR");
                messagei.setIfMerchant(true);
                int msgNum = messageService.queryList(messagei).size();
                return Result.success(msgNum);
            }else {
                return Result.error("更新状态失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("更新状态异常");
            return Result.error("更新状态异常");
        }
    }
}
