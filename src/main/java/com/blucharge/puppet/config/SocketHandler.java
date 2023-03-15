package com.blucharge.puppet.config;

import com.blucharge.puppet.controller.BootNotificationController;
import com.blucharge.puppet.service.BootNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Component
@Slf4j
public class SocketHandler extends TextWebSocketHandler {
	
	private static final Map <String , WebSocketSession> sessions = new HashMap<>();
	private static final Map <String, String> sessionIdToChargeBoxIdMapping = new HashMap<>();

	@Autowired
	private BootNotificationService bootNotificationService;
	@PostConstruct
	public WebSocketSession createWebSocketConnection() throws ExecutionException, InterruptedException, IOException {

		StandardWebSocketClient client = new StandardWebSocketClient();
		WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
		headers.add("Sec-WebSocket-Protocol","ocpp1.6");
		String chargeBoxId = "chargepoint0234";
		WebSocketSession session = client.doHandshake(this,headers, URI.create("ws://ocpp.uat.blucgn.com/blucharge/connect/"+ chargeBoxId)).get();
		sessionIdToChargeBoxIdMapping.put(session.getId(), chargeBoxId);
		sessions.put(chargeBoxId, session);
		return session;
	}


	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) // chargeboxid
			throws  IOException {
		log.info("Payload {}", message.getPayload());

		// response listening for BootNotification
		//session.sendMessage(new TextMessage("status notification"));
		// response listening
		// heart-beat
		// response listening
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("Connection Established");
		String currentChargeBoxId = sessionIdToChargeBoxIdMapping.get(session.getId());

		session.sendMessage(new TextMessage(bootNotificationService.sendBootNotificationMessage(currentChargeBoxId).toString()));
		log.info("Message Sent");
	}

	public static WebSocketSession getSessionByChargeBoxId(String chargeBoxId){

		return sessions.get(chargeBoxId);
	}
}