package com.zhougl.multithread.word;

import org.springframework.stereotype.Component;

/**
 * @author zhougl
 * 2018/11/1
 **/
@Component
public class TotalWords {
    private long sum = 0 ;
    public void sum(int count){
        sum += count;
    }
    public long total(){
        return sum;
    }
}
