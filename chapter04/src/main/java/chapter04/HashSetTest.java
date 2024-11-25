package chapter04;

import java.util.HashSet;
import java.util.Set;

public class HashSetTest {

	public static void main(String[] args) {
		Set<Rect> set = new HashSet<>(); //Set은 인터페이스, HashSet은 구현체
		
		Rect r1 = new Rect(10, 20);
		Rect r2 = new Rect(10, 20);
		Rect r3 = new Rect(4, 5);
		
		set.add(r1);
		set.add(r2); //Object 클래스 내 hashCode()로 구현된 해시코드라 주소 기반이기에 r1, r2 둘 다 들어감 
		set.add(r3);
		
		for (Rect r : set) {
			System.out.println(r);
		}
	}

}
