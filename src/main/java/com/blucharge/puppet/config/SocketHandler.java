package com.blucharge.puppet.config;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutionException;

@Component
@Slf4j
public class SocketHandler extends TextWebSocketHandler {
	
	List<WebSocketSession>sessions = new CopyOnWriteArrayList<>();


	public WebSocketSession createWebSocketConnection() throws ExecutionException, InterruptedException, IOException {
		StandardWebSocketClient client = new StandardWebSocketClient();
		WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
		headers.add("Sec-WebSocket-Protocol","ocpp1.6");
		WebSocketSession session = client.doHandshake(this,headers, URI.create("ws://ocpp.uat.blucgn.com/blucharge/connect/chargepoint0234")).get();
		session.sendMessage(new TextMessage("Hellp"));

		return session;
	}
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message)
			throws InterruptedException, IOException {
		log.info("Payload {}", message.getPayload());
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("Connection Established");
		sessions.add(session);
	}
}