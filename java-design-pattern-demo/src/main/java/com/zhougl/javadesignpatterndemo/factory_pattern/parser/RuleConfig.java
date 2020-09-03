package com.zhougl.javadesignpatterndemo.factory_pattern.parser;

import lombok.Data;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/2/13 10:50
 */
@Data
public class RuleConfig {
    private String type;

    public RuleConfig(String type) {
        this.type = type;
    }
}
