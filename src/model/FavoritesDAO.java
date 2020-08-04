package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FavoritesDAO {
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
	
	public ArrayList<FavoritesDTO> favoritesSelectList(String id){
		getConnect();
		String sql = "select * from favorites where id=?";
		ArrayList<FavoritesDTO> dtos = new ArrayList<FavoritesDTO>();
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id);
			rs = pstm.executeQuery();
			while(rs.next()) {
				String uId = rs.getString(1);
				String model = rs.getString(2);
				String name = rs.getString(3);
				String category = rs.getString(4);
				FavoritesDTO dto = new FavoritesDTO(uId, model, name, category);
				dtos.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dtos;
	}
}
