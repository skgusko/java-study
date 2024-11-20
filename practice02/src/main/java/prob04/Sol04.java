package prob04;
public class Sol04 {

	public static void main(String[] args) {
		char[] c1 = reverse("Hello World"); //0 10 1 11 2 10
		printCharArray(c1);
		
		char[] c2 = reverse("Java Programming!"); //0 16 1 15
		printCharArray(c2);
	}
	
	public static char[] reverse(String str) {
		int arrSize = str.length();
		char[] answer = new char[arrSize];
		for (int i = str.length()-1; i >= 0; i--) {
			answer[arrSize-1-i] = str.charAt(i);
		}
		
		return answer;
	}

	public static void printCharArray(char[] array){
		System.out.println(array);
	}
}