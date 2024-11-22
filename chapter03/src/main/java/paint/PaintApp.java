package paint;

public class PaintApp {

	public static void main(String[] args) {
		Point point1 = new Point();
		point1.setX(10);
		point1.setY(20);
		//point1.show();
		//drawPoint(point1);
		draw(point1);
		
		Point point2 = new Point(100, 200);
		//point2.show();
		//drawPoint(point2);
		draw(point2);
		//point2.disappear();
		point2.show(false);
		
//		ColorPoint point3 = new ColorPoint();
//		point3.setX(50);
//		point3.setY(100);
//		point3.setColor("red");
		//drawColorPoint(point3);
		ColorPoint point3 = new ColorPoint(50, 100, "red");
		//drawPoint(point3); //다형성의 오버라이딩
		draw(point3);
		
//		drawTriangle(new Triangle());
//		drawRectangle(new Rectangle());
//		drawShape(new Triangle());
//		drawShape(new Rectangle());
//		drawShape(new Circle());
		
		draw(new Triangle());
		draw(new Rectangle());
		draw(new Circle());
		
		draw(new GraphicText("안녕"));
	}

	public static void draw(Drawable drawable) {
		drawable.draw();
	}
	
//	public static void drawPoint(Point point) { //다형성. static에선 static만 부를 수 있음!
//		point.show();
//	}
//	
//	public static void drawShape(Shape shape) {
//		shape.draw();
//	}
	
	
	
//	public static void drawColorPoint(ColorPoint colorPoint) { //static에선 static만 부를 수 있음!
//		colorPoint.show();
//	}
	
//	public static void drawTriangle(Triangle triangle) {
//		triangle.draw();
//	}
//	
//	public static void drawRectangle(Rectangle rectangle) {
//		rectangle.draw();
//	}
}
