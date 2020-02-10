package cbuc.homestay.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    private Date beginTime;

    private Date endTime;

    private Date createTime;

    private Date updateTime;

    private String auditStatus;

    private String status;

    /**---------非表字段---------*/
    private String publishName;
    private List images;
    private String isFavorite;
    private List<Comment> commentList;
    private Merchant merchant;
    /**-------------------------*/
}