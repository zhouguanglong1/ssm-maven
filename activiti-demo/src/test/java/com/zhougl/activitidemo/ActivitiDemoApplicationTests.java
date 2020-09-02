package com.zhougl.activitidemo;

import static org.junit.Assert.assertNotNull;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActivitiDemoApplicationTests {

    @Test
    public void contextLoads() {
    	ProcessEngine pe = ProcessEngines.getDefaultProcessEngine();
    	assertNotNull("not null",pe);
    }

}
