package WordPad;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static WPadDAO myDao = new WPadDAO();
	public static WPadDTO m = new WPadDTO();
	public static MDTO n = new MDTO();
	public static Scanner in = new Scanner(System.in);
	public static void main(String[] args) {
		Member m = new Member();
		for(;;) {
			//ȸ�����
			home();
			String selNum = in.nextLine();
			if(selNum.equals("1")) {
				m.runThis();
			}//�ܾ���
			else if(selNum.equals("2")) {
				menu();
				String selNum1 = in.nextLine();
				if(selNum1.equals("1")) {
				input();   // 1
				}else if(selNum1.equals("2")) {
				mod();
				}else if(selNum1.equals("3")) {
				del();
				}else if(selNum1.equals("4")) {
				search();
				}else if(selNum1.equals("5")) {
				info();   // 2
				}else if(selNum1.equals("6")) {
				add();
				}else if(selNum1.equals("7")) {
				myword();
				}else if(selNum1.equals("8")) {
				home(); break;
				}
			}
		}
		
	//�ܾ���ȸ
		//1�� �ܾ� ��ȸ
//		WPadDTO nowWord = myDao.selOne("kiwii");
//		if(nowWord != null) {
//			System.out.println("no : " + m.getNo());
//			System.out.println("word : " + m.getWord());
//		}else {
//			System.out.println("��ϵ��� ���� ��ȣ�Դϴ�.");
//		}
		
//		
	}
	
	private static void myword() {
		ArrayList<WPadDTO> mList = myDao.selallword();
		for (WPadDTO i : mList) {
			System.out.println(i.getId());
			System.out.println(i.getWord());

		}
	}

	public static void home() {
		System.out.println("-- �޴� --");
		System.out.println(" 1. ȸ������");
		System.out.println(" 2. �ܾ����");
	}

	// �ܾ���ȸ
	public static void info() {
		ArrayList<WPadDTO> wList = myDao.selall();
		for (WPadDTO i : wList) {
			System.out.println(i.getNo() + ". "+ i.getWord());
		}
	}
	
	//�ܾ�˻�
	public static void search() {
		System.out.println("�˻��� ��ȣ�� �Է��ϼ���");
		int a = in.nextInt();
		in.nextLine();
		m.setNo(a);
		myDao.selOne(a);
			System.out.println(m.getWord());
	}
	
	//�ܾ����
	public static void del() {
		info();
		System.out.println("������ ��ȣ�� �����ϼ���");
		int a = in.nextInt();
		in.nextLine();
		m.setNo(a);
		myDao.delOne(a);
		System.out.println(m.getNo()+"�� �ܾ �����Ǿ����ϴ�.");
	}
	
	//�ܾ����
	public static void mod() {
		info();
		System.out.println("������ ��ȣ�� �����ϼ���");
		int a = in.nextInt();
		in.nextLine();
		System.out.println("�ܾ �Է��ϼ���");
		String b = in.nextLine();
		m.setNo(a);
		m.setWord(b);
		myDao.updateOne(m);
			System.out.println("�����Ǿ����ϴ�");
	}
	
	//�ܾ���
	public static void input() {
		System.out.println("����� �ܾ �Է��ϼ���");
		String a =in.nextLine();
		m.setWord(a);
		int k = myDao.insertOne(m);
		if (k == 1) {
			System.out.println("����");
		}else {
			System.out.println("����");
		}
		
//		m = new WPadDTO();
//		m.setWord("chair");
//		myDao.insertOne(m);
	}
	public static void add() {
		System.out.println("ID�� �Է��ϼ���");
		String a =in.nextLine();
		n.setId(a); //���ID
		System.out.println("�߰��� �ܾ �Է��ϼ���");
		String b =in.nextLine();
		m.setWord(b);
		 myDao.insertword(m,n);
		
	}

	public static void menu() {
		System.out.println("-- �޴� --");
		System.out.println(" 1. ���");
		System.out.println(" 2. ����");
		System.out.println(" 3. ����");
		System.out.println(" 4. �˻�");
		System.out.println(" 5. ��ȸ");
		System.out.println(" 6. ���Ǵܾ��߰�");
		System.out.println(" 7. ���Ǵܾ���");
		System.out.println(" 8. ����ȭ��");
	
	}

}
