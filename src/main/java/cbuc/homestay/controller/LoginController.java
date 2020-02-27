package cbuc.homestay.controller;

import cbuc.homestay.base.Result;
import cbuc.homestay.bean.Apply;
import cbuc.homestay.bean.Merchant;
import cbuc.homestay.evt.UserEvt;
import cbuc.homestay.service.ApplyService;
import cbuc.homestay.service.MerchantService;
import cbuc.homestay.utils.CacheUtil;
import cbuc.homestay.utils.SendMessageUtil;
import com.google.code.kaptcha.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@Api(value = "登录操作控制器", description = "处理登录相关操作")
public class LoginController {

    @Autowired
    private CacheUtil cacheUtil;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private ApplyService applyService;

    @Value("${sendMsg.key}")
    private String sendMsg;

    @Value("${sendMsg.uid}")
    private String uid;

    @ApiOperation("登录操作")
    @ResponseBody
    @RequestMapping("/doLogin")
    public Object doLogin(UserEvt userEvt, HttpSession session) {
        try {
            if ("wxapp".equals(userEvt.getLoginType())) {           // loginType: 登录类型   wxapp为通过小程序登录
                Merchant merchant = merchantService.queryDetail(userEvt);
                Apply apply = applyService.queryDetail(merchant.getAuditId());
                merchant.setApply(apply);
                if (Objects.isNull(merchant)) {
                    return Result.error(513, "用户名或密码错误");
                } else {
                    session.setAttribute("LOGIN_MERCHANT", merchant);
                    session.setMaxInactiveInterval(30 * 60);
                    return Result.success(merchant);
                }
            } else {                                                  //网页端登录
                //获取登录失败次数
                Integer error_count = cacheUtil.get("login_error_count");
                Merchant merchant = merchantService.queryDetail(userEvt);
                if (error_count != null && error_count > 3) {
                    return Result.error(500, "您输入密码已经错误超过3次，请1分钟后尝试!");
                } else if (StringUtils.isBlank((String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY))) {
                    return Result.error(510, "验证码已过期,请重新输入验证码!");
                } else if (StringUtils.isBlank(userEvt.getVerifyCode())
                        || StringUtils.isBlank(userEvt.getMaccount())
                        || StringUtils.isBlank(userEvt.getMpwd())) {
                    return Result.error(511, "请输入必填字段！");
                } else if (!session.getAttribute(Constants.KAPTCHA_SESSION_KEY).equals(userEvt.getVerifyCode())) {
                    return Result.error(512, "验证码不正确,请重新输入");
                } else if (Objects.isNull(merchant)) {
                    error_count = null == error_count ? 1 : error_count + 1;
                    cacheUtil.set("login_error_count", error_count, 60);
                    return Result.error(513, "用户名或密码错误");
                } else {
                    session.removeAttribute(Constants.KAPTCHA_SESSION_KEY);
                    session.setAttribute("LOGIN_MERCHANT", merchant);
                    session.setMaxInactiveInterval(30 * 60);
                    return Result.success(merchant);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("登录功能异常！");
            return Result.error("登录功能异常！");
        }
    }

    @ApiOperation("获取短信验证码")
    @ResponseBody
    @RequestMapping("/getMsgCode")
    public Object getMsgCode(String smsMob, HttpSession session) {
        try {
            UserEvt userEvt = new UserEvt();
            userEvt.setMphone(smsMob);
            Merchant merchant = merchantService.queryDetail(userEvt);
            if (Objects.isNull(merchant)) {
                return Result.error("该号码不存在,请在小程序端申请入驻");
            }
            String randomCode = SendMessageUtil.getRandomCode(6);
            session.setAttribute("MESSAGE_CODE", randomCode);
            session.setMaxInactiveInterval(1000 * 60);
//            Integer resultCode = SendMessageUtil.send(uid, sendMsg, smsMob, "您的短信验证码为:" + randomCode);    //TODO 实际启用短信
            Integer resultCode = 1;
            log.info(SendMessageUtil.getMessage(resultCode));
            if (resultCode > 0) {
                return Result.success(randomCode);
            } else {
                return Result.error("发送短信验证码失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("发送短信验证码异常！");
            return Result.error("发送短信验证码异常！");
        }
    }

    @ApiOperation("忘记密码操作")
    @ResponseBody
    @RequestMapping("/doForget")
    public Object doForget(UserEvt userEvt, HttpSession session) {
        try {
            Merchant merchant = merchantService.queryDetail(userEvt);
            String messageCode = (String) session.getAttribute("MESSAGE_CODE");
            if (StringUtils.isBlank(messageCode)) {
                return Result.error(522, "短信验证码已失效,请重新获取");
            } else if (!userEvt.getMsgCode().equals(messageCode)) {
                return Result.error(523, "短信验证码不正确,请重新获取");
            } else if (Objects.isNull(merchant)) {
                return Result.error(524, "该用户不存在,请申请入驻后登录");
            }
            userEvt.setId(merchant.getId());
            int res = merchantService.doEdit(userEvt);
            if (res > 0) {
                return Result.success();
            } else {
                return Result.error("修改密码失败!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("修改密码异常!");
            return Result.error("修改密码异常!");
        }
    }

    @ApiOperation("退出登录")
    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request) {
        request.getSession().removeAttribute("LOGIN_MERCHANT");
        request.getSession().invalidate();
        return new ModelAndView(new RedirectView("/toLogin"));
    }

    @ApiOperation("修改密码操作")
    @ResponseBody
    @RequestMapping("/doModPwd")
    public Object doModPwd(UserEvt userEvt, HttpSession session) {
        try {
            Merchant merchant = (Merchant) session.getAttribute("LOGIN_MERCHANT");
            if (!userEvt.getMpwd().equals(merchant.getMpwd())) {
                return Result.error("旧密码不正确,请重新输入");
            }
            userEvt.setId(merchant.getId());
            int res = merchantService.doEdit(userEvt);
            if (res > 0) {
                session.removeAttribute("LOGIN_MERCHANT");
                return Result.success();
            } else {
                return Result.error("修改密码失败!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("修改密码异常");
            return Result.error("修改密码异常");
        }
    }
}
