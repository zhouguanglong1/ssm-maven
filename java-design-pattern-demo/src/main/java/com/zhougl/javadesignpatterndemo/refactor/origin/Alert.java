package com.zhougl.javadesignpatterndemo.refactor.origin;

/**
 * @author by zhougl
 * @date 2019/12/9 14:45
 */
public class Alert {
    private AlertRule rule;
    private Notification notification;

    public Alert(AlertRule rule, Notification notification) {
        this.rule = rule;
        this.notification = notification;
    }

    public void check(String api,long requestCount,long errorCount,long durationOfSeconds,long timeoutCount){
        long tps = requestCount/durationOfSeconds;

        if(tps > rule.getMatchRule(api).getMaxTps()){
            notification.notify("rule a");
        }

        if(errorCount > rule.getMatchRule(api).getMaxTps()){
            notification.notify("rule b");
        }

        // 改动需求，每秒钟超时请求数大于最大的tps时，也要触发告警
        long timeoutTps = timeoutCount / durationOfSeconds;
        if(timeoutTps > rule.getMatchRule(api).getMaxTps()){
            notification.notify("rule c");
        }
    }
}
