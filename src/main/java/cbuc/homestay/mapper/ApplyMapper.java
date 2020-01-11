package cbuc.homestay.mapper;

import cbuc.homestay.bean.Apply;
import cbuc.homestay.bean.ApplyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApplyMapper {
    long countByExample(ApplyExample example);

    int deleteByExample(ApplyExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Apply record);

    int insertSelective(Apply record);

    List<Apply> selectByExample(ApplyExample example);

    Apply selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Apply record, @Param("example") ApplyExample example);

    int updateByExample(@Param("record") Apply record, @Param("example") ApplyExample example);

    int updateByPrimaryKeySelective(Apply record);

    int updateByPrimaryKey(Apply record);
}