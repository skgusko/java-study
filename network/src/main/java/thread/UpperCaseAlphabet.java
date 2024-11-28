package thread;

public class UpperCaseAlphabet {
	public void print() {
		for (char c = 'A'; c <= 'Z'; c++) {
			System.out.println(c);
			
			try {
				Thread.sleep(1000); //1초 쉬기. 스레드랑 상관 없음 
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
	}
}
