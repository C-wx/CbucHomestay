package cbuc.homestay.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class News {
    private Long id;

    private Long publishId;

    private String title;

    private String summary;

    private Date createTime;

    private Date updateTime;

    private Date beginTime;

    private Date endTime;

    private String auditStatus;

    private String status;

    private String content;

    /**--------非表字段-----------*/
    private String publishName;
    private boolean valid;
    /**--------------------------*/

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}