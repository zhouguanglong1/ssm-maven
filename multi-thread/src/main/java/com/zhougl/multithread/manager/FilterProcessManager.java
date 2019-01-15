package com.zhougl.multithread.manager;

import com.zhougl.multithread.process.FilterProcess;
import com.zhougl.multithread.word.TotalWords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhougl
 * 2018/11/1
 **/
@Service
public class FilterProcessManager {

    private List<FilterProcess> filterProcesses = new ArrayList<>(10);

    @Resource(name = "httpFilterProcess")
    private FilterProcess httpFilterProcess;

    @Resource(name = "numberFilterProcess")
    private FilterProcess numberFilterProcess;

    @Autowired
    private TotalWords totalWords;

    private FilterProcessManager addProcess(FilterProcess process){
        filterProcesses.add(process);
        return this;
    }

    @PostConstruct
    public void start(){
        this.addProcess(httpFilterProcess).addProcess(numberFilterProcess);
    }

    public void process(String msg){
        for (FilterProcess process : filterProcesses) {
            msg = process.process(msg);
        }
        totalWords.sum(msg.toCharArray().length);
    }
}
