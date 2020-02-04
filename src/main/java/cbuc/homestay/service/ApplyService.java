package cbuc.homestay.service;

import cbuc.homestay.CommonEnum.StatusEnum;
import cbuc.homestay.bean.Apply;
import cbuc.homestay.bean.ApplyExample;
import cbuc.homestay.mapper.ApplyMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Explain: 商家申请处理器
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/1/10
 */
@Service
public class ApplyService {

    @Autowired
    private ApplyMapper applyMapper;

    public List<Apply> queryList(String title) {
        ApplyExample applyExample = new ApplyExample();
        ApplyExample.Criteria criteria = applyExample.createCriteria();
        if (StringUtils.isNotBlank(title)) {
            criteria.andMnameLike("%" + title + "%");
        }
        criteria.andStatusEqualTo(StatusEnum.E.getValue());
        applyExample.setOrderByClause("id desc");
        return applyMapper.selectByExample(applyExample);
    }

    public Apply queryDetail(Long id) {
        return applyMapper.selectByPrimaryKey(id);
    }

    public int doEdit(Apply apply) {
        return applyMapper.updateByPrimaryKeySelective(apply);
    }

    public int doAdd(Apply apply) {
        return applyMapper.insertSelective(apply);
    }

    public Apply getApplyDetail(String openId) {
        ApplyExample applyExample = new ApplyExample();
        applyExample.createCriteria().andOpenIdEqualTo(openId);
        List<Apply> applyList = applyMapper.selectByExample(applyExample);
        return applyList.size() > 0 ? applyList.get(0) : null;
    }
}
