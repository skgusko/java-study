package chapter04;

public class StringTest03 {

	public static void main(String[] args) {
		// String s1 = "Hello " + "World " + "Java" + 21;
		
//		String s1 = new StringBuilder("Hello ") //버퍼를 늘렸다가 줄였다가 함
//			.append("World ")
//			.append("Java")
//			.append(21)
//			.toString();
		
		String s1 = new StringBuffer("Hello ") //버퍼를 늘렸다가 줄였다가 함
				.append("World ")
				.append("Java")
				.append(21)
				.toString();
		
		System.out.println(s1);
		
		String s2 = "";
		for (int i = 0; i < 1000000; i++) {
			// s2 = s2 + "h";
			// s2 = new StringBuffer(s2).append("h").toString();
		}
		// System.out.println(s2);
		
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < 1000000; i++) {
			sb.append("h");
		}
		String s3 = sb.toString();
		
		// String method들...
		String s4 = "abcABCabcAbc";
		System.out.println(s4.length());
		System.out.println(s4.charAt(2));
		System.out.println(s4.indexOf("abc"));
		System.out.println(s4.indexOf("abc", 7)); //index 7부터 찾음. 못 찾으면 -1 나옴 
		System.out.println(s4.substring(3));
		System.out.println(s4.substring(3, 5)); //AB. 3~4 까지 
		
		String s5 = "    ab    cd  ";
		String s6 = "abc,def,ghi";
		
		String s7 = s5.concat(s6);
		System.out.println(s7);
		
		System.out.println("---" + s5.trim() + "---"); //바깥만 쳐냄
		System.out.println("---" + s5.replaceAll(" ", "") + "---");
		
		String[] tokens = s6.split(",");
		for (String s : tokens) {
			System.out.println(s);
		}
		
		String[] tokens2 = s6.split(" ");
		for (String s : tokens2) {
			System.out.println(s);
		}
		
	}
}
