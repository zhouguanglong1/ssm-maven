package com.zhougl.javadesignpatterndemo.refactor.reporter;

import com.zhougl.javadesignpatterndemo.refactor.Aggregator;
import com.zhougl.javadesignpatterndemo.refactor.bean.RequestInfo;
import com.zhougl.javadesignpatterndemo.refactor.bean.RequestStat;
import com.zhougl.javadesignpatterndemo.refactor.storage.MetricsStorage;
import com.zhougl.javadesignpatterndemo.refactor.viewer.StatViewer;

import java.util.List;
import java.util.Map;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/2/1 20:52
 */
public abstract class AbstractReporter {
    protected static final Long DAY_HOURS_IN_SECONDS = 86400L;

    protected MetricsStorage metricsStorage;
    protected Aggregator aggregator;
    protected StatViewer statViewer;

    public AbstractReporter(MetricsStorage metricsStorage,Aggregator aggregator,StatViewer statViewer){
        this.metricsStorage = metricsStorage;
        this.aggregator = aggregator;
        this.statViewer = statViewer;
    }

    protected void execute(long durationInSeconds){
        long durationInMillis = durationInSeconds * 1000;
        long endTimeInMillis = System.currentTimeMillis();
        long startTimeInMillis = endTimeInMillis - durationInMillis;
        Map<String, List<RequestInfo>> requestInfos =
                metricsStorage.getRequestInfos(startTimeInMillis, endTimeInMillis);
        Map<String, RequestStat> stats = aggregator.aggregate(requestInfos, durationInMillis);
        statViewer.output(stats,startTimeInMillis,endTimeInMillis);
    }

}
