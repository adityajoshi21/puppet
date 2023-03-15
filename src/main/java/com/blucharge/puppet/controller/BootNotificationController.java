package com.blucharge.puppet.controller;

import com.blucharge.puppet.config.SocketHandler;
import com.blucharge.puppet.dto.conf.BootNotificationConf;
import com.blucharge.puppet.dto.req.BootNotificationReq;
//import com.blucharge.puppet.config.SocketHandler.afterConnectionEstablished;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.WebSocketSession;
import com.blucharge.puppet.service.BootNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import static com.blucharge.puppet.config.SocketHandler.getSessionByChargeBoxId;


@Controller

public class BootNotificationController{





//    public BootNotificationConf response (BootNotificationReq req) throws InterruptedException {
//
//        return new BootNotificationConf();
//    }

}
