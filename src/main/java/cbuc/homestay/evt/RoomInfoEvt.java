package cbuc.homestay.evt;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * @Explain: 房源传输实体
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/2/13
 */
@Data
public class RoomInfoEvt implements Serializable {

    private Long id;

    private String title;

    private String des;

    private Float price;

    private String isActive;

    private String type;

    private String bedRoomCount;

    private String bedCount;

    private String bathRoomCount;

    private String peopleCount;

    private Map<String, Object> propertyInfo;
}