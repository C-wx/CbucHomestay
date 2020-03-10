package cbuc.homestay.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long id;

    private String openId;

    private String uname;

    private String uphone;

    private String avatarUrl;

    private Date createTime;

    private Date updateTime;

    private String status;

    private String js_code;

}