package prob05;

public class Sol05 {
	public static void main(String[] args) {

		for (int i = 1; i <= 100; i++) {
			String str = String.valueOf(i);
			
			for (int j = 0; j < str.length(); j++) {
				if (str.charAt(j) == '3' || str.charAt(j) == '6' || str.charAt(j) == '9') {
					System.out.println(str + " 짝");
					break;
				}
			}
		}
	}
}