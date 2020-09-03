package com.zhougl.javadesignpatterndemo.refactor.reporter;

import com.google.gson.Gson;
import com.zhougl.javadesignpatterndemo.refactor.Aggregator;
import com.zhougl.javadesignpatterndemo.refactor.storage.MetricsStorage;
import com.zhougl.javadesignpatterndemo.refactor.bean.RequestInfo;
import com.zhougl.javadesignpatterndemo.refactor.bean.RequestStat;
import com.zhougl.javadesignpatterndemo.refactor.viewer.StatViewer;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/2/1 17:23
 */

public class ConsoleReporter extends AbstractReporter{
    private ScheduledExecutorService executor;

    public ConsoleReporter(MetricsStorage metricsStorage,Aggregator aggregator,StatViewer statViewer) {
        super(metricsStorage, aggregator, statViewer);
        this.executor = Executors.newSingleThreadScheduledExecutor();
    }

    public void startRepeatedReport(long periodInSeconds, long durationInSeconds) {
        executor.scheduleAtFixedRate(() -> execute(durationInSeconds), 0, periodInSeconds, TimeUnit.SECONDS);
    }

}




