package print;

import java.util.List;

public class Print {
	
	public void printBar() {
		System.out.println("-----------------------------------------------");
	}
	public void printLn(int num) {
		for(int i=0; i<num; i++)
			System.out.println();
	}

	public void printHome() {
		System.out.println("HOME");
		printBar();
		System.out.println("1. 검색");
		System.out.println("2. 최근 리뷰 보기");
		System.out.println("3. 리뷰, 식당 등록");
		System.out.println("4. 점메추");
		System.out.println("5. 마이 페이지");
		printLn(1);
		printBar();
	}
	
	public void print_member() {
		System.out.println("로그인");
		printBar();
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("3. 아이디, 비밀번호 찾기");
		System.out.println("0. 뒤로 가기");
		printLn(2);
		printBar();
	}
	
	public void print_join() {
		printLn(7);
		System.out.println("회원가입");
		printBar();
	}
	
}
