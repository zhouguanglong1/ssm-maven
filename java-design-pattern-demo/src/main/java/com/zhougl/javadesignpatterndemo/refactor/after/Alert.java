package com.zhougl.javadesignpatterndemo.refactor.after;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by zhougl
 * @date 2019/12/9 15:10
 */
public class Alert {

    private TimeoutParams timeoutParams;

    public Alert(TimeoutParams timeoutParams) {
        this.timeoutParams = timeoutParams;
    }

    private List<AlertHandler> list = new ArrayList<>();

    void add(AlertHandler alertHandler){
        list.add(alertHandler);
    }

    void execute(){
        for (AlertHandler alertHandler : list) {
            alertHandler.handle(timeoutParams);
        }
    }
}
