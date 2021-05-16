package com.example.websocketspringpractice.controller;

import com.example.websocketspringpractice.client.StompClient;
import com.example.websocketspringpractice.model.HelloMessage;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingControllerJavaClient {

    @PutMapping("/send")
    public void send(@RequestParam("name") String name) {
        StompClient.getStompSession().send("/app/hello", new HelloMessage(name));
    }


}
