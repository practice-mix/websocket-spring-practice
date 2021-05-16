package com.example.websocketspringpractice.controller;

import com.example.websocketspringpractice.model.Greeting;
import com.example.websocketspringpractice.model.HelloMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;

@Controller
public class GreetingControllerDeclarative {


  @MessageMapping("/hello")// client should access /app/hello
  @SendTo("/topic/greetings")
  public Greeting greeting(HelloMessage message) throws Exception {
    return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
  }

  @MessageMapping("/hello3")
  @SendToUser("/queue/greetings")
  public Greeting reply(@Payload HelloMessage message,
                        Principal user) {
    return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
  }

}
