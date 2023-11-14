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
	
	
	Service service = Service.getInstance();
	MemberService memberService = new MemberService();
	MemberController memberController = new MemberController();
	
	
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
			case SEARCH:
//				view = search();
				break;
			case SEE_REVIEW:
//				view = see_review();
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
			case MY_PAGE:
//				view = my_page();
				break;
			case MEMBER:
				view = member();
				break;
			case JOIN:
				view = memberController.join();
				break;
			}
		}
	}
	private View member() {
		print_member();
		int select = ScanUtil.nextInt("메뉴 선택 >> ");
		switch (select) {
		case 1:
			return View.LOG_IN;
		case 2:
			return View.JOIN;
		case 3:
			return View.FIND_PW;
		case 0:
			return goBack();
		default:
			return View.MEMBER;
		}
	}

	private View home() {
		printHome();
		int select = ScanUtil.nextInt("메뉴를 선택하세요\s");
		switch (select) {
		case 1:
			return View.SEARCH;
		case 2:
			return View.SEE_REVIEW;
		case 3:
			return View.ADD;
		case 4:
			return View.RECOMMAND_MENU;
		case 5:
			return View.MEMBER;
		default :
			return View.HOME;
		}
	}
	
	private void newPage(View view) {
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
	
}
