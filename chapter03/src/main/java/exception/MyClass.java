package exception;

import java.io.IOException;

public class MyClass {
	public void danger() throws MyException, IOException { //컴파일러는 이 표기를 보고 IOException 예외처리 하라고 알려줌 (checked exception)
		System.out.println("some code1...");
		System.out.println("some code2...");
		
		if (1 - 1 == 0) {
			throw new MyException();
		}
		
		if (2 - 2 == 0) {
			//exception 던지는 구문 있으면, 이 메서드는 예외 던지는 위험한 메서드임을 알리기 위해 메소드명 뒤에 명시해줘야 함 (throws IOException 적어줘야 함)
			throw new IOException(); 
		}
		
		// 만약 예외 던지면 실행 안 됨
		System.out.println("some code3...");
		System.out.println("some code4...");
	}
}