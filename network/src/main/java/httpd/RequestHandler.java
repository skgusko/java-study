package httpd;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.file.Files;

public class RequestHandler extends Thread {
	private Socket socket;
	private final String DOCUMENT_ROOT = "./webapp";
	
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
			
			String request = null;
			
			while(true) {
				String line = br.readLine();
				
				//브라우저가 연결을 끊으면...
				if (line == null) { 
					break;
				}
				
				// SimpleHttpServer는 HTTP Header만 읽는다. (개행만 들어오는 그 지점=바디 부터는 읽지 않음)
				if ("".equals(line)) {  
					break;
				}
				
				// Request Header의 첫 줄만 읽음 
				if (request == null) { //첫번째 라인(요청 라인 : 요청 메서드, URL, 프로토콜을 포함) 만 저장 후 끝내버림 
					request = line;
					break;
				}
			}
			
			consoleLog(request);
			String[] tokens = request.split(" ");
			if ("GET".equals(tokens[0])) {
				responseStaticResources(outputStream, tokens[1], tokens[2]); //GET /user/join.html HTTP/1.1
			}
			else {
				// methods: POST, DELETE, PUT, HEAD, CONNECT, ...
				// SimpleHttpServer에서는 무시(400 Bad Request)
				response400Error(outputStream, tokens[2]);
			}
			
			// 예제 응답입니다.
			// 서버 시작과 테스트를 마친 후, 주석 처리 합니다.
//			outputStream.write("HTTP/1.1 200 OK\n".getBytes("UTF-8")); //응답 헤더
//			outputStream.write("Content-Type:text/html; charset=utf-8\n".getBytes("UTF-8"));
//			outputStream.write("\n".getBytes()); //개행 기준 위로 헤더, 아래로 바디
//			outputStream.write("<h1>이 페이지가 잘 보이면 실습과제 SimpleHttpServer를 시작할 준비가 된 것입니다.</h1>".getBytes("UTF-8"));

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

	private void response400Error(OutputStream os, String protocol) throws IOException {
		/*
		 HTTP/1.1 400 Bad Request Found\n
		 Content-Type: text/html; charset=utf-8\n
		 \n
		 */
		
		File file = new File("./webapp/error/400.html");
		byte[] body = Files.readAllBytes(file.toPath());
		String contentType = Files.probeContentType(file.toPath()); //브라우저에게 서버가 보낸 데이터 형식 알려줌 (이를 보고 브라우저가 데이터 처리 방식 결정)
		
		os.write((protocol + " 400 Bad Request\n").getBytes("UTF-8"));
		os.write(("Content-Type:" + contentType + "; charset=utf-8\n").getBytes("UTF-8"));
		os.write("\n".getBytes());
		os.write(body);
	}

	private void responseStaticResources(OutputStream os, String url, String protocol) throws IOException { //이 메소드 내에서 처리 안 하고 메소드 호출하는 곳에서 예외처리 하라고 토스 
		// default(welcome) file
		if ("/".equals(url)) {
			url = "/index.html";
		}
		
		File file = new File(DOCUMENT_ROOT + url);
		if (!file.exists()) {
			// 404 response
			response404Error(os, protocol);
			return;
		}
		
		// nio 
		byte[] body = Files.readAllBytes(file.toPath());
		String contentType = Files.probeContentType(file.toPath()); //content type 알아내는 법
		
		os.write((protocol + " 200 OK\n").getBytes("UTF-8")); //응답 헤더
		os.write(("Content-Type:" + contentType + "; charset=utf-8\n").getBytes("UTF-8"));
		os.write("\n".getBytes()); //빈 개행 기준 위로 헤더, 아래로 바디
		os.write(body);
	}

	private void response404Error(OutputStream os, String protocol) throws IOException {
		/*
		 HTTP/1.1 404 File Not Found\n
		 Content-Type: text/html; charset=utf-8\n
		 \n
		 */
		
		File file = new File("./webapp/error/404.html");
		byte[] body = Files.readAllBytes(file.toPath());
		String contentType = Files.probeContentType(file.toPath()); //브라우저에게 서버가 보낸 데이터 형식 알려줌 (이를 보고 브라우저가 데이터 처리 방식 결정)
		
		os.write((protocol + " 404 Not Found\n").getBytes("UTF-8"));
		os.write(("Content-Type:" + contentType + "; charset=utf-8\n").getBytes("UTF-8"));
		os.write("\n".getBytes());
		os.write(body);
	}

	public void consoleLog(String message) {
		System.out.println("[RequestHandler#" + getId() + "] " + message);
	}
}
