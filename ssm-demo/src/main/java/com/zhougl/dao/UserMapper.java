package com.zhougl.dao;

import com.zhougl.bean.User;
import java.util.List;

/**
 * @author zhougl
 * 2018/8/26
 **/
public interface UserMapper {
    List<User> selectUser();
}
