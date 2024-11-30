package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;


public class ChatClient {
	private static final String SERVER_IP = "127.0.0.1";
	
	public static void main(String[] args) {
		System.out.println("client start..."); //삭제 예정
		
		Scanner scanner = null;
		Socket socket = null;
		
		try {
			// 1. 키보드 연결 
			scanner = new Scanner(System.in);
			
			// 2. socket 생성 
			socket = new Socket();
		
			// 3. socket 연결 
			socket.connect(new InetSocketAddress(SERVER_IP, ChatServer.PORT));
			
			// 4. reader/writer 생성 
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			
			// 5. JOIN 프로토콜 전송
			System.out.print("닉네임>>");
			String nickName = scanner.nextLine();
			pw.println("JOIN:" + nickName);
//			pw.flush();
			
			// 6. ChatClientReceiveThread 시작
			new ChatClientThread(br).start();

			pw.flush();
			
			// 7. 키보드 입력 처리
			while(true) {
				//System.out.print(">>");
				String input = scanner.nextLine(); //client가 보내는 메시지
				
				if ("quit".equals(input) || "QUIT".equals(input)) {
					// 8. QUIT 프로토콜 전송
					pw.println("QUIT:" + nickName); //프로토콜 형태로 보내기
					pw.flush();
					System.out.println("대화가 종료되었습니다");
					break;
				}
				else {
					// 9. MSG 프로토콜 전송 (메시지 처리)
//					if (data == null) {
//						log("closed server");
//						break;
//					}
					pw.println("MSG:" + input); //server에 보낼 메시지
					pw.flush();
				}
			}
			
		} catch (SocketException e) {
			log("error : " + e);
		} catch (IOException e) {
			log("error : " + e);
		} finally {
			try {
				if (scanner != null) {
					scanner.close();
				}
				if (socket != null && !socket.isClosed()) {
					socket.close();
				}  
			} catch (IOException e) {
					e.printStackTrace();
			}
		}

	}

	public static void log(String message) {
		System.out.println("[client] : " + message);
	}
}
