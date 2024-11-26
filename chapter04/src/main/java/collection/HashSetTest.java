package collection;

import java.util.HashSet;
import java.util.Set;

public class HashSetTest {

	public static void main(String[] args) {
		Set<String> s = new HashSet<>();
		
		String str = new String("마이콜");

		s.add("둘리");
		s.add("마이콜");
		s.add("도우너");
		s.add(str); //이미 마이콜 값이 있기 때문에 안 넣어짐. 자료구조 안에 "값"이 넣어지는 것이기에  
		
		System.out.println(s.size());
		System.out.println(s.contains("둘리")); //값으로 묻는 것임
		System.out.println(s.contains(str)); //true로 나옴. 값으로 묻는 것이기 때문에 
		
		// 순회
		for (String str2 : s) {
			System.out.println(str2);
		}
	}

}
