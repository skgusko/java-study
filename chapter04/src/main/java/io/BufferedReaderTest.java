package io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderTest {

	public static void main(String[] args) {
		BufferedReader br = null; //보조스트림
		
		try { 
			// 기반 스트림
			FileReader fr = new FileReader("./src/main/java/io/BufferedReaderTest.java"); //파일에 주스트림 꽂음
			
			// 보조 스트림
			br = new BufferedReader(fr); //보조스트림 연결
			
			String line = null;
			while((line = br.readLine()) != null) {
				System.out.println(line);
			}
			
			
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found : " + e);
		} catch (IOException e) {
			System.out.println("error : " + e);
		} finally {
			try {
				if (br != null) {
					br.close();
				} 
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
