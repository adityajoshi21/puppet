package com.blucharge.puppet.repository;

import java.util.Scanner;

import com.blucharge.puppet.controller.handler.BootNotificationHandler;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

public class BootNotificationClient {



    /**
     * Stand alone Java WebSocketStompClient.
     *
     */
    public class StompClient {

        private static   String  URL = "ws://ocpp.uat.blucgn.com/blucharge/connect/BSCN-GGN004";

        public static void main(String[] args) {
            WebSocketClient client = new StandardWebSocketClient();
            WebSocketStompClient stompClient = new WebSocketStompClient(client);

            stompClient.setMessageConverter(new MappingJackson2MessageConverter());

            StompSessionHandler sessionHandler =  new BootNotificationHandler();
            stompClient.connect(URL, sessionHandler);

            new Scanner(System.in).nextLine(); // Don't close immediately.
        }
    }
}
