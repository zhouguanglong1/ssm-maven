package com.zhougl.javadesignpatterndemo.repsonsibility_command_pattern;

import com.zhougl.javadesignpatterndemo.repsonsibility_command_pattern.bean.WorkflowActionParam;

/**
 * @author by zhougl
 * @classname NodeService
 * @desc TODO
 * @date 2019/6/4 16:01
 */
public class NodeService {
    public void process(WorkflowActionParam param){
        System.out.println(param);
    }
}
