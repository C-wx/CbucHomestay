package cbuc.homestay.mapper;

import cbuc.homestay.bean.Message;
import cbuc.homestay.bean.MessageExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageMapper {
    long countByExample(MessageExample example);

    int deleteByExample(MessageExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Message record);

    int insertSelective(Message record);

    List<Message> selectByExample(MessageExample example);

    Message selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Message record, @Param("example") MessageExample example);

    int updateByExample(@Param("record") Message record, @Param("example") MessageExample example);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

    Message queryLast(@Param("mid") Long mid,@Param("type") String type);

    List<Message> getList(@Param("message") Message message);
}