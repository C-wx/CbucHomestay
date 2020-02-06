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
public class Order {
    private Long id;

    private Long rid;

    private String openId;

    private String name;

    private String cardno;

    private String phone;

    private String orderCode;

    private Float price;

    private String comment;

    private Date createTime;

    private Date updateTime;

    private Date payTime;

    private Date confirmTime;

    private String status;

    /**----------非表字段---------*/
    private String rName;
    /**--------------------------*/
}