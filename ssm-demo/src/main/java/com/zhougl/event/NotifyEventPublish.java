package com.zhougl.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhougl
 * 2019/3/23
 **/
@Controller
@RequestMapping("/test")
public class NotifyEventPublish {
    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("haha")
    public void sendNotify(long userId,long followId){
        NotifyEvent notifyEvent = new NotifyEvent("notifyEvent");
        notifyEvent.setFromUserId(userId);
        notifyEvent.setToUserId(followId);
        notifyEvent.setEvent(1);
        applicationContext.publishEvent(notifyEvent);
    }

    public static void main(String[] args){
        NotifyEventPublish publish = new NotifyEventPublish();
        publish.sendNotify(2131L,5445L);
    }
}
