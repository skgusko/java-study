package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TCPServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		
		try {
			// 1. 서버 소켓 생성
			serverSocket = new ServerSocket();
			
			// 2. 바인딩(binding) - IP, PORT를 서버 소켓에 할당
			// 	  Socket에 InetSocketAddress[InetAddress(IPAddress) + port]를 바인딩 한다.
			// 	  IPAddress: 0.0.0.0: 특정 호스트 IP를 바인딩하지 않는다. 
			serverSocket.bind(new InetSocketAddress("0.0.0.0", 50000)); //어떤 IP가 자신에게 connect할 수 있는지를 적어줌. 0.0.0.0 -> connect할 수 있는 대상을 다 열어줌 
			
			// 3. accept
			Socket socket = serverSocket.accept(); //blocking (연결될 때까지 block, 잘 연결됐으면 데이터 통신용 소켓 생성해서 리턴) 
			
			try {
				// IP와 포트를 확인하려면 InetSocketAddress로 변환 필요
				InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress(); //Client의 IPAddress, Port 가져오기 
				String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
				int remotePort = inetRemoteSocketAddress.getPort();
				
				System.out.println("[server] : connected by client [" + remoteHostAddress + ":" + remotePort + "]");
				
				// 4. IO Stream 받아오기 
				InputStream is = socket.getInputStream(); //추상화. 소켓의 InputStream 리턴
				OutputStream os = socket.getOutputStream(); // client 소켓에서 받아오기 
				
				while(true) {
					// 5. 데이터 읽기
					byte[] buffer = new byte[256]; //InputStream에서 읽어 들인 데이터를 임시로 저장하는 버퍼
					int readByteCount = is.read(buffer); //blocking (데이터 들어오기 전까지 잠듦)
					if (readByteCount == -1) { 
						// closed by client (소켓의 경우, -1은 소켓을 닫았다는 의미)
						System.out.println("[server] closed by client");
						break;
					}
					
					String data = new String(buffer, 0, readByteCount, "utf-8"); //UTF-8 인코딩으로 문자열로 변환
					System.out.println("[server] receive : " + data);
					
					// 6. 데이터 쓰기
					os.write(data.getBytes("utf-8")); //string을 byte로 변경해줘야 함
				}
			} catch (SocketException e) {
				System.out.println("[server] Socket Exception : " + e);
			} catch (IOException e) {
				System.out.println("[server] error : " + e);
			} finally {
				try {
					if (socket != null && !socket.isClosed()) { //내가 클로즈 안했는데 (프로그램 등에 의해) 닫혀있을 수도 있기에 isClosed 확인 
						socket.close();
					}
				} catch (IOException e) {
					e.printStackTrace(); // 거의 안 터져서 그냥 printStackTrace로 둠
				}
			}
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
