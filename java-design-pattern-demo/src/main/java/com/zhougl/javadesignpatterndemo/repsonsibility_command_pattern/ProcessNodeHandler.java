package com.zhougl.javadesignpatterndemo.repsonsibility_command_pattern;

import com.zhougl.javadesignpatterndemo.repsonsibility_command_pattern.bean.WorkflowActionParam;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author by zhougl
 * @classname ProcessNodeHandler
 * @desc TODO
 * @date 2019/6/4 16:00
 */
public class ProcessNodeHandler extends AbstractNodeHandler {

    @Autowired
    private NodeService nodeService;

    @Override
    protected void process(WorkflowActionParam param) {
        nodeService.process(param);
    }
}
