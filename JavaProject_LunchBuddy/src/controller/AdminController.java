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
	
	AdminController ac = new AdminController();
	
	AdminService adminService = AdminService.getInstance();
	
	public static void main(String[] args) {
		new AdminController().start();
	}
	
	void start() {
		View view = View.HOME;
		while (true) {
			switch (view) {
			case HOME:
				view = home();
				break;
			case ADMIN_LOGIN:
				view = admin_login();
				break;
			}
		}
	}

	 View admin_login() {
		String id = ScanUtil.nextLine("ID >> ");
		String pass = ScanUtil.nextLine("PASS >> ");
			
		List<Object> param = new ArrayList<Object>();
		param.add(id);
		param.add(pass);
		boolean chk = adminService.adminLogin(param);
		if(chk) {
			AdminVo ad = (AdminVo) sessionStorage.get("admin");
			System.out.println(ad.getAdm_name()+" 관리자님 환영합니다.\n");
			return View.HOME;
		}else {
			System.out.println("해당 아이디가 없습니다.");
			return View.ADMIN_LOGIN;
		}
		
	}

	View home() {
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
		case 99:
			return View.ADMIN_LOGIN;
		default :
			return View.HOME;
		}
	}
	
	
	
	
}
