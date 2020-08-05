package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAO {
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
	
	public ProducDTO favoritesSelect(String model) {
		getConnect();
		String sql = "select * from product where model=?";
		ProducDTO dto = null;
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, model);
			rs = pstm.executeQuery();
			while(rs.next()) {
				String pModel = rs.getString(1);
				String name = rs.getString(2);
				String category = rs.getString(3);
				int pClass = rs.getInt(4);
				int maxEv = rs.getInt(5);
				int eCost = rs.getInt(6);
				int price = rs.getInt(7);
				String img = rs.getString(8);
				int refund = rs.getInt(9);
				dto = new ProducDTO(pModel, name, category, pClass, maxEv, eCost, price, img, refund);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return dto;
	}
}
