package Member;

import java.sql.Connection; //db�� �����ϱ� ���� �ڿ�
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
	private ResultSet rs; //Ʃ���� ��°�� �����ϰ� ���� Ʃ�������� ���
	
	
	MemberDAO(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Ŭ���� �ε� ����");
		} catch (ClassNotFoundException e) {
			System.out.println("Ŭ���� �ε� ����");
		}
	}
	
	private boolean connect() { //oracle DB�� �����ϱ� ���� �ڿ�
		boolean cFlag=false;
		try {//Ŀ�ؼ��ڿ��� sql�� ����
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
			System.out.println("DB ���� ����");
		}
	}
	public int insertOne(MemberDTO m) {
		//oracle�� m������ ����Ű�� ��ü�� ����
		//���� : db:�� ���� > sql query�� �������Ѵ� > query ����
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
			System.out.println("DB ���� ����");
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
			System.out.println("DB ���� ����");
		}
	}

	public MemberDTO selOne(String id) {
		MemberDTO searDTO = null;
		if(connect()) {
			try {
				stmt = conn.createStatement();
				String sql = "select * from mmember where id = '"+id+"'";
				rs = stmt.executeQuery(sql);
				if (rs.next()) { //���� rs�� �ִٸ�
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
			System.out.println("DB ���� ����");
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
				while (rs.next()) { //���� rs�� �ִٸ�
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
			System.out.println("DB ���� ����");
		}
		return uList;
	}
	
}
