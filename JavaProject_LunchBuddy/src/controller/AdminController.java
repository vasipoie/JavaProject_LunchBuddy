package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import print.AdminPrint;
import service.AdminService;
import util.ScanUtil;
import util.View;
import vo.AdminVo;
import vo.RestaurantVo;

public class AdminController extends AdminPrint {
	
	static private Map<String, Object> sessionStorage = Controller.sessionStorage;
	AdminService adminService = AdminService.getInstance();
	
	public View adminController(View view) {
		while (true) {
			Controller.newPage(view);
			switch (view) {
			case ADMIN_LOGIN:
				view = adminLogin();
				break;
			case ADMIN_HOME:
				view = adminHome();
				break;
			case ADMIN_REVIEW_CHECK:
				view = adminHome();
				break;
			case ADMIN_MEMBER_MANAGE:
				view = adminHome();
				break;
			case ADMIN_RES_MANAGE:	//관리자 식당관리
				view = adminResManage();
				break;
			case ADMIN_STANDBY_RES_MANAGE:	//관리자용 등록 대기 중 식당 리스트
				view = adminStandbyResManage();
				break;
			case ADMIN_STANDBY_RES_DETAIL:	//관리자용 등록대기중 식당 상세보기
				view = adminStandbyResDetail();
				break;
			default :
				Controller.removeHistory();
				return view;
			}
		}
	}

	//관리자용 등록대기중 식당 상세보기
	public View adminStandbyResDetail() {
		
		return null;
	}
	
	//식당등록 관리자요청온거 확인 sessionStorage.get("resAddOnePrint");
	
	//관리자용 등록 대기 중 식당 리스트
	public View adminStandbyResManage() {
		List<RestaurantVo> adminResList = adminService.adminResList();
		Controller.init_page(5, 2, "식당상세보기", "adminStandbyResDetail", View.ADMIN_HOME);
		sessionStorage.put("list_for_paging", adminResList);
		return View.LIST_PAGING;
	}
	
	//관리자 식당관리
	private View adminResManage() {
		printAdminResManage();
		int select = ScanUtil.nextInt("메뉴를 선택하세요\s");
		switch (select) {
		case 1:
			return View.ADMIN_REVIEW_CHECK;//수정
		case 2:
			return View.ADMIN_STANDBY_RES_MANAGE;
		case 0:
			return View.ADMIN_HOME;
		default :
			return View.ADMIN_RES_MANAGE;
		}
	}

	public View adminHome() {
		printAdminHome();
		int select = ScanUtil.nextInt("메뉴를 선택하세요\s");
		switch (select) {
		case 1:
			return View.ADMIN_REVIEW_CHECK;
		case 2:
			return View.ADMIN_MEMBER_MANAGE;
		case 3:
			return View.ADMIN_RES_MANAGE;
		case 0:
			sessionStorage.clear();
			return View.HOME;
		default :
			return View.ADMIN_HOME;
		}
	}

	View adminLogin() {
//		AdminVo adLog = (AdminVo) sessionStorage.get("admin");
//		if(adLog==null) {
//			sessionStorage.put("view", View.ADMIN_LOGIN);
//			return View.ADMIN_LOGIN;
//		}
		
//		String id = ScanUtil.nextLine("ID >> ");
//		String pass = ScanUtil.nextLine("PASS >> ");
		String id = "nahye";
		String pass = "lunch";
			
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		param.add(pass);
		boolean chk = adminService.adminLogin(param);
		if(chk) {
			AdminVo ad = (AdminVo) sessionStorage.get("admin");
			System.out.println(ad.getAdm_name()+" 관리자님 환영합니다.\n");
			return View.ADMIN_HOME;
		}else {
			System.out.println("해당 아이디가 없습니다.");
			return View.ADMIN_LOGIN;
		}
	}

}
