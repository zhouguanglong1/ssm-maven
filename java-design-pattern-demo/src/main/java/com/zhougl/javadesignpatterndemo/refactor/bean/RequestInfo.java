package com.zhougl.javadesignpatterndemo.refactor.bean;

import lombok.Data;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/2/1 17:22
 */
@Data
public class RequestInfo {
    private String apiName;
    private double responseTime;
    private long timestamp;

    public RequestInfo(String apiName, double responseTime, long timestamp) {
        this.apiName = apiName;
        this.responseTime = responseTime;
        this.timestamp = timestamp;
    }
}
