package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class EchoRequestHandler extends Thread {
	private Socket socket;
	
	public EchoRequestHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			InetSocketAddress inetRemoteSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress(); //Client의 IPAddress, Port 가져오기 
			String remoteHostAddress = inetRemoteSocketAddress.getAddress().getHostAddress();
			int remotePort = inetRemoteSocketAddress.getPort();
			EchoServer.log("connected by client [" + remoteHostAddress + ":" + remotePort + "]");
			
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			
			while(true) {
				String data = br.readLine();
				if (data == null) { 
					EchoServer.log("closed by client");
					break;
				}
				EchoServer.log("received : " + data);
				
				// 6. 데이터 쓰기
				pw.println(data); //pw.print(data + "\n") 으로 하면 flush가 안 됨
				
			}
		} catch (SocketException e) {
			EchoServer.log("Socket Exception : " + e);
		} catch (IOException e) {
			EchoServer.log("error : " + e);
		} finally {
			try {
				if (socket != null && !socket.isClosed()) {  
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
