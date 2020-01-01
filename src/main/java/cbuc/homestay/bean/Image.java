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
public class Image {
    private Long id;

    private Long parentId;

    private String origin;

    private String url;

    private Date createTime;

    private Date updateTime;

    private String status;
}