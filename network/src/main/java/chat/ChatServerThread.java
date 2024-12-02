package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.List;

//클라이언트와 채팅 데이터 통신
public class ChatServerThread extends Thread {
	private Socket socket;
	private String nickName;
	
	private List<PrintWriter> listWriters;
	
	public ChatServerThread(Socket socket, List<PrintWriter> listWriters) {
		this.socket = socket;
		this.listWriters = listWriters;
	}
	
	@Override
	public void run() {
		try {
			// 1. Remote Host Infomation
			InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();  
			String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
			int remotePort = inetRemoteSocketAddress.getPort();
			ChatServer.log("connected by client [" + remoteHostAddress + ":" + remotePort + "]");
			
			// 2. 스트림 얻기
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			
			// 3. 요청 처리
			while(true) {
				String request = br.readLine();
				if (request == null) { 
					ChatServer.log("closed by client");
					break;
				}
			
				// 4. 프로토콜 분석
				String[] tokens = request.split(":"); //base64 인코드 상태
				//System.out.println("=== 클라이언트에서 받은 데이터 :  " + request);
				if ("JOIN".equals(tokens[0])) {
					String nickname = tokens[1];
					doJoin(nickname, pw);
				}
				else if ("MSG".equals(tokens[0])) {
					String encodedMessage = tokens[1];
					doMessage(encodedMessage);
				}
				else if ("QUIT".equals(tokens[0])) {
					doQuit(pw);
				}
				else {
					//없는 요청
				}
				
				
				
			}
			
		} catch (IOException e) {
			ChatServer.log("error : " + e);
		}
	}
	
	private void doJoin(String nickName, PrintWriter pw) {
		this.nickName = nickName;
		
		//String data = nickName + "님이 입장습니다.";
		broadcast("MSG:" + nickName + "님이 입장했습니다."); //입장한 사람 제외하고 출력
		
		// write pool 에 저장 
		addWriter(pw);
		
		// ack
		pw.println("JOIN:OK");
		pw.flush();
		//System.out.println("서버 JOIN 프로토콜 보내기 완료");
	}

	//private void doMessage(String encodedMessage) {
	private void doMessage(String message) {
//		byte[] decodeBytesMsg = Base64.getDecoder().decode(encodedMessage);
//		String message = new String(decodeBytesMsg);
//		System.out.println("message : " + message);
		
		//writer pool 리스트 for문 돌면서 메시지 보내기
		broadcast("MSG:" + nickName + "-" + message);
	}

	private void doQuit(PrintWriter pw) {
		synchronized(listWriters) {
			listWriters.remove(pw);
		}
		
		broadcast("MSG:" + nickName + "님이 퇴장하였습니다.");
	}

	private void addWriter(PrintWriter pw) {
		synchronized(listWriters) {
			listWriters.add(pw);
		}
	}

	private void broadcast(String data) {
		for (PrintWriter pw : listWriters) {
			pw.println(data);
			pw.flush();
		}
	}

	
}
