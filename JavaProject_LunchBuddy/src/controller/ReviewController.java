package controller;

import java.util.ArrayList;
import java.util.List;

import org.omg.PortableInterceptor.SUCCESSFUL;

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
			case SEE_MENU_REVIEW:
				view = see_menu_review();
				break;
			default:
				Controller.removeHistory();
				return view;
			}
		}
	}

	private View see_menu_review() {
		
		return null;
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
			return View.SEE_MENU_REVIEW;
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
//		Controller.sessionStorage.put("selected_review_list", review_recent);
		Controller.sessionStorage.put("list_for_paging", review_recent);
		Controller.sessionStorage.put("type_for_paging", "리뷰");
		Controller.sessionStorage.put("pageSize_for_paging", 5);
		Controller.sessionStorage.put("object_size_for_paging", 2);
		Controller.sessionStorage.put("pageno", 1);
		Controller.sessionStorage.put("after_page", View.REVIEW_DETAIL);
//		Controller.sessionStorage.put("selected_review", review);
		return new Controller().list_paging();
	}
	
	
	
	
}
