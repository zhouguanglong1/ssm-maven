package com.zhougl.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跨域设置filter
 * @author zhougl
 * 2018/9/26
 **/
@Component
public class CorsFilter implements Filter{

    private static final String TOKEN_HEADER = "X_Auth_Token";
    private static final String ORIGIN = "Origin";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String token = request.getHeader(TOKEN_HEADER);
        System.out.println("token:"+token);
        String origin = request.getHeader(ORIGIN);
        response.setHeader("Access-Control-Allow-Origin",origin);
        response.setHeader("Access-Control-Allow-Credentials","true");
        response.setHeader("Access-Control-Allow-Methods","POST, GET, PUT, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age","3600");
        response.setHeader("Access-Control-Allow-Headers","X-Requested-With,Content-Type,Origin,Accept,"+TOKEN_HEADER);
    }

    @Override
    public void destroy() {

    }
}
