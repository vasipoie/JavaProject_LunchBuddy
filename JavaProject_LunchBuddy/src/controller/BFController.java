package controller;

import service.BFService;
import util.View;
import vo.MemberVo;
import vo.RestaurantVo;

public class BFController {
	
	BFService bfService = new BFService();
	
	public View bfcontroller(View view) {
		while(true) {
			Controller.newPage(view);
			switch (view) {
			case BF_MAKE:
				view = bf_make();
				break;
			case RES_SEARCH_FOR_BF:
				view = res_search_for_bf();
				break;
			default:
				Controller.removeHistory();
				return view;
			}
			
		}
	}

	private View res_search_for_bf() {
		
		return null;
	}

	private View bf_make() {
		MemberVo login_member = (MemberVo) Controller.sessionStorage.get("log_in_member");
		if(login_member==null) return View.LOG_IN;
		
		RestaurantVo restaurant = Controller.sessionStorage.get(key)
		
		return null;
	}
	
	
	
	
}
