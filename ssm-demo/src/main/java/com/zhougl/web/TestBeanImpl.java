package com.zhougl.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2019/11/12 19:49
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestBeanImpl extends TestBean {
    private String name;
    private Integer age;
}
