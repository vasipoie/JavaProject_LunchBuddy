package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import print.BFPrint;
import print.Print;
import service.MemberService;
import service.MenuService;
import service.RestaurantService;
import service.Service;
import util.ScanUtil;
import util.View;
import vo.MemberVo;
import vo.MenuVo;
import vo.RestaurantVo;

public class Controller extends Print {

	static public Map<String, Object> sessionStorage = new HashMap<>();
	static public Map<Integer, View> pageHistory = new HashMap<>();

	AdminController ac = new AdminController();
	MemberController memberController = new MemberController();
	ReviewController revc = new ReviewController();
	RestaurantController resc = new RestaurantController();
	BFController bfController = new BFController();
	RestaurantController resController = new RestaurantController();

	Service service = Service.getInstance();
	MemberService memberService = new MemberService();
	MenuService menuService = new MenuService();
	RestaurantService restaurantService = new RestaurantService();
	
	public static void main(String[] args) {
//		new Controller().start();
		new Controller().test();
	}
	
	public void test() {
//		List<Object> param = new ArrayList();
//		param.add("nahye");
//		param.add("nahye1234");
//		MemberVo member = memberService.log_in(param);
//		sessionStorage.put("log_in_member", member);
//		
//		RestaurantVo res = restaurantService.getRes_by_resNo("02003");
//		sessionStorage.put("selected_res_for_bf", res);
//		
////		printAdd();
//		View view = bfController.bfcontroller(View.BF_MAKE);
//		new BFPrint().print_make_bf("문밀원","와랄라","홀롤로",3,"23.11.27",false);
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(new Date());
//		new BFPrint().printDate(cal);
		new BFPrint().print_already_part();
	}

	public void start() {
		View view = View.HOME;
		while (true) {
			System.out.println("main controller, view = " + view);
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
			case SEARCH:
//				view = search();
				break;
			case ADD:
				view = add();
				break;
			case RES_ADD:
				view = resc.resAdd();
				break;
			case RES_ADD_ONE:
				view = resc.resAddOne();
				break;
			case RECOMMAND_MENU:
				view = recommand_menu();
				break;
			case MEMBER:
				view = memberController.memberController(view);
				break;
			case LIST_PAGING:
				view = list_paging();
				break;
			default:
				removeHistory();
				System.out.println("main controller out");
				view = memberController.memberController(view);
				view = revc.reviewController(view);
				view = bfController.bfcontroller(view);
				view = resController.restController(view);
				view = ac.adminController(view);
			}
		}
	}

	/**
	 * 랜덤 메뉴 추천 메소드
	 * @return
	 */
	private View recommand_menu() {
		int menu_qtty = menuService.count_menu();
		System.out.println(menu_qtty);
		int menu_no = (int) (Math.random()*menu_qtty);
		System.out.println(menu_no);
		List<MenuVo> menuList = menuService.get_all_menu_list();
		MenuVo selected_menu = menuList.get(menu_no);
		RestaurantVo res = restaurantService.getRes_by_resNo(selected_menu.getRes_no());
		List<MenuVo> menuList_by_res = menuService.menuList_by_res(res.getRes_no());
		while(true) {
			print_menu_recommanded(selected_menu, res, menuList_by_res);
			switch (ScanUtil.nextInt("메뉴 선택 >> ")) {
			case 1:
				sessionStorage.put("resDetailResName", res);
				return View.RES_DETAIL;
			case 2:
				sessionStorage.put("selected_menu", selected_menu);
				return View.SEE_MENU_REVIEW_BY_MENU;
			case 0:
				removeHistory();
				return View.RECOMMAND_MENU;
			case 9:
				return View.HOME;
			default:
				continue;
			}
		}
	}

	private View add() {
		printAdd();
		int select = ScanUtil.nextInt("메뉴를 선택하세요 ");
		switch (select) {
		case 1:
			return View.ADD_REVIEW;// 리뷰등록
		case 2:
			return View.RES_ADD;// 식당등록
		default:
			return View.ADD;
		}
	}

	private View home() {
		printHome();
		int select = ScanUtil.nextInt("메뉴를 선택하세요 ");
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
			return View.BF_MAIN;
		case 6:
			return View.MEMBER;
		case 99:
			return View.ADMIN_LOGIN;
		default:
			return View.HOME;
		}
	}

	/**
	 * Controller.sessionStorage에 "list_for_paging" 따로 넣어주세요~ 
	 * @param num  한 페이지에 들어갈 오브젝트 갯수
	 * @param line 오브젝트 하나의 출력 줄 수
	 * @param type 오브젝트 선택 보기에 들어갈 단어
	 * @param returnName 선택한 오브젝트가 들어갈 sessionStorage 키
	 * @param view 상세보기 눌렀을 때 이동할 뷰
	 */
	public static void init_page(int num, int line, String type, String returnName, View view) {
			sessionStorage.put("pageSize_for_paging", num);
			sessionStorage.put("object_size_for_paging", line);
			sessionStorage.put("type_for_paging", type);
			sessionStorage.put("returnName", returnName);
			sessionStorage.put("after_page", view);
			sessionStorage.put("pageno", 1);
	}

	/**
	 * 출력하는 오브젝트는 Controller.sessionStorage.get("selected_object")에 들어있습니다.
	 * Controller.sessionStorage에 다음 항목 넣어주세요 (list_for_paging : 출력할 리스트)
	 * (pageSize_for_paging : 한 페이지에 들어갈 오브젝트 갯수) (object_size_for_paging : 오브젝트 출력
	 * 하나의 줄 수 ) (type_for_paging : 오브젝트 종류 이름) (after_page : 상세보기 눌렀을 때 이동할 뷰)
	 * (pageno : 1넣어주세요~)
	 * 
	 * @return
	 */
	public View list_paging() {
		int page_size = (int) sessionStorage.get("pageSize_for_paging");
		int object_size = (int) sessionStorage.get("object_size_for_paging");
		String type = (String) sessionStorage.get("type_for_paging");
		List<Object> list = (List<Object>) sessionStorage.get("list_for_paging");
		String returnName = (String) sessionStorage.get("returnName");
		int page = (int) sessionStorage.get("pageno");
		View view = (View) sessionStorage.get("after_page");
		int lastNo = list.size();
		while (true) {
			printBar();
			System.out.println("\t\t\t\t"+page + "페이지");
			printBar();
			int topNo = (page - 1) * page_size;
			int bottomNo = page * page_size;
			int no = topNo + 1;
			if(list.size()==0) {
				System.out.println("\t\t\t   검색 결과가 없습니다.");
				printLn(9);
			}else {
				for (int i = topNo; i < bottomNo; i++) {
					if (i < lastNo) {
						System.out.println("\t\t"+no + ". " + list.get(i));
						no++;
					} else {
						for (int j = 0; j < object_size; j++)
							System.out.println();
					}
				}
			}
			printBar();
			
			if(list.size()==0) System.out.println();
			else {
				if (page == 1) 	System.out.print("\t\t            2." + type);
				else 			System.out.print("\t\t1.이전페이지     2." + type);
				
				if((page*page_size) < lastNo) System.out.println("   3.다음페이지");
				else System.out.println();
			}
			
			
			System.out.println("\t\t\t9.홈              0.뒤로가기");
			printBar();
			int select = ScanUtil.nextInt(" 선택 >> ");
			switch (select) {
			case 1:
				if (page == 1)
					break;
				else {
					sessionStorage.put("pageno", --page);
					return View.LIST_PAGING;
				}
			case 2:
				if(list.size()==0) {
					removeHistory();
					return View.LIST_PAGING;
				}else {
					int selected_no = ScanUtil.nextInt(" 번호 >> ") - 1;
					sessionStorage.put(returnName, list.get(selected_no));
					return view;
				}
			case 3:
				if ((page * page_size) >= lastNo)
					break;
				else {
					sessionStorage.put("pageno", ++page);
					return View.LIST_PAGING;
				}
			case 9 : return View.HOME;
			case 0 : return goBack();
			default:
				removeHistory();
				return View.LIST_PAGING;
			}
		}
	}

	/**
	 * 뷰 이동 경로 저장
	 * Controller.pageHistory에 ("순서",뷰)로 저장
	 * @param view
	 */
	static void newPage(View view) {
		int page = pageHistory.size();
		if(pageHistory.get(page)==view) return; //이전 페이지와 같으면 저장 안함
		System.out.println(page+1+"페이지에"+view+"저장");
		if (pageHistory.get(page-1) == view)
			return;
		pageHistory.put(page + 1, view);
	}

	/**
	 * 뒤로가기
	 * 직전 뷰 리턴
	 * @return
	 */
	public static View goBack() {
		System.out.println("뒤로가기 실행");
		if (pageHistory.size() == 1)
			return View.HOME; //첫번째 페이지에서 뒤로가기 호출 시 홈으로 보내기
		int page = pageHistory.size();
		pageHistory.remove(page);
		System.out.println(page+"에서 뒤로가기 실행, "+pageHistory.get(page - 1)+"리턴");
		return pageHistory.get(page - 1);
	}

	public static void removeHistory() {
		System.out.println(Controller.pageHistory.size()+"번째 페이지 삭제");
		pageHistory.remove(Controller.pageHistory.size());
	}
	
}
