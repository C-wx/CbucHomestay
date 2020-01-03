package cbuc.homestay.controller.Admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Explain:
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/1/2
 */
@Api(value = "页面跳转控制器", description = "页面跳转")
@Controller
public class PageController {

    @ApiOperation("跳转到登录页")
    @GetMapping("/")
    public String toLogin() {
        return "login";
    }

}