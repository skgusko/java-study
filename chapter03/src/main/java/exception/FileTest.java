package exception;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileTest {

	public static void main(String[] args) {
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream("hello.txt"); //파일 읽기. 파일 없는 경우에 대해 꼭 예외처리 하는 게 강제됨 -> checked exception. 여기에서 상대경로 시작 기준은 프로젝트(chapter03 기준)
			int data = fis.read();
			System.out.println((char)data);
		} catch (FileNotFoundException e) { //new FileInputStream("hello.txt") 에서 던짐
			System.out.println("error : " + e);
		} catch (IOException e) { //fis.read()에서 던짐
			System.out.println("error : " + e);
		} finally {
			try {
				if (fis != null) { //13번째 줄에서 hello.txt 파일 없으면 fis = null 이므로
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}