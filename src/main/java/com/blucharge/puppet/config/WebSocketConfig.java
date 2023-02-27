package com.blucharge.puppet.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(final StompEndpointRegistry registry) { //path were websocket are created
        registry.addEndpoint("/chat");    //end point where CP/client will connect to the backend
        registry.addEndpoint("/chat").withSockJS();
        //Here what happens
    }
    @Override
    public void configureMessageBroker(final MessageBrokerRegistry config){  //configureMessageBroker is used to configure the message broker
        config.enableSimpleBroker("/topic"); //(cs)Enable a simple message broker and configure one or more prefixes to filter destinations targeting the broker (e.g. destinations prefixed with "/receive").
        config.setApplicationDestinationPrefixes("/app");
    }

}