package cbuc.homestay.mapper;

import cbuc.homestay.bean.RoomInfo;
import cbuc.homestay.bean.RoomInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoomInfoMapper {
    long countByExample(RoomInfoExample example);

    int deleteByExample(RoomInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(RoomInfo record);

    int insertSelective(RoomInfo record);

    List<RoomInfo> selectByExample(RoomInfoExample example);

    RoomInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") RoomInfo record, @Param("example") RoomInfoExample example);

    int updateByExample(@Param("record") RoomInfo record, @Param("example") RoomInfoExample example);

    int updateByPrimaryKeySelective(RoomInfo record);

    int updateByPrimaryKey(RoomInfo record);
}