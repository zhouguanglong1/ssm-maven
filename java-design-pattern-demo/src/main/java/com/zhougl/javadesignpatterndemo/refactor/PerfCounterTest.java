package com.zhougl.javadesignpatterndemo.refactor;

import com.zhougl.javadesignpatterndemo.refactor.bean.RequestInfo;
import com.zhougl.javadesignpatterndemo.refactor.collector.MetricsCollector;
import com.zhougl.javadesignpatterndemo.refactor.reporter.ConsoleReporter;
import com.zhougl.javadesignpatterndemo.refactor.reporter.EmailReporter;
import com.zhougl.javadesignpatterndemo.refactor.storage.MetricsStorage;
import com.zhougl.javadesignpatterndemo.refactor.storage.RedisMetricsStorage;
import com.zhougl.javadesignpatterndemo.refactor.viewer.ConsoleViewer;
import com.zhougl.javadesignpatterndemo.refactor.viewer.EmailViewer;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/2/1 21:07
 */
public class PerfCounterTest {
    public static void main(String[] args) {
        MetricsStorage metricsStorage = new RedisMetricsStorage();
        Aggregator aggregator = new Aggregator();

        ConsoleViewer viewer = new ConsoleViewer();
        ConsoleReporter consoleReporter = new ConsoleReporter(metricsStorage, aggregator, viewer);
        consoleReporter.startRepeatedReport(60, 60);

        EmailViewer emailViewer = new EmailViewer();
        emailViewer.addToAddress("zhouguanglong@qq.com");
        EmailReporter emailReporter = new EmailReporter(metricsStorage, aggregator, emailViewer);
        emailReporter.startDailyReport();

        MetricsCollector collector = new MetricsCollector(metricsStorage);

        collector.recordRequest(new RequestInfo("register", 123, 10234));
        collector.recordRequest(new RequestInfo("register", 223, 11234));
        collector.recordRequest(new RequestInfo("register", 323, 12334));
        collector.recordRequest(new RequestInfo("login", 23, 12434));
        collector.recordRequest(new RequestInfo("login", 1223, 14234));

        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
