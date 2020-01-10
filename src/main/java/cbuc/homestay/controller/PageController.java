package cbuc.homestay.controller;

import cbuc.homestay.bean.Merchant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @ApiOperation("跳转到登录页")
    @GetMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @ApiOperation("跳转到管理主页")
    @GetMapping("/admin/")
    public String toIndex(HttpSession session, Model model) {
        Merchant login_merchant = (Merchant) session.getAttribute("LOGIN_MERCHANT");
        model.addAttribute("LOGIN_MERCHANT",login_merchant);
        return "home";
    }

    @ApiOperation("跳转到修改密码页面")
    @RequestMapping("/toModPwd")
    public String toModPwd() {
        return "modPwd";
    }
}