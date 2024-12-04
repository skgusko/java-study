package chat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
	/**
	 클라이언트로 부터 연결 요청을 기다린다.
     클라이언트와 연결된 후, 클라이언트와 채팅 데이터 통신은 ChatServerTread가  한다.
     Accept 1 + 소켓가지고 클라이언트와 데이터 통신하는 스레드 1
	 */
	public static final int PORT = 9999;
	public static final String ADDRESS = "0.0.0.0";
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		List<PrintWriter> listWriters = new ArrayList<PrintWriter>();
		
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress(ADDRESS, PORT));
			
			while(true) {
				Socket socket = serverSocket.accept();
				
				new ChatServerThread(socket, listWriters).start(); //클라이언트와의 채팅 데이터 통신
			}
		} catch (IOException e) {
			log("error : " + e);
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
	
	public static void log(String message) {
		System.out.println("[server] : " + message);
	}
}