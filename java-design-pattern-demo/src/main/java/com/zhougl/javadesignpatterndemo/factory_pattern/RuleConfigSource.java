package com.zhougl.javadesignpatterndemo.factory_pattern;

import com.zhougl.javadesignpatterndemo.factory_pattern.parser.*;
import com.zhougl.javadesignpatterndemo.factory_pattern.simple_factory.RuleConfigParserFactory;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/2/13 1:22
 */
public class RuleConfigSource {

    public RuleConfig load(String ruleConfigFilePath) {
        String ruleConfigFileExtension = getFileExtension(ruleConfigFilePath);
        IRuleConfigParser parser = RuleConfigParserFactory.getRuleConfigParser(ruleConfigFileExtension);
        if(parser == null){
            throw new RuntimeException("Rule config file format is not supported: " + ruleConfigFilePath);
        }
        String configText = "";
        //从ruleConfigFilePath文件中读取配置文本到configText中
        RuleConfig ruleConfig = parser.parse(configText);
        return ruleConfig;
    }

//    private IRuleConfigParser getRuleConfigParser(String ruleConfigFileExtension) {
//        IRuleConfigParser parser = null;
//        if ("json".equalsIgnoreCase(ruleConfigFileExtension)) {
//            parser = new JsonRuleConfigParser();
//        } else if ("xml".equalsIgnoreCase(ruleConfigFileExtension)) {
//            parser = new XmlRuleConfigParser();
//        } else if ("yaml".equalsIgnoreCase(ruleConfigFileExtension)) {
//            parser = new YamlRuleConfigParser();
//        } else if ("properties".equalsIgnoreCase(ruleConfigFileExtension)) {
//            parser = new PropertiesRuleConfigParser();
//        }
//        return parser;
//    }

    private String getFileExtension(String filePath) {
        //...解析文件名获取扩展名，比如rule.json，返回json
        return "json";
    }
}

