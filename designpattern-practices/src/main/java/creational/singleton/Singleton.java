package creational.singleton;

public class Singleton {
	private static Singleton instance = null;
	
	private Singleton() { //바깥에서는 객체 생성 불가
		
	}
	
	public static Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
}