package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class TCPClient {

	public static void main(String[] args) {
		Socket socket = null;
		
		try {
			// 1. 소켓 생성
			socket = new Socket();
		
			// 2. 서버 연결
			socket.connect(new InetSocketAddress("127.0.0.1", 50000));
			
			// 3. IO Stream 받아오기
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			
			// 4. 쓰기
			String data = "Hello World";
			os.write(data.getBytes("utf-8"));
			
			// 5. 읽기
			byte[] buffer = new byte[256];
			int readByteCount = is.read(buffer);
			if (readByteCount == -1) { //상대가 close()를 명시적으로 호출했을 때 -1 임. 우아한 종료 (명시적이지 않은 경우 ex. 컴퓨터 꺼질때)
				System.out.println("[client] closed by server");
				return;
			}
			
			data = new String(buffer, 0, readByteCount, "utf-8"); //byte -> string
			System.out.println(data);
			
			
		} catch (SocketException e) {
			System.out.println("[client] error : " + e);
		} catch (IOException e) {
			System.out.println("[client] error : " + e);
		} finally {
			if (socket != null && !socket.isClosed()) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
