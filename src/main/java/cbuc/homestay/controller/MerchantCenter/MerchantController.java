package cbuc.homestay.controller.MerchantCenter;

import cbuc.homestay.base.Result;
import cbuc.homestay.bean.Apply;
import cbuc.homestay.bean.Merchant;
import cbuc.homestay.service.ApplyService;
import cbuc.homestay.service.MerchantService;
import cbuc.homestay.utils.QiniuCloudUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

/**
 * @Explain:
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/1/11
 */
@Slf4j
@Api(value = "商户控制器", description = "商户处理相关操作")
@Controller
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private ApplyService applyService;

    @RequestMapping("/toMerchantInfo")
    public String toMerchantInfo() {
        return "merchant/merchantInfo";
    }

    @ResponseBody
    @RequestMapping("/doEditInfo")
    public Object doEditInfo(@RequestParam(value = "file", required = false) MultipartFile file, Merchant merchant, HttpSession session) {
        if (file != null) {
            if (StringUtils.isNotBlank(file.getOriginalFilename())) {
                try {
                    byte[] bytes = file.getBytes();
                    String imageName = UUID.randomUUID().toString();
                    try {
                        String avatarUrl = QiniuCloudUtil.put64image(bytes, imageName);
                        merchant.setAvatarUrl(avatarUrl);
                        log.info("上传地址为----：" + avatarUrl);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    return Result.error("上传图片异常");
                }
            }
        }
        int res = merchantService.doEdit(merchant);
        Merchant mi = merchantService.queryDetail(merchant.getId());
        session.setAttribute("LOGIN_MERCHANT", mi);
        Apply apply = applyService.queryDetail(mi.getAuditId());
        mi.setApply(apply);
        return res > 0 ? Result.success(mi) : Result.error();
    }

}

