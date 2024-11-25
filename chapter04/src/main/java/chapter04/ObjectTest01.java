package chapter04;

public class ObjectTest01 {

	public static void main(String[] args) {
		Point point = new Point();
		
		Class klass = point.getClass(); 		// reflection
		System.out.println(klass);
		
		System.out.println(point.hashCode());	// address 기반의 해싱값 (Object 클래스 내의 hashCode 메소드)
												// address는 아님! 
												// reference 값도 아님!
		
		System.out.println(point.toString()); 	// getClass().toString + "@" + hashCode()
		System.out.println(point);
	}

}
