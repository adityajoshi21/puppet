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

import static com.blucharge.puppet.constants.ApplicationConstants.*;

@Component
@Slf4j
public class SocketHandler extends TextWebSocketHandler {
	private static final Map <String , WebSocketSession> chargeBoxToSessionMapping = new HashMap<>();
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



	@PostConstruct
	public WebSocketSession createWebSocketConnection() throws ExecutionException, InterruptedException, IOException {
		StandardWebSocketClient client = new StandardWebSocketClient();
		WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
		headers.add("Sec-WebSocket-Protocol","ocpp1.6");
		System.out.println("1st");
		WebSocketSession session = client.doHandshake(this,headers, URI.create(STAGING_BASE_URL+ TEST_CHARGER)).get();
		return session;
	}



	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("Connection Established at: "+ session.getId());
		chargeBoxToSessionMapping.put(TEST_CHARGER,session);
		TextMessage bootNotificationMsg = new TextMessage(bootNotificationService.sendBootNotificationMessage(TEST_CHARGER));
		session.sendMessage(bootNotificationMsg);
		log.info(bootNotificationMsg.getPayload());

	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		TextMessage heartbeatMsg = new TextMessage(heartbeatService.sendHeartbeatMessage());
//		session.sendMessage(heartbeatMsg);
		TextMessage statusMsg = new TextMessage(statusNotificationService.sendStatusNotificationMessage());
		log.info(statusMsg.getPayload());
		session.sendMessage(statusMsg);
		TextMessage authoriseMsg = new TextMessage(authoriseService.sendAuthoriseMessage());
		session.sendMessage(authoriseMsg);
		TextMessage startTransactionMsg = new TextMessage(startTransactionService.sendStartTransactionMessage());
		session.sendMessage(startTransactionMsg);
		TextMessage stopTransactionMsg = new TextMessage(stopTransactionService.sendStopTransactionMessage());
		session.sendMessage(stopTransactionMsg);

	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		log.info("There was some error in Transport Layer!");
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		log.info("Connection has been closed!");
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
}