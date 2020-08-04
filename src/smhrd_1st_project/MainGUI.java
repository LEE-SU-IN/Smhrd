package smhrd_1st_project;

import java.awt.EventQueue;

import javax.swing.JFrame;

import model.UserDTO;
import view.SearchGUI;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainGUI {

	private JFrame frame;
	
	//main()
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//MainGUI()
	public MainGUI() {
		initialize();
	}
	
	//initialize()
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//���� ������ ��ư
		JButton btnNewButton = new JButton("\uC815\uBCF4 \uBCF4\uB0B4\uAE30");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//�α������� ����� ������ ������ (������)
				UserDTO dto = new UserDTO("userId1", "1234", "userName1", "01012341234");
				SearchGUI search = new SearchGUI(dto);
				
				//���� â ����
				frame.dispose();
			}
		});
		btnNewButton.setBounds(12, 10, 120, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
