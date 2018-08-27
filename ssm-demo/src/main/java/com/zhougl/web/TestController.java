package com.zhougl.web;

import com.zhougl.bean.User;
import com.zhougl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhougl
 * 2018/8/24
 **/
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping("test1")
    public List<User> test1(){
        final List<User> users = userService.selectAll();
        return users;
    }

    @RequestMapping("test2")
    public String test2(){
        return "傻虎";
    }

}
