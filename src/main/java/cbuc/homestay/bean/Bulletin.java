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
public class Bulletin {
    private Long id;

    private String content;

    private Date createTime;

    private Date updateTime;

    private Date beginTime;

    private Date endTime;

    private String status;
}