package cbuc.homestay.controller.Admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Explain:
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/1/2
 */
@Slf4j
@Api(value = "管理员操作控制器",description = "处理管理员相关操作")
@Controller
@RequestMapping("/admin")
public class OperateController {

    @ApiOperation("跳转数据统计页面")
    @GetMapping("/dataStatistic")
    public String dataStatisticList() {
        return "";
    }
}
