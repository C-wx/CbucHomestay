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
public class Log {
    private Long id;

    private String accessUname;

    private String accessIp;

    private String accessCode;

    private String accessCname;

    private Date createTime;

    private String status;
}