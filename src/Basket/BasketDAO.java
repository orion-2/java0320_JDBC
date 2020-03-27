package Basket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BasketDAO {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs; 
	private static BasketDAO BDAOobj;
	
	private BasketDAO() {
	}	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("클래스 로드 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("클래스 로드 실패");
		}
	}
	
	public static BasketDAO getInstance() {
		if (BDAOobj == null) {
			BDAOobj = new BasketDAO();
		}
		return BDAOobj;
		
	}
	
	private boolean connect() { //오라클 DB접속 자원
		boolean cFlag=false;
		try {
			conn = DriverManager.getConnection
				(""+"jdbc:oracle:thin:@localhost:1521:orcl","system","11111111");
			cFlag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cFlag;
	}
	
	public ArrayList<String[]> selAll() {
		ArrayList<String[]> bList = new ArrayList<>();
		String sql = "SELECT * FROM BASKET";
		if(connect()) {
			try {
				stmt = conn.createStatement();
				if(stmt != null) {
					rs = stmt.executeQuery(sql);
					while(rs.next()) {
						BasketDTO basket = new BasketDTO();
						basket.setNo(rs.getString("No"));
						basket.setId(rs.getString("ID"));
						basket.setName(rs.getString("NAME"));
						basket.setItem(rs.getString("ITEM"));
						basket.setQuantity(rs.getString("QUANTITY"));
						basket.setPrice(rs.getString("PRICE"));
						bList.add(basket.getArray());
						
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("DB접속 오류");
		}
		return bList;
		
	}
	
	public boolean insertOne(BasketDTO m) {
		boolean cFlag = false;
		if(connect()) {
			String sql = "INSERT INTO Basket VALUES (NO.NEXTVAL,?,?,?,?,?)";
			try {
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1, m.getId());
				psmt.setString(2, m.getName());
				psmt.setString(3, m.getItem());
				psmt.setString(4, m.getQuantity());
				psmt.setString(5, m.getPrice());
				psmt.executeUpdate();
				int r = psmt.executeUpdate();
				
				if(r > 0) {
					cFlag = true;
				}
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("DB연결 실패");
			System.exit(0);
		}
		return cFlag;
	}
	
	public void delOne(BasketDTO m) {
		if(connect()) {
			String sql = "DELETE FROM BASKET WHERE NO = ? ";
			PreparedStatement psmt;
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, m.getNo());
				psmt.executeUpdate();
				int r = psmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("DB접속 오류");
		}
	}
	
	public void modOne(BasketDTO m) {
		if(connect()) {
			String sql = "UPDATE BASKET SET ITEM=?,QUANTITY=?, WHERE ID = ? ";
			PreparedStatement psmt;
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, m.getId());
				psmt.executeUpdate();
				int r = psmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("DB접속 오류");
		}
	}
	
	
}
