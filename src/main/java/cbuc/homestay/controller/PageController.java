package cbuc.homestay.controller;

import cbuc.homestay.CommonEnum.LevelEnum;
import cbuc.homestay.bean.Merchant;
import cbuc.homestay.bean.Message;
import cbuc.homestay.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @Explain:
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/1/2
 */
@Slf4j
@Controller
@Api(value = "页面跳转控制器", description = "页面跳转")
public class PageController {

    @Autowired
    private MessageService messageService;

    @ApiOperation("跳转到登录页")
    @GetMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @ApiOperation("跳转到管理主页")
    @GetMapping("/home/{mlevel}")
    public String toIndex(@PathVariable("mlevel") String mlevel, HttpSession session, Model model) {
        Merchant login_merchant = (Merchant) session.getAttribute("LOGIN_MERCHANT");
        String receiveType = login_merchant.getMlevel().equals(LevelEnum.ADMIN.getValue())?LevelEnum.ADMIN.getValue():"MERCHANT";
        Message message = Message.builder().receiveId(login_merchant.getId()).receiveType(receiveType).readStatus("WR").build();
        int msgNum = messageService.queryList(message).size();
        model.addAttribute("msgNum",msgNum);
        model.addAttribute("LOGIN_MERCHANT",login_merchant);
        model.addAttribute("MLEVEL",mlevel);
        return "home";
    }

    @ApiOperation("跳转到修改密码页面")
    @RequestMapping("/toModPwd")
    public String toModPwd() {
        return "modPwd";
    }
}