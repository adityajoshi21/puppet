
package com.blucharge.puppet.client.messageHandler;

import com.blucharge.puppet.dto.req.BootNotificationReq;
import org.apache.logging.log4j.LogManager;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.messaging.simp.stomp.StompSession;
import java.lang.reflect.Type;

public class BootNotificationHandler extends StompSessionHandlerAdapter {
    private Logger logger = LogManager.getLogger(BootNotificationHandler.class); //logger for our class

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        logger.info("New session established on : " + session.getSessionId());
        session.subscribe("/topic/messages", this);
        logger.info("Subscribed to /topic/messages");
        session.send("/app/chat", getBootNotiMessage());
        logger.info("Message sent to webSocket server");
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        logger.error("Got an exception", exception);
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {

        return BootNotificationReq.class;
    }

    @Override
    public  void handleFrame(StompHeaders headers, Object payload) {
        BootNotificationReq request = (BootNotificationReq) payload;
        logger.info("Received : " + request);
    }

    private  BootNotificationReq getBootNotiMessage() {
        BootNotificationReq req = new BootNotificationReq();
        req.setChargeBoxSerialNumber("012345");
        req.setChargePointModel("Statiq fast");
        req.setChargePointVendor("Statiq");
        return req;
    }
}


