package com.zhougl.javadesignpatterndemo.refactor.after;

import com.zhougl.javadesignpatterndemo.refactor.origin.AlertRule;
import com.zhougl.javadesignpatterndemo.refactor.origin.Notification;

/**
 * @author by zhougl
 * @date 2019/12/9 15:05
 */
public class TpsAlertHandler extends AlertHandler {

    public TpsAlertHandler(Notification notification, AlertRule alertRule) {
        super(notification, alertRule);
    }

    @Override
    public void handle(TimeoutParams timeoutParams) {
        long tps = timeoutParams.getRequestCount()/timeoutParams.getDurationOfSeconds();

        if(tps > alertRule.getMatchRule(timeoutParams.getApi()).getMaxTps()){
            notification.notify("rule a");
        }
    }
}
