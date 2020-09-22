package com.zhougl.javadesignpatterndemo.factory_pattern.factory_method;

import com.zhougl.javadesignpatterndemo.factory_pattern.parser.IRuleConfigParser;
import com.zhougl.javadesignpatterndemo.factory_pattern.parser.XmlRuleConfigParser;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/2/13 22:43
 */
public class XmlRuleConfigParserFactory implements IRuleConfigParserFactory {
    @Override
    public IRuleConfigParser createParser() {
        return new XmlRuleConfigParser();
    }
}
