package controller;

import java.util.ArrayList;
import java.util.List;

import print.ReviewPrint;
import service.MenuReviewService;
import service.RestaurantService;
import service.ReviewService;
import util.ScanUtil;
import util.View;
import vo.MemberVo;
import vo.MenuReviewVo;
import vo.MenuVo;
import vo.RestaurantVo;
import vo.ReviewVo;

public class ReviewController extends ReviewPrint{
	
	ReviewService reviewService = new ReviewService();
	MenuReviewService menuReviewService = new MenuReviewService();
	RestaurantService restaurantService = new RestaurantService();
	
	public View reviewController(View view) {
		while(true) {
			System.out.println("리뷰 컨트롤러, view = " + view);
			Controller.newPage(view);
			switch (view) {
			case RECENT_REVIEW:
				view = recent_review();
				break;
			case REVIEW_DETAIL:
				view = review_detail();
				break;
			case SEE_MENU_REVIEW_BY_MENUREVIEW:
				view = see_menu_review_by_menuReview();
				break;
			case SEE_MENU_REVIEW_BY_RES_N_WRITER:
				view = see_menu_review_by_res_n_writer();
				break;
			case SEE_REVIEW_BY_WRITER:
				view = see_review_by_writer();
				break;
			case SEE_REVIEW_BY_RES:
				view = see_review_by_res();
				break;
			case ADD_REVIEW:
				view = add_review();
				break;
			case SEE_MENU_REVIEW_BY_MENU:
				view = see_menu_review_by_menu();
				break;
			case RES_BY_MENUREVIEW:
				view = res_by_menureview();
				break;
			default:
				Controller.removeHistory();
				System.out.println("review controller out");
				return view;
			}
		}
	}

	/**
	 * 메뉴리뷰 -> 식당 상세 보내주는 메소드
	 * @return
	 */
	private View res_by_menureview() {
		MenuReviewVo menuReview = (MenuReviewVo) Controller.sessionStorage.get("selected_menureview_for_res");
		String res_no = menuReview.getMenu_no().substring(0, 5);
		RestaurantVo res = restaurantService.getRes_by_resNo(res_no);
		Controller.sessionStorage.put("resDetailResName", res);
		Controller.removeHistory();
		return View.RES_DETAIL;
	}


	private View see_menu_review_by_menu() {
		MenuVo menu = (MenuVo) Controller.sessionStorage.get("selected_menu");
		List<MenuReviewVo> menuReviewList = menuReviewService.see_menu_review_by_menu(menu.getMenu_no());
		
		Controller.sessionStorage.put( "list_for_paging", menuReviewList);
		Controller.init_page(10, 1, "식당 상세보기", "selected_menureview_for_res", View.RES_BY_MENUREVIEW);
		Controller.removeHistory();
		return View.LIST_PAGING;
	}

	private View see_review_by_res() {
		String res_no = (String) Controller.sessionStorage.get("selected_rest_no");
		List<ReviewVo> reviewList = reviewService.review_by_res(res_no);
		Controller.init_page(5, 2, "리뷰 상세 보기", "selected_review" , View.REVIEW_DETAIL);
		Controller.sessionStorage.put("list_for_paging", reviewList);
		return new Controller().list_paging();
	}

	/**
	 * 리뷰 추가
	 * @return
	 */
	private View add_review() {
		MemberVo login_member = (MemberVo) Controller.sessionStorage.get("log_in_member");
		if(login_member==null) return View.LOG_IN;
		
		//테스트용
		RestaurantVo restaurant = new RestaurantService().resAddOnePrint("01");
		//
//		RestaurantVo restaurant = (RestaurantVo) Controller.sessionStorage.get("selected_restaurant");
		if(restaurant==null) return View.RES_SEARCH_RESNAME;
		
		String res_name = restaurant.getRes_name();
		int score = 0;
		String review_cont = "";
		
		print_add_review(res_name, score, review_cont,true);
		score = ScanUtil.nextInt("평점 (1~10) >> ");
		print_add_review(res_name, score, review_cont,true);
		review_cont = ScanUtil.nextLine("후기를 남겨주세요 : ");
		print_add_review(res_name, score, review_cont,false);
		switch (ScanUtil.nextInt("선택 >>")) {
		case 0:
			return Controller.goBack();
		default:
			break;
		}
		
		//param = res_no, mem_no, res_no, mem_no, rev_star
		//		, rev_cont, res_no, mem_no
		List<Object> param = new ArrayList();
		param.add(restaurant.getRes_no());
		param.add(login_member.getMem_no());
		param.add(restaurant.getRes_no());
		param.add(login_member.getMem_no());
		param.add(score);
		param.add(review_cont);
		param.add(restaurant.getRes_no());
		param.add(login_member.getMem_no());
		reviewService.add_review(param);
		
		ReviewVo review = reviewService.review_just_wrote();
		Controller.sessionStorage.put("selected_review", review);
		return View.REVIEW_DETAIL;
	}

	/**
	 * 작성자 리뷰 모아보기
	 * >>리뷰 상세보기
	 * @return
	 */
	private View see_review_by_writer() {
		ReviewVo review = (ReviewVo) Controller.sessionStorage.get("selected_review");
		List<ReviewVo> reviewList = reviewService.review_by_writer(review.getMem_no());
		Controller.init_page(5, 2, "리뷰 상세 보기", "selected_review" , View.REVIEW_DETAIL);
		Controller.sessionStorage.put("list_for_paging", reviewList);
		return new Controller().list_paging();
	}

	/**
	 * 특정 메뉴의 메뉴 리뷰 모아보기
	 * >>식당 보기
	 * @return
	 */
	private View see_menu_review_by_menuReview() {
		MenuReviewVo menuReview = (MenuReviewVo) Controller.sessionStorage.get("selected_menuReview");
		List<MenuReviewVo> menuReviewList = menuReviewService.see_menu_review_by_menu(menuReview.getMenu_no());
		Controller.init_page(10, 1, "식당 보기", "selected_menuReview" , View.RES_DETAIL);
		Controller.sessionStorage.put("list_for_paging", menuReviewList);
		return new Controller().list_paging();
	}

	/**
	 * 리뷰 페이지에서 메뉴 리뷰 더 보기 누르면 해당 식당 해당 작성자의 메뉴 리뷰 출력
	 * >> 이 메뉴 리뷰 더 보기 (해당 메뉴 리뷰 모아보기)
	 * @return
	 */
	private View see_menu_review_by_res_n_writer() {
		List<MenuReviewVo> menuReviewList = (List<MenuReviewVo>) Controller.sessionStorage.get("menu_review");
		Controller.init_page(10, 1, "이 메뉴 리뷰 더보기", "selected_menuReview" , View.SEE_MENU_REVIEW_BY_MENU);
		Controller.sessionStorage.put("list_for_paging", menuReviewList);
		return new Controller().list_paging();
	}


	/**
	 * 리뷰 상세보기
	 * sessionStorage "selected_object" 가져와서 보여줌
	 * >>식당 정보보기 >>작성자 리뷰 더보기 >> 메뉴 리뷰 더보기
	 * @return
	 */
	private View review_detail() {
		//테스트용
//		Controller.sessionStorage.put("selected_review", reviewService.review_just_wrote());
		//
		ReviewVo review = (ReviewVo) Controller.sessionStorage.get("selected_review");
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
			Controller.sessionStorage.put("selected_review", review);
			return View.SEE_REVIEW_BY_WRITER;
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


	/**
	 * 최근 리뷰 보기
	 * @return
	 */
	public View recent_review() {
		Controller.removeHistory();
		List<ReviewVo> review_recent = reviewService.recent_review();
		Controller.init_page(5, 2, "리뷰 상세 보기", "selected_review" , View.REVIEW_DETAIL);
		Controller.sessionStorage.put("list_for_paging", review_recent);
		return View.LIST_PAGING;
	}
	
	
	
	
}
