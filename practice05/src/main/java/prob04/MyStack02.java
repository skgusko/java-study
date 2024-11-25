package prob04;

public class MyStack02 {
	private int top;
	private Object[] buffer;

	public MyStack02(int capacity) {
		/* 구현하기 */
		buffer = new Object[capacity];
	}

	public void push(Object s) {
		/* 구현하기 */
		if (top == buffer.length) { //버퍼 꽉 찬 경우
			resize();
		}
		buffer[top] = s;
		top++;
	}

	public Object pop() throws MyStackException {
		/* 구현하기 */
		if (isEmpty()) {
			throw new MyStackException();
		}
		Object result = buffer[top-1];
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
		Object[] tmp = new Object[top+1];
		for (int i = 0; i < buffer.length; i++) {
			tmp[i] = buffer[i];
		}
		buffer = tmp;
	}	
}
