package WordPad;

import java.util.ArrayList;
import java.util.Scanner;


public class Member {
		
	public static MDAO myDao = new MDAO();
	public static MDTO m = new MDTO();
	public static Scanner in = new Scanner(System.in);
	
	public void runThis() {
		for(;;) {
			//회원관리
			menu();
			String selNum = in.nextLine();
			if(selNum.equals("1")) {
			input();   //등록
			}else if(selNum.equals("2")) {
			mod();	//수정
			}else if(selNum.equals("3")) {
			del(); //삭제
			}else if(selNum.equals("4")) {
			search();	//조회
			}else if(selNum.equals("5")) {
			info();   //회원목록
			}else if(selNum.equals("6")) {
			Main.home(); break;   //이전화면
			}
		}
	}
	public static void menu() {
		System.out.println("-- 메뉴 --");
		System.out.println(" 1. 회원등록");
		System.out.println(" 2. 회원수정");
		System.out.println(" 3. 회원삭제");
		System.out.println(" 4. 회원검색");
		System.out.println(" 5. 회원조회");
		System.out.println(" 6. 이전화면");
		
	}
	public static void input() {
		System.out.println("ID를 입력하세요");
		String a =in.nextLine();
		m.setId(a);
		System.out.println("PW를 입력하세요");
		int b = in.nextInt();
		in.nextLine();
		m.setPw(b);
		int k = myDao.insertOne(m);
		if (k == 1) {
			System.out.println("성공");
		}else {
			System.out.println("실패");
		}
	}
	public static void mod() {
		info();
		System.out.println("수정할 Id를 선택하세요");
		String a = in.nextLine();
		System.out.println("수정할 Pw를 입력하세요");
		int b = in.nextInt();
		in.nextLine();
		m.setId(a);
		m.setPw(b);
		myDao.updateOne(m);
			System.out.println("수정되었습니다");
	}
	public static void del() {
		info();
		System.out.println("삭제할 ID를 선택하세요");
		String a = in.nextLine();
		m.setId(a);
		myDao.delOne(a);
		System.out.println("Id가 삭제되었습니다.");
	}
	public static void search() {
		System.out.println("Pw찾을 Id를 입력하세요");
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
