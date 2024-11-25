package chapter04;

public class ObjectTest03 {

	public static void main(String[] args) {
		String s1 = new String("hello");
		String s2 = new String("hello");
		
		System.out.println(s1 == s2); //false 다른 객체
		System.out.println(s1.equals(s2)); //true 내용이 같음
		System.out.println(s1.hashCode() + " : " + s2.hashCode()); //true 내용기반으로 String이 오버라이딩 해놓았음

		System.out.println("======================================");
		
		String s3 = "hello";
		String s4 = "hello";
		
		System.out.println(s3 == s4); //true 다른 객체
		System.out.println(s3.equals(s4)); //true 내용이 같음
		System.out.println(s3.hashCode() + " : " + s4.hashCode()); //true 내용기반으로 String이 오버라이딩 해놓았음
		System.out.println(System.identityHashCode(s3) + " : " + System.identityHashCode(s4)); //Object 클래스 내 hashCode() 출력. 주소 기반의 해시코드 
	}
}
