package prob02;

public class Sol02 {
	public static void main(String[] args) {
		
		int cnt = 10;
		
		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= cnt; j++) {
				System.out.print(j + " ");
			}
			cnt++;
			System.out.println();
		}
	}
}