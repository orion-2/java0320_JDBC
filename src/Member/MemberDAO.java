package Member;

import java.sql.Connection; //db에 접근하기 위한 자원
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.Test;  

public class MemberDAO {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs; //튜플을 통째로 저장하고 다음 튜플저장을 대기
	
	
	MemberDAO(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("클래스 로드 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("클래스 로드 실패");
		}
	}
	
	private boolean connect() { //oracle DB에 접속하기 위한 자원
		boolean cFlag=false;
		try {//커넥션자원을 sql과 연결
			conn = DriverManager.getConnection(""+"jdbc:oracle:thin:@localhost:1521:orcl","system","11111111");
			cFlag=true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cFlag;
	}
	public void updateOne(MemberDTO m) {
		if(connect()) {
			String sql = "update mmember set name=? where id=?";
			try {
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1, m.getName());
				psmt.setString(2, m.getId());
				
				psmt.executeUpdate();
				
				int k = psmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("DB 접속 오류");
		}
	}
	public int insertOne(MemberDTO m) {
		//oracle에 m변수가 가리키는 객체를 저장
		//절차 : db:에 접속 > sql query를 만들어야한다 > query 실행
		if(connect()) {
			String sql = "insert into mmember values(mmember_no.nextval,?,?,5)";
			try {
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1, m.getId());
				psmt.setString(2, m.getName());
				
				psmt.executeUpdate();
				
				int k = psmt.executeUpdate();
				return k;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("DB 접속 오류");
		}
		return 0;
		}
	
	
	public void delOne(String id) {
		if(connect()) {
			try {
				stmt = conn.createStatement();
				String sql = "delete from mmember where id = '"+id+"'";
				stmt.executeQuery(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public void deleteOne(MemberDTO m) {
		if(connect()) {
			String sql = "delete from mmember where id=?";
			try {
				PreparedStatement psmt = conn.prepareStatement(sql);
				psmt.setString(1, m.getId());
				
				psmt.executeUpdate();
				
				int k = psmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("DB 접속 오류");
		}
	}

	public MemberDTO selOne(String id) {
		MemberDTO searDTO = null;
		if(connect()) {
			try {
				stmt = conn.createStatement();
				String sql = "select * from mmember where id = '"+id+"'";
				rs = stmt.executeQuery(sql);
				if (rs.next()) { //다음 rs가 있다면
					searDTO = new MemberDTO();
					searDTO.setName(rs.getString("name"));
					searDTO.setId(rs.getString("id"));
					searDTO.setPoint(rs.getFloat("Point"));
					return searDTO;
				} 
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("DB 접속 오류");
		}
		return searDTO;
	}

	public ArrayList<MemberDTO> selall() {
		ArrayList<MemberDTO> uList = new ArrayList<>();
		MemberDTO searDTO = null;
		if(connect()) {
			try {
				stmt = conn.createStatement();
				String sql = "select * from mmember";
				rs = stmt.executeQuery(sql);
				while (rs.next()) { //다음 rs가 있다면
					searDTO = new MemberDTO();
					searDTO.setName(rs.getString("name"));
					searDTO.setId(rs.getString("id"));
					searDTO.setPoint(rs.getFloat("Point"));
					uList.add(searDTO);	
					
				} 
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("DB 접속 오류");
		}
		return uList;
	}
	
}
