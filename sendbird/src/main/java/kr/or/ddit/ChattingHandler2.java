package kr.or.ddit;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import kr.or.ddit.service.MemberService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MessageVO;

public class ChattingHandler2 extends TextWebSocketHandler {

	@Inject
	private MemberService service;
	
	// === 웹소켓서버에 연결한 클라이언트 사용자들을 저장하는 리스트 === //
	private Map<String, List<WebSocketSession>> chatRooms = new HashMap<>();

	// init-method
	public void init() throws Exception {

	}

	// === 클라이언트가 웹소켓서버에 연결했을때의 작업 처리하기 ===
	/*
	 * afterConnectionEstablished(WebSocketSession wsession) 메소드는 클라이언트가 웹소켓서버에 연결이
	 * 되어지면 자동으로 실행되는 메소드로서 WebSocket 연결이 열리고 사용이 준비될 때 호출되어지는(실행되어지는) 메소드이다.
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession wsession) throws Exception {
		// 웹소켓서버에 접속한 클라이언트의 IP Address 얻어오기
		/*
		 * IPv4 스택을 선호하도록 설정하는 것은 네트워크 호환성 및 연결 문제를 해결하기 위한 일반적인 방법 Run --> Run
		 * Configuration --> Arguments 탭 --> VM arguments 속에 맨 뒤에 --> 한칸 띄우고
		 * -Djava.net.preferIPv4Stack=true 을 추가한다.
		 */
		
		Map<String, Object> a = wsession.getAttributes();
		String roomNo = a.get("no").toString();
		
		if (!chatRooms.containsKey(roomNo)) {
		    chatRooms.put(roomNo, new ArrayList<>());
		}

		List<WebSocketSession> users = chatRooms.get(roomNo);
		users.add(wsession);
		chatRooms.put(roomNo, users);
		

		// 접속자 명단

		String connectingUserName = "「";
		// "loginuser" 은 HttpSession에 저장된 키 값으로 로그인 되어진 사용자이다.
		for (WebSocketSession webSocketSession : chatRooms.get(roomNo)) {
			Map<String, Object> map = webSocketSession.getAttributes();
			MemberVO user = (MemberVO) map.get("user");
			connectingUserName += user.getMem_name() + " ";
		}

		// end of for--------------------

		connectingUserName += "」";

		for (WebSocketSession webSocketSession : chatRooms.get(roomNo)) {
			webSocketSession.sendMessage(new TextMessage(connectingUserName));
		}
	}

	// === 클라이언트가 웹소켓 서버로 메시지를 보냈을때의 Send 이벤트를 처리하기 ===
	/*
	 * handleTextMessage(WebSocketSession wsession, TextMessage message) 메소드는 클라이언트가
	 * 웹소켓서버로 메시지를 전송했을 때 자동으로 호출되는(실행되는) 메소드이다. 첫번째 파라미터 WebSocketSession 은 메시지를 보낸
	 * 클라이언트임. 두번째 파라미터 TextMessage 은 메시지의 내용임.
	 */
	@Override
	protected void handleTextMessage(WebSocketSession wsession, TextMessage message) throws Exception {

		// >>> 파라미터 WebSocketSession wsession은 웹소켓서버에 접속한 클라이언트임. <<<
		// >>> 파라미터 TextMessage message 은 클라이언트 사용자가 웹소켓서버로 보낸 웹소켓 메시지임. <<<

		// Spring에서 WebSocket 사용시 먼저 HttpSession에 저장된 값들을 읽어와서 사용하기
		/*
		 * 먼저 /webapp/WEB-INF/spring/config/websocketContext.xml 파일에서 websocket:handlers
		 * 태그안에 websocket:handshake-interceptors에 HttpSessionHandshakeInterceptor를 추가해주면
		 * WebSocketHandler 클래스를 사용하기 전에, 먼저 HttpSession에 저장되어진 값들을 읽어 들여,
		 * WebSocketHandler 클래스에서 사용할 수 있도록 처리해준다.
		 */
		Map<String, Object> a = wsession.getAttributes();
		String roomNo = a.get("no").toString();
		
		Map<String, Object> map = wsession.getAttributes();
		MemberVO loginuser = (MemberVO) map.get("user");
		System.out.println(roomNo);
		System.out.println(loginuser.getMem_name());

		MessageVO messagevo = MessageVO.converMessage(message.getPayload());
		/*
		 * 파라미터 message 는 클라이언트 사용자가 웹소켓서버로 보낸 웹소켓 메시지임 message.getPayload() 은 클라이언트
		 * 사용자가 보낸 웹소켓 메시지를 String 타입으로 바꾸어주는 것이다.
		 * /Board/src/main/webapp/WEB-INF/views/tiles1/chatting/multichat.jsp 파일에서
		 * 클라이언트가 보내준 메시지는 JSON 형태를 뛴 문자열(String) 이므로 이 문자열을 Gson을 사용하여 MessageVO 형태의
		 * 객체로 변환시켜서 가져온다.
		 */

		Date now = new Date();
		String currentTime = String.format("%tp %tl:%tM", now, now, now);
		// %tp 오전, 오후를 출력
		// %tl 시간을 1~12 으로 출력
		// %tM 분을 00~59 으로 출력

		for (WebSocketSession webSocketSession : chatRooms.get(roomNo)){
			if ("all".equals(messagevo.getType())) {
				// 나를 제외한 모두에게 보내기
				if (!wsession.getId().equals(webSocketSession.getId())) {
					// wsession 은 메시지를 보낸 클라이언트임.
					// webSocketSession 은 웹소켓서버에 연결된 모든 클라이언트중 하나임.
					// wsession.getId() 와 webSocketSession.getId() 는 자동증가되는 고유한 숫자로 나옴
					// wsession.getRemoteAddress().getAddress().getHostAddress() => ip주소
					webSocketSession.sendMessage(new TextMessage("<span>" + loginuser.getMem_no()
							+ "</span>&nbsp;[<span style='font-weight:bold; cursor:pointer;' class='loginuserName'>"
							+ loginuser.getMem_name()
							+ "</span>]<br><div style='background-color: white; display: inline-block; max-width: 60%; padding: 7px; border-radius: 15%; word-break: break-all;'>"
							+ messagevo.getMessage()
							+ "</div> <div style='display: inline-block; padding: 20px 0 0 5px; font-size: 7pt;'>"
							+ currentTime + "</div> <div>&nbsp;</div>"));
				}
			} else {
				// 채팅할 대상이 "전체"가 아닌 특정대상(지금은 귓속말대상 IP address 임) 일 경우
//				String hostAddress = webSocketSession.getRemoteAddress().getAddress().getHostAddress();
				Map<String, Object> map1 = webSocketSession.getAttributes();
				MemberVO user = (MemberVO) map1.get("user");
				String to = user.getMem_no();
				// webSocketSession 은 웹소켓서버에 연결한 모든 클라이언트중 하나이며, 그 클라이언트의 IP address를 알아오는 것임.

				if (messagevo.getTo().equals(to)) {
					// messageVO.getTo() 는 클라이언트가 보내온 귓속말대상 IP address 임.
					webSocketSession.sendMessage(new TextMessage("<span> 귓속말" + loginuser.getMem_no()
							+ "</span>&nbsp;[<span style='font-weight:bold; cursor:pointer;' class='loginuserName'>"
							+ loginuser.getMem_name()
							+ "</span>]<br><div style='background-color: white; display: inline-block; max-width: 60%; padding: 7px; border-radius: 15%; word-break: break-all; color: red;'>"
							+ messagevo.getMessage()
							+ "</div> <div style='display: inline-block; padding: 20px 0 0 5px; font-size: 7pt;'>"
							+ currentTime + "</div> <div>&nbsp;</div>"));
					/* word-break: break-all; 은 공백없이 영어로만 되어질 경우 해당구역을 빠져나가므로 이것을 막기위해서 사용한다. */
					break; // 지금의 특정대상(지금은 귓속말대상 IP address 임)은 1개이므로
							// 특정대상(지금은 귓속말대상 IP address 임)에게만 메시지를 보내고 break;를 한다.
				}

			}
		}
	}

	// === 클라이언트가 웹소켓서버와의 연결을 끊을때 작업 처리하기 ===
	/*
	 * afterConnectionClosed(WebSocketSession session, CloseStatus status) 메소드는
	 * 클라이언트가 연결을 끊었을 때 즉, WebSocket 연결이 닫혔을 때(채팅페이지가 닫히거나 채팅페이지에서 다른 페이지로 이동되는 경우)
	 * 자동으로 호출되어지는(실행되어지는) 메소드이다.
	 */

	@Override
	public void afterConnectionClosed(WebSocketSession wsession, CloseStatus status) throws Exception {

		// 파라미터 WebSocketSession wsession 은 연결을 끊은 웹소켓 클라이언트임.
		// 파라미터 CloseStatus 은 웹소켓 클라이언트의 연결 상태.
		
		Map<String, Object> map = wsession.getAttributes();
		MemberVO loginuser = (MemberVO) map.get("user");

		Map<String, Object> a = wsession.getAttributes();
		String roomNo = a.get("no").toString();
		
		// 웹소켓 서버에 연결되어진 클라이언트 목록에서 연결은 끊은 클라이언트는 삭제시킨다.
		List<WebSocketSession> sessions = chatRooms.get(roomNo);
		sessions.remove(wsession);
		
		chatRooms.put(roomNo, sessions);

		if (roomNo.equals(map.get("no").toString())) {
			for (WebSocketSession webSocketSession : chatRooms.get(roomNo)) {

				// 나를 제외한 모두에게 보내기
				if (!wsession.getId().equals(webSocketSession.getId())) {
					// wsession 은 메시지를 보낸 클라이언트임.
					// webSocketSession 은 웹소켓서버에 연결된 모든 클라이언트중 하나임.
					// wsession.getId() 와 webSocketSession.getId() 는 자동증가되는 고유한 숫자로 나옴
					webSocketSession
							.sendMessage(new TextMessage(wsession.getRemoteAddress().getAddress().getHostAddress()
									+ " [<span style='font-weight:bold;'>" + loginuser.getMem_name() + "</span>]"
									+ "님이 <span style='color: red;'>퇴장</span>했습니다."));
				}

			}

			///// ===== 접속을 끊을시 접속자명단을 알려주기 위한 것 시작 ===== /////
			String connectingUserName = "「";

			for (WebSocketSession webSocketSession : chatRooms.get(roomNo)) {
				Map<String, Object> map2 = webSocketSession.getAttributes();
				MemberVO loginuser2 = (MemberVO) map2.get("user");
				// "loginuser" 은 HttpSession에 저장된 키 값으로 로그인 되어진 사용자이다.

				connectingUserName += loginuser2.getMem_name() + " ";
			} // end of for------------------------------------------

			connectingUserName += "」";

			for (WebSocketSession webSocketSession : chatRooms.get(roomNo)) {
				webSocketSession.sendMessage(new TextMessage(connectingUserName));
			} // end of for------------------------------------------
				///// ===== 접속해제시 접속자명단을 알려주기 위한 것 끝 ===== /////

		}
	}

}
