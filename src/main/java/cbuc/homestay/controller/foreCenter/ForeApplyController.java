package cbuc.homestay.controller.foreCenter;

import cbuc.homestay.base.Result;
import cbuc.homestay.bean.Apply;
import cbuc.homestay.service.ApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Explain: 小程序端商家入驻控制器
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/2/4
 */
@Controller
public class ForeApplyController {

    @Autowired
    private ApplyService applyService;

    @ResponseBody
    @RequestMapping("/doApply")
    public Object doApply(Apply apply) {
        int res = applyService.doAdd(apply);
        return res > 0 ? Result.success() : Result.error();
    }

    @ResponseBody
    @RequestMapping("/getApplyDetail")
    public Object getApplyDetail(String openId) {
        Apply apply = applyService.getApplyDetail(openId);
        return Result.success(apply);
    }

}
