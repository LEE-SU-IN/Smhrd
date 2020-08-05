package view;

import java.awt.EventQueue;

import javax.swing.JFrame;

import model.FavoritesDAO;
import model.FavoritesDTO;
import model.ProducDTO;
import model.ProductDAO;
import model.SearchDAO;
import model.UserDTO;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

import org.jfree.data.xy.XYSeries;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	JScrollPane scrollPane_compare;
	
	public SearchGUI(UserDTO udto) {	
		initialize(udto);
		frame.setVisible(true);
	}

	private void initialize(UserDTO udto) {		
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
		textField_search.setBounds(106, 6, 268, 21);
		panel_quadrant2.add(textField_search);
		
		//임시
		textField_search.setText("냉장고");
		
		JScrollPane scrollPane_search = new JScrollPane();
		scrollPane_search.setBounds(106, 37, 350, 163);
		panel_quadrant2.add(scrollPane_search);
		
		//검색테이블 - DefaultTableModel을 사용하여 맨처음 보여줄 검색테이블 생성
		Object[][] data = null;
		String[] col = {"카테고리","모델명","제품명","에너지효율등급","금액","연간에너지비용","환급여부"};
		DefaultTableModel tmodel = new DefaultTableModel(data,col);
		table_search = new JTable(tmodel);

		//검색
		JButton btn_search = new JButton("\uAC80\uC0C9");
		btn_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//검색테이블모델 초기화
				DefaultTableModel tmodel = (DefaultTableModel)table_search.getModel();
				tmodel.setNumRows(0);
				
				//입력받은 카테고리값으로 제품들의 데이터 조회
				String category = textField_search.getText();
				ArrayList<ProducDTO> dtos = dao.searchSelectList(category);
				
				//tableModelChange()로 데이터 배열구조 변경 - 테이블을 만들기 위함
				Object[][] data = tableModelChange(dtos);
				
				//검색테이블모델에 데이터 추가
				for(Object[] d : data) {
					tmodel.addRow(d);
				}
				
				//패널에 테이블 부착
				scrollPane_search.setViewportView(table_search);
			}
		});
		btn_search.setBounds(386, 5, 70, 23);
		panel_quadrant2.add(btn_search);
		
		//낮은가격순
		JRadioButton rdbtn_asc = new JRadioButton("\uB0AE\uC740\uAC00\uACA9\uC21C");
		rdbtn_asc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel tmodel = (DefaultTableModel)table_search.getModel();
				tmodel.setNumRows(0);
				
				ArrayList<ProducDTO> dtos = dao.searchSelectAsc();
				Object[][] data = tableModelChange(dtos);
				for(Object[] d : data) {
					tmodel.addRow(d);
				}
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
				DefaultTableModel tmodel = (DefaultTableModel)table_search.getModel();
				tmodel.setNumRows(0);
				
				ArrayList<ProducDTO> dtos = dao.searchSelectDesc();
				Object[][] data = tableModelChange(dtos);
				for(Object[] d : data) {
					tmodel.addRow(d);
				}
				scrollPane_search.setViewportView(table_search);
			}
		});
		buttonGroup.add(rdbtn_desc);
		rdbtn_desc.setBounds(8, 55, 90, 23);
		panel_quadrant2.add(rdbtn_desc);
		
		//검색테이블 클릭 addMousListner(MouseAdapter())
		table_search.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//비교테이블모델 0번째 행 삭제
				DefaultTableModel ctmodel = (DefaultTableModel)table_compare.getModel();
				ctmodel.removeRow(0);
				
				//클릭한 행의 행 인덱스 추출
				int row = table_search.getSelectedRow();
				
				//클릭한 행의 카테고리, 모델명, 제품명 데이터 추출
				String category = table_search.getValueAt(row, 0).toString();
				String model = table_search.getValueAt(row, 1).toString();
				String name = table_search.getValueAt(row, 2).toString();
				int pClass = (Integer)table_search.getValueAt(row, 3);
				int price = (Integer)table_search.getValueAt(row, 4);
				int eCost = (Integer)table_search.getValueAt(row, 5);
				int refund = (Integer)table_search.getValueAt(row, 6);
				
				//비교테이블모델에 데이터 추가	
				Object[] selected_sdata = {category, model, name, pClass, price, eCost, refund};
				ctmodel.insertRow(0,selected_sdata);
				
				//패널에 테이블 부착
				scrollPane_compare.setViewportView(table_compare);
			}
		});
		
		JPanel panel_quadrant3 = new JPanel();
		panel_quadrant3.setBounds(12, 241, 470, 210);
		frame.getContentPane().add(panel_quadrant3);
		panel_quadrant3.setLayout(null);
		
		JLabel lbl_favorites = new JLabel("\uC990\uACA8\uCC3E\uAE30");
		lbl_favorites.setBounds(12, 10, 60, 15);
		panel_quadrant3.add(lbl_favorites);
		
		JScrollPane scrollPane_favorites = new JScrollPane();
		scrollPane_favorites.setBounds(12, 35, 446, 165);
		panel_quadrant3.add(scrollPane_favorites);
		
		//즐겨찾기테이블
		FavoritesDAO dao = new FavoritesDAO();
		ArrayList<FavoritesDTO> dtos = dao.favoritesSelectList(udto.getId());
		Object[][] fdata = new Object[dtos.size()][3];
		for(int i=0; i<fdata.length; i++) {
			fdata[i][0] = dtos.get(i).getCategory();
			fdata[i][1] = dtos.get(i).getModel();
			fdata[i][2] = dtos.get(i).getName();
		}
		String[] fcol = {"카테고리","모델명","제품명"};
		DefaultTableModel ftmodel = new DefaultTableModel(fdata, fcol);
		table_favorites = new JTable(ftmodel);
		scrollPane_favorites.setViewportView(table_favorites);
		
		//즐겨찾기테이블 클릭 addMousListner(MouseAdapter())
		table_favorites.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//비교테이블모델의 1번째 행 삭제
				DefaultTableModel ctmodel = (DefaultTableModel)table_compare.getModel();
				ctmodel.removeRow(1);
				
				//클릭한 행의 행 인덱스 추출
				int row = table_favorites.getSelectedRow();
				
				//클릭한 행의 모델명으로 제품들의 데이터 조회
				String model = table_favorites.getValueAt(row, 1).toString();
				ProductDAO dao = new ProductDAO();
				ProducDTO dto = dao.favoritesSelect(model);
				String pModel = dto.getModel();
				String name = dto.getName();
				String category = dto.getCategory();
				int pClass = dto.getpClass();
				int maxEv = dto.getMaxEv();
				int eCost = dto.geteCost();
				int price = dto.getPrice();
				String img = dto.getImg();
				int refund = dto.getrefund();
				
				//비교테이블모델에 데이터 추가	
				Object[] selected_fdata = {category, model, name, pClass, price, eCost, refund};
				ctmodel.addRow(selected_fdata);
				
				//패널에 테이블 부착
				scrollPane_compare.setViewportView(table_compare);
			}
		});
		
		JPanel panel_quadrant1 = new JPanel();
		panel_quadrant1.setBounds(502, 10, 470, 210);
		frame.getContentPane().add(panel_quadrant1);
		panel_quadrant1.setLayout(null);
		
		JLabel lbl_select = new JLabel("\uC120\uD0DD\uD55C\uC81C\uD488");
		lbl_select.setBounds(30, 35, 74, 15);
		panel_quadrant1.add(lbl_select);
		
		JLabel lbl_favorite = new JLabel("\uC990\uACA8\uCC3E\uAE30\uC81C\uD488");
		lbl_favorite.setBounds(18, 50, 80, 15);
		panel_quadrant1.add(lbl_favorite);
		
		scrollPane_compare = new JScrollPane();
		scrollPane_compare.setBounds(98, 10, 360, 100);
		panel_quadrant1.add(scrollPane_compare);
		
		//비교테이블 - DefaultTableModel을 사용하여 맨처음 보여줄 비교테이블 생성
		String[] ccol = {"카테고리","모델명","제품명","에너지효율등급","금액","연간에너지비용","환급여부"};
		Object[][] cdata = {{null},{null}};
		DefaultTableModel ctmodel = new DefaultTableModel(cdata, ccol);
		table_compare = new JTable(ctmodel);
		
		table_compare.tableChanged(new TableModelEvent(ctmodel) {
			
		});
		
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
