package print;

import vo.RestaurantVo;
import vo.ReviewVo;

public class AdminPrint extends Print{
	
	public void printAdminHome() {
		System.out.println("Admin Home");
		printBar();
		System.out.println("1. 리뷰 관리");
//		System.out.println("2. 회원 관리");
		System.out.println("3. 식당 관리");
		System.out.println("0. 로그아웃");
		printBar();
	}
	
	public void printAdminRevDetail(ReviewVo reviewDetail) {
		printBar();
		System.out.println("리뷰 상세 보기");
		printBar();
		System.out.println(reviewDetail.adminRev());
		printBar();
	}
	
	public void printSelectForReviewDetail() {
		System.out.println("1.게시여부 수정");
		System.out.println("9.관리자 홈      0.뒤로가기");
		printBar();
	}
	
	public void printBlindReview() {
		printBar();
		System.out.println("1.게시여부 Y로 수정하기");
		System.out.println("2.게시여부 N로 수정하기");
		printBar();
	}
	
	public void printPostYReview() {
		printBar();
		System.out.println("게시여부가 Y로 수정되었습니다");
		System.out.println("리뷰 상세보기로 이동합니다");
		printBar();
	}
	
	public void printPostNReview() {
		printBar();
		System.out.println("게시여부가 N으로 수정되었습니다");
		System.out.println("리뷰 상세보기로 이동합니다");
		printBar();
	}
	
	public void printAdminReviewSearch() {
		printBar();
		System.out.println("리뷰 검색");
		printBar();
		System.out.println("1.식당이름으로 검색");
		System.out.println("2.닉네임으로 검색");
		printBar();
	}
	
	public void printAdminResManage() {
		printBar();
		System.out.println("식당 관리");
		printBar();
		System.out.println("1.등록된 식당 관리");
		System.out.println("2.미등록 식당 관리");
		System.out.println("0.뒤로가기");
		printBar();
	}
	
	public void printAdminResDetail(RestaurantVo standbyRes) {
		printBar();
		System.out.println("식당 상세 보기");
		printBar();
		
		System.out.println("["+standbyRes.getRes_name()+"]  카테고리 : "+new RestaurantPrint().cateName(standbyRes));
		System.out.println("이동시간 : "+standbyRes.getRes_walk()+"min  예약가능여부 : "+standbyRes.getRes_bookyn());
		System.out.println("주소 : "+standbyRes.getRes_add());
		System.out.println("전화번호 : "+standbyRes.getRes_phone());
		System.out.println("대표메뉴 : "+standbyRes.getMenu_name()+" - "+standbyRes.getMenu_price()+"원");
		printBar();
	}
	
	public void printSelectForRegiResDetail() {
		System.out.println("1.식당정보 수정   2.식당 삭제");
		System.out.println("9.관리자 홈      0.뒤로가기");
		printBar();
	}
	
	public void printSelectForStandbyResDetail() {
		System.out.println("1.식당정보 수정   2.식당 등록");
		System.out.println("9.관리자 홈      0.뒤로가기");
		printBar();
	}
	
	public void printAdminModifyResDetail(RestaurantVo standbyRes) {
		printBar();
		System.out.println("수정된 식당 상세 보기");
		printBar();
		
		System.out.println("["+standbyRes.getRes_name()+"]  카테고리 : "+new RestaurantPrint().cateName(standbyRes));
		System.out.println("이동시간 : "+standbyRes.getRes_walk()+"min  예약가능여부 : "+standbyRes.getRes_bookyn());
		System.out.println("주소 : "+standbyRes.getRes_add());
		System.out.println("전화번호 : "+standbyRes.getRes_phone());
		System.out.println("대표메뉴 : "+standbyRes.getMenu_name()+" - "+standbyRes.getMenu_price()+"원");
		printBar();
	}
	
	public void printAdminModifyResDetailSelect() {
		System.out.println("식당 정보 수정");
		printBar();
		System.out.println("1.식당이름 수정");
		System.out.println("2.이동시간 수정");
		System.out.println("3.예약가능여부 수정");
		System.out.println("4.주소 수정");
		System.out.println("5.전화번호 수정");
		System.out.println("6.대표메뉴 수정");
		System.out.println("7.가격 수정");
		System.out.println("8.식당 등록");
		System.out.println("9.관리자 홈");
		printBar();
	}
	
	public void printAdminModifyRegiResDetailSelect() {
		System.out.println("식당 정보 수정");
		printBar();
		System.out.println("1.식당이름 수정");
		System.out.println("2.이동시간 수정");
		System.out.println("3.예약가능여부 수정");
		System.out.println("4.주소 수정");
		System.out.println("5.전화번호 수정");
		System.out.println("6.대표메뉴 수정");
		System.out.println("7.가격 수정");
		System.out.println("9.관리자 홈");
		System.out.println("0.뒤로가기");//선택한식당상세보기
		printBar();
	}
	
	public void printAdminRegiRes() {
		printBar();
		System.out.println("식당등록이 완료되었습니다");
		System.out.println("*.☆⸜(⑉˙ᗜ˙⑉)⸝♡.*");
		System.out.println("관리자 홈으로 이동합니다");
		pause();
		printBar();
	}
	
	public void printAdminDeleteRes() {
		printBar();
		System.out.println("식당이 삭제되었습니다");
		System.out.println("관리자 홈으로 이동합니다");
		pause();
		printBar();
	}
	
	public void printModiCancle() {
		printBar();
		System.out.println("수정을 취소합니다");
		System.out.println("이전 페이지로 이동합니다");
		printBar();
	}
	
	public void printDelCancle() {
		printBar();
		System.out.println("삭제를 취소합니다");
		System.out.println("이전 페이지로 이동합니다");
		printBar();
	}
	
	public void printChkCancle() {
		printBar();
		System.out.println("게시여부 수정을 취소합니다");
		System.out.println("이전 페이지로 이동합니다");
		printBar();
	}
	
}