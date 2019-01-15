package com.zhougl.multithread.process;

import org.springframework.stereotype.Component;

/**
 * @author zhougl
 * 2018/11/1
 **/
@Component(value = "numberFilterProcess")
public class WrapFilterProcess implements FilterProcess {
    @Override
    public String process(String msg) {
        msg = msg.replaceAll("\\s*", "");
        return msg ;
    }
}
