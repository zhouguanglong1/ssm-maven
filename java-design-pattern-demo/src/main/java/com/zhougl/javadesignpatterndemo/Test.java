package com.zhougl.javadesignpatterndemo;

import io.swagger.oas.models.OpenAPI;
import io.swagger.oas.models.Operation;
import io.swagger.oas.models.PathItem;
import io.swagger.oas.models.Paths;
import io.swagger.oas.models.parameters.Parameter;
import io.swagger.oas.models.tags.Tag;
import io.swagger.parser.v3.OpenAPIV3Parser;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author by zhougl
 * @date 2020/3/10 23:24
 */
public class Test {
    public static void main(String[] args) {
        OpenAPI openAPI = new OpenAPIV3Parser().read("C:/git_project2/deepexi-middle-pay/swagger/swagger.json");
        Paths paths = openAPI.getPaths();
        List<Tag> tags = openAPI.getTags();

        for(Map.Entry<String, PathItem> map: paths.entrySet() ){
            String key = map.getKey();
            System.out.println(key);
            PathItem value = map.getValue();
            Operation get = value.getGet();
            if(Objects.nonNull(get)){
                List<Parameter> parameters = get.getParameters();
                System.out.println(parameters);
            }
            Operation post = value.getPost();
            if(Objects.nonNull(post)){
                List<Parameter> parameters1 = post.getParameters();
                System.out.println(parameters1);
            }
        }

//        System.out.println(paths);
        System.out.println(tags);
    }
}
