package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class EchoClient {
//	private static final String SERVER_IP = "127.0.0.1";
	private static final String SERVER_IP = "192.168.56.5";
	
	public static void main(String[] args) {
		Scanner scanner = null;
		Socket socket = null;
		
		try {
			scanner = new Scanner(System.in);
			socket = new Socket();
		
			socket.connect(new InetSocketAddress(SERVER_IP, EchoServer.PORT));
			
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			
			while(true) {
				System.out.print(">>");
				String line = scanner.nextLine();
				
				if ("exit".equals(line)) {
					break;
				}
				
				pw.println(line); //개행 있는 게 프로토콜
				String data = br.readLine();
				if (data == null) {
					log("closed server");
					break;
				}
				
				System.out.println("<<" + data);
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
		System.out.println("[Echo client] " + message);
	}
}
