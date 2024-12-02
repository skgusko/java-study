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

public class ChatWindow {

	private Frame frame;
	private Panel pannel;
	private Button buttonSend;
	private TextField textField;
	private TextArea textArea;

	public ChatWindow(String name) {
		frame = new Frame(name);
		pannel = new Panel();
		buttonSend = new Button("Send");
		textField = new TextField();
		textArea = new TextArea(30, 80);
	}

	public void show() {
		// Button
		/**
		 * 여기에서 소켓 생성하기
		 */
		buttonSend.setBackground(Color.WHITE);
		buttonSend.setForeground(Color.GRAY);
		buttonSend.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent actionEvent ) {
				System.out.println("clicked");
				sendMessage();
			}
		});
		//buttonSend.addActionListener(actionEvent -> {}); //actionEvent가 파라미터로 들어오면 뭔가를 하는 애

		// Textfield
		textField.setColumns(80);
		textField.addKeyListener(new KeyAdapter() { //엔터 시 send 되도록
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
		
		// 1. 서버 연결 작업
		// 2, IO Stream Set
		// 3. JOIN Protocol
		// 4. ChatClientThread 생성
	}
	
	private void sendMessage() {
		String message = textField.getText();
		//pw로 보내기
		System.out.println("메시지를 보내는 프로토콜 구현!: " + message);
		
		textField.setText("");
		textField.requestFocus();
		
		// ChatClientThread에서 서버로부터 받은 메세지가 있다고 치고~
		updateTextArea("아무개:" + message);
//		br 받으면 updateTextArea 호출하도록 ChatClientThread에 추가해야해
	}
	
	private void updateTextArea(String message) {
		textArea.append(message);
		textArea.append("\n");
	}
	
	private void finish() {
		//quit protocol 구현 필요 
		//pw로 보내고 br로 받아야 함. quit ok 오면 자바 프로그램 종료
		
		//exit java application 
		System.exit(0); // 정상 종료 
	}
	
	private class ChatClientThread extends Thread {
		
		@Override
		public void run() {
			updateTextArea("...");
		}
	}
}
