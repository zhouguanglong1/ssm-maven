package com.zhougl.multithread.process;

/**
 * @author zhougl
 * 2018/11/1
 **/
public interface FilterProcess {
    /**
     * 处理文本
     * @param msg 文本
     * @return
     */
    String process(String msg);
}
