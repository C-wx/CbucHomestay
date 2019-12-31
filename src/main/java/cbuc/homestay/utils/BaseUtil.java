package cbuc.homestay.utils;

import cbuc.blog.evt.Base64MultipartFile;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.IOException;

/**
 * @Explain:   基本工具类
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2019/11/13
 */
public class BaseUtil {
    /**
     * base64转multipart file
     *
     * @param base64
     * @return
     */
    public static MultipartFile base64ToMultipartFile(String base64) {
        try {
            String[] baseStrs = base64.split(",");
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b;
            b = decoder.decodeBuffer(baseStrs[1]);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            return new Base64MultipartFile(b, baseStrs[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
