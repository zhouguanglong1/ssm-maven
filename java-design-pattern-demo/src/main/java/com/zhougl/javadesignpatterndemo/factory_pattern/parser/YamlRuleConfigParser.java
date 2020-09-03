package com.zhougl.javadesignpatterndemo.factory_pattern.parser;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/2/13 10:52
 */
public class YamlRuleConfigParser implements IRuleConfigParser {
    @Override
    public RuleConfig parse(String ruleConfigFilePath) {
        return new RuleConfig(ruleConfigFilePath);
    }
}
