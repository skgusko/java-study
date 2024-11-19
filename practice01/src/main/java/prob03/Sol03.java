package prob03;

import java.util.Scanner;

public class Sol03 {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		while(true) {
			int answer = 0;
			
			System.out.print("수를 입력 하세요 : ");
			int number = scanner.nextInt();
			
			if (number % 2 == 0) { //짝수
				for (int cur = 0; cur <= number; cur++) {
					if (cur % 2 == 0) {
						answer += cur;
					}
				}
			}
			else { //홀수
				for (int cur = 0; cur <= number; cur++) {
					if (cur % 2 != 0) {
						answer += cur;
					}
				}
			}

			System.out.println("결과값 : " + answer);	
		}
	}
}
