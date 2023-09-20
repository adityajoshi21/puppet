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
	@Autowired
	private MeterValueService meterValueService;
	@Autowired
	private RemoteTransactionService remoteTransactionService;



	@PostConstruct
	public WebSocketSession createWebSocketConnection() throws ExecutionException, InterruptedException {
		StandardWebSocketClient client = new StandardWebSocketClient();
		WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
		headers.add("Sec-WebSocket-Protocol",OCPP_VERSION);
		WebSocketSession session = client.doHandshake(this,headers, URI.create(LOCAL_BASE_URL+ TEST_CHARGER)).get();
		return session;
	}



	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("Connection Established at: "+ session.getId());
		chargeBoxToSessionMapping.put(TEST_CHARGER,session);

		TextMessage bootNotificationMsg = new TextMessage(bootNotificationService.sendBootNotificationMessage(TEST_CHARGER));
		session.sendMessage(bootNotificationMsg);
		log.info(bootNotificationMsg.getPayload());

		TextMessage statusMsg = new TextMessage(statusNotificationService.sendStatusNotificationMessage());
		log.info(statusMsg.getPayload());
		session.sendMessage(statusMsg);

		TextMessage heartbeatMsg = new TextMessage(heartbeatService.sendHeartbeatMessage());
		session.sendMessage(heartbeatMsg);
		log.info(heartbeatMsg.getPayload());

		TextMessage authoriseMsg = new TextMessage(authoriseService.sendAuthoriseMessage());
		log.info(authoriseMsg.getPayload());
		session.sendMessage(authoriseMsg);

		TextMessage startTransactionMsg = new TextMessage(startTransactionService.sendStartTransactionMessage());
		log.info(startTransactionMsg.getPayload());
		session.sendMessage(startTransactionMsg);

		TextMessage meterValueMsg = new TextMessage((meterValueService.sendMeterValueMessage()));
		log.info(meterValueMsg.getPayload());
		session.sendMessage(meterValueMsg);

		TextMessage stopTransactionMsg = new TextMessage(stopTransactionService.sendStopTransactionMessage());
		log.info(stopTransactionMsg.getPayload());
		session.sendMessage(stopTransactionMsg);
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

		String receivedMessage = (String) message.getPayload();
		if(receivedMessage.contains("2") && receivedMessage.contains("RemoteStartTransaction")){
			//We've received a request from server for Remotely Starting a Txn
			remoteTransactionService.remoteStartTransaction();
			startTransactionService.sendStartTransactionMessage();

			meterValueService.sendMeterValueMessage();

		}
		if(receivedMessage.contains("2") && receivedMessage.contains("RemoteStopTransaction")){
			//We've received a request from server for Remotely Starting a Txn
			meterValueService.sendMeterValueMessage();
			remoteTransactionService.remoteStopTransaction();
			stopTransactionService.sendStopTransactionMessage();

		}
		if (receivedMessage.contains("3")) {
			// Process the server's response to the client's request
			log.info("Received server response: " + receivedMessage);
		} else {
			log.info("Error in response from Server");
		}
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