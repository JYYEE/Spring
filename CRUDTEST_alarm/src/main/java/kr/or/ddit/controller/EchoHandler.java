package kr.or.ddit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class EchoHandler extends TextWebSocketHandler {

	// 로그인한 세션을 담을 리스트
	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();

	// 클라이언트가 연결되었을 때 실행
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("afterConnectionEstablished : " + session);
		// 리스트에 로그인한 세션 담기
		sessionList.add(session);
	}

	// 클라이언트가 웹소켓 서버로 메시지를 전송했을 때 실행
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String msg = message.getPayload();
		TextMessage textMessage = new TextMessage(msg);
		// 로그인한 사용자에게 접속 메시지 전달
		session.sendMessage(textMessage);
	}

	// 클라이언트 연결을 끊었을 때 실행
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println("afterConnectionClosed : " + session);
		sessionList.remove(session);
	}
}
