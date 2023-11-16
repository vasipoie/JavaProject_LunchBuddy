package controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import print.BFPrint;
import service.BFListService;
import service.BFService;
import service.RestaurantService;
import util.ConvertUtils;
import util.ScanUtil;
import util.View;
import vo.BFListVo;
import vo.BFVo;
import vo.MemberVo;
import vo.RestaurantVo;

public class BFController extends BFPrint{
	
	BFService bfService = new BFService();
	RestaurantService restaurantService = RestaurantService.getInstance();
	BFListService bfListService = new BFListService();
	
	public View bfcontroller(View view) {
		while(true) {
			Controller.newPage(view);
			switch (view) {
			case BF_MAIN:
				view = bf_main();
				break;
			case BF_MAKE:
				view = bf_make();
				break;
			case RES_SEARCH_FOR_BF:
				view = res_search_for_bf();
				break;
			case BF_DETAIL:
				view = bf_detail();
				break;
			default:
				Controller.removeHistory();
				return view;
			}
			
		}
	}

	private View bf_main() {
		print_bf_main();
		int select = ScanUtil.nextInt("선택 >>");
		switch (select) {
		case 1 : 
			Controller.init_page(5, 2, "상세보기", "selected_bf", View.BF_DETAIL);
			return new Controller().list_paging();
		case 2 : return View.BF_MAKE;
		case 9 : return View.HOME;
		case 0 : return Controller.goBack();
		default:
			Controller.removeHistory();
			return View.BF_MAIN;
		}
	}

	private View bf_detail() {
		BFVo bobF = (BFVo) Controller.sessionStorage.get("selected_bf");
		List<BFListVo> bfMemList = bfListService.getmems(bobF.getBf_no());
		printBF( bobF , bfMemList );
		MemberVo loginmem = (MemberVo) Controller.sessionStorage.get("log_in_member");
		if( loginmem!=null && loginmem.getMem_no()==bobF.getMem_no() ) {
			System.out.println("1. 삭제하기");
		} else if ( bfMemList.size() < (bobF.getBf_num()-1) ){
			System.out.println("1. 참석하기");
		}else System.out.println();
		System.out.println("9.홈으로 가기     0.뒤로가기");
		printBar();
		int select = ScanUtil.nextInt("메뉴 선택 >> ");
		switch (select) {
		case 1:
			if( loginmem!=null && loginmem.getMem_no()==bobF.getMem_no() ) {
				bfService.delete(bobF.getBf_no());
				print_delete_sucess();
				return View.HOME;
			}else {
				if(loginmem==null) return View.LOG_IN;
				else if( bfMemList.size() < (bobF.getBf_num()-1) ){
					if(bfListService.checkParti(loginmem.getMem_no(),bobF.getBf_no())) {
						bfListService.parti(loginmem.getMem_no(),bobF.getBf_no());
						return View.BF_DETAIL;
					}else {
						print_already_part();
						Controller.removeHistory();
						return View.BF_DETAIL;
					}
				}else {
					Controller.removeHistory();
					return View.BF_DETAIL;
				}
			}
		case 9 : return View.HOME;
		case 0 : return Controller.goBack();
		default:
			Controller.removeHistory();
			return View.BF_DETAIL;
		}
	}
	

	/**
	 * 밥친구 찾기용 식당 검색 메소드
	 * @return
	 */
	private View res_search_for_bf() {
		String res_name = ScanUtil.nextLine("식당 이름  검색: ");
		List<Object> param = new ArrayList();
		param.add(res_name);
		List<Map<String,Object>> l = restaurantService.resSearchResName(param);
		List<RestaurantVo> restaurantList = ConvertUtils.convertToList(l, RestaurantVo.class);
		Controller.init_page( 5, 2, "식당 선택하기", "selected_res_for_bf", View.BF_MAKE );
		Controller.sessionStorage.put("list_for_paging", restaurantList);
		return new Controller().list_paging();
	}

	private View bf_make() {
		//로그인 되어있는지 확인
		//안되어있으면 로그인
		MemberVo login_member = (MemberVo) Controller.sessionStorage.get("log_in_member");
		if(login_member==null) return View.LOG_IN;
		
		// 식당 선택 했는지 확인
		// 안되어있으면 검색
		RestaurantVo restaurant = (RestaurantVo) Controller.sessionStorage.get("selected_res_for_bf");
		if(restaurant==null) return View.RES_SEARCH_FOR_BF;
		
		String bfTitle = ScanUtil.nextLine("모임 이름 : ");
		String bfCont = ScanUtil.nextLine("모임 내용 : ");
		int bfNum = ScanUtil.nextInt("인원수 : ");
		System.out.println("모임 날짜를 선택하세요. 일주일 뒤의 날짜까지 선택이 가능합니다.");
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		DateFormat df = new SimpleDateFormat("yyMMdd");
		printDate(cal);
		if(cal.HOUR_OF_DAY<13) cal.add(Calendar.DATE, -1);
		
		int select = ScanUtil.nextInt("선택 >>");
		cal.add(Calendar.DATE, select);
		if(cal.get(Calendar.DAY_OF_WEEK)==1) cal.add(Calendar.DATE, 1);		
		String bfDate = df.format(cal.getTime());

		//param = date, date, mem_no, title, cont, num, date, res_no
		List<Object> param = new ArrayList<Object>();
		param.add(bfDate);
		param.add(bfDate);
		param.add(login_member.getMem_no());
		param.add(bfTitle);
		param.add(bfCont);
		param.add(bfNum);
		param.add(bfDate);
		param.add(restaurant.getRes_no());
		
		bfService.bf_make(param);
		System.out.println("등록 되었습니다!");
		
		BFVo bf = bfService.getBF_just_wrote();
		Controller.sessionStorage.put("selected_bf", bf);
		return View.BF_DETAIL;
	}
	
	
	
	
}
