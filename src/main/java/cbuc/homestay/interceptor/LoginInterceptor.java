package cbuc.homestay.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @Explain:    登录拦截器(判断是否为登录状态，否则强制退回登录页面)
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/1/6
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object login_merchant = request.getSession().getAttribute("LOGIN_MERCHANT");
        if (Objects.isNull(login_merchant)) {
            log.warn("用户未登录,无法访问后台");
            response.sendRedirect("/toLogin");
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
