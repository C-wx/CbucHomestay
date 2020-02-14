package cbuc.homestay.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
    private String publishName;             //评论的发布人名称
    private List images;                    //当前房间的标题图
    private String isFavorite;              //当前房间是否被收藏
    private List<Comment> commentList;      //当前房间下的评论列表
    private Merchant merchant;              //当前房间的房东
    private List<Comment> allComment;       //当前房间的房东的所有评论
    private Map<String, Object> propertyInfo;
    private List<Property> propertyList;
    /**-------------------------*/
}