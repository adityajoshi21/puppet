package com.blucharge.puppet.config;

import com.blucharge.puppet.service.*;
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
//	public static final  Map <WebSocketSession, String> sessionToChargeBoxIdMapping = new HashMap<>();
	public static final String CHARGEBOX_ID_KEY = "chargepoint0234";
	@Autowired
	private AuthoriseService authoriseService;
	@Autowired
	private BootNotificationService bootNotificationService;
	@Autowired
	private HeartbeatService heartbeatService;
	@Autowired
	private StatusNotificationService statusNotificationService;

	@Autowired
	private StartTransactionService startTransactionService;
	@Autowired
	private StopTransactionService stopTransactionService;

	@Autowired
	private RemoteStartTxnService remoteStartTxnService;


	@PostConstruct
	public WebSocketSession createWebSocketConnection() throws ExecutionException, InterruptedException, IOException {
		StandardWebSocketClient client = new StandardWebSocketClient();
		WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
		headers.add("Sec-WebSocket-Protocol","ocpp1.6");
		System.out.println("1st");
		WebSocketSession session = client.doHandshake(this,headers, URI.create("ws://localhost:8082/blucharge/connect/"+ CHARGEBOX_ID_KEY)).get();
		return session;
	}


	public String getChargeBoxId(WebSocketSession session){

		return (String) session.getAttributes().get(CHARGEBOX_ID_KEY);
	}


	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) // chargeboxid
			throws  IOException {
				session.sendMessage(message);
				//TextMessage heartBeatMsg = new TextMessage(heartbeatService.sendHeartbeatMessage());
				TextMessage statusMsg = new TextMessage(statusNotificationService.sendStatusNotificationMessage());
				TextMessage authoriseMsg = new TextMessage(authoriseService.sendAuthoriseMessage());
				TextMessage startTransactionMsg = new TextMessage(startTransactionService.sendStartTransactionMessage());
				TextMessage stopTransactionMsg = new TextMessage(stopTransactionService.sendStopTransactionMessage());
				//TextMessage remoteStartMsg = new TextMessage(remoteStartTxnService.sendRemoteStartMessage());
				session.sendMessage(statusMsg);
				session.sendMessage(authoriseMsg);
				session.sendMessage(startTransactionMsg);
				session.sendMessage(stopTransactionMsg);

	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("Connection Established at: "+ session.getId());
		chargeBoxToSessionMapping.put(CHARGEBOX_ID_KEY,session);
		TextMessage msg = new TextMessage(bootNotificationService.sendBootNotificationMessage(CHARGEBOX_ID_KEY));
		System.out.println(msg.getPayload());
		handleTextMessage(session, msg);
	}
}