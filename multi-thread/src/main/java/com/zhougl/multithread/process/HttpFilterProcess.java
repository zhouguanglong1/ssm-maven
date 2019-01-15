package com.zhougl.multithread.process;

import org.springframework.stereotype.Component;

/**
 * @author zhougl
 * 2018/11/1
 **/
@Component(value = "httpFilterProcess")
public class HttpFilterProcess implements FilterProcess {
    @Override
    public String process(String msg) {
        msg = msg.replaceAll("^((https|http|ftp|rtsp|mms)?:\\/\\/)[^\\s]+","");
        return msg;
    }
}
