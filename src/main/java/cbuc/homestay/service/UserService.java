package cbuc.homestay.service;

import cbuc.homestay.CommonEnum.StatusEnum;
import cbuc.homestay.bean.User;
import cbuc.homestay.bean.UserExample;
import cbuc.homestay.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Explain: 用户处理器
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/1/13
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User queryDetail(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public List<User> queryList() {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andStatusNotEqualTo(StatusEnum.D.getValue());
        return userMapper.selectByExample(userExample);
    }

    public int doAdd(User user) {
        return userMapper.insertSelective(user);
    }

    public User queryDetail(String openId) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andOpenIdEqualTo(openId);
        List<User> userList = userMapper.selectByExample(userExample);
        return userList.size() > 0 ? userList.get(0) : null;
    }

    public int update(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andOpenIdEqualTo(user.getOpenId());
        return userMapper.updateByExampleSelective(user, userExample);
    }
}
