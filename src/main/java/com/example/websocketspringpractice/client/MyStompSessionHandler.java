package com.example.websocketspringpractice.client;

import com.example.websocketspringpractice.model.HelloMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

public class MyStompSessionHandler extends StompSessionHandlerAdapter {

    Logger logger = LoggerFactory.getLogger(MyStompSessionHandler.class);

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        session.subscribe("/topic/greetings", this);
        logger.info("connected!"  );
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        HelloMessage msg = (HelloMessage) payload;
        logger.info("Received : " + msg.getName() );
    }
}
