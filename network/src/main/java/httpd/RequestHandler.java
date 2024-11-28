package httpd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class RequestHandler extends Thread {
	private Socket socket;
	
	public RequestHandler( Socket socket ) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			// get IOStream
			OutputStream outputStream = socket.getOutputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));

			// logging Remote Host IP Address & Port
			InetSocketAddress inetSocketAddress = (InetSocketAddress )socket.getRemoteSocketAddress();
			consoleLog("connected from " + inetSocketAddress.getAddress().getHostAddress() + ":" + inetSocketAddress.getPort());
			
			while(true) {
				String line = br.readLine();
				
				// SimpleHttpServer는 HTTP Header만 읽는다. (개행만 들어오는 그 지점=바디 부터는 읽지 않음)
				if ("".equals(line)) {  
					break;
				}
				
				consoleLog(line);
			}
			
			// 예제 응답입니다.
			// 서버 시작과 테스트를 마친 후, 주석 처리 합니다.
			outputStream.write("HTTP/1.1 200 OK\n".getBytes("UTF-8")); //응답 헤더
			outputStream.write("Content-Type:text/html; charset=utf-8\n".getBytes("UTF-8"));
			outputStream.write("\n".getBytes()); //개행 기준 위로 헤더, 아래로 바디
			outputStream.write("<h1>이 페이지가 잘 보이면 실습과제 SimpleHttpServer를 시작할 준비가 된 것입니다.</h1>".getBytes("UTF-8"));

		} catch(Exception ex) {
			consoleLog("error:" + ex);
		} finally {
			// clean-up
			try{
				if(socket != null && socket.isClosed() == false) {
					socket.close();
				}
				
			} catch(IOException ex) {
				consoleLog("error:" + ex);
			}
		}			
	}

	public void consoleLog(String message) {
		System.out.println("[RequestHandler#" + getId() + "] " + message);
	}
}
