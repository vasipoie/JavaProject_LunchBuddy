package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import print.RestaurantPrint;
import service.MenuService;
import service.RestaurantService;
import util.ScanUtil;
import util.View;
import vo.MemberVo;
import vo.MenuVo;
import vo.RestaurantVo;

public class RestaurantController extends RestaurantPrint{
	Scanner sc = new Scanner(System.in);
	static private Map<String, Object> sessionStorage = Controller.sessionStorage;
	RestaurantService resService = RestaurantService.getInstance();
	MenuService menuService = MenuService.getInstance();
	
	void start() {
		View view = View.HOME;
		while (true) {
			Controller.newPage(view);
			switch (view) {
			case RES_SEARCH_SELECT:	//식당 검색 전 선택
				view = resSearchSelect();
				break;
			case RES_SEARCH_RESNAME://식당 이름으로 검색
				view = resSearchResName();
				break;
			case RES_SEARCH_CATEGORY://메뉴 카테고리로 검색
				view = resSearchCategory();
				break;
			case RES_LIST:		//식당 리스트
				view = resList();
				break;
			case RES_DETAIL:	//식당 상세보기
				view = resDetail();
				break;
			case RES_ADD:		//식당 등록
				view = resAdd();
				break;
			case RES_ADD_ONE:
				view = resAddOne();	//식당 등록요청 전에 사용자가 요청한 등록 출력
//			case ADMIN_RES_MANAGE:
//				view = adminHome();
//				break;
			}
		}
	}
	
	

	//식당 상세보기
	public View resDetail() {
		//페이징
		return null;
	}
	
	//식당 등록요청 전에 사용자가 요청한 등록 출력
	public View resAddOne() {
		MemberVo mb = (MemberVo) sessionStorage.get("log_in_member");
		RestaurantVo resAddOnePrint = (RestaurantVo) sessionStorage.get("resAddOnePrint");
		printBar();
		System.out.println(mb.getMem_nick()+"님이 입력한 식당 등록");
		printResAddOne(resAddOnePrint);
		
		return null;
	}

	//식당 등록
	public View resAdd() {
		//로그인확인
		MemberVo mb = (MemberVo) sessionStorage.get("log_in_member");
		if(mb==null) {
			int select = ScanUtil.nextInt("로그인이 되어있지 않습니다.\n1.로그인\s2.회원가입\s|메뉴선택 : ");
			switch(select) {
			case 1:
				return View.LOG_IN;
			case 2:
				return View.JOIN;
			}
		}
		printBar();
		System.out.println("식당 등록");
		printBar();
		System.out.println("예시)\n 카테고리 : 2\n 식당 이름 : 버거킹\n 주소 : 대전 중구 계룡로 853\n 전화번호 : 042-221-0332\n"
				+ " 예약가능여부(Y/N) : Y\n 대표메뉴 : 비프+슈림프버거 세트\n 가격 : 8500");
		printBar();
		System.out.println("카테고리 종류 : 1.한식/2.양식/3.아시아음식/4.일식/5.중식/6.분식/7.카페/8.뷔페/9.기타");
		String cateNo;
		int cate = ScanUtil.nextIntC("카테고리 번호 : ");
		switch(cate) {
		case 1 : cateNo = "01";	break;
		case 2 : cateNo = "02";	break;
		case 3 : cateNo = "03";	break;
		case 4 : cateNo = "04";	break;
		case 5 : cateNo = "05";	break;
		case 6 : cateNo = "06";	break;
		case 7 : cateNo = "07";	break;
		case 8 : cateNo = "08";	break;
		case 9 : cateNo = "09";	break;
		default : 
			System.out.println("잘못 입력했습니다. 다시 입력해주세요");
			return View.RES_ADD;
		}
		
//		String resName = ScanUtil.nextLine("식당 이름 : ");
//		if(resName.isEmpty()) {
//			System.out.println("식당 이름을 입력해주세요");
//			resName = ScanUtil.nextLine("식당 이름 : ");
//		}
//		
//		String address = ScanUtil.nextLine("주소 : ");
//		if(address.isEmpty()) {
//			System.out.println("주소를 입력해주세요");
//			address = ScanUtil.nextLine("주소 : ");
//		}
//	
//		String phone = ScanUtil.nextLine("전화번호 : ");
//		if(phone.isEmpty()) {
//			System.out.println("전화번호를 입력해주세요");
//			phone = ScanUtil.nextLine("전화번호 : ");
//		}
//		if(phone.contains("-")||phone.contains(".")) {
//			phone = phone.replaceAll("[-.]", "");
//		}
//		
//		String bookyn = ScanUtil.nextLine("예약가능여부(Y/N) : ");
//		if(bookyn.isEmpty()) {
//			System.out.println("예약가능여부를 입력해주세요");
//			bookyn = ScanUtil.nextLine("예약가능여부(Y/N) : ");
//		}
//		switch(bookyn) {
//		case "y":case"가능":case"네":case"ㅇ":case"ㅇㅇ":case"o":case"ㅛ":
//			bookyn = "Y";
//			break;
//		case "n":case"불가능":case"아니":case"ㄴ":case"ㄴㄴ":case"x":case"ㅜ":
//			bookyn = "N";
//			break;
//		default :
//			System.out.println("예약가능여부를 Y 또는 N으로 입력해주세요");
//			bookyn = ScanUtil.nextLine("예약가능여부(Y/N) : ");
//		}
//		
//		String sigMenu = ScanUtil.nextLine("대표메뉴 : ");
//		if(sigMenu.contains("&")) {
//			sigMenu = sigMenu.replace("&", "+");
//		}
//		
//		System.out.println("가격에는 숫자만 적으세요");
//		int price = ScanUtil.nextIntP("가격 : ");
		
		String resName = "테스트이름";
		String address = "테스트주소";
		String phone = "테스트번호";//수정!!!!!숫자만 넣게하기
		String bookyn = "y";
		String sigMenu = "테스트대표메뉴";
		String price = "999";
		
		//식당테이블에 회원이 새로 등록하려는 식당 insert, vo로 select
		List<Object> restAdd = new ArrayList<Object>();
//		restAdd(param) = res_no, mem_no, res_no, mem_no, rev_star, rev_cont, res_no, mem_no
		restAdd.add(cateNo);
		restAdd.add(cateNo);
		restAdd.add(resName);
		restAdd.add(address);
		restAdd.add(phone);
		restAdd.add(bookyn);
		restAdd.add(cateNo);
		resService.resAdd(restAdd);
		RestaurantVo resAddOneBefore = resService.resAddOneBefore(cateNo);
		
		//메뉴테이블에 대표메뉴, 가격 insert, vo로 select
		List<Object> menuAdd = new ArrayList<Object>();
//		menuAdd(param) = res_no, res_no, menu_name, menu_price, res_no
		menuAdd.add(resAddOneBefore.getRes_no());
		menuAdd.add(resAddOneBefore.getRes_no());
		menuAdd.add(sigMenu);
		menuAdd.add(price);
		menuAdd.add(resAddOneBefore.getRes_no());
		menuService.menuAdd(menuAdd);
		RestaurantVo resAddOnePrint = resService.resAddOnePrint(cateNo);
		sessionStorage.put("resAddOnePrint", resAddOnePrint);
		
		return View.RES_ADD_ONE; //다음 뷰로 이동해서 등록요청
	}

	//메뉴 카테고리로 검색
	public View resSearchCategory() {
		printCategory();
		int category = ScanUtil.nextInt("검색 하고싶은 카테고리의 숫자를 입력하세요: ");
		switch(category) {
		case 1:case 2:case 3:case 4:case 5:case 6:case 7:case 8:case 9:
			break;
		default :
			System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
			return View.RES_SEARCH_CATEGORY;
		}
		List<Map<String, Object>> cateList = resService.resSearchCategory(category);
		if(cateList==null) {
			System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
			return View.RES_SEARCH_CATEGORY;
		}
		printResList(cateList); //Vo에 평점,대표메뉴,가격 넣고 print에서 출력
		//이전페이지 식당상세보기 다음페이지 뒤로가기 홈 넣기
		int select = ScanUtil.nextInt("메뉴를 선택하세요\s");
		switch(select) {
		case 1:
			break;
		case 2:
			return View.RES_DETAIL;
		}
		return null;
	}

	public View resList() {
		//페이징
//		List<RestaurantVo> resSearchList = resService.
		new Controller().list_paging();
		
//		List<RestaurantVo> resList = resService.resList();
//		printResList(resList);
		List<Object> param = new ArrayList<Object>();
		int pageNo = 1;
		if(sessionStorage.containsKey("pageNo")) {
			pageNo = (int) sessionStorage.get("pageNo");
		}
		int start_no = 1+10*(pageNo-1);
		int last_no = 10*pageNo;
		param.add(start_no);
		param.add(last_no);
		printResList((List<Map<String, Object>>) sessionStorage.get("resSearchName"));
		int select = ScanUtil.nextInt("메뉴를 선택하세요\s");
		switch(select) {
		case 1:
			if(10*pageNo > last_no) {
				pageNo = pageNo-1;
			}
			sessionStorage.put("pageNo", pageNo+1);
			return View.RES_SEARCH_CATEGORY;
		case 2:
			return View.RES_SEARCH_CATEGORY;
		}
		return null;
	}
	
	//식당 이름으로 검색
	public View resSearchResName() {
		List<Object> name = new ArrayList<Object>();
		name.add(ScanUtil.nextLine("식당 이름을 검색하세요 : "));
		List<Map<String, Object>> rsrn = resService.resSearchResName(name);
		if(rsrn==null) {
			System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
			return View.RES_SEARCH_RESNAME;
		}
		Controller.init_page(5,2,"식당 상세 보기", "",View.RES_DETAIL);
		sessionStorage.put("resSearchName", rsrn);
		return new Controller().list_paging();
		
	}

	//식당 검색 전 선택
	public View resSearchSelect() {
		printResSearchSelect();
		int select = ScanUtil.nextInt("메뉴를 선택하세요\s");
		switch(select) {
		case 1:
			return View.RES_SEARCH_RESNAME;//식당이름으로 검색
		case 2:
			return View.RES_SEARCH_CATEGORY;//식당카테고리로 검색
		default:
			return View.RES_SEARCH_SELECT;
		}
	}

//	View resHome() {
//		int select = ScanUtil.nextInt("식당메뉴를 선택하세요\s");
//		printResHome();
//		switch (select) {
//		case 1:
//			return View.RES_SEARCH;
//		case 2:
//			return View.RES_LIST;
//		case 3:
//			return View.RES_DETAIL;
//		case 0:
//			return View.RES_ADD;
//		default :
//			return View.HOME;
//		}
//	}
}
