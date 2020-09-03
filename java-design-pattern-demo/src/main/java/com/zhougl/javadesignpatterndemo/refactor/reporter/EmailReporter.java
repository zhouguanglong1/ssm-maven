package com.zhougl.javadesignpatterndemo.refactor.reporter;

import com.zhougl.javadesignpatterndemo.refactor.Aggregator;
import com.zhougl.javadesignpatterndemo.refactor.EmailSender;
import com.zhougl.javadesignpatterndemo.refactor.storage.MetricsStorage;
import com.zhougl.javadesignpatterndemo.refactor.viewer.StatViewer;

import java.util.*;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/2/1 17:24
 */
public class EmailReporter extends AbstractReporter {

    private EmailSender emailSender;
    private List<String> toAddresses = new ArrayList<>();

    public EmailReporter(MetricsStorage metricsStorage, Aggregator aggregator, StatViewer statViewer) {
        this(metricsStorage, new EmailSender(), aggregator, statViewer);
    }

    public EmailReporter(MetricsStorage metricsStorage, EmailSender emailSender, Aggregator aggregator, StatViewer statViewer) {
        super(metricsStorage, aggregator, statViewer);
        this.emailSender = emailSender;
    }

    public void addToAddress(String address) {
        toAddresses.add(address);
    }

    public void startDailyReport() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date firstTime = calendar.getTime();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                execute(DAY_HOURS_IN_SECONDS);
                emailSender.send("hello");
            }
        }, firstTime, DAY_HOURS_IN_SECONDS * 1000);
    }
}
