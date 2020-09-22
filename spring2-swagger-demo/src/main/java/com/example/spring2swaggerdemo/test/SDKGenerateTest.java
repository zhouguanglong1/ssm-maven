package com.example.spring2swaggerdemo.test;

import io.swagger.codegen.ClientOptInput;
import io.swagger.codegen.ClientOpts;
import io.swagger.codegen.DefaultGenerator;
import io.swagger.codegen.languages.JavaClientCodegen;
import io.swagger.models.Swagger;
import io.swagger.parser.SwaggerParser;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/3/4 17:26
 */
public class SDKGenerateTest {
    public static void main(String[] args) throws IOException {
        String info = FileUtils.readFileToString(new File("C:/develop/personal/ssm-maven/spring2-swagger-demo/swagger/swagger.json"));
        Swagger swagger = new SwaggerParser().parse(info);
        JavaClientCodegen serviceCodegen = new JavaClientCodegen();
        serviceCodegen.setUseGzipFeature(true);
        serviceCodegen.setOutputDir("c:/develop/deepexi-domain-sdk-trade");
        serviceCodegen.setModelPackage("com.deepexi.sdk.trade.model");
        serviceCodegen.setApiPackage("com.deepexi.sdk.trade.api");
        serviceCodegen.setInvokerPackage("com.deepexi.sdk");
        serviceCodegen.setArtifactId("deepexi-domain-sdk-trade");
        ClientOptInput input = new ClientOptInput().opts(new ClientOpts()).swagger(swagger);
        input.setConfig(serviceCodegen);
        DefaultGenerator apiCodegen = new DefaultGenerator();
        List<File> files = apiCodegen.opts(input).generate();
    }
}
