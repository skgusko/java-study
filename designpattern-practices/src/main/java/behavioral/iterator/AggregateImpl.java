package behavioral.iterator;

public class AggregateImpl<E> implements Aggregate<E> { //new 할 때 E 타입 지정해줘야 하므로 AggregateImpl<E>로 써야 함

	private E[] datas;
	
	public AggregateImpl(E[] datas) {
		this.datas = datas;
	}
	
	@Override
	public Iterator<E> createIterator() {
		return new IteratorImpl();
	}
	
	private class IteratorImpl implements Iterator<E> { //Inner Class : Outer Class의 private 필드 접근 가능 !

		private int index = 0;
		
		@Override
		public E next() {
			return index < datas.length ? datas[index++] : null;
		}

		@Override
		public boolean hasNext() {
			return index < datas.length;
		}
		
	}

}
