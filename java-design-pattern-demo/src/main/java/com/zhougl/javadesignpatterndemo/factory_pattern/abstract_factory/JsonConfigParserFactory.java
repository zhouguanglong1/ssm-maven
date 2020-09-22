package com.zhougl.javadesignpatterndemo.factory_pattern.abstract_factory;

import com.zhougl.javadesignpatterndemo.factory_pattern.parser.IRuleConfigParser;
import com.zhougl.javadesignpatterndemo.factory_pattern.parser.ISystemConfigParser;
import com.zhougl.javadesignpatterndemo.factory_pattern.parser.JsonRuleConfigParser;
import com.zhougl.javadesignpatterndemo.factory_pattern.parser.JsonSystemConfigParser;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/2/13 22:56
 */
public class JsonConfigParserFactory implements IConfigParserFactory {
    @Override
    public IRuleConfigParser createRuleParser() {
        return new JsonRuleConfigParser();
    }

    @Override
    public ISystemConfigParser createSystemParser() {
        return new JsonSystemConfigParser();
    }
}
