package collection;

import java.util.Enumeration;
import java.util.Vector;

//이 부분은 까먹어도 됨 
public class VectorTest01 {

	public static void main(String[] args) {
		Vector<String> v = new Vector<>();
		
		v.addElement("둘리");
		v.addElement("마이콜");
		v.addElement("또치");
		
		// 순회 1
		for (int i = 0; i < v.size(); i++) {
			String s = v.elementAt(i);
			System.out.println(s);
		}
		
		// 삭제
		v.removeElementAt(2);
		
		// 순회 2
		Enumeration<String> e = v.elements(); //aggregate(=여기선 벡터) 가 가지고 있는 데이터 "한 번"만 순회 가능
		while (e.hasMoreElements()) {
			String s = e.nextElement();
			System.out.println(s);
		}
	}

}
