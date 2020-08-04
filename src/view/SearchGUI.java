package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import model.ProducDTO;
import model.SearchDAO;
import model.UserDTO;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class SearchGUI {
	
	private JFrame frame;
	private JTextField textField_search;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTable table_search;
	private JTable table_favorites;
	private JTable table_compare;
	
	SearchDAO dao = new SearchDAO();
	int ck1, ck2, ck3 = -1;
	
	public SearchGUI(UserDTO dto) {	
		initialize(dto);
		frame.setVisible(true);
	}

	private void initialize(UserDTO dto) {		
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel_quadrant2 = new JPanel();
		panel_quadrant2.setBounds(12, 10, 470, 210);
		frame.getContentPane().add(panel_quadrant2);
		panel_quadrant2.setLayout(null);
		
		JLabel lbl_price = new JLabel("\uAC00\uACA9");
		lbl_price.setBounds(12, 9, 30, 15);
		panel_quadrant2.add(lbl_price);
		
		textField_search = new JTextField();
		textField_search.setBounds(156, 6, 240, 21);
		panel_quadrant2.add(textField_search);
		
		//임시
		textField_search.setText("냉장고");
		
		JScrollPane scrollPane_search = new JScrollPane();
		scrollPane_search.setBounds(156, 37, 302, 141);
		panel_quadrant2.add(scrollPane_search);

		//검색
		JButton btn_search = new JButton("\uAC80\uC0C9");
		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//입력받은 카테고리값으로 제품들의 정보 조회
				String category = textField_search.getText();
				ArrayList<ProducDTO> dtos = dao.searchSelectList(category);
				//tableModelChange()로 데이터 배열구조 변경 - 테이블을 만들기 위함
				Object[][] data = tableModelChange(dtos);
				String[] col = {"카테고리","모델명","제품명","에너지효율등급","금액","연간에너지비용","환급여부"};
				table_search = new JTable(data,col); 				
				scrollPane_search.setViewportView(table_search);
			}
		});
		btn_search.setBounds(398, 5, 60, 23);
		panel_quadrant2.add(btn_search);
		
		//낮은가격순
		JRadioButton rdbtn_asc = new JRadioButton("\uB0AE\uC740\uAC00\uACA9\uC21C");
		rdbtn_asc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//이전테이블 삭제
				scrollPane_search.remove(table_search);
				ArrayList<ProducDTO> dtos = dao.searchSelectAsc();
				Object[][] data = tableModelChange(dtos);
				String[] col = {"카테고리","모델명","제품명","에너지효율등급","금액","연간에너지비용","환급여부"};
				table_search = new JTable(data,col);
				scrollPane_search.setViewportView(table_search);
			}
		});
		buttonGroup.add(rdbtn_asc);
		rdbtn_asc.setBounds(8, 30, 90, 23);
		panel_quadrant2.add(rdbtn_asc);
		
		//높은가격순
		JRadioButton rdbtn_desc = new JRadioButton("\uB192\uC740\uAC00\uACA9\uC21C");
		rdbtn_desc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPane_search.remove(table_search);
				ArrayList<ProducDTO> dtos = dao.searchSelectDesc();
				Object[][] data = tableModelChange(dtos);
				String[] col = {"카테고리","모델명","제품명","에너지효율등급","금액","연간에너지비용","환급여부"};
				table_search = new JTable(data,col);
				scrollPane_search.setViewportView(table_search);
			}
		});
		buttonGroup.add(rdbtn_desc);
		rdbtn_desc.setBounds(8, 55, 90, 23);
		panel_quadrant2.add(rdbtn_desc);
		
		JLabel lbl_eClass = new JLabel("\uC5D0\uB108\uC9C0\uD6A8\uC728\uB4F1\uAE09");
		lbl_eClass.setBounds(12, 84, 90, 15);
		panel_quadrant2.add(lbl_eClass);
		
		JCheckBox chckbx_1 = new JCheckBox("1\uB4F1\uAE09");
		chckbx_1.setBounds(8, 105, 115, 23);
		panel_quadrant2.add(chckbx_1);
		
		JCheckBox chckbx_2 = new JCheckBox("2\uB4F1\uAE09");
		chckbx_2.setBounds(8, 130, 115, 23);
		panel_quadrant2.add(chckbx_2);
		
		JCheckBox chckbx_3 = new JCheckBox("3\uB4F1\uAE09");
		chckbx_3.setBounds(8, 155, 115, 23);
		panel_quadrant2.add(chckbx_3);
		
		JPanel panel_quadrant3 = new JPanel();
		panel_quadrant3.setBounds(12, 241, 470, 210);
		frame.getContentPane().add(panel_quadrant3);
		panel_quadrant3.setLayout(null);
		
		JLabel lbl_favorites = new JLabel("\uC990\uACA8\uCC3E\uAE30");
		lbl_favorites.setBounds(12, 10, 50, 15);
		panel_quadrant3.add(lbl_favorites);
		
		JScrollPane scrollPane_favorites = new JScrollPane();
		scrollPane_favorites.setBounds(12, 35, 446, 165);
		panel_quadrant3.add(scrollPane_favorites);
		
		table_favorites = new JTable();
		scrollPane_favorites.setViewportView(table_favorites);
		
		JPanel panel_quadrant1 = new JPanel();
		panel_quadrant1.setBounds(502, 10, 470, 210);
		frame.getContentPane().add(panel_quadrant1);
		panel_quadrant1.setLayout(null);
		
		JLabel lbl_select = new JLabel("\uC120\uD0DD\uD55C\uC81C\uD488");
		lbl_select.setBounds(12, 70, 74, 15);
		panel_quadrant1.add(lbl_select);
		
		JLabel lbl_favorite = new JLabel("\uC990\uACA8\uCC3E\uAE30\uC81C\uD488");
		lbl_favorite.setBounds(12, 95, 74, 15);
		panel_quadrant1.add(lbl_favorite);
		
		JScrollPane scrollPane_compare = new JScrollPane();
		scrollPane_compare.setBounds(98, 10, 360, 100);
		panel_quadrant1.add(scrollPane_compare);
		
		table_compare = new JTable();
		scrollPane_compare.setViewportView(table_compare);
		
		JLabel lbl_realPrice = new JLabel("New label");
		lbl_realPrice.setBounds(81, 120, 377, 80);
		panel_quadrant1.add(lbl_realPrice);
		
		JPanel panel_quadrant4 = new JPanel();
		panel_quadrant4.setBounds(502, 241, 470, 210);
		frame.getContentPane().add(panel_quadrant4);
		panel_quadrant4.setLayout(null);
	}
	
	//tableModelChange()
	public Object[][] tableModelChange(ArrayList<ProducDTO> dtos) {
		Object[][] data = new Object[dtos.size()][9];
		for(int i=0; i<data.length; i++) {
			data[i][0] = dtos.get(i).getCategory();
			data[i][1] = dtos.get(i).getModel();
			data[i][2] = dtos.get(i).getName();
			data[i][3] = dtos.get(i).getpClass();
			data[i][4] = dtos.get(i).getPrice();
			data[i][5] = dtos.get(i).geteCost();
			data[i][6] = dtos.get(i).getrefund();	
		}
		return data;
	}
}
