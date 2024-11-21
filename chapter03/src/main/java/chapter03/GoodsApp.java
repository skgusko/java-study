package chapter03;

public class GoodsApp {

	public static void main(String[] args) {
		Goods goods = new Goods();
		
//		goods.name = "nikon";
//		goods.price = 400000;
//		goods.countSold = 10;
//		goods.countStock = 20;
		goods.setName("nikon");
		goods.setPrice(400000);
		goods.setCountSold(10);
		goods.setCountSold(20);
		
//		System.out.println("상품 이름 : " + goods.name +
//				", 가격 : " + goods.price +
//				", 재고 개수 : " + goods.countSold +
//				", 판매량 : " + goods.countStock);
		
//		System.out.println("상품 이름 : " + goods.getName() +
//				", 가격 : " + goods.getPrice() +
//				", 재고 개수 : " + goods.getCountSold() +
//				", 판매량 : " + goods.getCountStock());
		
		goods.getPrintInfo();
		System.out.println("price : " + goods.calcDiscountPrice(0.5f));
		
		// 정보은닉(객체의 상태를 보호) 
		goods.setPrice(-1000);
		System.out.println(goods.getPrice());
		
		Goods goods2 = new Goods();
		Goods goods3 = new Goods();
		
		//static 변수(클래스 변수) 
		System.out.println(Goods.countOfGoods);
	}
}
