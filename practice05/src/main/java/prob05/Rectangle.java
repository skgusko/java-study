package prob05;

public class Rectangle extends Shape implements Resizable {
	
	Rectangle(double width, double height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public double getArea() { //넓이
		double area = width * height;
		return area;
	}

	@Override
	public double getPerimeter() { //둘레
		double perimeter = (width + height) * 2;
		return perimeter;
	}
	
	@Override
	public void resize(double rate) {
		width *= 0.5;
		height *= 0.5;
	}

}
