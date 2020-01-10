package cbuc.homestay.evt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Explain:
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/1/2
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEvt {
    private Long id;
    private String maccount;
    private String mpwd;
    private String npwd;
    private String verifyCode;
    private String mphone;
    private String msgCode;
}
