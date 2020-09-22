package com.zhougl.javadesignpatterndemo.refactor.bean;

import lombok.Data;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/2/1 17:21
 */
@Data
public class RequestStat {
    private double maxResponseTime;
    private double minResponseTime;
    private double avgResponseTime;
    private double p999ResponseTime;
    private double p99ResponseTime;
    private long count;
    private long tps;
    //...省略getter/setter方法...
}
