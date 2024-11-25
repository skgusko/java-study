package chapter04;

import java.util.Objects;

public class Rect {
	private int w;
	private int h;
	
	public Rect(int w, int h) {
		this.w = w;
		this.h = h;
	}

	@Override
	public int hashCode() {
		return Objects.hash(h, w); // 내용 기반의 해시코드
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass()) // 타입이 다른 경우임. 
			return false;
		Rect other = (Rect) obj; // 동일한 놈도 아니고 null도 아니고 타입도 같은 놈
		return h == other.h && w == other.w;
	}

	@Override
	public String toString() {
		return "Rect [w=" + w + ", h=" + h + "]";
	}
}
