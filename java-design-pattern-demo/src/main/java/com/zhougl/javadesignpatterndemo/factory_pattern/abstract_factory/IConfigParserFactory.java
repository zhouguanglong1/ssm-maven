package com.zhougl.javadesignpatterndemo.factory_pattern.abstract_factory;

import com.zhougl.javadesignpatterndemo.factory_pattern.parser.IRuleConfigParser;
import com.zhougl.javadesignpatterndemo.factory_pattern.parser.ISystemConfigParser;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/2/13 22:55
 */
public interface IConfigParserFactory {
    IRuleConfigParser createRuleParser();
    ISystemConfigParser createSystemParser();
}
