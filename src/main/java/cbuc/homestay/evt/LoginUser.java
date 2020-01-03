package cbuc.homestay.evt;

import lombok.Data;

/**
 * @Explain:
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/1/2
 */
@Data
public class LoginUser {
    private String maccount;
    private String mpwd;
    private String verifyCode;
}
