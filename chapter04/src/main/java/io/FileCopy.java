package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileCopy {

	public static void main(String[] args) {
		InputStream is = null;
		OutputStream os = null;
		try {	
			is = new FileInputStream("loopy.jpeg");
			os = new FileOutputStream("loopy.copy.jpeg");
			
			int data = -1;
			while((data = is.read()) != -1) {
				os.write(data);
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("file not found : " + e);
		} catch (IOException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				if (is != null) { //파일 없는 경우 is가 null이니까 is.close() 하면 NullPointerException 발생
					is.close();
				}
				if (os != null) {
					os.close();
				}
				is.close();
			} catch (IOException e) {  
				e.printStackTrace();
			}
		}

	}

}
