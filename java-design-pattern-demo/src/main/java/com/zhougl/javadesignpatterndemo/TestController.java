package com.zhougl.javadesignpatterndemo;

import com.deepexi.sdk.commodity.api.BrandApi;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/4/7 16:26
 */
@RestController
public class TestController {

    @RequestMapping(value = "/test1")
    public String test1(HttpServletRequest request) {
        List<Byte[]> temp = new ArrayList<Byte[]>();

        Byte[] b = new Byte[1024 * 1024];
        temp.add(b);

        return "success";
    }

    final static ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(100, 100, 1, TimeUnit.MINUTES,
            new LinkedBlockingQueue<>());// 创建线程池，通过线程池，保证创建的线程存活

    final static ThreadLocal<Byte[]> localVariable = new ThreadLocal<Byte[]>();// 声明本地变量

    @RequestMapping(value = "/test0")
    public String test0(HttpServletRequest request) {
        poolExecutor.execute(() -> {
            Byte[] c = new Byte[4096 * 1024];
            localVariable.set(c);// 为线程添加变量


        });
        return "success";
    }

    @RequestMapping(value = "/test2")
    public String test2(HttpServletRequest request) {
        List<Byte[]> temp1 = new ArrayList<Byte[]>();

        Byte[] b = new Byte[1024 * 20];
        temp1.add(b);// 添加局部变量

        return "success";
    }

    @PostMapping(value = "/test3")
    public String test3(Long id) {
        System.out.println(id);

        return "success";
    }


    public static void main(String[] args) {

//        // 初始化
//        ApiClient apiClient = new ApiClient();
//        // 网关地址
//        apiClient.setBasePath("http://120.24.37.100:8083/gateway");
//        // 在开放平台获取到的AppKey
//        apiClient.setAppKey("8HeUUzB2");
//        // 在开放平台获取到的AppSecret
//        apiClient.setAppSecret("c9da8be2296d520c893a59bebacccbd3401d0d23");
//
//        // 构建BrandApi
//        BrandApi brandApi = new BrandApi();
//        brandApi.setApiClient(apiClient);
//
//        // 请求接口，并打印响应信息
//        System.out.println(new Gson().toJson(brandApi.getBrandLogo(1L)));

    }
}
