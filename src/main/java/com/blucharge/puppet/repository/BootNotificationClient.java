package com.blucharge.puppet.repository;

import java.util.Scanner;

import com.blucharge.puppet.controller.handler.BootNotificationHandler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSessionHandler;

    /**
     * Stand alone Java WebSocketStompClient.
     *
     */
    public  class BootNotificationClient {

        private static String URL = "ws://ocpp.uat.blucgn.com/blucharge/connect/BSCN-GGN004";

        public static void main(String[] args) {
            WebSocketClient client = new StandardWebSocketClient();
            WebSocketStompClient bootNotificationStompClient = new WebSocketStompClient(client);

            bootNotificationStompClient.setMessageConverter(new MappingJackson2MessageConverter());

            StompSessionHandler sessionHandler = new BootNotificationHandler();
            bootNotificationStompClient.connect(URL, sessionHandler);

            new Scanner(System.in).nextLine(); // Don't close immediately.
        }
    }
