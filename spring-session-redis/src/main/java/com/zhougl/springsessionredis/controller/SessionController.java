package com.zhougl.springsessionredis.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhougl
 * 2019/1/7
 **/
@RestController
@RequestMapping(value = "/")
public class SessionController extends BashController{
    @RequestMapping(value = "/session")
    public Map<String, Object> getSession() {
        request.getSession().setAttribute("userName", "glmapper");
        Map<String, Object> map = new HashMap<>();
        map.put("sessionId", request.getSession().getId());
        return map;
    }

    @RequestMapping(value = "/get")
    public String get(HttpServletRequest request) {
        String userName = (String) request.getSession().getAttribute("userName");
        System.out.println(userName);
        return userName;
    }

}
