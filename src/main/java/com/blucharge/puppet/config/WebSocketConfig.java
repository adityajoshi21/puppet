package com.blucharge.puppet.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker( final MessageBrokerRegistry registry){  //configureMessageBroker is used to configure the message broker
        registry.enableSimpleBroker("/topic"); //Enable a simple message broker and configure one or more prefixes to filter destinations targeting the broker (e.g. destinations prefixed with "/receive").
        registry.setApplicationDestinationPrefixes("/app"); //endpoint /app acts as a filter destination targeting application annotated methods via @MessageMapping
    }

    @Override
    public void registerStompEndpoints(final StompEndpointRegistry registry) { //path were websocket are created
        registry.addEndpoint("/stomp-endpoint");    //end point where client will connect to the backend
        registry.addEndpoint("/stomp-endpoint").withSockJS();
    }
}
