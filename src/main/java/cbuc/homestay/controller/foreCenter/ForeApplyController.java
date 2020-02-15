package cbuc.homestay.controller.foreCenter;

import cbuc.homestay.base.Result;
import cbuc.homestay.bean.Apply;
import cbuc.homestay.bean.AuditLog;
import cbuc.homestay.bean.Merchant;
import cbuc.homestay.service.ApplyService;
import cbuc.homestay.service.AuditLogService;
import cbuc.homestay.service.MerchantService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

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

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private AuditLogService auditLogService;

    @ResponseBody
    @RequestMapping("/doApply")
    public Object doApply(@RequestParam(value = "file", required = false) MultipartFile file,
                          @RequestParam("apply") String apply) {
        Apply applyInf = JSON.parseObject(apply, Apply.class);
        Apply einfo = Apply.builder().openId(applyInf.getOpenId()).status("D").build();
        applyService.doDel(einfo);
        int res = applyService.doAdd(applyInf);
        return res > 0 ? Result.success() : Result.error();
    }

    @ResponseBody
    @RequestMapping("/getApplyDetail")
    public Object getApplyDetail(String openId) {
        Apply apply = applyService.getApplyDetail(openId);
        if (!Objects.isNull(apply)) {
            if ("SA".equals(apply.getAuditStatus())) {
                Merchant merchant = merchantService.getMerchant(apply.getId());
                apply.setMerchant(merchant);
            } else {
                AuditLog auditLog = auditLogService.queryDetail(apply.getId(), "MERCHANT");
                apply.setAuditLog(auditLog);
            }
        }
        return Result.success(apply);
    }

}
