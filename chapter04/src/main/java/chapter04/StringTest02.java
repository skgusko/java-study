package chapter04;

public class StringTest02 {

	public static void main(String[] args) {
		// immutability (불변성)
		
		
		String s1 = "abc";
		String s2 = "def";
		String s3 = s2;
		
		s1.toUpperCase(); //새로운 객체로 만들어서 리턴하는 거임. 내부를 절대 바꿀 수 없음! (공유해서 쓰기 때문에)
		String s4 = s2.concat("??");
		
		String s5 = "!".concat(s2).concat("@");
		
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		System.out.println(s4);
		System.out.println(s5);
		
		System.out.println(equalsHello("hello"));
		System.out.println(equalsHello(null));
	}

	private static boolean equalsHello(String s) {
		return "hello".equals(s);
	}
}
