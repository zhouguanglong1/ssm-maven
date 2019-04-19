package com.zhougl.distributedtools.service;

import com.zhougl.distributedtools.lock.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author zhougl
 * 2019/4/2
 **/
@Service
public class RedisLockService {
    @Autowired
    private RedisLock redisLock;

    public void use() {
        String key = "key";
        String request = UUID.randomUUID().toString();
        try {
            boolean locktest = redisLock.trylock(key, request);
            if (!locktest) {
                System.out.println("locked error");
                return;
            }else{
                System.out.println("lock success");
            }


            //do something

        } finally {
            redisLock.unlock(key,request);
        }

    }
}
