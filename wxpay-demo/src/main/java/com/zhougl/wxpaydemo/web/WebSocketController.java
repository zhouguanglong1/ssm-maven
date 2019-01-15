package com.zhougl.wxpaydemo.web;

import com.zhougl.wxpaydemo.bean.RequestMsg;
import com.zhougl.wxpaydemo.bean.ResponseMsg;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @author zhougl
 * 2018/9/8
 **/
@Controller
public class WebSocketController {

    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public ResponseMsg sayHello(RequestMsg message){
        System.out.println(message.getMessage());
        return new ResponseMsg("welcome," + message.getMessage() + " !");
    }
}
