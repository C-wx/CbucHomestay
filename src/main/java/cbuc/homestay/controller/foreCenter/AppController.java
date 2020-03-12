package cbuc.homestay.controller.foreCenter;

import cbuc.homestay.base.Result;
import cbuc.homestay.bean.User;
import cbuc.homestay.service.UserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Objects;

/**
 * @Explain: 解决小程序HTTPS问题  (登录)
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/3/9
 */
@Slf4j
@Controller
@Api(value = "小程序api接口跳转", description = "解决小程序HTTPS问题  (登录)")
public class AppController {

    @Value("${weixin.grantType}")
    private String grant_type;

    @Autowired
    private UserService userService;

    /**
     * 小程序用户登录
     **/
    @ResponseBody
    @RequestMapping("/getJscode2session")
    public Object getJscode2session(User user) {
        try {
            String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" +
                    user.getAppId() + "&secret=" + user.getSecret() + "&js_code=" + user.getJs_code() + "&grant_type=" + grant_type;
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response;
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String resultString = response.body().string();
                Object object = JSON.parseObject(resultString);
                String openId = (String) ((JSONObject) object).get("openid");
                User ui = userService.queryDetail(openId);
                int res;
                user.setOpenId(openId);
                if (Objects.isNull(ui)) {
                    res = userService.doAdd(user);
                } else {
                    res = userService.update(user);
                }
                return res > 0 ? Result.success(user) : Result.error();
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.info("获取小程序用户信息异常");
        }
        return null;
    }


}