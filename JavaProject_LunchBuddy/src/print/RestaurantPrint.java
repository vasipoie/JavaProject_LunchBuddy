package print;

import java.util.List;
import java.util.Map;

import controller.Controller;
import vo.RestaurantVo;

public class RestaurantPrint {

	public void printBar() {
		System.out.println("-----------------------------------------------");
	}

	public void printLn(int num) {
		for (int i = 0; i < num; i++)
			System.out.println();
	}

	public void printResHome() {
		printBar();
		System.out.println("1. 식당 검색");
		System.out.println("2. 식당 리스트");
		System.out.println("3. 식당 상세보기");
		System.out.println("4. 식당 등록");
		printLn(2);
		printBar();
	}
	
	public void printResSearchSelect() {
		System.out.println("식당 검색");
		printBar();
		System.out.println("1. 식당 이름으로 검색");
		System.out.println("2. 식당 카테고리로 검색");
		printBar();
	}
	
	public void printResList(List<Map<String, Object>> rsrn) {
		System.out.println("식당 리스트");
		printBar();
		System.out.println("No\t식당이름\t거리\t예약여부\t평점\t대표메뉴\t가격");
		for(Map<String, Object> reslist : rsrn) {
			System.out.println(reslist);//get으로 고치기
		}
		printBar();
		System.out.print("1. 이전 페이지------");
		System.out.print("2. 식당 상세보기-----");
		System.out.println("3. 다음페이지");
		System.out.print("\t4. 뒤로가기-----");
		System.out.println("5. 홈");
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
	
	
	public void printResAdd(RestaurantVo resAdd) {
		System.out.println(Controller.sessionStorage.get("")+"등록한 식당");
		printBar();
		System.out.print("[식당이름] \t"+resAdd.getRes_name());
		System.out.print("예약여부 \t"+resAdd.getRes_bookyn());
		System.out.println("전화번호 \t"+resAdd.getRes_phone());
		System.out.println("주소 \t"+resAdd.getRes_add());
		printBar();
	}
	
	
	public void printResAddOne(RestaurantVo resAddOnePrint) {
		printBar();
		System.out.println("[ " + resAddOnePrint.getRes_name() + " ] " +"\s 카테고리 : "+resAddOnePrint.getCat_no());
		System.out.println("주소 : "+ resAddOnePrint.getRes_add() + "\s 전화번호 : " + resAddOnePrint.getRes_phone()+"\s 예약가능여부 : "+ resAddOnePrint.getRes_bookyn());
		System.out.println("대표메뉴 : " + resAddOnePrint.getMenu_name() + "\s 가격 : "+resAddOnePrint.getMenu_price()+"원");
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
	
	
}
