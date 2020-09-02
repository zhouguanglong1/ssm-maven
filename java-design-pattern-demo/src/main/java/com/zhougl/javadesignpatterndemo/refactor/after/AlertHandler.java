package com.zhougl.javadesignpatterndemo.refactor.after;

import com.zhougl.javadesignpatterndemo.refactor.origin.AlertRule;
import com.zhougl.javadesignpatterndemo.refactor.origin.Notification;

/**
 * @author by zhougl
 * @date 2019/12/9 15:01
 */
public abstract class AlertHandler {

    Notification notification;
    AlertRule alertRule;

    public AlertHandler(Notification notification,AlertRule alertRule) {
        this.notification = notification;
        this.alertRule = alertRule;
    }

    protected abstract void handle(TimeoutParams timeoutParams);
}
