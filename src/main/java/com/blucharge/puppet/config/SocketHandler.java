package com.blucharge.puppet.config;

import com.blucharge.puppet.service.BootNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
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
public class SocketHandler extends TextWebSocketHandler{
	private static final Map <String , WebSocketSession> chargeBoxToSessionMapping = new HashMap<>();
	public static final  Map <WebSocketSession, String> sessionToChargeBoxIdMapping = new HashMap<>();
	public static final String CHARGEBOX_ID_KEY = "chargepoint0234";
	@Autowired
	private BootNotificationService bootNotificationService;
	@PostConstruct
	public WebSocketSession createWebSocketConnection() throws ExecutionException, InterruptedException, IOException {
		StandardWebSocketClient client = new StandardWebSocketClient();
		WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
		headers.add("Sec-WebSocket-Protocol","ocpp1.6");
		System.out.println("1st");
		WebSocketSession session = client.doHandshake(this,headers, URI.create("ws://ocpp.uat.blucgn.com/blucharge/connect/"+ CHARGEBOX_ID_KEY)).get();
		System.out.println("4th");
		return session;
	}


	public String getChargeBoxId(WebSocketSession session){

		return (String) session.getAttributes().get(CHARGEBOX_ID_KEY);
	}


	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) // chargeboxid
			throws  IOException {
				System.out.println("3rd");
				System.out.println(message.toString());
				String currChargeBoxId =sessionToChargeBoxIdMapping.get(session);
				WebSocketSession currSession = chargeBoxToSessionMapping.get(currChargeBoxId);
				log.info("Current session: " + currSession +" with ChargeBoxId: " + currChargeBoxId);
				currSession.sendMessage(message);
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("Connection Established at: "+ session.getId());
		chargeBoxToSessionMapping.put(CHARGEBOX_ID_KEY,session);
		sessionToChargeBoxIdMapping.put(session, CHARGEBOX_ID_KEY);
		String currChargeBoxId =sessionToChargeBoxIdMapping.get(session);
		System.out.println("2nd");
		System.out.println(currChargeBoxId);
		TextMessage msg = new TextMessage(bootNotificationService.sendBootNotificationMessage(currChargeBoxId));
		System.out.println(msg);
		handleMessage(session, msg);
	}

}