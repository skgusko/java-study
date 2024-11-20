package prob05;

public class Sol05 {
	public static void main(String[] arg) {

		int array[] = {5, 9, 3, 8, 60, 20, 1};
		int count = array.length;

		System.out.println("Before Sort.");

		for (int i = 0; i < count; i++) {
			System.out.print(array[i] + " ");
		}

		//
		// 정렬 알고리즘이 적용된 코드를 여기에 작성합니다.
		//
		int temp = 0;
		for (int i = 0; i < array.length-1; i++) {
			for (int j = 1; j < array.length-1; j++) {
				if (array[j] > array[j-1]) {
					temp = array[j-1];
					array[j-1] = array[j];
					array[j] = temp;
				}
			}
		}
		
		System.out.println("\nAfter Sort.");

		for (int i = 0; i < count; i++) {
			System.out.print(array[i] + " ");
		}
	}
}