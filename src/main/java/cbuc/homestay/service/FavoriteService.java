package cbuc.homestay.service;

import cbuc.homestay.bean.Favorite;
import cbuc.homestay.bean.FavoriteExample;
import cbuc.homestay.mapper.FavoriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Explain: 收藏操作处理器
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/2/9
 */
@Service
public class FavoriteService {

    @Autowired
    private FavoriteMapper favoriteMapper;

    public int doAdd(Favorite favorite) {
        return favoriteMapper.insertSelective(favorite);
    }

    public List<Favorite> queryList(String openId) {
        FavoriteExample favoriteExample = new FavoriteExample();
        FavoriteExample.Criteria criteria = favoriteExample.createCriteria();
        criteria.andOpenIdEqualTo(openId);
        criteria.andStatusEqualTo("E");
        favoriteExample.setOrderByClause("id desc");
        return favoriteMapper.selectByExample(favoriteExample);
    }

    public Favorite queryDetail(Long id, String openId) {
        FavoriteExample favoriteExample = new FavoriteExample();
        FavoriteExample.Criteria criteria = favoriteExample.createCriteria();
        criteria.andRidEqualTo(id).andOpenIdEqualTo(openId);
        List<Favorite> favoriteList = favoriteMapper.selectByExample(favoriteExample);
        return favoriteList.size() > 0 ? favoriteList.get(0) : null;
    }

    public int doEdit(Favorite favorite) {
        FavoriteExample example = new FavoriteExample();
        example.createCriteria().andRidEqualTo(favorite.getRid()).andOpenIdEqualTo(favorite.getOpenId());
        return favoriteMapper.updateByExampleSelective(favorite, example);
    }
}
