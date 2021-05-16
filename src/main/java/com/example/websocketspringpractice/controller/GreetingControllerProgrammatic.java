package com.example.websocketspringpractice.controller;

import com.example.websocketspringpractice.model.Greeting;
import com.example.websocketspringpractice.model.HelloMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;

@Controller
public class GreetingControllerProgrammatic {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/hello2")// client should access /app/hello2
    public void accept(HelloMessage message) throws Exception {
        Greeting greeting = new Greeting("Hello2, " + HtmlUtils.htmlEscape(message.getName()) + "!");
        simpMessagingTemplate.convertAndSend("/topic/greetings", greeting);
    }

    @MessageMapping("/greetings2")// client should access /app/greetings
    public void accept2(HelloMessage message, Principal user) throws Exception {
        Greeting greeting = new Greeting("Hello2, " + HtmlUtils.htmlEscape(message.getName()) + "!");
        simpMessagingTemplate.convertAndSendToUser(user.getName(),"/topic/greetings", greeting);
    }

}
