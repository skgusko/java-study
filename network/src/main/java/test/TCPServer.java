package test;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		
		try {
			// 1. 서버 소켓 생성
			serverSocket = new ServerSocket();
			
			// 2. 바인딩(binding) - IP, PORT를 서버 소켓에 할당
			// 	  Socket에 InetSocketAddress[InetAddress(IPAddress) + port]를 바인딩 한다.
			// 	  IPAddress: 0.0.0.0: 특정 호스트 IP를 바인딩하지 않는다. 
			serverSocket.bind(new InetSocketAddress("0.0.0.0", 5000)); //어떤 IP가 자신에게 connect할 수 있는지를 적어줌. 0.0.0.0 -> connect할 수 있는 대상을 다 열어줌 
			
			// 3. accept
			Socket socket = serverSocket.accept(); //blocking (연결될 때까지 block, 잘 연결됐으면 데이터 통신용 소켓 생성해서 리턴) 
			
			System.out.println("연결 성공");
			
			// 4. IO Stream 받아오기 
			InputStream is = socket.getInputStream(); //추상화. 소켓의 InputStream 리턴
			
			// 5. 데이터 읽기
			byte[] buffer = new byte[256];
			int readByteCount = is.read(buffer); //blocking (데이터 들어오기 전까지 잠듦)
			if (readByteCount == -1) { 
				// closed by client (소켓의 경우, -1은 소켓을 닫았다는 의미)
				System.out.println("[server] closed by client");
				return;
			}
			
			String data = new String(buffer, 0, readByteCount, "utf-8"); //UTF-8 인코딩으로 문자열로 변환
			System.out.println("[server] receive : " + data);
			
			
		} catch (IOException e) {
			System.out.println("[server] error : " + e);
		} finally {
			try {
				if (serverSocket != null && !serverSocket.isClosed()) { 
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
