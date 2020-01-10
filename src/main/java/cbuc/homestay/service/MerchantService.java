package cbuc.homestay.service;

import cbuc.homestay.CommonEnum.StatusEnum;
import cbuc.homestay.bean.Merchant;
import cbuc.homestay.bean.MerchantExample;
import cbuc.homestay.evt.UserEvt;
import cbuc.homestay.mapper.MerchantMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Explain:
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/1/2
 */
@Service
public class MerchantService {
    @Autowired
    private MerchantMapper merchantMapper;

    public Merchant queryDetail(UserEvt userEvt) {
        MerchantExample merchantExample = new MerchantExample();
        MerchantExample.Criteria criteria = merchantExample.createCriteria();
        if (StringUtils.isNotBlank(userEvt.getMaccount())) {
            criteria.andMaccountEqualTo(userEvt.getMaccount());
        }
        if (StringUtils.isNotBlank(userEvt.getMpwd())) {
            criteria.andMpwdEqualTo(userEvt.getMpwd());
        }
        if (StringUtils.isNotBlank(userEvt.getMphone())) {
            criteria.andMphoneEqualTo(userEvt.getMphone());
        }
        criteria.andStatusNotEqualTo(StatusEnum.D.getValue());
        List<Merchant> merchants = merchantMapper.selectByExample(merchantExample);
        return CollectionUtils.isEmpty(merchants)?null:merchants.get(0);
    }

    public int doEdit(UserEvt userEvt) {
        Merchant merchant = new Merchant();
        merchant.setId(userEvt.getId());
        if (StringUtils.isNotBlank(userEvt.getNpwd())) {
            merchant.setMpwd(userEvt.getNpwd());
        }
        merchant.builder();
        return merchantMapper.updateByPrimaryKeySelective(merchant);
    }
}
