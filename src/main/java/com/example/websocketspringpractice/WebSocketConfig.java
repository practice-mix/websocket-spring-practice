package com.example.websocketspringpractice;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  /**
   *  follow the convention that destinations for messages to be carried on to all subscribed clients via the pub-sub model should be prefixed with topic.
   *  On the other hand, destinations for private messages are typically prefixed by queue
   */
//  @Override
  public void configureMessageBroker1(MessageBrokerRegistry config) {
    config.enableSimpleBroker("/topic","/queue");//enable a simple memory-based message broker
    config.setApplicationDestinationPrefixes("/app");//Defines the prefix app that is used to filter destinations handled by methods annotated with @MessageMapping which you will implement in a controller.
  }

  /**
   *  rabbitmq broker
   *  <p>
   *      {@code
   *      rabbitmq-plugins enable rabbitmq_stomp
   *      }
   *  </p>
   */
  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {

    config
            .setApplicationDestinationPrefixes("/app")
            .enableStompBrokerRelay("/topic")
            .setRelayHost("localhost")
            .setRelayPort(61613)
            .setClientLogin("guest")
            .setClientPasscode("guest");

  }

  /**
   * The SockJS client will attempt to connect to /gs-guide-websocket and use the best available transport (websocket, xhr-streaming, xhr-polling, and so on).
   *
   * <p>
   *     SockJS transports fall in three general categories: WebSockets, HTTP Streaming, and HTTP Long Polling. The communication starts with SockJS sending GET /info to obtain basic information from the server. Basing on the response, SockJS decides on the transport to be used. The first choice are WebSockets. If they are not supported, then, if possible, Streaming is used. If this option is also not possible, then Polling is chosen as a transport method.
   * </p>
   */
  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/gs-guide-websocket").withSockJS();

  }

}
