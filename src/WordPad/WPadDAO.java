package WordPad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class WPadDAO {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;

	WPadDAO() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("클래스 로드 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("클래스 로드 실패");
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

	public int insertOne(WPadDTO m) {
		if (connect()) {
			try {
				String sql = "insert into wordpad values(word_no.nextval,?)";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1, m.getWord());
//				psmt.executeUpdate();
				int k = psmt.executeUpdate();
				return k;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}return 0;
	}

	public void updateOne(WPadDTO m) {
		if (connect()) {
			String sql = "update wordpad set word=? where no=?";
			try {
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1, m.getWord());
				psmt.setInt(2, m.getNo());

				psmt.executeUpdate();

//				int k = psmt.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("DB 접속 오류");
		}
	}

	public void delOne(int no) {
		if(connect()) {
			try {
				stmt = conn.createStatement();
				String sql = "delete from wordpad where no='"+no+"'";
				stmt.executeQuery(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			
	}

	public WPadDTO selOne(int no) {
//		ArrayList<WPadDTO> wList = new ArrayList<>();
		WPadDTO searDTO = null;
		if(connect()) {
			try {
				stmt = conn.createStatement();
				String sql = "select * from wordpad where word= '"+no+"'";
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					searDTO = new WPadDTO();
					searDTO.setNo(rs.getInt("no"));
					searDTO.setWord(rs.getString("word"));
					return searDTO;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("DB 접속 오류");
		}
		return searDTO;
	}

	public ArrayList<WPadDTO> selall() {
		ArrayList <WPadDTO> wList = new ArrayList<>();
		WPadDTO searDTO = null;
		if(connect()) {
			try {
				stmt = conn.createStatement();
			String sql = "select * from wordpad";
			rs = stmt.executeQuery(sql);
			while (rs.next()){
				searDTO = new WPadDTO();
				searDTO.setNo(rs.getInt("no"));
				searDTO.setWord(rs.getString("word"));
				wList.add(searDTO);
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("DB 접속 오류");
		}
		return wList;
	}

	public int insertword(WPadDTO m, MDTO n) {
		if (connect()) {
			try {
				String sql = "insert into myword values(?,?)";
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1, n.getId());
				psmt.setString(2, m.getWord());
//				psmt.executeUpdate();
				int k = psmt.executeUpdate();
				return k;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}return 0;
	}

	public ArrayList<WPadDTO> selallword() {
		ArrayList <WPadDTO> mList = new ArrayList<>();
		WPadDTO searDTO = null;
		if(connect()) {
			try {
				stmt = conn.createStatement();
			String sql = "select * from myword";
			rs = stmt.executeQuery(sql);
			while (rs.next()){
				searDTO = new WPadDTO();
				searDTO.setId(rs.getString("id"));
				searDTO.setWord(rs.getString("word"));
				mList.add(searDTO);
			}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("DB 접속 오류");
		}
		return mList;
	}
	
}
	
	
	

