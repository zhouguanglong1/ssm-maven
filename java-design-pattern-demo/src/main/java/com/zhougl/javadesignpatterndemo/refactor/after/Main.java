package com.zhougl.javadesignpatterndemo.refactor.after;

import com.zhougl.javadesignpatterndemo.refactor.origin.AlertRule;

/**
 * @author by zhougl
 * @date 2019/12/9 15:16
 */
public class Main {
    public static void main(String[] args) {
        TimeoutParams timeoutParams = new TimeoutParams();
        timeoutParams.setApi("");
        timeoutParams.setRequestCount(1L);
        timeoutParams.setErrorCount(0L);
        timeoutParams.setDurationOfSeconds(1L);
        timeoutParams.setTimeoutCount(0L);


        Alert alert = new Alert(timeoutParams);
        alert.add(new TpsAlertHandler(System.out::println, rule -> new AlertRule.Rule(1L)));

        alert.execute();
    }
}
