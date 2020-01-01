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
public class RoomInfo {
    private Long id;

    private Long mid;

    private String title;

    private String des;

    private Float price;

    private String isActive;

    private Integer sales;

    private String type;

    private Integer likeCount;

    private Integer commentCount;

    private Date createTime;

    private Date updateTime;

    private String status;
}