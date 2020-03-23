package WordPad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MDAO {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;

	MDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
		}
	}

	private boolean connect() {
		boolean cFlag = false;
		try {
			conn = DriverManager.getConnection("" + "jdbc:oracle:thin:@localhost:1521:orcl", "system", "11111111");
			cFlag = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cFlag;
	}

	public int insertOne(MDTO m) {
		if (connect()) {
			try {
				String sql = "insert into member values(?,?)";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1, m.getId());
				psmt.setInt(2, m.getPw());
//				psmt.executeUpdate();
				int k = psmt.executeUpdate();
				return k;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}return 0;
	}

	public void updateOne(MDTO m) {
		if (connect()) {
			String sql = "update member set pw=? where id=?";
			try {
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setInt(1, m.getPw());
				psmt.setString(2, m.getId());

				psmt.executeUpdate();

//				int k = psmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("DB 立加 坷幅");
		}
	}

	public void delOne(String id) {
		if(connect()) {
			try {
				stmt = conn.createStatement();
				String sql = "delete from member where id='"+id+"'";
				stmt.executeQuery(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
	}

	public MDTO selOne(String id) {
//		ArrayList<MDTO> mList = new ArrayList<>();
		MDTO searDTO = null;
		if(connect()) {
			try {
				stmt = conn.createStatement();
				String sql = "select * from member where id= '"+id+"'";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					searDTO = new MDTO();
					searDTO.setId(rs.getString("id"));
					System.out.println(rs.getString("pw"));
					return searDTO;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("DB 立加 坷幅");
		}
		return searDTO;
	}

	public ArrayList<MDTO> selall() {
		ArrayList <MDTO> mList = new ArrayList<>();
		MDTO searDTO = null;
		if(connect()) {
			try {
				stmt = conn.createStatement();
			String sql = "select * from member";
			rs = stmt.executeQuery(sql);
			while (rs.next()){
				searDTO = new MDTO();
				searDTO.setId(rs.getString("id"));
				searDTO.setPw(rs.getInt("pw"));
				mList.add(searDTO);
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("DB 立加 坷幅");
		}
		return mList;
	}
	
}
