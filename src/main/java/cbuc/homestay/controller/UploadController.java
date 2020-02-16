package cbuc.homestay.controller;

import cbuc.homestay.base.Result;
import cbuc.homestay.evt.NkUploader;
import cbuc.homestay.utils.QiniuCloudUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @Explain: 上传工具控制器
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/1/13
 */
@Slf4j
@RestController
public class UploadController {

    private final HttpServletRequest request;

    public UploadController(HttpServletRequest request) {
        this.request = request;
    }

    @RequestMapping("/uploadImg")
    public Object uploadImg(@RequestParam(value = "file", required = false) MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error("文件不能为空");
        }
        try {
            byte[] bytes = file.getBytes();
            String imageName = UUID.randomUUID().toString();
            //使用base64方式上传到七牛云
            String url = QiniuCloudUtil.put64image(bytes, imageName);
            log.info("上传地址为----：" + url);
            return new NkUploader().ok(url);
        } catch (Exception e) {
            return Result.error("上传图片异常");
        }
    }

}
