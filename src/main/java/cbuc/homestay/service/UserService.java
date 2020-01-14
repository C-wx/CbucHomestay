package cbuc.homestay.service;

import cbuc.homestay.bean.User;
import cbuc.homestay.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Explain:   用户处理器
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
}
