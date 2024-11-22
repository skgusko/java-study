package chapter03;

public class Student extends Person {
	public Student() {
		/**
		 * 자식 생성자에서 부모 생성자를 명시적으로 호출하지 않으면
		 * 자동으로 부모의 기본 생성자를 호출하게 됨
		 * 
		 * 부모생성자 호출하는 코드인 super();가 자동으로 들어가있는 것임
		 */
		//super();
		System.out.println("Student() called");
	}
}
