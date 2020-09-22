package com.zhougl.javadesignpatterndemo.refactor.storage;

import com.zhougl.javadesignpatterndemo.refactor.bean.RequestInfo;

import java.util.List;
import java.util.Map;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/2/1 19:36
 */
public class RedisMetricsStorage implements MetricsStorage {
    @Override
    public void saveRequestInfo(RequestInfo requestInfo) {

    }

    @Override
    public List<RequestInfo> getRequestInfos(String apiName, long startTimeInMillis, long endTimeInMillis) {
        return null;
    }

    @Override
    public Map<String, List<RequestInfo>> getRequestInfos(long startTimeInMillis, long endTimeInMillis) {
        return null;
    }
}
