package prob03;

public class Book {
	private int no;
	private String title;
	private String author;
	private int stateCode;
	
	public Book(int no, String title, String author) {
		//new Book(1, "트와일라잇", "스테파니메이어");
		this.no = no;
		this.title = title;
		this.author = author;
		this.stateCode = 1; //재고 있음
	}

	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}
	public String getTitle() {
		return title;
	}
	public int getNo() {
		return no;
	}
	public int getStateCode() {
		return stateCode;
	}
	
	//대여
	public void rent() {
		stateCode = 0;
		System.out.println(title + "이(가) 대여 됐습니다.");
	}
	
	public void print() {
		System.out.println("책 제목:" + title + 
				", 작가:" + author +
				", 대여 유무:" + (stateCode==1 ? "재고있음" : "대여중") 
				);
	}
}
