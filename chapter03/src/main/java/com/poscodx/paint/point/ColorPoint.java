package com.poscodx.paint.point;

public class ColorPoint extends Point {
	private String color;
	
	public ColorPoint(int x, int y, String color) {
//		setX(x);
//		setY(y);
		super(x, y);
		this.color = color;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public void show() {
		// super.show(); //이거 쓰면 부모의 기능을 사용하면서 추가로 구현하는 것이므로 부분 재구현 
		System.out.println("Point[x=" + getX() + ", y=" + getY() + ", color=" + color + "]을 그렸습니다");
	}

	@Override
	public void draw() {
		show();
	}
}
