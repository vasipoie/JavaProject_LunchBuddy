package controller;

import java.util.HashMap;
import java.util.Map;

import print.Print;
import service.MemberService;
import service.Service;
import util.ScanUtil;
import util.View;

public class Controller extends Print {
	
	static public Map<String, Object> sessionStorage = new HashMap<>();
	static public Map<Integer, View> pageHistory = new HashMap<>();
	
	AdminController ac = new AdminController();
	MemberController memberController = new MemberController();
	ReviewController revc = new ReviewController();
	RestaurantController resc = new RestaurantController();
	
	Service service = Service.getInstance();
	MemberService memberService = new MemberService();
	
	
	public static void main(String[] args) {
		new Controller().start();
	}

	public void start() {
		View view = View.HOME;
		while (true) {
			newPage(view);
			switch (view) {
			case HOME:
				view = home();
				break;
			case ADMIN_LOGIN:
				view = ac.adminLogin();
				break;
			case ADMIN_HOME:
				view = ac.adminHome();
				break;
			case RES_SEARCH_SELECT:	//식당 검색 전 선택
				view = resc.resSearchSelect();
				break;
			case RES_SEARCH_RESNAME://식당 이름으로 검색
				view = resc.resSearchResName();
				break;
			case RES_SEARCH_CATEGORY://메뉴 카테고리로 검색
				view = resc.resList();
				break;
			case RES_LIST:			//식당 리스트
				view = resc.resList();
				break;
			case SEARCH:
//				view = search();
				break;
			case RECENT_REVIEW:
				view = revc.reviewController(view);
				break;
			case ADD:
//				view = add();
				break;
			case ADD_REVIEW:
//				view = add_review();
				break;
			case RECOMMAND_MENU:
//				view = recommand_menu();
				break;
			case MEMBER:
				view = memberController.memberController(view);
				break;
			}
		}
	}

	private View home() {
		printHome();
		int select = ScanUtil.nextInt("메뉴를 선택하세요\s");
		switch (select) {
		case 1:
			return View.RES_SEARCH_SELECT;
		case 2:
			return View.RECENT_REVIEW;
		case 3:
			return View.ADD;
		case 4:
			return View.RECOMMAND_MENU;
		case 5:
			return View.MEMBER;
		case 99:
			return View.ADMIN_LOGIN;
		default :
			return View.HOME;
		}
	}
	
	static void newPage(View view) {
		int page = pageHistory.size();
		if(pageHistory.get(page)==view) return;
		pageHistory.put(page+1, view);
	}
	
	public static View goBack() {
		int page = pageHistory.size();
		if(page ==1) return View.HOME;
		pageHistory.remove(page);
		return pageHistory.get(page-1);
	}
	
	public static void removeHistory() {
		pageHistory.remove(Controller.pageHistory.size());
	}
	
}
