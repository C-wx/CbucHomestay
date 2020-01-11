package cbuc.homestay.service;

import cbuc.homestay.bean.Apply;
import cbuc.homestay.bean.ApplyExample;
import cbuc.homestay.mapper.ApplyMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Explain:
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
        applyExample.setOrderByClause("id desc");
        return applyMapper.selectByExample(applyExample);
    }

    public Apply queryDetail(Long id) {
        return applyMapper.selectByPrimaryKey(id);
    }

    public int doEdit(Apply apply) {
        return applyMapper.updateByPrimaryKeySelective(apply);
    }
}
