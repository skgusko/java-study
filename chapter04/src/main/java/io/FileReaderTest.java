package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class FileReaderTest {

	public static void main(String[] args) {
		Reader in = null; //한 번에 3byte씩 읽음 
		InputStream is = null;
		try {
			in = new FileReader("test.txt");
			is = new FileInputStream("test.txt");
			
			int count = 0;
			int data = -1; //char가 2byte 이므로 int로 읽어도 됨. -1인지 체크하기 위해 int로 받은 것임. 뒤에 2byte는 안 읽음.
			while((data = in.read()) != -1) {
				System.out.print((char)data); //2의보수 체계로 저장
				count++;
			}
			
			System.out.println("");
			System.out.println("count : " + count);
			System.out.println("===============================");
			
			count = 0;
			data = -1;
			while((data = is.read()) != -1) {
				System.out.print((char)data); //1byte씩 읽으므로 한글이 깨짐! utf-8 코드에는 
				count++; //utf-8 코드이고 한글 7글자이니까 총 3byte * 7 = 21byte이고, FileInputStream은 1byte씩 읽으므로 결과값은 21
			}
			System.out.println("");
			System.out.println("count : " + count);
			
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
		} catch (IOException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
