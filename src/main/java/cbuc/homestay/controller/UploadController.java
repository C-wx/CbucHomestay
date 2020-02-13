package cbuc.homestay.controller;

import cbuc.homestay.base.Result;
import cbuc.homestay.utils.QiniuCloudUtil;
import cbuc.homestay.utils.UploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;
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

    @PostMapping("/upload")
    public Object upload(@RequestParam(value = "file", required = false) MultipartFile file) {
        return Objects.requireNonNull(UploadUtil.upload(file));
    }

    @ResponseBody
    @RequestMapping(value="/uploadImg", method=RequestMethod.POST)
    public Result uploadImg(@RequestParam MultipartFile image, HttpServletRequest request) {
        Result result = new Result();

        if (image.isEmpty()) {
            result.setCode(400);
            result.setMsg("文件为空，请重新上传");
            return result;
        }

        try {
            byte[] bytes = image.getBytes();
            String imageName = UUID.randomUUID().toString();
            try {
                //使用base64方式上传到七牛云
                String url = QiniuCloudUtil.put64image(bytes, imageName);
                result.setCode(200);
                result.setMsg("文件上传成功");
                result.setData(url);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            result.setCode(500);
            result.setMsg("文件上传发生异常！");
        } finally {
            return result;
        }
    }

}
