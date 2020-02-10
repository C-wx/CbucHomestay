package cbuc.homestay.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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

    private String dayCount;

    private Float price;

    private String comment;

    private Date createTime;

    private Date updateTime;

    private Date payTime;

    private Date confirmTime;

    private Date beginTime;

    private Date endTime;

    private String status;

    /**----------非表字段---------*/
    private String rName;
    private RoomInfo roomInfo;
    /**--------------------------*/

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}