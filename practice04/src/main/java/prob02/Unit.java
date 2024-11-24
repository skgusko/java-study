package prob02;

public abstract class Unit {
	// 현재 위치
	private int x;
	private int y;

	/* 지정된 위치로 이동 */
	public abstract void move(int x, int y);

	/* 현재 위치에 정지 */
	public abstract void stop();
}
