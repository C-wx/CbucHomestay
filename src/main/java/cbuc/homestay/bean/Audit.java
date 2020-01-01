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
public class Audit {
    private Long id;

    private Long uid;

    private String mname;

    private String mphone;

    private String mcardno;

    private String maddr;

    private String mlicense;

    private Date createTime;

    private String remark;

    private String status;

}