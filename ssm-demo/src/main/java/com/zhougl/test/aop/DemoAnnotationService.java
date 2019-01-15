package com.zhougl.test.aop;

import org.springframework.stereotype.Service;

/**
 * @author zhougl
 * 2018/8/29
 **/
@Service
public class DemoAnnotationService {
    @Action(name = "注解式拦截的add操作")
    public void add(){}
}
