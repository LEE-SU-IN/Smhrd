package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchDAO {
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	
	public void getConnect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
			String user_id = "hr";
			String user_pw = "hr";
			conn = DriverManager.getConnection(url,user_id,user_pw);
			
			if(conn != null) {
				//System.out.println("연결 성공");
			}else {
				//System.out.println("연결 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {

		try {
			if(rs != null) {
				rs.close();
			}
			if(pstm != null) {
				pstm.close();
			}
			if(conn != null) {
				conn.close();
			}				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//searchSelectList()
	public ArrayList<ProducDTO> searchSelectList(String category){
		getConnect();
		String sql = "select * from product where category=?";
		ArrayList<ProducDTO> dtos = new ArrayList<ProducDTO>();
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, category);
			rs = pstm.executeQuery();
			while(rs.next()) {
				String model = rs.getString(1);
				String name = rs.getString(2);
				String pCategory = rs.getString(3);
				int pClass = rs.getInt(4);
				int maxEv = rs.getInt(5);
				int eCost = rs.getInt(6);
				int price = rs.getInt(7);
				String img = rs.getString(8);
				int refund = rs.getInt(9);
				ProducDTO dto = new ProducDTO(model, name, pCategory, pClass, maxEv, eCost, price, img, refund);
				dtos.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return dtos;
	}
	
	//searchSelectAsc()
	public ArrayList<ProducDTO> searchSelectAsc(){
		getConnect();
		String sql = "select * from product order by price";
		ArrayList<ProducDTO> dtos = new ArrayList<ProducDTO>();
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				String model = rs.getString(1);
				String name = rs.getString(2);
				String category = rs.getString(3);
				int pClass = rs.getInt(4);
				int maxEv = rs.getInt(5);
				int eCost = rs.getInt(6);
				int price = rs.getInt(7);
				String img = rs.getString(8);
				int refund = rs.getInt(9);
				ProducDTO dto = new ProducDTO(model, name, category, pClass, maxEv, eCost, price, img, refund);
				dtos.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return dtos;
	}
	
	//searchSelectDesc()
	public ArrayList<ProducDTO> searchSelectDesc(){
		getConnect();
		String sql = "select * from product order by price desc";
		ArrayList<ProducDTO> dtos = new ArrayList<ProducDTO>();
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				String model = rs.getString(1);
				String name = rs.getString(2);
				String category = rs.getString(3);
				int pClass = rs.getInt(4);
				int maxEv = rs.getInt(5);
				int eCost = rs.getInt(6);
				int price = rs.getInt(7);
				String img = rs.getString(8);
				int refund = rs.getInt(9);
				ProducDTO dto = new ProducDTO(model, name, category, pClass, maxEv, eCost, price, img, refund);
				dtos.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return dtos;
	}
}
