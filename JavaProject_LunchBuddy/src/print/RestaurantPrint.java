package print;

import java.util.List;

import controller.Controller;
import vo.MemberVo;
import vo.RestaurantVo;

public class RestaurantPrint extends Print{

	public void printResSearchSelect() {
		printBar();
		System.out.println("식당 검색");
		printBar();
		System.out.println("1. 식당 이름으로 검색");
		System.out.println("2. 식당 카테고리로 검색");
		printBar();
	}
	
	public void printResList(List<RestaurantVo> rsrn) {
		printBar();
		System.out.println("식당 리스트");
		printBar();
		for(RestaurantVo reslist : rsrn) {
			System.out.println("["+reslist.getRes_name()+"]\s거리 :"+reslist.getRes_walk()+"분 / 예약여부 : "+reslist.getRes_bookyn());
			System.out.println("(평점:"+reslist.getRev_star()+")\s대표메뉴 :"+reslist.getMenu_name()+" - "+reslist.getMenu_price()+"원");
		}
		printBar();
	}
	
	//메뉴카테고리로 검색 할 때 선택 할 카테고리 출력
	public void printCategory() {
		printBar();
		System.out.println("1. 한식");
		System.out.println("2. 양식");
		System.out.println("3. 아시아음식");
		System.out.println("4. 일식");
		System.out.println("5. 중식");
		System.out.println("6. 분식");
		System.out.println("7. 카페");
		System.out.println("8. 뷔페");
		System.out.println("9. 기타");
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
		case "01 ": cateName2 = "한식"; break;
		case "02 ": cateName2 = "양식"; break;
		case "03 ": cateName2 = "아시아음식"; break;
		case "04 ": cateName2 = "일식"; break;
		case "05 ": cateName2 = "중식"; break;
		case "06 ": cateName2 = "분식"; break;
		case "07 ": cateName2 = "카페"; break;
		case "08 ": cateName2 = "뷔페"; break;
		case "09 ": cateName2 = "기타"; break;
		}
		return cateName2;
	}
	
	//사용자가 입력한 식당등록 insert 전 출력
	public void printResInsertBefore(List<Object> chk) {
		MemberVo mb = (MemberVo) Controller.sessionStorage.get("log_in_member");
		printBar();
		System.out.println(mb.getMem_nick()+"님이 입력한 식당 등록");
		printBar();
		System.out.println("["+chk.get(0)+"]\s카테고리 : "+cateName2((String)chk.get(1)));
		System.out.println("주소 : "+chk.get(2)+"\s전화번호 : "+chk.get(3)+"\s 예약가능여부 : "+chk.get(4));
		System.out.println("대표메뉴 : "+chk.get(5)+"\s가격 : "+chk.get(6)+"원");
		printBar();
		System.out.println("1. 등록요청");
		System.out.println("2. 수정");
		System.out.println("3. 등록취소");
		printBar();
	}
	
	//삭제할듯
	//식당 등록요청 전 사용자가 입력한 등록 출력
	public void printResAddOne(RestaurantVo resAddOnePrint) {
		MemberVo mb = (MemberVo) Controller.sessionStorage.get("log_in_member");
		printBar();
		System.out.println(mb.getMem_nick()+"님이 입력한 식당 등록");
		printBar();
		System.out.println("["+resAddOnePrint.getRes_name()+"]\s카테고리 : "+cateName(resAddOnePrint));
		System.out.println("주소 : "+resAddOnePrint.getRes_add()+"\s전화번호 : "+resAddOnePrint.getRes_phone()+"\s 예약가능여부 : "+resAddOnePrint.getRes_bookyn());
		System.out.println("대표메뉴 : "+resAddOnePrint.getMenu_name()+"\s가격 : "+resAddOnePrint.getMenu_price()+"원");
		printBar();
		System.out.println("1. 등록요청");
		System.out.println("2. 수정");
		System.out.println("3. 등록취소");
		printBar();
	}
	
	//식당등록 요청 전 수정내역 선택
	public void printResAddModify() {
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
	
//	public void printCateList(List<Map<String, Object>> cateList) {
//		printBar();
//		System.out.println("식당이름\t거리\t예약여부\t평점\t대표메뉴\t가격");
//		for(Object reslist : cateList) {
//			System.out.println(reslist);
//		}
//		printBar();
//	}
	
	//예약여부 null값인거 바꾸기
	public void printResDetail(RestaurantVo rest) {
		System.out.println("식당 상세 보기");
		printBar();
		System.out.println("["+rest.getRes_name()+"]\s"+ new ReviewPrint().stars(rest.getRev_star()));
		System.out.println(cateName(rest) + "\t" + rest.getRes_walk()+"min");
		System.out.println(rest.getRes_phone());
		System.out.println(rest.getRes_add());
		System.out.println(rest.getMenu_name()+" - "+rest.getMenu_price()+"원");
		printBar();
	}
	
	public void printCateDetail(RestaurantVo cate) {
		System.out.println("식당 상세 보기");
		printBar();
		System.out.println("["+cate.getRes_name()+"]\s"+ new ReviewPrint().stars(cate.getRev_star()));
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
	
	public void printRegiReque() {
		printBar();
		System.out.println("관리자에게 등록요청을 보냈습니다!");
		System.out.println("₍₍ (◟˙꒳​˙)◟ ⁾⁾등록해주셔서 감사합니다₍₍ ◝(˙꒳​˙◝) ⁾⁾");
		System.out.println("홈으로 이동합니다");
		pause();
	}
	
	
}
