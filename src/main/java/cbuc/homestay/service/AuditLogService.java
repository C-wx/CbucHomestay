package cbuc.homestay.service;

import cbuc.homestay.CommonEnum.LevelEnum;
import cbuc.homestay.CommonEnum.StatusEnum;
import cbuc.homestay.bean.*;
import cbuc.homestay.mapper.AuditLogMapper;
import cbuc.homestay.utils.SendMessageUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Explain: 审核历史处理器
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/1/11
 */
@Service
public class AuditLogService {

    @Autowired
    private AuditLogMapper auditLogMapper;

    @Autowired
    private ApplyService applyService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private RoomInfoService roomInfoService;


    public int doAdd(AuditLog auditLog) {
        return auditLogMapper.insertSelective(auditLog);
    }

    public List<AuditLog> queryList(Long parentId, String type) {
        AuditLogExample auditLogExample = new AuditLogExample();
        auditLogExample.createCriteria()
                .andParentIdEqualTo(parentId)
                .andTypeEqualTo(type);
        auditLogExample.setOrderByClause("CREATE_TIME DESC");
        return auditLogMapper.selectByExample(auditLogExample);
    }

    public AuditLog queryDetail(Long parentId, String type) {
        AuditLogExample auditLogExample = new AuditLogExample();
        auditLogExample.createCriteria().andParentIdEqualTo(parentId).andTypeEqualTo(type);
        List<AuditLog> auditLogList = auditLogMapper.selectByExample(auditLogExample);
        return auditLogList.size() > 0 ? auditLogList.get(0) : null;
    }

    /**审核操作*/
    public void doAudit(AuditLog auditLog) {
        switch (auditLog.getType()) {
            case "MERCHANT":            //审核商家
                Apply apply = applyService.queryDetail(auditLog.getParentId());
                apply.setAuditStatus(auditLog.getAuditStatus());
                applyService.doEdit(apply);
                if (StatusEnum.SA.getValue().equals(auditLog.getAuditStatus())) {   //审核通过才建立商户信息
                    Merchant merchant = new Merchant();
                    BeanUtils.copyProperties(apply, merchant);
                    String maccount = SendMessageUtil.getRandomCode(4) + "66";          //随机生成商户账号
                    String mpwd = SendMessageUtil.getRandomCode(6);                     //随机生成密码
                    merchant.setAuditId(apply.getId());
                    merchant.setMaccount(maccount);
                    merchant.setMpwd(mpwd);
                    merchant.setMlevel(LevelEnum.NORMAL.getValue());
                    merchant.setCreateTime(new Date());
                    merchantService.doAdd(merchant);
                }
                break;
            case "NEWS":            //审核资讯
                News news = News.builder().id(auditLog.getParentId()).auditStatus(auditLog.getAuditStatus()).build();
                newsService.doEdit(news);
                break;
            case "ROOM":            //审核房源
                RoomInfo roomInfo = RoomInfo.builder().id(auditLog.getParentId()).auditStatus(auditLog.getAuditStatus()).build();
                roomInfoService.doEdit(roomInfo);
                break;
        }
    }
}
