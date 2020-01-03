package cbuc.homestay.controller.Admin;

import cbuc.homestay.base.Result;
import cbuc.homestay.evt.LoginUser;
import cbuc.homestay.service.MerchantServie;
import cbuc.homestay.utils.CacheUtil;
import com.google.code.kaptcha.Constants;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @Explain:
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/1/2
 */
@Slf4j
@Api(value = "管理端操作控制器",description = "管理端操作")
@Controller
public class OperateController {

    @Autowired
    private CacheUtil cacheUtil;

    @Autowired
    private MerchantServie merchantServie;

    @ResponseBody
    @PostMapping("/doLogin")
    public Object doLogin(LoginUser loginUser, HttpSession session) {
        try {
            //获取登录失败次数
            Integer error_count = cacheUtil.get("login_error_count");
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
            }else if (!merchantServie.verify(loginUser)) {
                error_count = null == error_count ? 1 : error_count + 1;
                cacheUtil.set("login_error_count", error_count, 60);
                return Result.error(513,"用户名或密码错误");
            }else {
                return Result.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("登录功能异常！");
            return Result.error("登录功能异常！");
        }
    }
}
