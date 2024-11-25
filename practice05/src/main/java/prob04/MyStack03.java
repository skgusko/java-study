package prob04;

public class MyStack03<T> {
	private int top;
	private T[] buffer; // 컴파일 타임에 T가 바뀔 것임

	public MyStack03(int capacity) {
		buffer = (T[])new Object[capacity]; // 런타임 때 실행이 됨 
	}

	public void push(T s) {
		if (top == buffer.length) { //버퍼 꽉 찬 경우
			resize();
		}
		buffer[top] = s;
		top++;
	}

	public T pop() throws MyStackException {
		if (isEmpty()) {
			throw new MyStackException();
		}
		T result = buffer[top-1];
		top--;
		
		return result;
	}

	public boolean isEmpty() {
		if (top == 0) {
			return true;
		}
		return false;
	}

	private void resize() {
		T[] tmp = (T[])new Object[top+1];
		for (int i = 0; i < buffer.length; i++) {
			tmp[i] = buffer[i];
		}
		buffer = tmp;
	}	
}
