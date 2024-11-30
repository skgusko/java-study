package chat;

import java.io.BufferedReader;
import java.io.IOException;

public class ChatClientThread extends Thread {
	private BufferedReader br;
	
	public ChatClientThread(BufferedReader br) {
		this.br = br;
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				// reader 통해 읽은 데이터 콘솔에 출력하기 (메시지 처리)
				String response = br.readLine();
				//System.out.println("=== 서버에서 받은 데이터 : " + response);
				if (response == null) { 
					ChatClient.log("closed by client");
					return;
				}
				
				// 프로토콜 분석 
				String[] tokens = response.split(":");
				if ("JOIN".equals(tokens[0])) {
					if ("OK".equals(tokens[1])) {
						System.out.println("입장하였습니다. 즐거운 채팅 되세요!");
						//ChatClient.log("입장하였습니다. 즐거운 채팅 되세요!");
					}
				}
				else if ("MSG".equals(tokens[0])) {
					String message = tokens[1];
					System.out.println(message);
				}
				else if ("QUIT".equals(tokens[0])) {
					
				}
			}
			
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
