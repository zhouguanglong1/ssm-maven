package com.example.spring2swaggerdemo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/3/4 17:12
 */
@Api(description = "分类管理", tags = "category")
@RequestMapping("/conductor/api/v1/categories")
public class DemoController {
    @GetMapping
    @ApiOperation(value = "分页查询分类", notes = "分页查询分类", nickname = "listCategory")
    public Object listCategory() {
        return null;
    }

    @GetMapping("/interfaces")
    @ApiOperation(value = "分页查询分类(带接口信息)", notes = "分页请求分类(带接口信息)", nickname = "listCategoryAndInterface")
    public Object listCategoryAndInterface() {
        return null;
    }

}
