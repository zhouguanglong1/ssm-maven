package com.zhougl.javadesignpatterndemo.refactor.viewer;

import com.zhougl.javadesignpatterndemo.refactor.bean.RequestStat;

import java.util.Map;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/2/1 20:32
 */
public interface StatViewer {
    void output(Map<String, RequestStat> requestStats, long startTimeInMillis, long endTimeInMills);
}
