package com.zhougl.javadesignpatterndemo.factory_pattern.factory_method;

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 调用者在使用的时候，直接使用RuleConfigParserFactoryMap.get(path).createParser();即可
 *
 * @author zhougl
 * @version v1.0.0
 * @since 2020/2/13 11:10
 */
public class RuleConfigParserFactoryMap {

    private RuleConfigParserFactoryMap() {
    }

    private static final Map<String, IRuleConfigParserFactory> cachedParsers = new HashMap<>();

    static {
        cachedParsers.put("json", new JsonRuleConfigParserFactory());
        cachedParsers.put("xml", new XmlRuleConfigParserFactory());
        cachedParsers.put("properties", new PropertiesRuleConfigParserFactory());
        cachedParsers.put("yaml", new YamlRuleConfigParserFactory());
    }

    public static IRuleConfigParserFactory getParser(String configFormat) {
        if (StringUtils.isEmpty(configFormat)) {
            throw new RuntimeException("format is empty");
        }
        return cachedParsers.get(configFormat);
    }

}
