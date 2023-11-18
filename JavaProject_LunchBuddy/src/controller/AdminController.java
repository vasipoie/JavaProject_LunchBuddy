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
			case ADMIN_MODIFY_RES_DETAIL:
				view = adminModifyResDetail();
				break;
			default :
				Controller.removeHistory();
				return view;
			}
		}
	}

	public View adminModifyResDetail() {
		//수정 할 메뉴 선택
		//선택해서 수정하게하고(update) + 보여주기(select)
		RestaurantVo standbyRes = (RestaurantVo) Controller.sessionStorage.get("adminStandbyResDetail");
//		standbyRes에 resNo가져오기
		System.out.println("standbyRes.getRes_no()"+standbyRes.getRes_no());
		//print로 넣기
		System.out.println("식당 정보 수정");
		printBar();
		System.out.println("1. 식당이름 수정");
		System.out.println("2. 주소 수정");
		System.out.println("3. 전화번호 수정");
		System.out.println("4. 예약가능여부 수정");
		System.out.println("5. 가격 수정");
		System.out.println("6. 이동시간 수정");
		System.out.println("0. 뒤로가기");
		printBar();
		int select = ScanUtil.nextInt("메뉴 선택 >> ");
		switch (select) {
		case 1:
			String newResName = ScanUtil.nextLine("식당이름 : ");
			if(newResName.isEmpty()) {
				System.out.println("식당 이름을 입력해주세요");
				newResName = ScanUtil.nextLine("식당이름 : ");
			}
			adminService.adminUpdateResName(newResName, standbyRes.getRes_no());
			//select로 가져오기
			RestaurantVo adminModiResNameDetail = adminService.adminSelectModifyResDetail(standbyRes.getRes_no());
			printAdminStandbyResDetail(adminModiResNameDetail);
			//standbyRes 로 변경
			restAdd.remove(2);
			restAdd.add(2, newResName);
			break;
		case 2:
			String newAdd = ScanUtil.nextLine("주소 : ");
			if(newAdd.isEmpty()) {
				System.out.println("주소를 입력해주세요");
				newAdd = ScanUtil.nextLine("주소 : ");
			}
			restAdd.remove(3);
			restAdd.add(3, newAdd);
			break;
		case 3:
			String newPhone = ScanUtil.nextLine("전화번호 : ");
			if(newPhone.isEmpty()) {
				System.out.println("전화번호를 입력해주세요");
				newPhone = ScanUtil.nextLine("전화번호 : ");
			}
			if(!newPhone.matches("^[0-9]*$")) {
				System.out.println("숫자만 입력가능합니다");
				newPhone = ScanUtil.nextLine("전화번호 : ");
			}
			restAdd.remove(4);
			restAdd.add(4, newPhone);
			break;
		case 4:
			System.out.println("예약가능여부는 숫자만 입력가능합니다");
			String newBookyn = "";
			while(true) {
				int book = ScanUtil.nextIntB("예약가능여부(1.가능/2.불가능/3.미확인) : ");
				if(book == 1) {
					newBookyn = "가능";
				}
				if(book == 2 ) {
					newBookyn = "불가능";
				}
				if(book == 3) {
					newBookyn = "미확인";
				}
				else {
					System.out.println("default 숫자 1,2,3 중 한 개를 입력해주세요");
					continue;
				}
				break;
			}
			restAdd.remove(5);
			restAdd.add(5, newBookyn);
			break;
		case 5:
			System.out.println("가격은 숫자만 입력가능합니다");
			String newPrice = ScanUtil.nextLine("가격 : ");
			if(newPrice.isEmpty()) {
				System.out.println("가격을 입력해주세요");
				newPrice = ScanUtil.nextLine("가격 : ");
			}
			if(!newPrice.matches("^[0-9]*$")) {
				System.out.println("가격은 숫자만 입력가능합니다");
				newPrice = ScanUtil.nextLine("가격 : ");
			}
			chk.remove(6);
			chk.add(6, newPrice);
			break;
		case 6:
			int newWalk = ScanUtil.nextIntW("이동시간 : ");
			adminService.adminUpdateWalkName(newWalk, standbyRes.getRes_no());
			//select로 가져오기
			RestaurantVo adminModiWalkNameDetail = adminService.adminSelectModifyWalkDetail(standbyRes.getRes_no());
			printAdminStandbyResDetail(adminModiWalkNameDetail);
			//standbyRes 로 변경
			restAdd.remove(2);
			restAdd.add(2, newResName);
			break;
		case 0:
			return View.RES_ADD_ONE;
		default:
			adminModifyResDetail();
		}
		sessionStorage.replace("adminStandbyResDetail", standbyRes);
		return View.ADMIN_STANDBY_RES_DETAIL;
	}

	//관리자용 등록대기중 식당 상세보기
	public View adminStandbyResDetail() {
		RestaurantVo standbyRes = (RestaurantVo) Controller.sessionStorage.get("adminStandbyResDetail");
		printAdminStandbyResDetail(standbyRes);
		printSelectForReviewDetail();
		int select = ScanUtil.nextInt("선택 >> ");
		switch (select) {
		case 1 :
			int yn = ScanUtil.nextInt("수정하시겠습니까?\s1.예\s2.아니오\s|메뉴선택 : ");
			switch(yn) {
			case 1:
				return View.ADMIN_MODIFY_RES_DETAIL;
			case 2:
				System.out.println("수정을 취소합니다");
				System.out.println("이전 페이지로 이동합니다");
				return View.RES_ADD_ONE;
			default:
				return View.ADMIN_STANDBY_RES_DETAIL;
			}
			Controller.sessionStorage.put("selectResNo", standbyRes.getRes_no());
			return View.ADMIN_MODIFY_RES_DETAIL;
		case 2 :
			adminService.adminResUpload(standbyRes.getRes_no());
			System.out.println("식당등록이 완료되었습니다");
			return View.ADMIN_HOME;
		case 9 : return View.HOME;
		case 0 : return Controller.goBack();
		default:
			Controller.removeHistory();
			return View.ADMIN_MODIFY_RES_DETAIL;
		}
	}
	//식당등록 관리자요청온거 확인 sessionStorage.get("resAddOnePrint");
	
	//관리자용 등록 대기 중 식당 리스트
	public View adminStandbyResManage() {
		List<RestaurantVo> adminResList = adminService.adminResList();
		Controller.init_page(5, 2, "식당상세보기", "adminStandbyResDetail", View.ADMIN_STANDBY_RES_DETAIL);
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
