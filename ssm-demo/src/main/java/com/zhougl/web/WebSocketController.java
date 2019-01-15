package com.zhougl.web;

import com.zhougl.bean.RequestMsg;
import com.zhougl.bean.ResponseMsg;
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
