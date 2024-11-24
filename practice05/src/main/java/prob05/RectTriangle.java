package prob05;

public class RectTriangle extends Shape {
	
	public RectTriangle(double width, double height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public double getArea() {
		double area = width * height * 0.5;
		return area;
	}

	@Override
	public double getPerimeter() {
		double perimeter = width + height + Math.sqrt(width * width + height * height);
		return perimeter;
	}

}
