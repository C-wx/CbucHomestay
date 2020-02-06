package cbuc.homestay.controller.foreCenter;

import cbuc.homestay.base.Result;
import cbuc.homestay.bean.User;
import cbuc.homestay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/**
 * @Explain: 小程序端用户控制器
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/2/5
 */
@Controller
public class ForeUserController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("/doAddUser")
    public Object doAddUser(User user) {
        User ui = userService.queryDetail(user.getOpenId());
        int res;
        if (Objects.isNull(ui)) {
            res = userService.doAdd(user);
        }else {
            res = 1;
        }
        return res > 0 ? Result.success() : Result.error();
    }
}
