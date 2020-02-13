package cbuc.homestay.controller.dataCenter;

import cbuc.homestay.base.Result;
import cbuc.homestay.bean.Merchant;
import cbuc.homestay.service.BaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @Explain: 商家端数据中心控制器
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/2/12
 */
@Controller
@RequestMapping("/merchant")
@Api(value = "商家端数据中心控制器", description = "处理商户相关数据业务")
public class MerchantDataController {

    @Autowired
    private BaseService baseService;

    @ApiOperation("跳转数据中心界面")
    @RequestMapping("/dataCenter")
    public String dataCenter(Model model, HttpSession session) {
        Merchant merchant = (Merchant) session.getAttribute("LOGIN_MERCHANT");
        Map<String, Object> dataMap = baseService.getTotalData(merchant.getId());
        Map<String, Object> lastMap = baseService.getLastData(merchant.getId(), "MERCHANT");
        model.addAttribute("dataMap", dataMap);
        model.addAttribute("lastMap", lastMap);
        return "merchant/dataCenter";
    }

    @ApiOperation("获取销售报表数据")
    @ResponseBody
    @RequestMapping("/getSalesData")
    public Object getBlogData(String beginTime, HttpSession session) {
        Merchant merchant = (Merchant) session.getAttribute("LOGIN_MERCHANT");
        List<Map<String, Object>> data = baseService.querySalesData(merchant.getId(), beginTime);
        return Result.success(data);
    }

}
