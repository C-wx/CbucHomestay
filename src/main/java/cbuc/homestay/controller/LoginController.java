package cbuc.homestay.controller;

import cbuc.homestay.base.Result;
import cbuc.homestay.bean.Merchant;
import cbuc.homestay.evt.LoginUser;
import cbuc.homestay.service.MerchantService;
import cbuc.homestay.utils.CacheUtil;
import com.google.code.kaptcha.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @Explain:
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/1/6
 */
@Slf4j
@Controller
@Api(value = "登录操作控制器",description = "处理登录相关操作")
public class LoginController {

    @Autowired
    private CacheUtil cacheUtil;

    @Autowired
    private MerchantService merchantService;

    @ResponseBody
    @PostMapping("/doLogin")
    public Object doLogin(LoginUser loginUser, HttpSession session) {
        try {
            //获取登录失败次数
            Integer error_count = cacheUtil.get("login_error_count");
            Merchant merchant = merchantService.queryDetail(loginUser);
            if (error_count !=null && error_count > 3) {
                return Result.error(500,"您输入密码已经错误超过3次，请1分钟后尝试!");
            }else if (StringUtils.isBlank((String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY))) {
                return Result.error(510,"验证码已过期,请重新输入验证码!");
            }else if (StringUtils.isBlank(loginUser.getVerifyCode())
                    ||StringUtils.isBlank(loginUser.getMaccount())
                    ||StringUtils.isBlank(loginUser.getMpwd())) {
                return Result.error(511,"请输入必填字段！");
            }else if (!session.getAttribute(Constants.KAPTCHA_SESSION_KEY).equals(loginUser.getVerifyCode())) {
                return Result.error(512,"验证码不正确,请重新输入");
            }else if (Objects.isNull(merchant)) {
                error_count = null == error_count ? 1 : error_count + 1;
                cacheUtil.set("login_error_count", error_count, 60);
                return Result.error(513,"用户名或密码错误");
            }else {
                session.removeAttribute(Constants.KAPTCHA_SESSION_KEY);
                session.setAttribute("LOGIN_MERCHANT",merchant);
                session.setMaxInactiveInterval(30 * 60);
                return Result.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("登录功能异常！");
            return Result.error("登录功能异常！");
        }
    }

    @ApiOperation("退出登录")
    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request) {
        request.getSession().removeAttribute("LOGIN_MERCHANT");
        request.getSession().invalidate();
        return new ModelAndView(new RedirectView("/toLogin"));
    }
}
