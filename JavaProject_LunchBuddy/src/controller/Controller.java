package controller;

import java.util.HashMap;
import java.util.List;
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
			case RES_SEARCH_SELECT: // 식당 검색 전 선택
				view = resc.resSearchSelect();
				break;
			case RES_SEARCH_RESNAME:// 식당 이름으로 검색
				view = resc.resSearchResName();
				break;
			case RES_SEARCH_CATEGORY:// 메뉴 카테고리로 검색
				view = resc.resSearchCategory();
				break;
			case RES_LIST: // 식당 리스트
				view = resc.resList();
				break;
			case SEARCH:
//				view = search();
				break;
			case RECENT_REVIEW:
				view = revc.reviewController(view);
				break;
			case ADD:
				view = add();
				break;
			case ADD_REVIEW:
//				view = add_review();
				break;
			case RES_ADD:
				view = resc.resAdd();
			case RECOMMAND_MENU:
//				view = recommand_menu();
				break;
			case MEMBER:
				view = memberController.memberController(view);
				break;
			case LIST_PAGING:
				view = list_paging();
				break;
			default :
				view = memberController.memberController(view);
				view = revc.reviewController(view);
			}
		}
	}

	private View add() {
		printAdd();
		int select = ScanUtil.nextInt("메뉴를 선택하세요\s");
		switch (select) {
		case 1:
			return View.ADD_REVIEW;//리뷰등록
		case 2:
			return View.RES_ADD;//식당등록
		default:
			return View.ADD;
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
		default:
			return View.HOME;
		}
	}

	public View list_paging() {
		int page_size = (int) sessionStorage.get("pageSize_for_paging");
		int object_size = (int) sessionStorage.get("object_size_for_paging");
		String type = (String) sessionStorage.get("type_for_paging");
		List<Object> list = (List<Object>) sessionStorage.get("list_for_paging");
		int page = (int) sessionStorage.get("pageno");
		View view = (View) sessionStorage.get("after_page");
		int lastNo = list.size();
		while (true) {
			System.out.println(page + "페이지");
			printBar();
			int topNo = (page - 1) * page_size;
			int bottomNo = page * page_size;
			int no = topNo + 1;
			for (int i = topNo; i < bottomNo; i++) {
				if (i < lastNo) {
					System.out.println(no + ". " + list.get(i));
					no++;
				} else {
					for (int j = 0; j < object_size; j++)
						System.out.println();
				}
			}
			printBar();

			if (page == 1) {
				System.out.println("            2." + type + " 상세   3.다음페이지");
			} else if ((page * page_size) >= lastNo) {
				System.out.println("1.이전페이지     2." + type + " 상세   3.다음페이지");
			} else {
				System.out.println("1.이전페이지     2." + type + " 상세");
			}
			System.out.println("9.홈              0.뒤로가기");
			printBar();
			int select = ScanUtil.nextInt(" 선택 >> ");
			switch (select) {
			case 1:
				if(page==1) break;
				else {
					sessionStorage.put("pageno", --page);
					return View.LIST_PAGING;
				}
			case 2:
				int selected_no = ScanUtil.nextInt(" 번호 >> ") - 1;
				sessionStorage.put("selected_object", list.get(selected_no));
				return view;
			case 3:
				if((page*page_size) >= lastNo) break;
				else {
					sessionStorage.put("pageno", ++page);
					return View.LIST_PAGING;
				}
			default:
				break;
			}

		}
	}

	static void newPage(View view) {
		int page = pageHistory.size();
		if (pageHistory.get(page) == view)
			return;
		pageHistory.put(page + 1, view);
	}

	public static View goBack() {
		int page = pageHistory.size();
		if (page == 1)
			return View.HOME;
		pageHistory.remove(page);
		return pageHistory.get(page - 1);
	}

	public static void removeHistory() {
		pageHistory.remove(Controller.pageHistory.size());
	}

}
