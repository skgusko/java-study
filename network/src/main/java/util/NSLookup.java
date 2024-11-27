package util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookup {

	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(System.in);
			System.out.print("> ");
			String domain = null;
			
			while ((domain = scanner.next()) != null) {
				if ("exit".equals(domain)) {
					break;
				}
				
				InetAddress[] InetAddresses = InetAddress.getAllByName(domain);
				
				for (InetAddress inetAddress : InetAddresses) {
					System.out.println(domain + " : " + inetAddress.getHostAddress());
				}
				
				System.out.print("> ");
			}
			
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
		}

	}

}
