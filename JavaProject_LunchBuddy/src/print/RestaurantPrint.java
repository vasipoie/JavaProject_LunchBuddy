package print;

import java.util.List;
import java.util.Map;

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
		printBar();
		System.out.println("1. 식당 이름으로 검색");
		System.out.println("2. 식당 카테고리로 검색");
		printBar();
	}
	
	public void printResList(List<Map<String, Object>> rsrn) {
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
	
//	public void printCateList(List<Map<String, Object>> cateList) {
//		printBar();
//		System.out.println("식당이름\t거리\t예약여부\t평점\t대표메뉴\t가격");
//		for(Object reslist : cateList) {
//			System.out.println(reslist);
//		}
//		printBar();
//	}
	
	
}
