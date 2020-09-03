package com.zhougl.javadesignpatterndemo.factory_pattern.factory_method;

import com.zhougl.javadesignpatterndemo.factory_pattern.parser.IRuleConfigParser;

/**
 * 工厂方法模式，这种模式并不能减少调用者的工作量，调用者需要自己执行if/else判断，解决方案是建多一个工厂类
 *
 * @author zhougl
 * @version v1.0.0
 * @since 2020/2/13 22:42
 */
public interface IRuleConfigParserFactory {
    IRuleConfigParser createParser();
}
