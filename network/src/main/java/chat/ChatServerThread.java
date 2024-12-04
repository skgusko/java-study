package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Base64;
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
				
				ChatServer.log("=== 클라이언트에서 받은 데이터 = " + request); //지울 예정
				if ("JOIN".equals(tokens[0])) {
					String nickname = tokens[1];
					doJoin(nickname, pw);
				}
				else if ("MSG".equals(tokens[0])) {
					String message = tokens[1];
					//여기에서 디코딩 
					byte[] decodedBytes = Base64.getDecoder().decode(message);
			        String decodedMsg = new String(decodedBytes);
					doMessage(decodedMsg);
				}
				else if ("QUIT".equals(tokens[0])) {
					doQuit(pw);
				}
				else {
					ChatServer.log("error : 알 수 없는 요청 (" + tokens[0] + ")");
				}
			}
			
		} catch (SocketException e) {
			System.out.println("[server] Socket Exception : " + e);
		} catch (IOException e) {
			ChatServer.log("error : " + e);
		} finally {
			try {
				if (socket != null && !socket.isClosed()) { //프로그램 등에 의해 닫혀있을 수도 있기에 isClosed 확인 
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace(); 
			}
		}
	}
	
	private void doJoin(String nickName, PrintWriter pw) {
		this.nickName = nickName;
		
		broadcast(nickName + "님이 입장했습니다."); //입장한 사람 제외하고 출력
		
		// write pool 에 저장 
		addWriter(pw);
		
		// ack
		pw.println("JOIN:OK");
		pw.flush();
	}

	private void doMessage(String message) {
		//writer pool 리스트 for문 돌면서 메시지 보내기
		broadcast(nickName + ":" + message);
	}

	private void doQuit(PrintWriter pw) {
		removeWriter(pw);
		
		pw.println("QUIT:OK");
		pw.flush();
		
		broadcast(nickName + "님이 퇴장하였습니다.");
	}

	private void addWriter(PrintWriter pw) {
		synchronized(listWriters) {
			listWriters.add(pw);
		}
	}

	private void removeWriter(PrintWriter pw) {
		synchronized(listWriters) {
			listWriters.remove(pw);
		}
	}

	private void broadcast(String data) {
		for (PrintWriter pw : listWriters) {
			pw.println(data);
			pw.flush();
		}
	}
}