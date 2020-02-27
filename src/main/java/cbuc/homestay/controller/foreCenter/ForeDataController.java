package cbuc.homestay.controller.foreCenter;

import cbuc.homestay.service.BaseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Explain: 小程序商家版数据中心
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/2/26 0026
 */
@Controller
@Api(value = "商家版小程序数据中心控制器",description = "数据中心业务调用")
public class ForeDataController {

    @Autowired
    private BaseService baseService;

    @ResponseBody
    @RequestMapping("/getDataSet")
    public Object getDataSet(Long id){

        return null;
    }
}
