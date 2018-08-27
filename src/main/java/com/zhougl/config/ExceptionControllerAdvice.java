package com.zhougl.config;

import com.zhougl.bean.RespEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author zhougl
 * 2018/8/26
 **/
@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RespEntity catchException(Exception e){
        e.printStackTrace();
        System.out.println(e.getMessage());
        RespEntity resp = new RespEntity();
        resp.setCode(-5);
        resp.setMsg(e.getMessage());
        return resp;
    }
}
