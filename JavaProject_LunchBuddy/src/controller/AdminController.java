package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import print.AdminPrint;
import service.AdminService;
import util.ScanUtil;
import util.View;
import vo.AdminVo;
import vo.RestaurantVo;
import vo.ReviewVo;

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
			case ADMIN_REVIEW_MANAGE:	//관리자 리뷰관리(리스트)
				view = adminReviewManage();
				break;
			case ADMIN_REVIEW_DETAIL:	//관리자 리뷰 상세보기
				view = adminReviewDetail();
				break;
			case ADMIN_REVIEW_BLIND:	//관리자 리뷰 블라인드 처리
				view = adminReviewBlind();
				break;
			case ADMIN_REVIEW_SEARCH:	//관리자 리뷰 검색
				view = adminReviewSearch();
				break;
			case ADMIN_REVIEW_SEARCH_RESNAME: //식당이름으로 리뷰검색
				view = adminReviewSearchResname();
				break;
			case ADMIN_REVIEW_SEARCH_NICKNAME: //닉네임으로 리뷰검색
				view = adminReviewSearchNickname();
				break;
			case ADMIN_MEMBER_MANAGE:
				view = adminHome();
				break;
			case ADMIN_RES_MANAGE:	//관리자 식당관리
				view = adminResManage();
				break;
			case ADMIN_REGI_RES_MANAGE:		//관리자 등록된 식당 관리(리스트)
				view = adminRegiResManage();
				break;
			case ADMIN_REGI_RES_DETAIL:		//관리자 등록된 식당 상세보기
				view = adminRegiResDetail();
				break;
			case ADMIN_MODIFY_REGI_RES_DETAIL:	//관리자 등록된 식당 수정
				view = adminModifyRegiResDeatil();
				break;
			case ADMIN_STANDBY_RES_MANAGE:	//관리자 미등록 식당 관리(리스트)
				view = adminStandbyResManage();
				break;
			case ADMIN_STANDBY_RES_DETAIL:	//관리자 미등록 식당 상세보기
				view = adminStandbyResDetail();
				break;
			case ADMIN_MODIFY_STANDBY_RES_DETAIL:	//관리자 미등록 식당 수정
				view = adminModifyStandbyResDetail();
				break;
			default :
				Controller.removeHistory();
				return view;
			}
		}
	}

	//관리자 미등록 식당 2.수정
	public View adminModifyStandbyResDetail() {
		RestaurantVo standbyRes = (RestaurantVo)sessionStorage.get("adminStandbyResDetail");
		printAdminModifyResDetailSelect();
		int select = ScanUtil.nextInt("메뉴 선택 >> ");
		switch (select) {
		case 1:
			String newResName = ScanUtil.nextLine("새로운 식당이름 : ");
			if(newResName.isEmpty()) {
				System.out.println("식당이름을 입력해주세요");
				newResName = ScanUtil.nextLine("새로운 식당이름 : ");
			}
			standbyRes.setRes_name(newResName);
			adminService.adminUpdateResName(newResName, standbyRes.getRes_no());
			RestaurantVo adminModiResDetail = adminService.adminSelectModifyDetail(standbyRes.getRes_no());
			printAdminModifyResDetail(adminModiResDetail);
			break;
		case 2:
			System.out.println("이동시간은 숫자만 입력가능합니다");
			int newWalk = ScanUtil.nextIntW("이동시간 : ");
			standbyRes.setRes_walk(newWalk);
			adminService.adminUpdateWalk(newWalk, standbyRes.getRes_no());
			RestaurantVo adminModiWalkDetail = adminService.adminSelectModifyDetail(standbyRes.getRes_no());
			printAdminModifyResDetail(adminModiWalkDetail);
			break;
		case 3:
			System.out.println("예약가능여부는 숫자만 입력가능합니다");
			String newBookyn = "";
			while(true) {
				int book = ScanUtil.nextIntB("새로운 예약가능여부(1.가능/2.불가능/3.미확인) : ");
				if(book == 1) {
					newBookyn = "가능";
				}
				else if(book == 2 ) {
					newBookyn = "불가능";
				}
				else if(book == 3) {
					newBookyn = "미확인";
				}
				else {
					System.out.println("숫자 1,2,3 중 한 개를 입력해주세요");
					continue;
				}
				break;
			}
			standbyRes.setRes_bookyn(newBookyn);
			adminService.adminUpdateBook(newBookyn, standbyRes.getRes_no());
			RestaurantVo adminModiBookDetail = adminService.adminSelectModifyDetail(standbyRes.getRes_no());
			printAdminModifyResDetail(adminModiBookDetail);
			break;
		case 4:
			String newAdd = ScanUtil.nextLine("새로운 주소 : ");
			if(newAdd.isEmpty()) {
				System.out.println("주소를 입력해주세요");
				newAdd = ScanUtil.nextLine("새로운 주소 : ");
			}
			standbyRes.setRes_add(newAdd);
			adminService.adminUpdateAdd(newAdd, standbyRes.getRes_no());
			RestaurantVo adminModiAddDetail = adminService.adminSelectModifyDetail(standbyRes.getRes_no());
			printAdminModifyResDetail(adminModiAddDetail);
			break;
		case 5:
			String newPhone = ScanUtil.nextLine("새로운 전화번호 : ");
			if(newPhone.isEmpty()) {
				System.out.println("전화번호를 입력해주세요");
				newPhone = ScanUtil.nextLine("새로운 전화번호 : ");
			}
			if(!newPhone.matches("^[0-9]*$")) {
				System.out.println("숫자만 입력가능합니다");
				newPhone = ScanUtil.nextLine("새로운 전화번호 : ");
			}
			standbyRes.setRes_phone(newPhone);
			adminService.adminUpdatePhone(newPhone, standbyRes.getRes_no());
			RestaurantVo adminModiPhoneDetail = adminService.adminSelectModifyDetail(standbyRes.getRes_no());
			printAdminModifyResDetail(adminModiPhoneDetail);
			break;
		case 6:
			String newMenu = ScanUtil.nextLine("새로운 메뉴 : ");
			if(newMenu.isEmpty()) {
				System.out.println("메뉴를 입력해주세요");
				newMenu = ScanUtil.nextLine("새로운 메뉴 : ");
			}
			standbyRes.setMenu_name(newMenu);
			adminService.adminUpdateMenu(newMenu, standbyRes.getRes_no());
			RestaurantVo adminModiMenuDetail = adminService.adminSelectModifyDetail(standbyRes.getRes_no());
			printAdminModifyResDetail(adminModiMenuDetail);
			break;
		case 7:
			System.out.println("가격은 숫자만 입력가능합니다");
			String newPrice = ScanUtil.nextLine("새로운 가격 : ");
			if(newPrice.isEmpty()) {
				System.out.println("가격을 입력해주세요");
				newPrice = ScanUtil.nextLine("가격 : ");
			}
			if(!newPrice.matches("^[0-9]*$")) {
				System.out.println("가격은 숫자만 입력가능합니다");
				newPrice = ScanUtil.nextLine("가격 : ");
			}
			standbyRes.setMenu_price(newPrice);
			adminService.adminUpdatePrice(newPrice, standbyRes.getRes_no());
			RestaurantVo adminModiPriceDetail = adminService.adminSelectModifyDetail(standbyRes.getRes_no());
			printAdminModifyResDetail(adminModiPriceDetail);
			break;
		case 8:
			adminService.adminResUpload(standbyRes.getRes_no());
			printAdminRegiRes();
			return View.ADMIN_HOME;
		case 9:
			return View.ADMIN_HOME;
		default:
			adminModifyStandbyResDetail();
		}
		sessionStorage.replace("adminStandbyResDetail", standbyRes);
		return View.ADMIN_MODIFY_STANDBY_RES_DETAIL;
	}
	
	//관리자 미등록 식당 상세보기
	public View adminStandbyResDetail() {
		RestaurantVo standbyRes = (RestaurantVo)sessionStorage.get("adminStandbyResDetail");
		Controller.sessionStorage.put("selectResNo", standbyRes.getRes_no());
		
		printAdminResDetail(standbyRes);
		printSelectForStandbyResDetail();
		
		int select = ScanUtil.nextInt("선택 >> ");
		switch (select) {
		case 1 ://식당정보 수정
			int yn = ScanUtil.nextInt("수정하시겠습니까? 1.예 2.아니오 |메뉴선택 : ");
			switch(yn) {
			case 1://예
				return View.ADMIN_MODIFY_STANDBY_RES_DETAIL;
			case 2://아니오
				System.out.println("수정을 취소합니다");
				System.out.println("이전 페이지로 이동합니다");//확인필
				return View.RES_ADD_ONE;//수정!!!!!!!!
			default:
				return View.ADMIN_STANDBY_RES_DETAIL;
			}
		case 2 ://식당등록
			adminService.adminResUpload(standbyRes.getRes_no());
			printAdminRegiRes();
			return View.ADMIN_HOME;
		case 9 : return View.ADMIN_HOME;
		case 0 : return Controller.goBack();
		default:
			Controller.removeHistory();
			return View.ADMIN_STANDBY_RES_DETAIL;
		}
	}
	//식당등록 관리자요청온거 확인 sessionStorage.get("resAddOnePrint");
	
	//관리자 식당관리->2.미등록 식당 관리(리스트)
	public View adminStandbyResManage() {
		List<RestaurantVo> adminStandbyResList = adminService.adminStandbyResList();
		Controller.init_page(5, 2, "식당상세보기", "adminStandbyResDetail", View.ADMIN_STANDBY_RES_DETAIL);
		sessionStorage.put("list_for_paging", adminStandbyResList);
		return View.LIST_PAGING;
	}
	
	//관리자 등록된 식당 수정
	public View adminModifyRegiResDeatil() {
		RestaurantVo regiRes = (RestaurantVo)sessionStorage.get("adminRegiResDetail");
		printAdminModifyResDetailSelect();
		int select = ScanUtil.nextInt("메뉴 선택 >> ");
		switch (select) {
		case 1:
			String newResName = ScanUtil.nextLine("새로운 식당이름 : ");
			if(newResName.isEmpty()) {
				System.out.println("식당이름을 입력해주세요");
				newResName = ScanUtil.nextLine("새로운 식당이름 : ");
			}
			regiRes.setRes_name(newResName);
			adminService.adminUpdateResName(newResName, regiRes.getRes_no());
			RestaurantVo adminModiResDetail = adminService.adminSelectModifyDetail(regiRes.getRes_no());
			printAdminModifyResDetail(adminModiResDetail);
			break;
		case 2:
			System.out.println("이동시간은 숫자만 입력가능합니다");
			int newWalk = ScanUtil.nextIntW("이동시간 : ");
			regiRes.setRes_walk(newWalk);
			adminService.adminUpdateWalk(newWalk, regiRes.getRes_no());
			RestaurantVo adminModiWalkDetail = adminService.adminSelectModifyDetail(regiRes.getRes_no());
			printAdminModifyResDetail(adminModiWalkDetail);
			break;
		case 3:
			System.out.println("예약가능여부는 숫자만 입력가능합니다");
			String newBookyn = "";
			while(true) {
				int book = ScanUtil.nextIntB("새로운 예약가능여부(1.가능/2.불가능/3.미확인) : ");
				if(book == 1) {
					newBookyn = "가능";
				}
				else if(book == 2 ) {
					newBookyn = "불가능";
				}
				else if(book == 3) {
					newBookyn = "미확인";
				}
				else {
					System.out.println("숫자 1,2,3 중 한 개를 입력해주세요");
					continue;
				}
				break;
			}
			regiRes.setRes_bookyn(newBookyn);
			adminService.adminUpdateBook(newBookyn, regiRes.getRes_no());
			RestaurantVo adminModiBookDetail = adminService.adminSelectModifyDetail(regiRes.getRes_no());
			printAdminModifyResDetail(adminModiBookDetail);
			break;
		case 4:
			String newAdd = ScanUtil.nextLine("새로운 주소 : ");
			if(newAdd.isEmpty()) {
				System.out.println("주소를 입력해주세요");
				newAdd = ScanUtil.nextLine("새로운 주소 : ");
			}
			regiRes.setRes_add(newAdd);
			adminService.adminUpdateAdd(newAdd, regiRes.getRes_no());
			RestaurantVo adminModiAddDetail = adminService.adminSelectModifyDetail(regiRes.getRes_no());
			printAdminModifyResDetail(adminModiAddDetail);
			break;
		case 5:
			String newPhone = ScanUtil.nextLine("새로운 전화번호 : ");
			if(newPhone.isEmpty()) {
				System.out.println("전화번호를 입력해주세요");
				newPhone = ScanUtil.nextLine("새로운 전화번호 : ");
			}
			if(!newPhone.matches("^[0-9]*$")) {
				System.out.println("숫자만 입력가능합니다");
				newPhone = ScanUtil.nextLine("새로운 전화번호 : ");
			}
			regiRes.setRes_phone(newPhone);
			adminService.adminUpdatePhone(newPhone, regiRes.getRes_no());
			RestaurantVo adminModiPhoneDetail = adminService.adminSelectModifyDetail(regiRes.getRes_no());
			printAdminModifyResDetail(adminModiPhoneDetail);
			break;
		case 6:
			String newMenu = ScanUtil.nextLine("새로운 메뉴 : ");
			if(newMenu.isEmpty()) {
				System.out.println("메뉴를 입력해주세요");
				newMenu = ScanUtil.nextLine("새로운 메뉴 : ");
			}
			regiRes.setMenu_name(newMenu);
			adminService.adminUpdateMenu(newMenu, regiRes.getRes_no());
			RestaurantVo adminModiMenuDetail = adminService.adminSelectModifyDetail(regiRes.getRes_no());
			printAdminModifyResDetail(adminModiMenuDetail);
			break;
		case 7:
			System.out.println("가격은 숫자만 입력가능합니다");
			String newPrice = ScanUtil.nextLine("새로운 가격 : ");
			if(newPrice.isEmpty()) {
				System.out.println("가격을 입력해주세요");
				newPrice = ScanUtil.nextLine("가격 : ");
			}
			if(!newPrice.matches("^[0-9]*$")) {
				System.out.println("가격은 숫자만 입력가능합니다");
				newPrice = ScanUtil.nextLine("가격 : ");
			}
			regiRes.setMenu_price(newPrice);
			adminService.adminUpdatePrice(newPrice, regiRes.getRes_no());
			RestaurantVo adminModiPriceDetail = adminService.adminSelectModifyDetail(regiRes.getRes_no());
			printAdminModifyResDetail(adminModiPriceDetail);
			break;
		case 8:
			adminService.adminResUpload(regiRes.getRes_no());
			printAdminRegiRes();
			return View.ADMIN_HOME;
		case 9:
			return View.ADMIN_HOME;
		default:
			adminModifyStandbyResDetail();
		}
		sessionStorage.replace("adminStandbyResDetail", regiRes);
		return View.ADMIN_MODIFY_REGI_RES_DETAIL;
	}
	
	//관리자 등록된 식당 상세보기
	public View adminRegiResDetail() {
		RestaurantVo regiRes = (RestaurantVo) Controller.sessionStorage.get("adminRegiResDetail");
		
		printAdminResDetail(regiRes);
		printSelectForRegiResDetail();
		
		int select = ScanUtil.nextInt("선택 >> ");
		switch (select) {
		case 1 ://식당정보수정
			int modi = ScanUtil.nextInt("수정하시겠습니까? 1.예 2.아니오 |메뉴선택 : ");
			switch(modi) {
			case 1:
				return View.ADMIN_MODIFY_REGI_RES_DETAIL;
			case 2:
				printModiCancle();
				return Controller.goBack();//확인필
			default://수정하시겠습니까?로 이동하고싶음!!!!ㅠㅠ
				return View.ADMIN_REGI_RES_DETAIL;
			}
		case 2 ://식당삭제
			int del = ScanUtil.nextInt("삭제하시겠습니까? 1.예 2.아니오 |메뉴선택 : ");
			switch(del) {
			case 1:
				adminService.adminResDelete(regiRes.getRes_no());
				printAdminDeleteRes();
				return View.ADMIN_HOME;
			case 2:
				printDelCancle();
				return Controller.goBack();//확인필
			default://삭제하시겠습니까?로 이동하고싶음!!!!ㅠㅠ
				return View.ADMIN_REGI_RES_DETAIL;
			}
		case 9 : return View.ADMIN_HOME;
		case 0 : return Controller.goBack();//안먹힘
		default:
			Controller.removeHistory();
			return View.ADMIN_REGI_RES_DETAIL;
		}
	}
	
	//관리자 식당관리->1.등록된 식당 관리(리스트)
	public View adminRegiResManage() {
		List<RestaurantVo> adminRegiResList = adminService.adminRegiResList();
		Controller.init_page(5, 2, "식당상세보기", "adminRegiResDetail", View.ADMIN_REGI_RES_DETAIL);
		sessionStorage.put("list_for_paging", adminRegiResList);
		return View.LIST_PAGING;
	}
	
	//관리자 식당관리
	public View adminResManage() {
		printAdminResManage();
		int select = ScanUtil.nextInt("메뉴를 선택하세요 ");
		switch (select) {
		case 1:
			return View.ADMIN_REGI_RES_MANAGE;
		case 2:
			return View.ADMIN_STANDBY_RES_MANAGE;
		case 0:
			return View.ADMIN_HOME;
		default :
			return View.ADMIN_RES_MANAGE;
		}
	}

	//관리자 리뷰 검색 - 닉네임
	public View adminReviewSearchNickname() {
		String nickName = ScanUtil.nextLine("닉네임을 검색하세요 : ");
		List<ReviewVo> reviewNickName = adminService.adminReviewSearchNickname(nickName);
		if(reviewNickName==null){
			System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			return View.ADMIN_REVIEW_SEARCH_RESNAME;
		}
		printBar();
		Controller.init_page(5, 2, "리뷰 상세 보기", "adminReviewDetail", View.ADMIN_REVIEW_DETAIL);
		sessionStorage.put("list_for_paging", reviewNickName);
		return View.LIST_PAGING;
	}
	
	//관리자 리뷰 검색 - 식당이름
	public View adminReviewSearchResname() {
		String resName = ScanUtil.nextLine("식당이름을 검색하세요 : ");
		List<ReviewVo> reviewResName = adminService.adminReviewSearchResname(resName);
		if(reviewResName==null){
			System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			return View.ADMIN_REVIEW_SEARCH_RESNAME;
		}
		printBar();
		Controller.init_page(5, 2, "리뷰 상세 보기", "adminReviewDetail", View.ADMIN_REVIEW_DETAIL);
		sessionStorage.put("list_for_paging", reviewResName);
		return View.LIST_PAGING;
	}

	//관리자 리뷰 검색
	public View adminReviewSearch(){
		printAdminReviewSearch();
		int select = ScanUtil.nextInt("선택 >>");
		switch (select) {
		case 1://식당이름으로 검색
			return View.ADMIN_REVIEW_SEARCH_RESNAME;
		case 2://닉네임으로 검색
			return View.ADMIN_REVIEW_SEARCH_NICKNAME;
		default:
			return View.ADMIN_REVIEW_SEARCH;
		}
	}

	//관리자 리뷰 블라인드 처리
	public View adminReviewBlind(){
		ReviewVo revDetail = (ReviewVo)sessionStorage.get("adminReviewDetail");
		adminService.adminReviewBlind(revDetail.getRev_no());
		ReviewVo revDetailChangeBlind = adminService.adminRevBlindCheck(revDetail.getRev_no());
		sessionStorage.put("adminReviewDetail", revDetailChangeBlind);
		printBlindReview();
		return View.ADMIN_REVIEW_DETAIL;
	}
	
	//관리자 리뷰관리-리뷰상세보기
	public View adminReviewDetail() {
		ReviewVo revDetail = (ReviewVo)sessionStorage.get("adminReviewDetail");
		
		printAdminRevDetail(revDetail);
		printSelectForReviewDetail();
		
		int select = ScanUtil.nextInt("선택 >> ");
		switch (select) {
		case 1 ://리뷰 블라인드
			int blind = ScanUtil.nextInt("현재 리뷰를 블라인드 하시겠습니까? 1.예 2.아니오 |메뉴선택 : ");
			switch(blind) {
			case 1:
				return View.ADMIN_REVIEW_BLIND;
			case 2:
				printBlindCancle();
				return Controller.goBack();//확인필
			default:
				return View.ADMIN_REVIEW_DETAIL;
			}
		case 9 : return View.ADMIN_HOME;
		case 0 : return Controller.goBack();//안먹힘
		default:
			Controller.removeHistory();
			return View.ADMIN_REVIEW_DETAIL;
		}
	}
	
	//관리자 리뷰관리
	public View adminReviewManage() {
		List<ReviewVo> adminReviewList = adminService.adminReviewList();
		sessionStorage.put("adminRevStart", adminReviewList);
		Controller.init_page(5,2,"리뷰상세보기", "adminReviewDetail", View.ADMIN_REVIEW_DETAIL);
		sessionStorage.put("list_for_paging", adminReviewList);
		return View.LIST_PAGING;
	}
	

	public View adminHome() {
		printAdminHome();
		int select = ScanUtil.nextInt("메뉴를 선택하세요 ");
		switch (select) {
		case 1://리뷰관리
			return View.ADMIN_REVIEW_MANAGE;
		case 2://회원관리
			return View.ADMIN_MEMBER_MANAGE;
		case 3://식당관리
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
