package com.zhougl.javadesignpatterndemo.factory_pattern.parser;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/2/13 10:50
 */
public interface ISystemConfigParser {
    /**
     * 解析成config类
     * @param ruleConfigFilePath
     * @return
     */
    RuleConfig parse(String ruleConfigFilePath);
}
