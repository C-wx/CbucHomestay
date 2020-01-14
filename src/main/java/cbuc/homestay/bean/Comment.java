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
public class Comment {
    private Long id;

    private Long rid;

    private Long commentor;

    private String type;

    private String content;

    private Integer likeCount;

    private Integer commentCount;

    private Date createTime;

    private String status;

    /**--------非表字段-----------*/
    private String publishName;
    private String origin;
    /**--------------------------*/

}