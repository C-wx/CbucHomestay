package cbuc.homestay.controller.foreCenter;

import cbuc.homestay.base.Result;
import cbuc.homestay.bean.Apply;
import cbuc.homestay.bean.AuditLog;
import cbuc.homestay.bean.Merchant;
import cbuc.homestay.service.ApplyService;
import cbuc.homestay.service.AuditLogService;
import cbuc.homestay.service.MerchantService;
import cbuc.homestay.utils.QiniuCloudUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * @Explain: 小程序端商家入驻控制器
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/2/4
 */
@Slf4j
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
                          Apply apply) {
        if (file.isEmpty()) {
            return Result.error("文件不能为空");
        }
        try {
            byte[] bytes = file.getBytes();
            String imageName = UUID.randomUUID().toString();
            try {
                String license = QiniuCloudUtil.put64image(bytes, imageName);
                apply.setMlicense(license);
                log.info("上传地址为----：" + license);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            return Result.error("上传图片异常");
        }
        Apply einfo = Apply.builder().openId(apply.getOpenId()).status("D").build();
        applyService.doDel(einfo);
        int res = applyService.doAdd(apply);
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
