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
import vo.RestaurantVo;

public class RestaurantController extends RestaurantPrint{
	Scanner sc = new Scanner(System.in);
	static private Map<String, Object> sessionStorage = Controller.sessionStorage;
	RestaurantService resService = RestaurantService.getInstance();
	MenuService menuService = MenuService.getInstance();
	
	public View restController(View view) {
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
			case RES_DETAIL:	//식당 상세보기
				view = resDetail();
				break;
			case RES_ADD:		//식당 등록
				view = resAdd();
				break;
			case RES_ADD_ONE:  //식당 등록요청 전에 사용자가 요청한 등록 출력
				view = resAddOne();	
//			case ADMIN_RES_MANAGE:
//				view = adminHome();
//				break;
			case RES_DETAIL_CATEGORY: //메뉴 카테고리로 검색할 때 상세보기
				view = resDetailCategory();
				break;
			case RES_ADD_MODIFY: //식당등록요청 전 수정
				view = resAddModify();
				break;
			default :
				Controller.removeHistory();
				return view;
			}
		}
	}
	
	//식당등록 요청 전 수정
	public View resAddModify() {
		MemberVo mb = (MemberVo) sessionStorage.get("log_in_member");
		RestaurantVo resAddOnePrint = (RestaurantVo) sessionStorage.get("resAddOnePrint");
		printBar();
		System.out.println(mb.getMem_nick()+"님이 입력한 식당 등록");
		printResAddOne(resAddOnePrint);
		
		printResAddModify();
		int select = ScanUtil.nextInt("메뉴 선택 >> ");
		switch (select) {
//		case 1:
//			boolean chk = true;
//			String newCate="";
//			while(chk) {
//				chk = false;
//				System.out.println("카테고리 종류 : 1.한식/2.양식/3.아시아음식/4.일식/5.중식/6.분식/7.카페/8.뷔페/9.기타");
//				int cate = ScanUtil.nextIntC("카테고리 번호 : ");
//				switch(cate) {
//				case 1 : newCate = "01"; break;
//				case 2 : newCate = "02"; break;
//				case 3 : newCate = "03"; break;
//				case 4 : newCate = "04"; break;
//				case 5 : newCate = "05"; break;
//				case 6 : newCate = "06"; break;
//				case 7 : newCate = "07"; break;
//				case 8 : newCate = "08"; break;
//				case 9 : newCate = "09"; break;
//				default : 
//					System.out.println("잘못 입력했습니다. 다시 입력해주세요");
//					chk = true;
//				}
//			}
//			resService.updateCate(newCate, resAddOnePrint.getRes_no());
//			break;
		case 1:
			String newResName = ScanUtil.nextLine("식당이름 : ");
			if(newResName.isEmpty()) {
				System.out.println("식당 이름을 입력해주세요");
				newResName = ScanUtil.nextLine("식당이름 : ");
			}
			resService.updateResName(newResName,resAddOnePrint.getRes_no());
			break;
		case 2:
			String newAdd = ScanUtil.nextLine("주소 : ");
			if(newAdd.isEmpty()) {
				System.out.println("주소를 입력해주세요");
				newAdd = ScanUtil.nextLine("주소 : ");
			}
			resService.updateAdd(newAdd,resAddOnePrint.getRes_no());
			break;
		case 3:
			String newPhone = ScanUtil.nextLine("전화번호 : ");
			if(newPhone.isEmpty()) {
				System.out.println("전화번호를 입력해주세요");
				newPhone = ScanUtil.nextLine("전화번호 : ");
			}
			if(newPhone!="[0-9]-") {
				System.out.println("숫자만 입력가능합니다");
				newPhone = ScanUtil.nextLine("전화번호 : ");
			}
			resService.updatePhone(newPhone,resAddOnePrint.getRes_no());
			break;
		case 4:
			System.out.println("예약가능여부는 숫자만 입력가능합니다");
			String newBookyn = "";
			int book = ScanUtil.nextInt("예약가능여부(1.가능/2.불가능/3.미확인) : ");
			switch(book) {
			case 1:
				newBookyn = "가능";
				break;
			case 2:
				newBookyn = "불가능";
				break;
			case 3:
				newBookyn = "미확인";
				break;
			default :
				System.out.println("숫자 1,2,3 중 한 개를 입력해주세요");
				book = ScanUtil.nextInt("예약가능여부(1.가능/2.불가능/3.미확인) : ");
			}
			resService.updateBookyn(newBookyn,resAddOnePrint.getRes_no());
			break;
		case 5:
			System.out.println("가격은 숫자만 입력가능합니다");
			String newPrice = ScanUtil.nextLine("가격 : ");
			if(newPrice.isEmpty()) {
				System.out.println("가격을 입력해주세요");
				newPrice = ScanUtil.nextLine("가격 : ");
			}
			resService.updatePrice(newPrice,resAddOnePrint.getRes_no());
			break;
		case 0:
			return View.RES_ADD_ONE;
		default:
			resAddModify();
		}
		
		//완료 후 수정된 내역 보여주기
		resAddOnePrint = resService.modifyResAdd(resAddOnePrint.getRes_no());
		sessionStorage.replace("resAddOnePrint", resAddOnePrint);
		return View.RES_ADD_ONE;
	}

	//메뉴 카테고리로 검색할 때 상세보기
	public View resDetailCategory() {
		RestaurantVo cate = (RestaurantVo) sessionStorage.get("resDetailCategory");
		printCateDetail(cate);
		print_select_for_restDetail();
		int select = ScanUtil.nextInt("선택 >> ");
		switch (select) {
		case 1 :
			Controller.sessionStorage.put("selected_res_no", cate.getRes_no());
			return View.SEE_REVIEW_BY_RES;
		case 2 :
			Controller.sessionStorage.put("selected_review", cate);
			return View.SEE_REVIEW_BY_WRITER;
		case 3 : 
			Controller.sessionStorage.put("menu_review", cate);
			return View.ADD_REVIEW;
		case 9 : return View.HOME;
		case 0 : return Controller.goBack();
		default:
			Controller.removeHistory();
			return View.RES_DETAIL_CATEGORY;
		}
	}

	//식당 이름으로 검색할 때 상세보기
	public View resDetail() {
		RestaurantVo rest = (RestaurantVo) Controller.sessionStorage.get("resDetailResName");
		printResDetail(rest);
		List<RestaurantVo> restaurant = resService.getResDetail(rest.getRes_no());
		print_select_for_restDetail();
		int select = ScanUtil.nextInt("선택 >> ");
		switch (select) {
		case 1 :
			Controller.sessionStorage.put("selected_rest_no", rest.getRes_no());
			return View.SEE_REVIEW_BY_RES;
		case 2 :
			Controller.sessionStorage.put("selected_review", rest);
			return View.SEE_REVIEW_BY_WRITER;
		case 3 : 
			Controller.sessionStorage.put("menu_review", rest);
			return View.ADD_REVIEW;
		case 9 : return View.HOME;
		case 0 : return Controller.goBack();
		default:
			Controller.removeHistory();
			return View.RES_DETAIL;
		}
	}
	
	//식당 등록요청 전에 사용자가 요청한 등록 출력 -> 관리자한테 등록요청 보내기
	public View resAddOne() {
		MemberVo mb = (MemberVo) sessionStorage.get("log_in_member");
//		RestaurantVo resAddOnePrint = (RestaurantVo) sessionStorage.get("resAddOnePrint");
//		printResAddOne(resAddOnePrint);
		int select = ScanUtil.nextInt("메뉴를 선택하세요\s");
		switch (select) {
		case 1://관리자가 등록요청온거 확인하기
			List<Object> restAdd = (List<Object>) sessionStorage.get("restAdd");
			List<Object> chk = (List<Object>) sessionStorage.get("chk");
			resService.resAdd(restAdd);
			RestaurantVo resAddOneBefore = resService.resAddOneBefore((String) restAdd.get(0));
			List<Object> menuAdd = new ArrayList<Object>();
			menuAdd.add(resAddOneBefore.getRes_no());
			menuAdd.add(resAddOneBefore.getRes_no());
			menuAdd.add(chk.get(5));
			menuAdd.add(chk.get(6));
			menuAdd.add(resAddOneBefore.getRes_no());
			menuService.menuAdd(menuAdd);
			RestaurantVo resAddOnePrint = resService.resAddOnePrint((String) restAdd.get(0));
			
			printRegiReque();
			return View.HOME;
		case 2:
			printBar();
			System.out.println("수정하시겠습니까? 1.예 2.아니오");
			int modify = ScanUtil.nextInt("메뉴선택 : ");
			switch(modify) {
			case 1:
				return View.RES_ADD_MODIFY;
			case 2:
				return View.RES_ADD_ONE;
			default:
				return View.RES_ADD_ONE;
			}
		case 3:
			printBar();
			sessionStorage.remove("resAddOnePrint");
			System.out.println("등록이 취소되었습니다");
			System.out.println("홈으로 이동합니다");
			pause();
			return View.HOME;
		default:
			return View.RES_ADD_ONE;
		}
	}

	//식당 등록
	public View resAdd() {
		page_need_login();
		//로그인 -> 로그인 되어있으면 잠시후 로그인페이지로 이동합니다 안나오게?
		MemberVo mb = (MemberVo) sessionStorage.get("log_in_member");
		if(mb==null) {
			int select = ScanUtil.nextInt("1.로그인\s2.회원가입\s|메뉴선택 : ");
			printBar();
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
		//카테고리,식당이름,주소,전화번호,예약가능여부,대표메뉴,가격
		System.out.println("예시)\n 카테고리 : 2\n 식당 이름 : 버거킹\n 주소 : 대전 중구 계룡로 853\n 전화번호 : 0422210332\n"
				+ " 예약가능여부(1.가능/2.불가능/3.미확인) : 1\n 대표메뉴 : 비프+슈림프버거 세트\n 가격 : 8500");
		printBar();
		//카테고리
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
		//식당이름
		String resName = ScanUtil.nextLine("식당 이름 : ");
		if(resName.isEmpty()) {
			System.out.println("식당 이름을 입력해주세요");
			resName = ScanUtil.nextLine("식당 이름 : ");
		}
		//주소
		String address = ScanUtil.nextLine("주소 : ");
		if(address.isEmpty()) {
			System.out.println("주소를 입력해주세요");
			address = ScanUtil.nextLine("주소 : ");
		}
		//전화번호
		String phone = ScanUtil.nextLine("전화번호 : ");
		if(phone.isEmpty()) {
			System.out.println("전화번호를 입력해주세요");
			phone = ScanUtil.nextLine("전화번호 : ");
		}
		if(phone.contains("-")||phone.contains(".")) {
			phone = phone.replaceAll("[-.]", "");
		}
		if(phone!="[0-9]-") {
			System.out.println("숫자만 입력가능합니다");
			phone = ScanUtil.nextLine("전화번호 : ");
		}
		//예약가능여부
		System.out.println("예약가능여부는 숫자만 입력가능합니다");
		String bookyn = "";
		int book = ScanUtil.nextInt("예약가능여부(1.가능/2.불가능/3.미확인) : ");
		switch(book) {
		case 1:
			bookyn = "가능";
			break;
		case 2:
			bookyn = "불가능";
			break;
		case 3:
			bookyn = "미확인";
			break;
		default :
			System.out.println("숫자 1,2,3 중 한 개를 입력해주세요");
			book = ScanUtil.nextInt("예약가능여부(1.가능/2.불가능/3.미확인) : ");
		}
		//대표메뉴
		String sigMenu = ScanUtil.nextLine("대표메뉴 : ");
		if(sigMenu.isEmpty()) {
			System.out.println("대표메뉴를 입력해주세요");
			sigMenu = ScanUtil.nextLine("대표메뉴 : ");
		}
		if(sigMenu.contains("&")) {
			sigMenu = sigMenu.replace("&", "+");
		}
		//가격
		System.out.println("가격은 숫자만 입력가능합니다");
		String price = ScanUtil.nextLineP("가격 : ");
		if(price.isEmpty()) {
			System.out.println("가격을 입력해주세요");
			price = ScanUtil.nextLine("가격 : ");
		}
		
//		String resName = "테스트이름";
//		String address = "테스트주소";
//		String phone = "테스트번호";//수정!!!!!숫자만 넣게하기
//		String bookyn = "y";
//		String sigMenu = "테스트대표메뉴";
//		String price = "999";
		
		//insert전 회원확인용
		List<Object> chk = new ArrayList<Object>();
		chk.add(resName);
		chk.add(cateNo);
		chk.add(address);
		chk.add(phone);
		chk.add(bookyn);
		chk.add(sigMenu);
		chk.add(price);
		printResInsertBefore(chk);
		sessionStorage.put("chk", chk);
		
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
		
		//등록요청 누르고 나면 insert되게하기
		sessionStorage.put("restAdd", restAdd);

//		List<Object> restAdd = (List<Object>) sessionStorage.get("restAdd");
//		resService.resAdd(restAdd);
//		RestaurantVo resAddOneBefore = resService.resAddOneBefore(cateNo);
		
		//메뉴테이블에 대표메뉴, 가격 insert, vo로 select
//		List<Object> menuAdd = new ArrayList<Object>();
////		menuAdd(param) = res_no, res_no, menu_name, menu_price, res_no
//		menuAdd.add(resAddOneBefore.getRes_no());
//		menuAdd.add(resAddOneBefore.getRes_no());
//		menuAdd.add(sigMenu);
//		menuAdd.add(price);
//		menuAdd.add(resAddOneBefore.getRes_no());
		
		//
//		sessionStorage.put("menuAdd", menuAdd);
		
//		List<Object> menuAdd = (List<Object>) sessionStorage.get("menuAdd");
//		menuService.menuAdd(menuAdd);
//		RestaurantVo resAddOnePrint = resService.resAddOnePrint(cateNo);
//		sessionStorage.put("resAddOnePrint", resAddOnePrint);
		
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
		List<RestaurantVo> cateList = resService.resSearchCategory(category);
		if(cateList==null) {
			System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
			return View.RES_SEARCH_CATEGORY;
		}
		printBar();
		Controller.init_page(5,2,"식당 상세 보기", "resDetailCategory",View.RES_DETAIL_CATEGORY);
		sessionStorage.put("list_for_paging", cateList);
		return View.LIST_PAGING;
	}

	//식당 이름으로 검색
	public View resSearchResName() {
		String name = ScanUtil.nextLine("식당 이름을 검색하세요 : ");
		List<RestaurantVo> rsrn = resService.resSearchResName(name);
		if(rsrn==null) {
			System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
			return View.RES_SEARCH_RESNAME;
		}
		printBar();
//		sessionStorage.put("resSearchResName", rsrn);
		Controller.init_page(5,2,"식당 상세 보기", "resDetailResName",View.RES_DETAIL);
		sessionStorage.put("list_for_paging", rsrn);
		return View.LIST_PAGING;
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
}
