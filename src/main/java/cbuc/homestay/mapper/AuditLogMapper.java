package cbuc.homestay.mapper;

import cbuc.homestay.bean.AuditLog;
import cbuc.homestay.bean.AuditLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuditLogMapper {
    long countByExample(AuditLogExample example);

    int deleteByExample(AuditLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AuditLog record);

    int insertSelective(AuditLog record);

    List<AuditLog> selectByExample(AuditLogExample example);

    AuditLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AuditLog record, @Param("example") AuditLogExample example);

    int updateByExample(@Param("record") AuditLog record, @Param("example") AuditLogExample example);

    int updateByPrimaryKeySelective(AuditLog record);

    int updateByPrimaryKey(AuditLog record);
}