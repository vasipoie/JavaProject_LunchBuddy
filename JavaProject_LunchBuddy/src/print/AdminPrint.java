package print;

import java.util.List;

import vo.AdminVo;

public class AdminPrint extends Print{
	
	public void printAdminHome() {
		System.out.println("Admin Home");
		printBar();
		System.out.println("1. 리뷰 관리");
//		System.out.println("2. 회원 관리");
		System.out.println("3. 식당 관리");
		System.out.println("0. 로그아웃");
		printLn(2);
		printBar();
		
	}
	
	public void printAdminResManage() {
		System.out.println("식당 관리");
		printBar();
		System.out.println("1. 등록된 식당 관리");
		System.out.println("2. 등록대기 식당 관리");
		System.out.println("0. 뒤로가기");
		printBar();
	}
	
	public void printResAdminList(List<AdminVo> adminResList) {
		printBar();
		printBar();
		
	}
}
