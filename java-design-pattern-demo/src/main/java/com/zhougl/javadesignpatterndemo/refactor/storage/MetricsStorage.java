package com.zhougl.javadesignpatterndemo.refactor.storage;

import com.zhougl.javadesignpatterndemo.refactor.bean.RequestInfo;

import java.util.List;
import java.util.Map;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/2/1 17:25
 */
public interface MetricsStorage {
    void saveRequestInfo(RequestInfo requestInfo);

    List<RequestInfo> getRequestInfos(String apiName, long startTimeInMillis, long endTimeInMillis);

    Map<String, List<RequestInfo>> getRequestInfos(long startTimeInMillis, long endTimeInMillis);
}
