package chapter03;

public class Goods {
	public static int countOfGoods = 0;
	
	private String name;
	private int price;
	private int countStock;
	private int countSold;
	
	public Goods() {
		//클래스 이름은 생략 가능 (내부에서)
		countOfGoods++;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		if (price < 0) {
			this.price = 0;
		}
		else {
			this.price = price;
		}
	}
	public int getCountStock() {
		return countStock;
	}
	public void setCountStock(int countStock) {
		this.countStock = countStock;
	}
	public int getCountSold() {
		return countSold;
	}
	public void setCountSold(int countSold) {
		this.countSold = countSold;
	}
	public void getPrintInfo() {
		System.out.println("상품 이름 : " + name +
		", 가격 : " + price +
		", 재고 개수 : " + countSold +
		", 판매량 : " + countStock);	
	}
	
	public int calcDiscountPrice(float discountRate) {
		//int result = (int)(price * (1-discountRate));
		int result = price - (int)(price * discountRate);
		
		return result;
	}
	
	
}
