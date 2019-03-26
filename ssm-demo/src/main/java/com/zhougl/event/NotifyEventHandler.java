package com.zhougl.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author zhougl
 * 2019/3/23
 **/
@Component
public class NotifyEventHandler implements ApplicationListener<NotifyEvent> {

    // 此处可引入具体的service，在onApplicationEvent方法中调用具体的逻辑


    @Override
    public void onApplicationEvent(NotifyEvent event) {
        String result;
        switch (event.getEvent()){
            case 1:
                //do something
                result = "哈哈哈";
                System.out.println("事件1");
                break;
            case 2:
                //do something
                result = "嘻嘻嘻";
                System.out.println("事件2");
                break;
            default:
                result = "呵呵呵";
                System.out.println("默认事件");
        }
        System.out.println("result = " + result);
    }
}
