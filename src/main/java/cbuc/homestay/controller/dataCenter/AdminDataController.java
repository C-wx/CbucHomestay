package cbuc.homestay.controller.dataCenter;

import cbuc.homestay.base.Result;
import cbuc.homestay.service.BaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Explain: 管理员之数据中心控制器
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/1/14
 */
@Slf4j
@Controller
@RequestMapping("/admin")
@Api(value = "管理员之数据中心控制器", description = "管理数据统计相关内容")
public class AdminDataController {

    @Autowired
    private BaseService baseService;

    @ApiOperation("跳转数据中心界面")
    @RequestMapping("/dataCenter")
    public String dataCenter(Model model) {
        Map<String, Object> dataMap = baseService.getTotalData();
        Map<String, Object> lastMap = baseService.getLastData();
        model.addAttribute("dataMap", dataMap);
        model.addAttribute("lastMap", lastMap);
        return "admin/dataCenter";
    }

    @ApiOperation("获取销售报表数据")
    @ResponseBody
    @RequestMapping("/getSalesData")
    public Object getBlogData(String beginTime) {
        List<Map<String, Object>> data = baseService.querySalesData(beginTime);
        return Result.success(data);
    }
}
