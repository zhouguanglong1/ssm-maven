package com.zhougl.javadesignpatterndemo.refactor.viewer;

import com.google.gson.Gson;
import com.zhougl.javadesignpatterndemo.refactor.bean.RequestStat;

import java.util.Map;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/2/1 20:34
 */
public class ConsoleViewer implements StatViewer {

    @Override
    public void output(Map<String, RequestStat> requestStats, long startTimeInMillis, long endTimeInMills) {
        System.out.println("Time Span: [" + startTimeInMillis + ", " + endTimeInMills + "]");
        Gson gson = new Gson();
        System.out.println(gson.toJson(requestStats));
    }
}
