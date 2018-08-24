package com.zhougl.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhougl
 * 2018/8/24
 **/
@RestController
@RequestMapping("test")
public class TestController {
    @RequestMapping("test1")
    public String test1(){
        return "hello,boot";
    }

}
