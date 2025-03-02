package prob03;

public class Sol03 {
	public static void main(String args[]) {
		char c[] = { 'T', 'h', 'i', 's', ' ', 'i', 's', ' ', 'a', ' ', 'p', 'e', 'n', 'c', 'i', 'l', '.' };

		// 원래 배열 원소 출력
		printCharArray(c);

		// 공백 문자 바꾸기
		replaceSpace(c);

		// 수정된 배열 원소 출력
		printCharArray(c);
	}

	private static void replaceSpace(char[] c) {
		for (int i = 0; i < c.length; i++) {
			if (c[i] == ' ') {
				c[i] = ',';
			}
		}
	}

	private static void printCharArray(char[] c) {
		String sentence = "";
		
		for (int i = 0; i < c.length; i++) {
			sentence += c[i];
		}
		System.out.println(sentence);
	}
}