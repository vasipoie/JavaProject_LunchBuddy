package controller;

import java.util.ArrayList;
import java.util.List;

import print.RestaurantPrint;
import service.RestaurantService;
import util.ScanUtil;
import util.View;
import vo.RestaurantVo;

public class RestaurantController extends RestaurantPrint{
	RestaurantService resService = RestaurantService.getInstance();
	
	public static void main(String[] args) {
		
	}
	
	void start() {
		View view = View.HOME;
		while (true) {
			switch (view) {
			case RES_SEARCH_SELECT:	//식당 검색 전 선택
				view = resSearchSelect();
				break;
			case RES_SEARCH_RESNAME://식당 이름으로 검색
				view = resSearchResName();
				break;
			case RES_SEARCH_CATEGORY://메뉴 카테고리로 검색
//				view = resList();
				break;
			case RES_LIST:		//식당 리스트
				view = resList();
				break;
			case RES_DETAIL:	//식당 상세보기
//				view = resDetail();
				break;
			case RES_ADD:		//식당 등록
//				view = resAdd();
				break;
//			case ADMIN_RES_MANAGE:
//				view = adminHome();
//				break;
			}
		}
	}

	

	public View resList() {
		//페이징
		List<RestaurantVo> resList = resService.resList();
		printResList(resList);
		return null;
	}
	
	//식당 이름으로 검색
	public View resSearchResName() {
		List<Object> param = new ArrayList<Object>();
		param.add(ScanUtil.nextLine("식당 이름을 검색하세요 : "));
		List<RestaurantVo> rsrn = resService.resSearchResName(param);
		if(rsrn == null) {
			System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
			return View.RES_SEARCH_RESNAME;
		}
		printResList(rsrn);
		return null;
	}

	//식당 검색 전 선택
	public View resSearchSelect() {
		printResSearchSelect();
		int select = ScanUtil.nextInt("메뉴를 선택하세요\s");
		switch(select) {
		case 1:
			return View.RES_SEARCH_RESNAME;
		case 2:
			return View.RES_SEARCH_CATEGORY;
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
