package cbuc.homestay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Explain:
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2019/12/31
 */
@Controller
public class TestController {

    @ResponseBody
    @RequestMapping("/test")
    public Object test() {
        return "test...";
    }
}
