package prob02;

import java.util.Scanner;

public class GoodsTest {
	private static final int COUNT_GOODS = 3;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		Goods[] goods = new Goods[COUNT_GOODS];

		for (int i = 0; i < COUNT_GOODS; i++) {
			// 상품 입력
			String line = scanner.nextLine(); 
			String[] info = line.split(" ");
			
			String name = info[0];
			int price = Integer.parseInt(info[1]);
			int stock = Integer.parseInt(info[2]);
			goods[i] = new Goods(name, price, stock);
		}

		// 상품 출력
		for (int i = 0; i < COUNT_GOODS; i++) {
			goods[i].printInfo();
		}
		
		scanner.close();
	}
}