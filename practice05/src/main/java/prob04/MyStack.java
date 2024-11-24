package prob04;

public class MyStack {
	private int top;
	private String[] buffer;

	public MyStack(int capacity) {
		/* 구현하기 */
		buffer = new String[capacity];
	}

	public void push(String s) {
		/* 구현하기 */
		if (top == buffer.length) { //버퍼 꽉 찬 경우
			resize();
		}
		buffer[top] = s;
		top++;
	}

	public String pop() throws MyStackException {
		/* 구현하기 */
		if (isEmpty()) {
			throw new MyStackException();
		}
		String result = buffer[top-1];
		top--;
		
		return result;
	}

	public boolean isEmpty() {
		/* 구현하기 */
		if (top == 0) {
			return true;
		}
		return false;
	}

	private void resize() {
		/* 구현하기 */
		String[] tmp = new String[top+1];
		for (int i = 0; i < buffer.length; i++) {
			tmp[i] = buffer[i];
		}
		buffer = tmp;
	}	
}