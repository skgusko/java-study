package test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class LocalHost {

	public static void main(String[] args) {
		try {
			InetAddress inetAddress = InetAddress.getLocalHost(); //내 컴퓨터의 IP 뽑아냄
			
			String hostName = inetAddress.getHostName(); //hostname (단순히 컴퓨터 이름)
			String hostIPAddress = inetAddress.getHostAddress();
			
			System.out.println(hostName);
			System.out.println(hostIPAddress);
			
			byte[] IPAddresses = inetAddress.getAddress(); //unsigned
			for(byte IPAddress : IPAddresses) {
				System.out.println(IPAddress & 0X000000ff); //signed로 바꾸기
			}
			
			
		} catch (UnknownHostException e) { //TCP/IP 설치 안 되어있는, 네트워크 안 되는 상황에 대한 예외
			e.printStackTrace();
		} 
	}

}
