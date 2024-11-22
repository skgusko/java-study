package exception;

public class ExceptionTest {

	public static void main(String[] args) {
		int a = 10;
		int b = 10 - a;
		
		System.out.println("some code1...");
		
		try {
			System.out.println("some code2...");
			System.out.println("some code3...");
			
			int result = (1 + 2 + 3) / b;
			
			System.out.println("some code4...");
			System.out.println("some code5...");
			
		} catch (ArithmeticException e) { //ArithmeticException 발생하는 경우, 여기서 처리할게
			//e.printStackTrace(); 이거라도 하기
			
			/* 예외처리 */
			
			// 1. 로깅
			System.out.println("error: " + e); //toString 돼서 문자로 바뀜
			
			// 2. 사과
			System.out.println("미안합니다...");
			
			// 3. 정상종료
			return;
		} finally { //(option) 예외 발생하든 안 하든 실행되는 구간
			System.out.println("자원정리 : ex) close file, socket, db connection");
		}
		
		System.out.println("some code6...");
		System.out.println("some code7...");
	}
}
