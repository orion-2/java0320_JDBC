package Member;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//DAO��ü ����
		MemberDAO myDao = new MemberDAO();
		
		
		// 1. ������ ����
		MemberDTO a = new MemberDTO(); //������ �ѹ��� ���� �Ѱ��ֱ�����
//		a.setId("aaa");
//		a.setName("aname");
//		int k = myDao.insertOne(a);
//		if (k==1) {
//			System.out.println("����");
//		}else if (k==0) {
//			System.out.println("����");
//		}
//		
//		// 2. ������ ����
//		a = new MemberDTO();
//		a.setId("bbb");
//		a.setName("bname");
//		myDao.insertOne(a);
		
		//2-1. ����
		//�ó������� : aaa ��� ����� �̸��� kim ����
//		update mmember set name = 'kim' where id='aaa';
		a.setId("aaa");
		a.setName("kim");
		myDao.updateOne(a);
//		2-2. ����
//		�ó����� : id aaa Ż���� ���� Ʃ�û���
//		a.setId("aaa");
//		myDao.delOne("aaa");

		//2-3. id aaa ������ ��������
		
		MemberDTO nowUser = myDao.selOne("aaa");
		if(nowUser != null) {
		System.out.println("id : " +nowUser.getId());
		System.out.println("name : " +nowUser.getName());
		System.out.println("point : " +nowUser.getPoint());
		}else {
			System.out.println("��ϵ� ȸ���� �ƴմϴ�.");
		}
		
		//3. ������ ��� ��������
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
