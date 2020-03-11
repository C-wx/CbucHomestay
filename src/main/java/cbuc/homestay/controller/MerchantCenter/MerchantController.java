package cbuc.homestay.controller.MerchantCenter;

import cbuc.homestay.base.Result;
import cbuc.homestay.bean.Apply;
import cbuc.homestay.bean.Merchant;
import cbuc.homestay.service.ApplyService;
import cbuc.homestay.service.MerchantService;
import cbuc.homestay.utils.QiniuCloudUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

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

    @ApiOperation("跳转商户信息编辑页")
    @RequestMapping("/toMerchantInfo")
    public String toMerchantInfo() {
        return "merchant/merchantInfo";
    }

    @ApiOperation("修改商户信息")
    @ResponseBody
    @RequestMapping("/doEditInfo")
    public Object doEditInfo(@RequestParam(value = "file", required = false) MultipartFile file, Merchant merchant, HttpSession session) {
        if (file != null) {
            if (StringUtils.isNotBlank(file.getOriginalFilename())) {
                String avatarUrl = QiniuCloudUtil.uploadFile(file);
                merchant.setAvatarUrl(avatarUrl);
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

