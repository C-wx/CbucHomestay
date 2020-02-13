package cbuc.homestay.mapper;

import cbuc.homestay.bean.Bulletin;
import cbuc.homestay.bean.BulletinExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface BulletinMapper {
    long countByExample(BulletinExample example);

    int deleteByExample(BulletinExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Bulletin record);

    int insertSelective(Bulletin record);

    List<Bulletin> selectByExample(BulletinExample example);

    Bulletin selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Bulletin record, @Param("example") BulletinExample example);

    int updateByExample(@Param("record") Bulletin record, @Param("example") BulletinExample example);

    int updateByPrimaryKeySelective(Bulletin record);

    int updateByPrimaryKey(Bulletin record);

    Bulletin getLastBulletin();
}