package cbuc.homestay.controller.foreCenter;

import cbuc.homestay.base.Result;
import cbuc.homestay.bean.Apply;
import cbuc.homestay.bean.Merchant;
import cbuc.homestay.bean.User;
import cbuc.homestay.service.ApplyService;
import cbuc.homestay.service.MerchantService;
import cbuc.homestay.service.UserService;
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
 * @Explain: 小程序端用户控制器
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/2/5
 */
@Slf4j
@Controller
public class ForeUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private ApplyService applyService;

    @ResponseBody
    @RequestMapping("/doAddUser")
    public Object doAddUser(User user) {
        User ui = userService.queryDetail(user.getOpenId());
        int res;
        if (Objects.isNull(ui)) {
            res = userService.doAdd(user);
        } else {
            res = 1;
        }
        return res > 0 ? Result.success() : Result.error();
    }

    @ResponseBody
    @RequestMapping("/doEditInfo")
    public Object doEditInfo(@RequestParam(value = "file", required = false) MultipartFile file, Merchant merchant) {
        if (file != null) {
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
        int res = merchantService.doEdit(merchant);
        Merchant mi = merchantService.queryDetail(merchant.getId());
        Apply apply = applyService.queryDetail(mi.getAuditId());
        mi.setApply(apply);
        return res > 0 ? Result.success(mi) : Result.error();
    }
}
