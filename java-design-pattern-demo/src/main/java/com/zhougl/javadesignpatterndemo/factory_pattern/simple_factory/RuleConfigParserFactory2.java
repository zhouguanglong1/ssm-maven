package com.zhougl.javadesignpatterndemo.factory_pattern.simple_factory;

import com.zhougl.javadesignpatterndemo.factory_pattern.parser.*;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/2/13 11:10
 */
public class RuleConfigParserFactory2 {

    private RuleConfigParserFactory2(){}

    private static final Map<String, IRuleConfigParser> cachedParsers = new HashMap<>();

    static {
        cachedParsers.put("json",new JsonRuleConfigParser());
        cachedParsers.put("xml",new XmlRuleConfigParser());
        cachedParsers.put("properties",new PropertiesRuleConfigParser());
        cachedParsers.put("yaml",new YamlRuleConfigParser());
    }

    public static IRuleConfigParser getParser(String configFormat){
        if(StringUtils.isEmpty(configFormat)){
            throw new RuntimeException("format is empty");
        }
        return cachedParsers.get(configFormat);
    }

}
