package com.zhougl.javadesignpatterndemo.refactor.viewer;

import com.zhougl.javadesignpatterndemo.refactor.EmailSender;
import com.zhougl.javadesignpatterndemo.refactor.bean.RequestStat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhougl
 * @version v1.0.0
 * @since 2020/2/1 20:45
 */
public class EmailViewer implements StatViewer {

    private EmailSender emailSender;
    private List<String> toAddresses = new ArrayList<>();

    public EmailViewer() {
        this.emailSender = new EmailSender();
    }

    public void addToAddress(String address) {
        toAddresses.add(address);
    }

    @Override
    public void output(Map<String, RequestStat> requestStats, long startTimeInMillis, long endTimeInMills) {
        // format the requestStats to HTML style.
        // send it to email toAddresses.
    }
}
