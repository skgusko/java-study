package chat.gui;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Base64;

import chat.ChatServer;

public class ChatWindow {

	private Frame frame;
	private Panel pannel;
	private Button buttonSend;
	private TextField textField;
	private TextArea textArea;
	private Socket socket;
	private PrintWriter pw;
	private BufferedReader br;
	private Thread clientThread;

	public ChatWindow(String name) {
		frame = new Frame(name); //GUI 창 제목 설정 
		pannel = new Panel();
		buttonSend = new Button("Send");
		textField = new TextField();
		textArea = new TextArea(30, 80);
	}

	public void show() {
		// Button
		buttonSend.setBackground(Color.WHITE);
		buttonSend.setForeground(Color.GRAY);
		buttonSend.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent actionEvent ) {
				System.out.println("clicked");
				sendMessage();
			}
		});
		//buttonSend.addActionListener(actionEvent -> {}); //actionEvent가 파라미터로 들어오면 특정 액션 취함  
		
		// Textfield
		textField.setColumns(80);
		textField.addKeyListener(new KeyAdapter() { //엔터 시 send 되도록 설정 
			@Override
			public void keyPressed(KeyEvent e) {
				char keyChar = e.getKeyChar();
				if (keyChar == KeyEvent.VK_ENTER) {
					sendMessage();
				}
			}
		});

		// Pannel
		pannel.setBackground(Color.LIGHT_GRAY);
		pannel.add(textField);
		pannel.add(buttonSend);
		frame.add(BorderLayout.SOUTH, pannel);

		// TextArea
		textArea.setEditable(false);
		frame.add(BorderLayout.CENTER, textArea);

		// Frame
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				finish();
			}
		});
		
		frame.setVisible(true);
		frame.pack();
		
		try {
			// 1. 서버 연결 작업
			socket = new Socket();
			socket.connect(new InetSocketAddress("127.0.0.1", ChatServer.PORT));
			
			// 2. IO Stream Set
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			
			// 3. JOIN Protocol
			String title = frame.getTitle();
			pw.println("JOIN:" + title); //생성 시 초기화 한 name
			pw.flush();
			
			// 4. ChatClientThread 생성
			clientThread = new ChatClientThread();
			clientThread.start();
		
		} catch (SocketException e) {
			log("error : " + e);
		} catch (IOException e) {
			log("error : " + e);
		} 
	}
	

	public static void log(String message) {
		System.out.println("[client] : " + message);
	}
	
	private void sendMessage() {
		String message = textField.getText();
		
		if (message.trim().isEmpty()) {
//		if ("".equals(message)) {
			updateTextArea("메시지를 입력해주세요");
			return;
		}
		
		
		if ("QUIT".equals(message) || "quit".equals(message)) {
			finish();
		}
		
		//MSG
		//여기에서 인코딩 
		String encodedMsg = Base64.getEncoder().encodeToString(message.getBytes());
		pw.println("MSG:" + encodedMsg); //server에 보낼 메시지
		pw.flush();
		
		textField.setText("");
		textField.requestFocus();
		
		// ChatClientThread에서 서버로부터 받은 메세지가 있다고 치고~
		//updateTextArea("아무개:" + message);
//		br 받으면 updateTextArea 호출하도록 ChatClientThread에 추가해야 
		//pw.println("MSG:" + message);
		
		
	}
	
	private void updateTextArea(String message) {
		textArea.append(message);
		textArea.append("\n");
	}
	
	private void finish() {
		pw.println("QUIT"); //프로토콜 형태로 보내기
		pw.flush();
		
		try {
			//join으로 기다리기
			//exit java application 
			clientThread.join();
			
			if (socket != null && !socket.isClosed()) {
				socket.close();
			}
		
			System.exit(0); // 정상 종료
		
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private class ChatClientThread extends Thread {
		
		@Override
		public void run() {
			try {
				while (true) {
					String response = br.readLine();
					if (response == null) { 
						log("closed by client");
						return;
					}
					
					String[] tokens = response.split(":");
					if ("JOIN".equals(tokens[0])) {
						if ("OK".equals(tokens[1])) {
							updateTextArea("입장하였습니다. 즐거운 채팅 되세요!");
							continue;
						}
					}
					else if ("QUIT".equals(tokens[0])) {
						if ("OK".equals(tokens[1])) {
							updateTextArea("대화가 종료되었습니다.");
							break;
						}
					}
					
					updateTextArea(response);

				}
				
			} catch (IOException e) {
				System.out.println(e);
			}
		}
	}
}
