package print;

import java.util.List;

import util.ScanUtil;

public class Print {

	public void printStart() {
		System.out.println("\t╔═══════════════════════════════════════════════════════════════╗");
	}
	public void printEnd() {
		System.out.println("\t╚═══════════════════════════════════════════════════════════════╝");
	}
	
	public void print_text(String str) {
		int tapSize = 4;
		if(str.length()<tapSize) System.out.println(       "\t║ "+ str +"\t\t\t\t\t\t\t\t\t║");
		else if(str.length()<tapSize*2) System.out.println("\t║ "+ str +"\t\t\t\t\t\t\t\t║");
		else if(str.length()<tapSize*3) System.out.println("\t║ "+ str +"\t\t\t\t\t\t\t║");
		else if(str.length()<tapSize*4) System.out.println("\t║ "+ str +"\t\t\t\t\t\t║");
		else if(str.length()<tapSize*5) System.out.println("\t║ "+ str +"\t\t\t\t\t║");
		else if(str.length()<tapSize*6) System.out.println("\t║ "+ str +"\t\t\t\t║");
		else if(str.length()<tapSize*7) System.out.println("\t║ "+ str +"\t\t\t║");
		else if(str.length()<tapSize*8) System.out.println("\t║ "+ str +"\t\t║");
		else if(str.length()<tapSize*9) System.out.println("\t║ "+ str +"\t║");
		else System.out.println("\t║ "+ str +"║");
	}

	public void print_text_wt(String str) {
		int tapSize = 4;
		if(str.length()<tapSize) System.out.println(       "\t║ "+ str +"\t\t\t\t\t\t\t\t║");
		else if(str.length()<tapSize*2) System.out.println("\t║ "+ str +"\t\t\t\t\t\t\t║");
		else if(str.length()<tapSize*3) System.out.println("\t║ "+ str +"\t\t\t\t\t\t║");
		else if(str.length()<tapSize*4) System.out.println("\t║ "+ str +"\t\t\t\t\t║");
		else if(str.length()<tapSize*5) System.out.println("\t║ "+ str +"\t\t\t\t║");
		else if(str.length()<tapSize*6) System.out.println("\t║ "+ str +"\t\t\t║");
		else if(str.length()<tapSize*7) System.out.println("\t║ "+ str +"\t\t║");
		else if(str.length()<tapSize*8) System.out.println("\t║ "+ str +"\t║");
		else System.out.println("\t║ "+ str +"║");
	}
	
	public void welcomePage() {
		
	}
	
	public void printBar() {
//		System.out.println("-----------------------------------------------");
		System.out.println("\t═══════════════════════════════════════════════════════════════");
	}
	public void printLn(int num) {
		for(int i=0; i<num; i++)
			System.out.println();
	}
	public void printLnP(int num) {
		for(int i=0; i<num; i++)
			printN();
	}
	public void printN() {
		System.out.println("\t║\t\t\t\t\t\t\t\t║");
	}
	
	public void pause() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	public void printHome() {
		printBar();
		System.out.println("\t\t\t\t           ☆★점심 친구☆★\t\t\t\t");
		System.out.println();
		System.out.println("\t   [ 지금 가장 핫한 식당 ]");
		//Top 3 식당 뽑기
		printLn(3);
		System.out.println();
		System.out.println("\t   [ 모집 중인 점심 친구 ]");
		new BFPrint().print_bf_for_home(3);
//		System.out.println();
		printBar();
		System.out.println("\t   1. 식당 검색     \t\t\t 2. 최근 리뷰");
		System.out.println("\t   3. 리뷰, 식당 등록\t\t 4. 점메추");
		System.out.println("\t   5. 점심 친구 구하기\t\t 6. 마이페이지");
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
		pause();
	}
	
	public void page_need_login() {
		System.out.println();
		printBar();
		printLn(5);
		System.out.println("\t  로그인이 필요한 페이지입니다.");
		System.out.println("\t잠시 후 로그인 페이지로 이동합니다.");
		printLn(6);
		printBar();
		pause();
	}
	
}







