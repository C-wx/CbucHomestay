package cbuc.homestay.service;

import cbuc.homestay.CommonEnum.StatusEnum;
import cbuc.homestay.bean.Merchant;
import cbuc.homestay.bean.MerchantExample;
import cbuc.homestay.evt.LoginUser;
import cbuc.homestay.mapper.MerchantMapper;
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

    public Merchant queryDetail(LoginUser loginUser) {
        MerchantExample merchantExample = new MerchantExample();
        merchantExample.createCriteria()
                .andMaccountEqualTo(loginUser.getMaccount())
                .andMpwdEqualTo(loginUser.getMpwd())
                .andStatusNotEqualTo(StatusEnum.D.getValue());
        List<Merchant> merchants = merchantMapper.selectByExample(merchantExample);
        return CollectionUtils.isEmpty(merchants)?null:merchants.get(0);
    }
}
