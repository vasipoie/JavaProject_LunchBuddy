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
	AdminController ac = new AdminController();
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
				break;
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
	
	//식당등록 요청 전 2.수정
	public View resAddModify() {
		//restAdd : 카테고리no,카테고리no,식당이름,주소,번호,예약,카테고리no
		List<Object> restAdd = (List<Object>) sessionStorage.get("restAdd");
		//chk : 식당이름,카테고리(01),주소,전화번호,예약(가능),대표메뉴,가격
		List<Object> chk = (List<Object>) sessionStorage.get("chk");
		printResInsertBefore(restAdd,chk);
		printResAddModify();
		int select = ScanUtil.nextInt("메뉴 선택 >> ");
		switch (select) {
		case 1:
			String newResName = ScanUtil.nextLine("식당이름 : ");
			if(newResName.isEmpty()) {
				System.out.println("식당 이름을 입력해주세요");
				newResName = ScanUtil.nextLine("식당이름 : ");
			}
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
		case 0:
			return View.RES_ADD_ONE;
		default:
			resAddModify();
		}
		
		//완료 후 수정된 내역 보여주기
		sessionStorage.replace("restAdd", restAdd);
		sessionStorage.replace("chk", chk);
		return View.RES_ADD_ONE;
	}

	//식당 등록요청 전에 사용자가 입력한 등록 출력 -> 1.등록요청 2.수정 3.등록취소
	public View resAddOne() {
		MemberVo mb = (MemberVo) Controller.sessionStorage.get("log_in_member");
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
		
		//restAdd : 카테고리no,카테고리no,식당이름,주소,번호,예약,카테고리no
		List<Object> restAdd = (List<Object>) sessionStorage.get("restAdd");
		//chk : 식당이름,카테고리(01),주소,전화번호,예약(가능),대표메뉴,가격
		List<Object> chk = (List<Object>) sessionStorage.get("chk");
		
		printResInsertBefore(restAdd,chk);
		printSelectResInsertBefore();
		//아래수정!!!!!!!!!!!!!!!
		int select = ScanUtil.nextInt("이야아아아아 메뉴를 선택하세요\s");
		switch (select) {
		case 1:
			resService.resAdd(restAdd);
			RestaurantVo resAddOneBefore = resService.resAddOneBefore((String) restAdd.get(4));//cateNo
			List<Object> menuAdd = new ArrayList<Object>();
			menuAdd.add(resAddOneBefore.getRes_no());
			menuAdd.add(resAddOneBefore.getRes_no());
			menuAdd.add(chk.get(5));
			menuAdd.add(chk.get(6));
			menuAdd.add(resAddOneBefore.getRes_no());
//			menuAdd(param) = res_no, res_no, menu_name, menu_price, res_no
			menuService.menuAdd(menuAdd);
			RestaurantVo resAddOnePrint = resService.resAddOnePrint((String) restAdd.get(4));
			sessionStorage.put("resAddOnePrint", resAddOnePrint);
			
			printRegiReque();
			return View.HOME;
			
		case 2:
			int yn = ScanUtil.nextInt("수정하시겠습니까?\s1.예\s2.아니오\s|메뉴선택 : ");
			switch(yn) {
			case 1:
				return View.RES_ADD_MODIFY;
			case 2:
				System.out.println("수정을 취소합니다");
				System.out.println("이전 페이지로 이동합니다");
				return View.RES_ADD_ONE;
			default:
				return View.RES_ADD_ONE;
			}
			
		case 3:
			printRegiCancle();
			sessionStorage.remove("restAdd");
			sessionStorage.remove("chk");
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
		printResAddExample();
		//카테고리
		printResAddCategory();
//		String cateNo;
//		int cate = ScanUtil.nextIntC("카테고리 번호 : ");
//		switch(cate) {
//		case 1 : cateNo = "01";	break;
//		case 2 : cateNo = "02";	break;
//		case 3 : cateNo = "03";	break;
//		case 4 : cateNo = "04";	break;
//		case 5 : cateNo = "05";	break;
//		case 6 : cateNo = "06";	break;
//		case 7 : cateNo = "07";	break;
//		case 8 : cateNo = "08";	break;
//		case 9 : cateNo = "09";	break;
//		default : 
//			System.out.println("잘못 입력했습니다. 다시 입력해주세요");
//			return View.RES_ADD;
//		}
//		//식당이름
//		String resName = ScanUtil.nextLine("식당 이름 : ");
//		if(resName.isEmpty()) {
//			System.out.println("식당 이름을 입력해주세요");
//			resName = ScanUtil.nextLine("식당 이름 : ");
//		}
//		//주소
//		String address = ScanUtil.nextLine("주소 : ");
//		if(address.isEmpty()) {
//			System.out.println("주소를 입력해주세요");
//			address = ScanUtil.nextLine("주소 : ");
//		}
//		//전화번호
//		String phone = ScanUtil.nextLine("전화번호 : ");
//		if(phone.isEmpty()) {
//			System.out.println("전화번호를 입력해주세요");
//			phone = ScanUtil.nextLine("전화번호 : ");
//		}
//		if(phone.contains("-")||phone.contains(".")) {
//			phone = phone.replaceAll("[-.]", "");
//		}
//		if(!phone.matches("^[0-9]*$")) {
//			System.out.println("숫자만 입력가능합니다");
//			phone = ScanUtil.nextLine("전화번호 : ");
//		}
//		//예약가능여부
//		System.out.println("예약가능여부는 숫자만 입력가능합니다");
//		String bookyn = "";
//		while(true) {
//			int book = ScanUtil.nextIntB("예약가능여부(1.가능/2.불가능/3.미확인) : ");
//			if(book == 1) {
//				bookyn = "가능";
//			}
//			if(book == 2 ) {
//				bookyn = "불가능";
//			}
//			if(book == 3) {
//				bookyn = "미확인";
//			}
//			else {
//				System.out.println("숫자 1,2,3 중 한 개를 입력해주세요");
//				continue;
//			}
//			break;
//		}
//		//대표메뉴
//		String sigMenu = ScanUtil.nextLine("대표메뉴 : ");
//		if(sigMenu.isEmpty()) {
//			System.out.println("대표메뉴를 입력해주세요");
//			sigMenu = ScanUtil.nextLine("대표메뉴 : ");
//		}
//		if(sigMenu.contains("&")) {
//			sigMenu = sigMenu.replace("&", "+");
//		}
//		//가격
//		System.out.println("가격은 숫자만 입력가능합니다");
//		String price = ScanUtil.nextLine("가격 : ");
//		if(price.isEmpty()) {
//			System.out.println("가격을 입력해주세요");
//			price = ScanUtil.nextLine("가격 : ");
//		}
//		if(!price.matches("^[0-9]*$")) {
//			System.out.println("가격은 숫자만 입력가능합니다");
//			price = ScanUtil.nextLine("가격 : ");
//		}
		
		String cateNo = "01";
		String resName = "옹기마을";
		String address = "대전 중구 계룡로816번길 16";
		String phone = "0425263332";
		String bookyn = "가능";
		String sigMenu = "부대찌개";
		String price = "8000";
		
		//insert전 회원확인용
		List<Object> chk = new ArrayList<Object>();
		chk.add(resName);
		chk.add(cateNo);
		chk.add(address);
		chk.add(phone);
		chk.add(bookyn);
		chk.add(sigMenu);
		chk.add(price);
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
		//등록요청 후에 insert되게하려고 일단 sessionStorage 저장
		sessionStorage.put("restAdd", restAdd);
		return View.RES_ADD_ONE; //다음 뷰로 이동해서 등록요청
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

	//식당 이름으로 검색할 때 상세보기
	public View resDetail() {
		RestaurantVo rest = (RestaurantVo) Controller.sessionStorage.get("resDetailResName");
		printResDetail(rest);
//		List<RestaurantVo> restaurant = resService.getResDetail(rest.getRes_no());
		print_select_for_restDetail();
		int select = ScanUtil.nextInt("선택 >> ");
		switch (select) {
		case 1 :
			Controller.sessionStorage.put("selected_rest_no", rest.getRes_no());
			return View.SEE_REVIEW_BY_RES;
		case 2 :
			Controller.sessionStorage.put("", rest);
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
