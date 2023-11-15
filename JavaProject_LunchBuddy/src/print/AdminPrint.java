package print;

public class AdminPrint {
	
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
	
	public void printAdminHome() {
		System.out.println("Admin Home");
		printBar();
		System.out.println("1. 리뷰 관리");
		System.out.println("2. 회원 관리");
		System.out.println("3. 식당 관리");
		System.out.println("0. 로그아웃");
		printLn(2);
		printBar();
		
	}
}
