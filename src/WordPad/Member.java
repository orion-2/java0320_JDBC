package WordPad;

import java.util.ArrayList;
import java.util.Scanner;


public class Member {
		
	public static MDAO myDao = new MDAO();
	public static MDTO m = new MDTO();
	public static Scanner in = new Scanner(System.in);
	
	public void runThis() {
		for(;;) {
			//ȸ������
			menu();
			String selNum = in.nextLine();
			if(selNum.equals("1")) {
			input();   //���
			}else if(selNum.equals("2")) {
			mod();	//����
			}else if(selNum.equals("3")) {
			del(); //����
			}else if(selNum.equals("4")) {
			search();	//��ȸ
			}else if(selNum.equals("5")) {
			info();   //ȸ�����
			}else if(selNum.equals("6")) {
			Main.home(); break;   //����ȭ��
			}
		}
	}
	public static void menu() {
		System.out.println("-- �޴� --");
		System.out.println(" 1. ȸ�����");
		System.out.println(" 2. ȸ������");
		System.out.println(" 3. ȸ������");
		System.out.println(" 4. ȸ���˻�");
		System.out.println(" 5. ȸ����ȸ");
		System.out.println(" 6. ����ȭ��");
		
	}
	public static void input() {
		System.out.println("ID�� �Է��ϼ���");
		String a =in.nextLine();
		m.setId(a);
		System.out.println("PW�� �Է��ϼ���");
		int b = in.nextInt();
		in.nextLine();
		m.setPw(b);
		int k = myDao.insertOne(m);
		if (k == 1) {
			System.out.println("����");
		}else {
			System.out.println("����");
		}
	}
	public static void mod() {
		info();
		System.out.println("������ Id�� �����ϼ���");
		String a = in.nextLine();
		System.out.println("������ Pw�� �Է��ϼ���");
		int b = in.nextInt();
		in.nextLine();
		m.setId(a);
		m.setPw(b);
		myDao.updateOne(m);
			System.out.println("�����Ǿ����ϴ�");
	}
	public static void del() {
		info();
		System.out.println("������ ID�� �����ϼ���");
		String a = in.nextLine();
		m.setId(a);
		myDao.delOne(a);
		System.out.println("Id�� �����Ǿ����ϴ�.");
	}
	public static void search() {
		System.out.println("Pwã�� Id�� �Է��ϼ���");
		String a = in.nextLine();
		m.setId(a);
		myDao.selOne(a);
			System.out.println(m.getPw());
	}
	public static void info() {
		ArrayList<MDTO> mList = myDao.selall();
		for (MDTO i : mList) {
			System.out.println(i.getId());
		}
	}
	
}
