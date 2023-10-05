package com.blucharge.puppet.config;

import com.blucharge.puppet.dto.Message;
import com.blucharge.puppet.dto.enums.MessageType;
import com.blucharge.puppet.dto.req.RemoteStartTransactionReq;
import com.blucharge.puppet.dto.req.RemoteStopTransactionReq;
import com.blucharge.puppet.service.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		log.info("Received from Server : {} ",message.getPayload());
		Message  messageObj  = parseMessage((String) message.getPayload());
		if( !Objects.isNull(messageObj) && MessageType.CALL.equals(messageObj.getType()) && "RemoteStartTransaction".equals(messageObj.getMessageName())){
			//After we've received a request from server for Remotely Starting a Txn


			TextMessage remoteStartTransactionResponse = new TextMessage(remoteTransactionService.remoteStartTransaction(messageObj.getMessageId()));
			log.info("Sending response for Remote Start Transaction {}", remoteStartTransactionResponse.getPayload());
			session.sendMessage(remoteStartTransactionResponse);

			Gson gson = new Gson();
			RemoteStartTransactionReq req = gson.fromJson(messageObj.getData(), RemoteStartTransactionReq.class);
			TextMessage startTransactionMsg = new TextMessage(startTransactionService.sendStartTransactionMessageFromIdTag(req.getIdTag()));
			log.info(startTransactionMsg.getPayload());
			session.sendMessage(startTransactionMsg);

		}
		 if( !Objects.isNull(messageObj) && MessageType.CALL.equals(messageObj.getType()) && "RemoteStopTransaction".equals(messageObj.getMessageName())){
			//After we've received a request from server for Remotely Starting a Txn

			TextMessage remoteStopTransactionResponse = new TextMessage(remoteTransactionService.remoteStopTransaction(messageObj.getMessageId()));
			log.info(remoteStopTransactionResponse.getPayload());
			session.sendMessage(remoteStopTransactionResponse);

			Gson gson = new Gson();
			RemoteStopTransactionReq req = gson.fromJson(messageObj.getData(), RemoteStopTransactionReq.class);

			TextMessage stopTransactionMsg = new TextMessage(stopTransactionService.sendStopTransactionMessageForTransactionId(req.getTransactionId()));
			log.info(stopTransactionMsg.getPayload());
			session.sendMessage(stopTransactionMsg);
		}
	}

	private Message parseMessage(String payload) throws Exception {
		String PATTERN = "\\[(?<messageType>\\d),(?<message>.*)\\]";
		Pattern p = Pattern.compile(PATTERN);
		Matcher m = p.matcher(payload);
		if(!m.find())
			throw new Exception("Error");

		String messageType =  m.group("messageType");

		String message =  m.group("message");
		log.info(" Message Type : {}",messageType);
		log.info(" Message  : {}",message);



		if("2".equals(messageType)) {
			p = Pattern.compile("(?<messageId>[^,]*),(?<messageName>[^,]*),(?<data>.*)");
			m = p.matcher(message);
			if(!m.find())
				throw new Exception("Error");
			String messageId=  m.group("messageId");
			String messageName = m.group("messageName");
			String data = m.group("data");
			log.info(" Message Id : {}",messageId);
			log.info(" Message Name  : {}", messageName);
			log.info(" Data  : {}",data);

			return Message.builder().type(MessageType.CALL).messageId(messageId.substring(1,messageId.length()-1)).messageName(messageName.substring(1,messageName.length()-1)).data(data).build();
		} else if("3".equals(messageType)){
			p = Pattern.compile("(?<messageId>[^,]*),(?<data>.*)");
			m = p.matcher(message);
			if(!m.find())
				throw new Exception("Error");
			String messageId=  m.group("messageId");
			String data = m.group("data");
			log.info(" Message Id : {}",messageId);
			log.info(" Data  : {}",data);

			return Message.builder().type(MessageType.CALL_RESULT).messageId(messageId.substring(1,messageId.length()-1)).data(data).build();
		}
		return null;
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