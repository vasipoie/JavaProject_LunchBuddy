package print;

import java.util.List;

import controller.Controller;
import vo.MemberVo;
import vo.RestaurantVo;

public class RestaurantPrint extends Print{

	public void printResSearchSelect() {
		printBar();
		System.out.println("\t\t\t\t식당 검색");
		printLn(4);
		System.out.println("\t\t\t1. 식당 이름으로 검색");
		System.out.println("\t\t\t2. 식당 카테고리로 검색");
		printLn(2);
		System.out.println("\t\t\t9. 홈");
		System.out.println("\t\t\t0. 뒤로 가기");
		printLn(4);
		printBar();
	}
	
	public void print_resSearchResName() {
		printBar();
		System.out.println("\t\t\t\t식당 검색");
		printLn(6);
		System.out.println("\t\t\t식당 이름을 검색하세요 : ");
		printLn(7);
		printBar();
	}
	
	public void printResList(List<RestaurantVo> rsrn) {
		printBar();
		System.out.println("식당 리스트");
		printBar();
		for(RestaurantVo reslist : rsrn) {
			System.out.println("["+reslist.getRes_name()+"] 거리 :"+reslist.getRes_walk()+"분 / 예약여부 : "+reslist.getRes_bookyn());
			System.out.println("(평점:"+reslist.getRev_star()+") 대표메뉴 :"+reslist.getMenu_name()+" - "+reslist.getMenu_price()+"원");
		}
		printBar();
	}
	
	//메뉴카테고리로 검색 할 때 선택 할 카테고리 출력
	public void printCategory() {
		printBar();
		System.out.println("\t\t\t\t카테고리 별 식당 검색");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("\t\t\t\t1. 한식");
		System.out.println("\t\t\t\t2. 양식");
		System.out.println("\t\t\t\t3. 아시아음식");
		System.out.println("\t\t\t\t4. 일식");
		System.out.println("\t\t\t\t5. 중식");
		System.out.println("\t\t\t\t6. 분식");
		System.out.println("\t\t\t\t7. 카페");
		System.out.println("\t\t\t\t8. 뷔페");
		System.out.println("\t\t\t\t9. 기타");
		System.out.println();
		System.out.println();
		printBar();
	}
	
	public String cateName(RestaurantVo resAddOnePrint) {
		String cateName = "";
		switch(resAddOnePrint.getCat_no()) {
		case "01 ": cateName = "한식"; break;
		case "02 ": cateName = "양식"; break;
		case "03 ": cateName = "아시아음식"; break;
		case "04 ": cateName = "일식"; break;
		case "05 ": cateName = "중식"; break;
		case "06 ": cateName = "분식"; break;
		case "07 ": cateName = "카페"; break;
		case "08 ": cateName = "뷔페"; break;
		case "09 ": cateName = "기타"; break;
		}
		return cateName;
	}
	
	public String cateName2 (String str) {
//		String cateName2 = (String) chk.get(1);
		String cateName2 = "";
		switch(str) {
		case "01": cateName2 = "한식"; break;
		case "02": cateName2 = "양식"; break;
		case "03": cateName2 = "아시아음식"; break;
		case "04": cateName2 = "일식"; break;
		case "05": cateName2 = "중식"; break;
		case "06": cateName2 = "분식"; break;
		case "07": cateName2 = "카페"; break;
		case "08": cateName2 = "뷔페"; break;
		case "09": cateName2 = "기타"; break;
		}
		return cateName2;
	}
	
	//식당등록 예시 보여주기
	public void printResAddExample() {
		printBar();
		System.out.println("식당 등록");
		printBar();
		//카테고리,식당이름,주소,전화번호,예약가능여부,대표메뉴,가격
		System.out.println("예시)\n 카테고리 : 2\n 식당 이름 : 버거킹\n 주소 : 대전 중구 계룡로 853\n 전화번호 : 0422210332\n"
				+ " 예약가능여부(1.가능/2.불가능/3.미확인) : 1\n 대표메뉴 : 비프+슈림프버거 세트\n 가격 : 8500");
		printBar();
	}
	
	//식당등록 카테고리 보여주기
	public void printResAddCategory() {
		System.out.println("카테고리 종류 : 1.한식/2.양식/3.아시아음식/4.일식/5.중식/6.분식/7.카페/8.뷔페/9.기타");
	}
	
	//사용자가 입력한 식당등록 insert 전 출력
	public void printResInsertBefore(List<Object> restAdd, List<Object> chk) {
		MemberVo mb = (MemberVo) Controller.sessionStorage.get("log_in_member");
		printBar();
		System.out.println(mb.getMem_nick()+"님이 입력한 식당 등록");
		printBar();
		
		System.out.println("["+restAdd.get(2)+"] 카테고리 : "+cateName2((String)chk.get(1)));
		System.out.println("주소 : "+restAdd.get(3)+"   전화번호 : "+restAdd.get(4)+"  예약가능여부 : "+restAdd.get(5));
		System.out.println("대표메뉴 : "+chk.get(5)+"   가격 : "+chk.get(6)+"원");
	}
	
	//사용자가 입력한 식당등록 insert 전 입력한 내용 출력해서 보여주고 선택창
	public void printSelectResInsertBefore() {
		printBar();
		System.out.println("1. 등록요청");
		System.out.println("2. 수정");
		System.out.println("3. 등록취소");
		printBar();
	}
	
	//1.등록요청
	public void printRegiReque() {
		printBar();
		System.out.println("관리자에게 등록요청을 보냈습니다!");
		System.out.println("*.☆⸜(⑉˙ᗜ˙⑉)⸝♡.*");
		System.out.println("등록해주셔서 감사합니다");
		System.out.println("홈으로 이동합니다");
		pause();
	}
	
	//2.식당등록 요청 전 수정내역 선택
	public void printResAddModify() {
		printBar();
		System.out.println("식당등록 수정");
		printBar();
		System.out.println("1. 식당이름 수정");
		System.out.println("2. 주소 수정");
		System.out.println("3. 전화번호 수정");
		System.out.println("4. 예약가능여부 수정");
		System.out.println("5. 가격 수정");
		System.out.println("0. 뒤로가기");
		printBar();
	}
	
	//3.등록취소
	public void printRegiCancle() {
		printBar();
		System.out.println("등록이 취소되었습니다");
		System.out.println("홈으로 이동합니다");
		pause();
	}
	
	public void printResDetail(RestaurantVo rest) {
		printBar();
		System.out.println("\t\t\t\t식당 상세 보기");
		printBar();
		System.out.println();
		System.out.println("\t\t["+rest.getRes_name()+"] "+ new ReviewPrint().stars(rest.getRev_star()));
		System.out.println("\t\t"+cateName(rest) + "\t" + rest.getRes_walk()+"min");
		System.out.println("\t\t"+rest.getRes_phone());
		System.out.println("\t\t"+rest.getRes_add());
		System.out.println("\t\t"+rest.getMenu_name()+" - "+rest.getMenu_price()+"원");
		printLn(4);
		printBar();
		System.out.println("\t\t1.리뷰 보기   2.메뉴 더보기  3. 리뷰 등록");
		System.out.println("\t\t9.홈  0.뒤로가기");
		printBar();
	}
	
	public void printCateDetail(RestaurantVo cate) {
		System.out.println("식당 상세 보기");
		printBar();
		System.out.println("["+cate.getRes_name()+"] "+ new ReviewPrint().stars(cate.getRev_star()));
		System.out.println(cateName(cate) + "\t" + cate.getRes_walk()+"min");
		System.out.println(cate.getRes_phone());
		System.out.println(cate.getRes_add());
		System.out.println(cate.getMenu_name()+" - "+cate.getMenu_price()+"원");
		printBar();
	}
	
	public void print_select_for_restDetail() {
		System.out.println("1.리뷰 보기   2.메뉴 더보기  3. 리뷰 등록");
		System.out.println("9.홈  0.뒤로가기");
		printBar();
	}
	
	
	
}
