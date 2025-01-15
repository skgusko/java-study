package structural.proxy.context;

import structural.proxy.i.Subject;

public class Context {
	private Subject realSubject;
	
	public Context() { //Subject를 구현한 익명 객체를 생성
		this(() -> {System.out.println("Subject.doAction in Context done");}); // Subject 클래스가 메서드 한 개라 
	}
	
	private Context(Subject subject) {
		realSubject = subject;
	}

	public Subject getSubject() {
		return realSubject;
	}
}
