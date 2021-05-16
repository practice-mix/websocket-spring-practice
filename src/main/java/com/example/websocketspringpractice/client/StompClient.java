package com.example.websocketspringpractice.client;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.RestTemplateXhrTransport;
import org.springframework.web.socket.sockjs.client.SockJsClient;

import java.util.Collections;
import java.util.concurrent.ExecutionException;

public class StompClient {
    private static final String URL = "ws://localhost:8080/gs-guide-websocket";

    private final StompSession stompSession;

    private StompClient()  {

        WebSocketClient client = new SockJsClient(Collections.singletonList(new RestTemplateXhrTransport()));

        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        MyStompSessionHandler sessionHandler = new MyStompSessionHandler();

        try {
            stompSession = stompClient.connect(URL, sessionHandler).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static StompClient getInstance() {
        return Holder.stompClient;
    }

    public static StompSession getStompSession() {
      return   getInstance().stompSession;
    }


    private static class Holder {
        private static final StompClient stompClient = new StompClient();

    }


}
