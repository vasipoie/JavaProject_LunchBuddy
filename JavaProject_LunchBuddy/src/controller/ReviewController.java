package controller;

import java.util.ArrayList;
import java.util.List;

import print.ReviewPrint;
import service.MenuReviewService;
import service.ReviewService;
import util.ScanUtil;
import util.View;
import vo.MenuReviewVo;
import vo.ReviewVo;

public class ReviewController extends ReviewPrint{
	
	ReviewService reviewService = new ReviewService();
	MenuReviewService menuReviewService = new MenuReviewService();
	
	public View reviewController(View view) {
		while(true) {
			Controller.newPage(view);
			switch (view) {
			case RECENT_REVIEW:
				view = recent_review();
				break;
			case REVIEW_DETAIL:
				view = review_detail();
				break;
			case REVIEW_LIST:
//				view = review_list();
				break;
			case SEE_MENU_REVIEW_BY_MENU:
				see_menu_review_by_menu();
				break;
			case SEE_MENU_REVIEW_BY_RES_N_WRITER:
				see_menu_review_by_res_n_writer();
				break;
			default:
				Controller.removeHistory();
				return view;
			}
		}
	}

	private void see_menu_review_by_menu() {
		Controller.removeHistory();
		MenuReviewVo menuReview = (MenuReviewVo) Controller.sessionStorage.get("selected_object");
		System.out.println("여기!");
		List<MenuReviewVo> menuReviewList = menuReviewService.see_menu_review_by_menu(menuReview.getMenu_no());
		Controller.init_page(10, 1, "식당 보기", View.RES_DETAIL);
		Controller.sessionStorage.replace("list_for_paging", menuReviewList);
		new Controller().list_paging();
	}

	private void see_menu_review_by_res_n_writer() {
		Controller.removeHistory();
		List<MenuReviewVo> menuReviewList = (List<MenuReviewVo>) Controller.sessionStorage.get("menu_review");
		Controller.init_page(10, 1, "식당 보기", View.RES_DETAIL);
		Controller.sessionStorage.put("list_for_paging", menuReviewList);
		new Controller().list_paging();
	}

	private void see_menu_review() {
		print_menu_review();
	}

	private View review_detail() {
		ReviewVo review = (ReviewVo) Controller.sessionStorage.get("selected_object");
		print_review_detail(review);
		List<MenuReviewVo> menuReviewList = menuReviewService.getMenuReview(review.getRes_no(),review.getMem_no());
		print_menuReview_for_reviewDetail(menuReviewList);
		print_select_for_reviewDetail();
		int select = ScanUtil.nextInt("선택 >> ");
		switch (select) {
		case 1 :
			Controller.sessionStorage.put("selected_res_no", review.getRes_no());
			return View.RES_DETAIL;
		case 2 :
			List<ReviewVo> reviewList = reviewService.get_review_list_by_writer(review.getMem_no());
			Controller.sessionStorage.put("selected_review_list", reviewList);
			return View.REVIEW_LIST;
		case 3 : 
			Controller.sessionStorage.put("menu_review", menuReviewList);
			return View.SEE_MENU_REVIEW_BY_RES_N_WRITER;
		case 9 : return View.HOME;
		case 0 : return Controller.goBack();
		default:
			Controller.removeHistory();
			return View.REVIEW_DETAIL;
		}
	}

	//리뷰 등록
	private View addReview() {
		//로그인 확인
//		MemberVo mb = (MemberVo) sessionStorage.get("member");
//		if(mb == null) {
			System.out.println("로그인 후 이용할 수 있습니다");
//			sessionStorage.put("view", View.MEMBER_MYBOARD_UPDATE);
//			return View.MEMBER_LOGIN;
//		}
		
		List<Object> addRev = new ArrayList<Object>();
		addRev.add(ScanUtil.nextInt("평점 : "));
		addRev.add(ScanUtil.nextLine("내용 : "));
		
		return null;
	}

	//리뷰 홈
	private View review() {
		printReviewHome();
		int select = ScanUtil.nextInt("메뉴를 선택하세요\s");
		switch (select) {
		case 1:
			return View.ADD_REVIEW;
		case 2:
			return View.REVIEW_DETAIL;
		case 3:
			return View.REVIEW_LIST;
		default:
			return null;
		}
	}

	/**
	 * 최근 리뷰 보기
	 * @return
	 */
	public View recent_review() {
		List<ReviewVo> review_recent = reviewService.recent_review();
		Controller.init_page(5, 2, "리뷰 상세 보기", View.REVIEW_DETAIL);
		Controller.sessionStorage.put("list_for_paging", review_recent);
		return new Controller().list_paging();
	}
	
	
	
	
}
