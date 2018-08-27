package com.zhougl.service;

import com.zhougl.bean.User;
import com.zhougl.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhougl
 * 2018/8/26
 **/
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> selectAll(){
        return userMapper.selectUser();
    }
}
