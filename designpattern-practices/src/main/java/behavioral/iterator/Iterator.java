package behavioral.iterator;

public interface Iterator<E> { //수제 이터레이터 
	E next(); //실제 데이터 넘겨주는 놈 
	boolean hasNext(); 
}
