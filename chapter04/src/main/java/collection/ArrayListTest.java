package collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListTest {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		
		list.add("둘리");
		list.add("마이콜");
		list.add("또치");
		
		// 순회 1
		for (int i = 0; i < list.size(); i++) {
			String s = list.get(i); // * list에 직접 접근해서 가져옴 
			System.out.println(s);
		}
		
		// 삭제
		list.remove(2);
		
		// 순회 2
		Iterator<String> e = list.iterator(); //이 인터페이스를 구현한 객체를 리턴해줌
		while (e.hasNext()) {
			String s = e.next();
			System.out.println(s);
		}
		
		// 순회
		for (String s : list) {
			System.out.println(s); // * 직접 접근 안 하고 받아서 씀
		}

	}

}
