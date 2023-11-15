package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import print.RestaurantPrint;
import service.RestaurantService;
import util.ScanUtil;
import util.View;
import vo.MemberVo;
import vo.RestaurantVo;

public class RestaurantController extends RestaurantPrint{
	
	static private Map<String, Object> sessionStorage = Controller.sessionStorage;
	RestaurantService resService = RestaurantService.getInstance();
	
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
//				view = resDetail();
				break;
			case RES_ADD:		//식당 등록
				view = resAdd();
				break;
//			case ADMIN_RES_MANAGE:
//				view = adminHome();
//				break;
			}
		}
	}
	
	//식당 등록
	public View resAdd() {
		//로그인확인
		MemberVo mb = (MemberVo) sessionStorage.get("log_in_member");
		if(mb==null) {
			int select = ScanUtil.nextInt("로그인이 되어있지 않습니다.\s1.로그인\s2.회원가입\n");
			switch(select) {
			case 1:
				return View.LOG_IN;
			case 2:
				return View.JOIN;
			}
		}
//		Controller.pageHistory.remove(Controller.pageHistory.size());
		printBar();
		List<Object> restAdd = new ArrayList<Object>();
		System.out.println("카테고리 종류 : 1.한식/2.양식/3.아시아음식/4.일식/5.중식/6.분식/7.카페/8.뷔페/9.기타");
		String cate = ScanUtil.nextLine("카테고리 번호 : ");
		String resName = ScanUtil.nextLine("식당 이름 : ");
		String address = ScanUtil.nextLine("주소 : ");
		String phone = ScanUtil.nextLine("전화번호 : ");
		String bookyn = ScanUtil.nextLine("예약가능여부 : ");
		restAdd.add(cate);
		restAdd.add(cate);
		restAdd.add(resName);
		restAdd.add(address);
		restAdd.add(phone);
		restAdd.add(bookyn);
		restAdd.add(cate);
		RestaurantVo resAdd = resService.resAdd(restAdd);
		System.out.println(resAdd);
		printResAdd(resAdd);
		//대표메뉴, 가격 추가하기
		//메뉴추가, 등록요청
		return null;
	}

	//메뉴 카테고리로 검색
	public View resSearchCategory() {
		printCategory();
		int category = ScanUtil.nextInt("검색 하고싶은 카테고리의 숫자를 입력하세요: ");
		List<Map<String, Object>> cateList = resService.resSearchCategory(category);
		if(cateList==null) {
			System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
			return View.RES_SEARCH_CATEGORY;
		}
		printResList(cateList);
		return null;
	}

	public View resList() {
		//페이징
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
		sessionStorage.put("resSearchName", rsrn);
		return View.RES_LIST;
		
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
