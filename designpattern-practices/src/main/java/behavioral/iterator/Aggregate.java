package behavioral.iterator;

public interface Aggregate<E> { //이 인터페이스 구현한 객체가 데이터 가지고 있어야 함
	Iterator<E> createIterator(); //자바에서 Collection인 놈 

}
