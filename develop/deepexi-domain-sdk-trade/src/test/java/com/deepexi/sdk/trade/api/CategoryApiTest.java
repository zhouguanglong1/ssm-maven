/*
 * swagger导入dapi demo
 * swagger导入dapi demo
 *
 * OpenAPI spec version: v1
 * Contact: 970711665@qq.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.deepexi.sdk.trade.api;

import com.deepexi.sdk.ApiException;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for CategoryApi
 */
@Ignore
public class CategoryApiTest {

    private final CategoryApi api = new CategoryApi();

    
    /**
     * 分页查询分类
     *
     * 分页查询分类
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void listCategoryTest() throws ApiException {
        Object response = api.listCategory();

        // TODO: test validations
    }
    
    /**
     * 分页查询分类(带接口信息)
     *
     * 分页请求分类(带接口信息)
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void listCategoryAndInterfaceTest() throws ApiException {
        Object response = api.listCategoryAndInterface();

        // TODO: test validations
    }
    
}
