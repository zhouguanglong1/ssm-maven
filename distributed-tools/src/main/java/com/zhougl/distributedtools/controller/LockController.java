package com.zhougl.distributedtools.controller;

import com.zhougl.distributedtools.service.RedisLockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhougl
 * 2019/4/2
 **/
@Controller
@RequestMapping("lock")
public class LockController {

    @Autowired
    private RedisLockService redisLockService;

    @RequestMapping("test")
    public void test(){
        redisLockService.use();
    }
}
