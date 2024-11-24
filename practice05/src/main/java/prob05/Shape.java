package prob05;

public abstract class Shape {
	//필드 두 개 protected로 두고 풀기 
	protected double width;
	protected double height;
	
	public abstract double getArea(); //넓이 
	public abstract double getPerimeter(); //둘레 
}
