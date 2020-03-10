package cbuc.homestay.controller.dataCenter;

import cbuc.homestay.base.Result;
import cbuc.homestay.bean.Merchant;
import cbuc.homestay.service.BaseService;
import cbuc.homestay.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Explain: 商家端数据中心控制器
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/2/12
 */
@Controller
@Api(value = "商家端数据中心控制器", description = "处理商户相关数据业务")
public class MerchantDataController {

    @Autowired
    private BaseService baseService;

    @Autowired
    private OrderService orderService;

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

    /**
     * ======================以下为小程序端接口=======================
     **/

    @ApiOperation("获取今日/昨日销售数据")
    @ResponseBody
    @RequestMapping("/getTodayAndYesterdayData")
    public Object getTodayAndYesterdayData(Long mid) {
        List<Map<String, Object>> dataMap = baseService.getTodayAndYesterdayData(mid);
        return Result.success(dataMap);
    }

    @ApiOperation("获取今日/昨日销售数据")
    @ResponseBody
    @RequestMapping("/getTotalCountAndPrice")
    public Object getTotalCountAndPrice(Long mid) {
        Map<String, Object> totalMap = baseService.getTotalCountAndPrice(mid);
        return Result.success(totalMap);
    }

    @ApiOperation("获取用户数据")
    @ResponseBody
    @RequestMapping("/getUserCount")
    public Object getUserCount(Long mid) {
        Map<String, Object> countMap = new HashMap<>();
        Integer currentAddCount = baseService.getCurrentMonthUserCount(mid);
        Integer totalCount = baseService.getTotalUserCount(mid);
        countMap.put("currentAddCount", currentAddCount);
        countMap.put("totalCount", totalCount);
        return Result.success(countMap);
    }

    @ApiOperation("获取客户列表")
    @ResponseBody
    @RequestMapping("/getCustomerList")
    public Object getCustomerList(Long mid) {
        List<Map<String, Object>> customerList = orderService.getCustomerList(mid);
        return Result.success(customerList);
    }
}
