package print;

import java.util.List;

import vo.RestaurantVo;

public class RestaurantPrint {

	public void printBar() {
		System.out.println("-----------------------------------------------");
	}

	public void printLn(int num) {
		for (int i = 0; i < num; i++)
			System.out.println();
	}

	public void printResSearchSelect() {
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
			System.out.println("["+reslist.getRes_name()+"]\s거리 :"+reslist.getRes_walk()+"분 /예약여부 : "+reslist.getRes_bookyn());
			System.out.println("(평점:"+reslist.getRev_star()+")\s대표메뉴 :"+reslist.getMenu_name()+" - "+reslist.getMenu_price()+"원");
		}
		printBar();
	}
	
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
	
	
//	public void printResAdd(RestaurantVo resAdd) {
//		System.out.println(Controller.sessionStorage.get("")+"등록한 식당");
//		printBar();
//		System.out.print("[식당이름] \t"+resAdd.getRes_name());
//		System.out.print("예약여부 \t"+resAdd.getRes_bookyn());
//		System.out.println("전화번호 \t"+resAdd.getRes_phone());
//		System.out.println("주소 \t"+resAdd.getRes_add());
//		printBar();
//	}
	
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
	
	//식당 등록요청 전 사용자가 요청한 등록 출력
	public void printResAddOne(RestaurantVo resAddOnePrint) {
		printBar();
		System.out.println("["+resAddOnePrint.getRes_name()+"]\s카테고리 : "+cateName(resAddOnePrint));
		System.out.println("주소 : "+resAddOnePrint.getRes_add()+"\s전화번호 : "+resAddOnePrint.getRes_phone()+"\s 예약가능여부 : "+resAddOnePrint.getRes_bookyn());
		System.out.println("대표메뉴 : "+resAddOnePrint.getMenu_name()+"\s가격 : "+resAddOnePrint.getMenu_price()+"원");
		printBar();
		System.out.println("1. 등록요청");
		System.out.println("2. 수정");
		System.out.println("3. 등록취소");
	}
	
//	public void printCateList(List<Map<String, Object>> cateList) {
//		printBar();
//		System.out.println("식당이름\t거리\t예약여부\t평점\t대표메뉴\t가격");
//		for(Object reslist : cateList) {
//			System.out.println(reslist);
//		}
//		printBar();
//	}
	
	public void printResDetail(RestaurantVo rest) {
		System.out.println("식당 상세 보기");
		printBar();
		System.out.println("["+rest.getRes_name()+"]"+ new ReviewPrint().stars(rest.getRev_star()));
		System.out.println(cateName(rest) + "\t" + rest.getRes_walk()+"min");
		System.out.println(rest.getRes_phone());
		System.out.println(rest.getRes_add());
		System.out.println(rest.getMenu_name()+" - "+rest.getMenu_price()+"원");
		printBar();
	}
	
	public void print_select_for_restDetail() {
		printBar();
		System.out.println("1.리뷰 보기   2.메뉴 더보기  3. 리뷰 등록");
		System.out.println("9.홈  0.뒤로가기");
		printBar();
	}
	
	
	
}
