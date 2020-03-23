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
			//회원등록
			home();
			String selNum = in.nextLine();
			if(selNum.equals("1")) {
				m.runThis();
			}//단어등록
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
		
	//단어조회
		//1번 단어 조회
//		WPadDTO nowWord = myDao.selOne("kiwii");
//		if(nowWord != null) {
//			System.out.println("no : " + m.getNo());
//			System.out.println("word : " + m.getWord());
//		}else {
//			System.out.println("등록되지 않은 번호입니다.");
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
		System.out.println("-- 메뉴 --");
		System.out.println(" 1. 회원관리");
		System.out.println(" 2. 단어관리");
	}

	// 단어조회
	public static void info() {
		ArrayList<WPadDTO> wList = myDao.selall();
		for (WPadDTO i : wList) {
			System.out.println(i.getNo() + ". "+ i.getWord());
		}
	}
	
	//단어검색
	public static void search() {
		System.out.println("검색할 번호를 입력하세요");
		int a = in.nextInt();
		in.nextLine();
		m.setNo(a);
		myDao.selOne(a);
			System.out.println(m.getWord());
	}
	
	//단어삭제
	public static void del() {
		info();
		System.out.println("삭제할 번호를 선택하세요");
		int a = in.nextInt();
		in.nextLine();
		m.setNo(a);
		myDao.delOne(a);
		System.out.println(m.getNo()+"번 단어가 삭제되었습니다.");
	}
	
	//단어수정
	public static void mod() {
		info();
		System.out.println("수정할 번호를 선택하세요");
		int a = in.nextInt();
		in.nextLine();
		System.out.println("단어를 입력하세요");
		String b = in.nextLine();
		m.setNo(a);
		m.setWord(b);
		myDao.updateOne(m);
			System.out.println("수정되었습니다");
	}
	
	//단어등록
	public static void input() {
		System.out.println("등록할 단어를 입력하세요");
		String a =in.nextLine();
		m.setWord(a);
		int k = myDao.insertOne(m);
		if (k == 1) {
			System.out.println("성공");
		}else {
			System.out.println("실패");
		}
		
//		m = new WPadDTO();
//		m.setWord("chair");
//		myDao.insertOne(m);
	}
	public static void add() {
		System.out.println("ID를 입력하세요");
		String a =in.nextLine();
		n.setId(a); //멤버ID
		System.out.println("추가할 단어를 입력하세요");
		String b =in.nextLine();
		m.setWord(b);
		 myDao.insertword(m,n);
		
	}

	public static void menu() {
		System.out.println("-- 메뉴 --");
		System.out.println(" 1. 등록");
		System.out.println(" 2. 수정");
		System.out.println(" 3. 삭제");
		System.out.println(" 4. 검색");
		System.out.println(" 5. 조회");
		System.out.println(" 6. 나의단어추가");
		System.out.println(" 7. 나의단어목록");
		System.out.println(" 8. 이전화면");
	
	}

}
