package cbuc.homestay.service;

import cbuc.homestay.bean.AuditLog;
import cbuc.homestay.bean.AuditLogExample;
import cbuc.homestay.mapper.AuditLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Explain:   审核历史处理器
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/1/11
 */
@Service
public class AuditLogService {

    @Autowired
    private AuditLogMapper auditLogMapper;


    public int doAdd(AuditLog auditLog) {
        return auditLogMapper.insertSelective(auditLog);
    }

    public List<AuditLog> queryList(Long  parentId, String type) {
        AuditLogExample auditLogExample = new AuditLogExample();
        auditLogExample.createCriteria()
                       .andParentIdEqualTo(parentId)
                       .andTypeEqualTo(type);
        auditLogExample.setOrderByClause("CREATE_TIME DESC");
        return auditLogMapper.selectByExample(auditLogExample);
    }
}
