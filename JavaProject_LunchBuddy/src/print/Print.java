package print;

import java.util.List;

import vo.MenuVo;
import vo.RestaurantVo;

public class Print {

	public void printStart() {
		System.out.println("\t╔═══════════════════════════════════════════════════════════════╗");
	}
	public void printEnd() {
		System.out.println("\t╚═══════════════════════════════════════════════════════════════╝");
	}
	
	public void print_text(String str) {
		int tapSize = 4;
		if(str.length()<tapSize) System.out.println(       "\t║ "+ str +"\t\t\t\t\t\t\t\t\t║");
		else if(str.length()<tapSize*2) System.out.println("\t║ "+ str +"\t\t\t\t\t\t\t\t║");
		else if(str.length()<tapSize*3) System.out.println("\t║ "+ str +"\t\t\t\t\t\t\t║");
		else if(str.length()<tapSize*4) System.out.println("\t║ "+ str +"\t\t\t\t\t\t║");
		else if(str.length()<tapSize*5) System.out.println("\t║ "+ str +"\t\t\t\t\t║");
		else if(str.length()<tapSize*6) System.out.println("\t║ "+ str +"\t\t\t\t║");
		else if(str.length()<tapSize*7) System.out.println("\t║ "+ str +"\t\t\t║");
		else if(str.length()<tapSize*8) System.out.println("\t║ "+ str +"\t\t║");
		else if(str.length()<tapSize*9) System.out.println("\t║ "+ str +"\t║");
		else System.out.println("\t║ "+ str +"║");
	}

	public void print_text_wt(String str) {
		int tapSize = 4;
		if(str.length()<tapSize) System.out.println(       "\t║ "+ str +"\t\t\t\t\t\t\t\t║");
		else if(str.length()<tapSize*2) System.out.println("\t║ "+ str +"\t\t\t\t\t\t\t║");
		else if(str.length()<tapSize*3) System.out.println("\t║ "+ str +"\t\t\t\t\t\t║");
		else if(str.length()<tapSize*4) System.out.println("\t║ "+ str +"\t\t\t\t\t║");
		else if(str.length()<tapSize*5) System.out.println("\t║ "+ str +"\t\t\t\t║");
		else if(str.length()<tapSize*6) System.out.println("\t║ "+ str +"\t\t\t║");
		else if(str.length()<tapSize*7) System.out.println("\t║ "+ str +"\t\t║");
		else if(str.length()<tapSize*8) System.out.println("\t║ "+ str +"\t║");
		else System.out.println("\t║ "+ str +"║");
	}
	
	public void welcomePage() {
		
	}
	
	public void printBar() {
//		System.out.println("-----------------------------------------------");
		System.out.println("\t═══════════════════════════════════════════════════════════════");
	}
	public void printLn(int num) {
		for(int i=0; i<num; i++)
			System.out.println();
	}
	public void printLnP(int num) {
		for(int i=0; i<num; i++)
			printN();
	}
	public void printN() {
		System.out.println("\t║\t\t\t\t\t\t\t\t║");
	}
	
	public void pause() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	public void printHome() {
		printBar();
		System.out.println("\t\t\t\t           ☆★점심 친구☆★\t\t\t\t");
		System.out.println();
		System.out.println("\t   [ 지금 가장 핫한 식당 ]");
		//Top 3 식당 뽑기
		new RestaurantPrint().print_rest_for_home(3);
		System.out.println();
		System.out.println("\t   [ 모집 중인 점심 친구 ]");
		new BFPrint().print_bf_for_home(3);
//		System.out.println();
//		printLn(3);
		printBar();
		System.out.println("\t   1. 식당 검색     \t\t\t 2. 최근 리뷰");
		System.out.println("\t   3. 리뷰, 식당 등록\t\t 4. 랜덤 메뉴 추천");
		System.out.println("\t   5. 점심 친구 구하기\t\t 6. 마이페이지");
		printBar();
	}

	public void printAdd() {
		printBar();
		System.out.println("\t\t\t\t  리뷰, 식당 등록");
		printLn(6);
		System.out.println("\t\t\t\t   1. 리뷰 등록");
		System.out.println("\t\t\t\t   2. 식당 등록");
		printLn(6);
		printBar();
	}

	public void print_wrong_input() {
		printBar();
		System.out.println("잘못된 입력입니다.");
		printBar();
	}
	
	public void print_wrong_acess() {
		System.out.println("잘못된 접근입니다.");
		pause();
	}
	
	public void page_need_login() {
		System.out.println();
		printBar();
		printLn(5);
		System.out.println("\t  로그인이 필요한 페이지입니다.");
		System.out.println("\t잠시 후 로그인 페이지로 이동합니다.");
		printLn(6);
		printBar();
		pause();
	}
	
	public void print_menu_recommanded(MenuVo selected_menu, RestaurantVo res, List<MenuVo> menuList_by_res) {
		printBar();
		System.out.println("\t\t\t\t메뉴 추천");
		System.out.println();
		System.out.println("\t\t\t랜덤으로 추천된 메뉴입니다.");
		System.out.println();
		System.out.println("\t\t[ "+res.getRes_name() + " ] "
				+selected_menu.getMenu_name()+" "
				+selected_menu.getMenu_price()+"원");
		System.out.println();
		printBar();
		System.out.println("\t\t[ "+res.getRes_name()+" ]의 다른 메뉴");
		for(int i =0; i<4; i++) {
			if(menuList_by_res.size()>i) {
				MenuVo menu = menuList_by_res.get(i);
				System.out.println("\t\t "+menu.getMenu_name()+"  "+menu.getMenu_price()+"원");
			}else System.out.println();
		}
		printBar();
		System.out.println("\t\t1. 이 식당 정보 보기     2. 이 메뉴 리뷰 보기");
		System.out.println("\t\t0. 다시 추천 받기       9. 뒤로가기");
		printBar();
	}
	
}







