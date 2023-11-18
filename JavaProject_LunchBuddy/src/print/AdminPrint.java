package print;

import java.util.List;

import vo.AdminVo;
import vo.RestaurantVo;

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
	
	public void printAdminStandbyResDetail(RestaurantVo standbyRes) {
		System.out.println("식당 상세 보기");
		printBar();
//		System.out.println("["+standbyRes.getRes_name()+"]\s"+ new ReviewPrint().stars(standbyRes.getRev_star()));
//		System.out.println(cateName(rest) + "\t" + standbyRes.getRes_walk()+"min");
//		System.out.println(standbyRes.getRes_phone());
//		System.out.println(standbyRes.getRes_add());
//		System.out.println(standbyRes.getMenu_name()+" - "+standbyRes.getMenu_price()+"원");
		
		System.out.println("["+standbyRes.getRes_name()+"]\s 카테고리 : "+cateName(standbyRes));//restPrint에서 cateName긁어오기
		System.out.println(standbyRes.getRes_walk()+"min");
		System.out.println(standbyRes.getRes_add());
		System.out.println(standbyRes.getRes_phone());
		System.out.println(standbyRes.getMenu_name()+" - "+standbyRes.getMenu_price()+"원");
		printBar();
	}
	
	public void printSelectForReviewDetail() {
		printBar();
		System.out.println("1.식당 정보 수정하기   2. 식당 등록하기");
		System.out.println("9.홈  0.뒤로가기");
		printBar();
	}
	
}
