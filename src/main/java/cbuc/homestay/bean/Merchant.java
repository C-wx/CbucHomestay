package cbuc.homestay.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Merchant {
    private Long id;

    private Long auditId;

    private String mname;

    private String mphone;

    private String maccount;

    private String mpwd;

    private String mlevel;

    private Date createTime;

    private Date updateTime;

    private String status;
}