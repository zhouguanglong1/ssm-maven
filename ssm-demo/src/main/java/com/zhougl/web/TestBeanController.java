package com.zhougl.web;

import com.deepexi.util.config.Payload;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2019/11/12 19:50
 */
@RestController
@RequestMapping("/api/v1/tests")
public class TestBeanController {
    @PostMapping
    public Payload queryOrder(TestBean testBean) {
        TestBeanImpl bean = (TestBeanImpl) testBean;
        return new Payload(bean);
    }
}
