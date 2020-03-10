package cbuc.homestay.controller.foreCenter;

import cbuc.homestay.base.Result;
import cbuc.homestay.bean.Apply;
import cbuc.homestay.bean.AuditLog;
import cbuc.homestay.bean.Merchant;
import cbuc.homestay.service.ApplyService;
import cbuc.homestay.service.AuditLogService;
import cbuc.homestay.service.MerchantService;
import cbuc.homestay.utils.QiniuCloudUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Controller
@Api(value = "小程序端商家入驻控制器", description = "处理商家入驻和获取入驻等信息")
public class ForeApplyController {

    @Autowired
    private ApplyService applyService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private AuditLogService auditLogService;

    @ApiOperation("申请商家入驻")
    @ResponseBody
    @RequestMapping("/doApply")
    public Object doApply(@RequestParam(value = "file", required = false) MultipartFile file,
                          Apply apply) {
        int res = 0;
        try {
            if (file.isEmpty()) {
                return Result.error("营业执照不能为空");
            }
            String license = QiniuCloudUtil.uploadFile(file);
            apply.setMlicense(license);
            Apply einfo = Apply.builder().openId(apply.getOpenId()).status("D").build();
            applyService.doDel(einfo);
            res = applyService.doAdd(apply);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("申请入驻异常");
        }
        return res > 0 ? Result.success() : Result.error();
    }

    @ApiOperation("获取申请入驻信息")
    @ResponseBody
    @RequestMapping("/getApplyDetail")
    public Object getApplyDetail(String openId) {
        Apply apply = null;
        try {
            apply = applyService.getApplyDetail(openId);
            if (!Objects.isNull(apply)) {
                if ("SA".equals(apply.getAuditStatus())) {
                    Merchant merchant = merchantService.getMerchant(apply.getId());
                    apply.setMerchant(merchant);
                } else {
                    AuditLog auditLog = auditLogService.queryDetail(apply.getId(), "MERCHANT");
                    apply.setAuditLog(auditLog);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("查看入驻信息异常");
        }
        return Result.success(apply);
    }

}
