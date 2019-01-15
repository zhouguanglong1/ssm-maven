package com.zhougl.springsessionredis.controller;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhougl
 * 2019/1/7
 **/
public class BashController {
    @Autowired
    protected HttpServletRequest request;
}
