package prob06;

import java.util.Random;
import java.util.Scanner;

public class Sol06 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("수를 결정하였습니다. 맞추어 보세요 :\n1-100");
		while(true) {
			
			/* 게임 작성 */

			// 정답 램덤하게 만들기
			Random random = new Random();
			int correctNumber = random.nextInt(100) + 1;
			System.out.println("정답 : " + correctNumber);
			
			int idx = 0, min = 1, max = 100;
			while (true) {
				System.out.print(++idx + ">>");
				
				int userNumber = scanner.nextInt();
				if (userNumber > correctNumber) {
					System.out.println("더 낮게");
					max = userNumber;
					System.out.println(min + "-" + max);
				}
				else if (userNumber < correctNumber) {
					System.out.println("더 높게");
					min = userNumber;
					System.out.println(min + "-" + max);
				}
				else {
					System.out.println("맞췄습니다.");
					break;
				}
			}
			
			//새 게임 여부 확인하기
			System.out.print("다시 하겠습니까(y/n)>>");
			String answer = scanner.next();
			if("y".equals(answer) == false) {
				break;
			}
		}
		
		scanner.close();
	}
}