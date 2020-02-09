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
public class Apply {
    private Long id;

    private String openId;

    private String mname;

    private String mphone;

    private String mcardno;

    private String maddr;

    private String mlicense;

    private Date createTime;

    private String remark;

    private String auditStatus;

    private String status;

    /**---------非表字段---------*/
    private String licenseUrl;
    private Merchant merchant;
    private AuditLog auditLog;
    /**-------------------------*/
}