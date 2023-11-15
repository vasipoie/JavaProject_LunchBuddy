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

public class AdminController extends AdminPrint {
	static public Map<String, Object> sessionStorage = new HashMap<>();
	
	AdminService adminService = AdminService.getInstance();
	
	public static void main(String[] args) {
		
	}
	
	void start() {
		View view = View.HOME;
		while (true) {
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
			case ADMIN_RES_MANAGE:
				view = adminHome();
				break;
			}
		}
	}

	View adminHome() {
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
