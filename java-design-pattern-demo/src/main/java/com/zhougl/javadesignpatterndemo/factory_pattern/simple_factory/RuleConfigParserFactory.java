package com.zhougl.javadesignpatterndemo.factory_pattern.simple_factory;

import com.zhougl.javadesignpatterndemo.factory_pattern.parser.*;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/2/13 10:58
 */
public class RuleConfigParserFactory {

    private RuleConfigParserFactory(){}

    public static IRuleConfigParser getRuleConfigParser(String ruleConfigFileExtension) {
        IRuleConfigParser parser = null;
        if ("json".equalsIgnoreCase(ruleConfigFileExtension)) {
            parser = new JsonRuleConfigParser();
        } else if ("xml".equalsIgnoreCase(ruleConfigFileExtension)) {
            parser = new XmlRuleConfigParser();
        } else if ("yaml".equalsIgnoreCase(ruleConfigFileExtension)) {
            parser = new YamlRuleConfigParser();
        } else if ("properties".equalsIgnoreCase(ruleConfigFileExtension)) {
            parser = new PropertiesRuleConfigParser();
        }
        return parser;
    }
}
