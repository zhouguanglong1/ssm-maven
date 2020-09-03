package com.zhougl.javadesignpatterndemo.refactor.collector;

import com.zhougl.javadesignpatterndemo.refactor.bean.RequestInfo;
import com.zhougl.javadesignpatterndemo.refactor.storage.MetricsStorage;
import org.springframework.util.StringUtils;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/2/1 18:56
 */
public class MetricsCollector {
    private MetricsStorage metricsStorage;

    //基于接口而非实现编程
    // 依赖注入
    public MetricsCollector(MetricsStorage metricsStorage) {
        this.metricsStorage = metricsStorage;
    }

    //用一个函数代替了最小原型中的两个函数
    public void recordRequest(RequestInfo requestInfo) {
        if (requestInfo == null || StringUtils.isEmpty(requestInfo.getApiName())) {
            return;
        }
        metricsStorage.saveRequestInfo(requestInfo);
    }
}
