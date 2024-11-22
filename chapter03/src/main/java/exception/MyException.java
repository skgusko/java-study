package exception;

public class MyException extends Exception {
	public MyException() {
		super("MyException Thrown");
	}
	
	public MyException(String message) { //예외 메시지 지정하는 경우 
		super(message);
	}
}
