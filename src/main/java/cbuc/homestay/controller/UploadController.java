package cbuc.homestay.controller;

import cbuc.homestay.utils.UploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

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

}
