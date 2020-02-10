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
public class Favorite {
    private Long id;

    private Long rid;

    private String openId;

    private Date createTime;

    private Date updateTime;

    private String status;

    private RoomInfo roomInfo;

}