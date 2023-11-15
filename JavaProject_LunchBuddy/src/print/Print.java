package print;

import java.util.List;

import util.ScanUtil;

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

	public void printAdd() {
		printBar();
		System.out.println("1. 리뷰 등록");
		System.out.println("2. 식당 등록");
		printBar();
	}

	public void print_wrong_input() {
		printBar();
		System.out.println("잘못된 입력입니다.");
		printBar();
	}
	
	public void print_wrong_acess() {
		System.out.println("잘못된 접근입니다.");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
