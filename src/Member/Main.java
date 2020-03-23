package Member;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//DAO객체 생성
		MemberDAO myDao = new MemberDAO();
		
		
		// 1. 데이터 삽입
		MemberDTO a = new MemberDTO(); //변수를 한번에 쉽게 넘겨주기위해
//		a.setId("aaa");
//		a.setName("aname");
//		int k = myDao.insertOne(a);
//		if (k==1) {
//			System.out.println("성공");
//		}else if (k==0) {
//			System.out.println("실패");
//		}
//		
//		// 2. 데이터 삽입
//		a = new MemberDTO();
//		a.setId("bbb");
//		a.setName("bname");
//		myDao.insertOne(a);
		
		//2-1. 갱신
		//시나리오는 : aaa 라는 사람의 이름을 kim 변경
//		update mmember set name = 'kim' where id='aaa';
		a.setId("aaa");
		a.setName("kim");
		myDao.updateOne(a);
//		2-2. 삭제
//		시나리오 : id aaa 탈퇴함 관련 튜플삭제
//		a.setId("aaa");
//		myDao.delOne("aaa");

		//2-3. id aaa 정보만 가져오기
		
		MemberDTO nowUser = myDao.selOne("aaa");
		if(nowUser != null) {
		System.out.println("id : " +nowUser.getId());
		System.out.println("name : " +nowUser.getName());
		System.out.println("point : " +nowUser.getPoint());
		}else {
			System.out.println("등록된 회원이 아닙니다.");
		}
		
		//3. 데이터 모두 가져오기
//		ArrayList <MemberDTO> cList = myDao.selall();
//		
//		for(MemberDTO i : cList) {
//				System.out.println("id : " + i.getId());
//				System.out.println("name : " + i.getName());
//				System.out.println("point : " + i.getPoint());
//				
//		}
		
	}

}
