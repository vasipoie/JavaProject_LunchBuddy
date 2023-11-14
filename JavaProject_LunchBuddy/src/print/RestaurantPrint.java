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
	
	public void printResList(List<RestaurantVo> resList) {
		printBar();
		for(RestaurantVo reslist : resList) {
			System.out.println(reslist);
		}
		printBar();
	}
}
