package cbuc.homestay.utils;

import com.qiniu.storage.BucketManager;
import com.qiniu.util.Auth;
import com.qiniu.util.Base64;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * 七牛云上传文件工具类
 */
public class QiniuCloudUtil {

    // 设置需要操作的账号的AK和SK
    private static final String ACCESS_KEY = "kpg-SH4Ry9YBc2lCM4hPBM7tsRkmzPfUBD1pS46u";
    private static final String SECRET_KEY = "7mRCHpSUqKXNscVLPGT4fynsZKCTaWcSZOf_Y0Tc";

    // 要上传的空间
    private static final String bucketname = "cbuc";

    // 密钥
    private static final Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    private static final String DOMAIN = "https://cbucbm.club/";

    private static final String style = "cbucImageStylei";

    public static String getUpToken() {
        return auth.uploadToken(bucketname, null, 3600, new StringMap().put("insertOnly", 1));
    }

    //base64方式上传
    public static String put64image(byte[] base64, String key) throws Exception {
        String file64 = Base64.encodeToString(base64, 0);
        Integer len = base64.length;

        //华北空间使用 upload-z1.qiniu.com，华南空间使用 upload-z2.qiniu.com，北美空间使用 upload-na0.qiniu.com
        String url = "http://upload-z2.qiniu.com/putb64/" + len + "/key/" + UrlSafeBase64.encodeToString(key);

        RequestBody rb = RequestBody.create(null, file64);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/octet-stream")
                .addHeader("Authorization", "UpToken " + getUpToken())
                .post(rb).build();
        OkHttpClient client = new OkHttpClient();
        okhttp3.Response response = client.newCall(request).execute();
        System.out.println(response);
        return DOMAIN + key;        //返回图片地址   https://cbucbm.club/812bbd78-62d3-44bc-836c-9ee27ba4866a
    }

    /**
     * @param key 图片的文件名
     * @Explain 删除空间中的图片
     */
    public static void delete(String key) {
        BucketManager bucketManager = new BucketManager(auth);
        try {
            bucketManager.delete(bucketname, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class Ret {
        public long fsize;
        public String key;
        public String hash;
        public int width;
        public int height;
    }
}
