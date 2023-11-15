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
			default:
				Controller.removeHistory();
				return view;
			}
		}
	}

	private View review_detail() {
		ReviewVo review = (ReviewVo) Controller.sessionStorage.get("selected_review");
		print_review_detail(review);
		List<MenuReviewVo> menuReviewList = menuReviewService.getMenuReview(review.getRes_no(),review.getMem_no());
		print_menuReview_for_reviewDetail(menuReviewList);
		print_select_for_reviewDetail();
		int select = ScanUtil.nextInt("선택 >> ");
		
		return null;
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
		ReviewVo review = view_review_list(review_recent);
		Controller.sessionStorage.put("selected_review", review);
		return View.REVIEW_DETAIL;
	}
	
	private ReviewVo view_review_list(List<ReviewVo> reviewList) {
		int page = 1;
		int lastNo = reviewList.size();
		while(true) {
			System.out.println(page + "페이지");
			printBar();
			int topNo = (page-1)*5;
			int bottomNo = page*5;
			int no = topNo+1;
			for(int i = topNo; i<bottomNo; i++) {
				if(i<lastNo) {
					ReviewVo review = reviewList.get(i);
					System.out.print(no + ".");
					print_review_for_list(review);
					no++;
				}else {
					System.out.println();
					System.out.println();
				}
			}
			printBar();
			if(page == 1) {
				System.out.println("         2. 리뷰 선택  3. 다음 페이지 \n9. 홈      0. 뒤로가기");
				printBar();
				int select = ScanUtil.nextInt(" 선택 >> ");
				switch (select) {
				case 2:
					int selected_no = ScanUtil.nextInt(" 번호 >> ")-1;
					ReviewVo selected_review = reviewList.get(selected_no);
					return selected_review;
				case 3 :
					page++;
					break;
				case 9 : 
					reviewController(View.HOME);
				case 0 :
					reviewController(Controller.goBack());
				default:
					break;
				}
			}else if( (page*5) >= lastNo) {
				System.out.println("1. 이전 페이지    2. 리뷰 선택 \n9. 홈      0. 뒤로가기");
				printBar();
				int select = ScanUtil.nextInt(" 선택 >> ");
				switch (select) {
				case 1 :
					page--;
					break;
				case 2:
					int selected_no = ScanUtil.nextInt(" 번호 >> ")-1;
					ReviewVo selected_review = reviewList.get(selected_no);
					return selected_review;
				case 9 : 
					reviewController(View.HOME);
				case 0 :
					reviewController(Controller.goBack());

				default:
					break;
				}
				
			}else {
				System.out.println("1. 이전 페이지    2. 리뷰 선택   3. 다음 페이지");
				System.out.println("9. 홈               0. 뒤로가기");
				printBar();
				int select = ScanUtil.nextInt(" 선택 >> ");
				switch (select) {
				case 1:
					page--;
					break;
				case 2:
					int selected_no = ScanUtil.nextInt(" 번호 >> ")-1;
					ReviewVo selected_review = reviewList.get(selected_no);
					return selected_review;
				case 3 :
					page++;
					break;
				case 9 : 
					reviewController(View.HOME);
				case 0 :
					reviewController(Controller.goBack());
				default:
					break;
				}
			}
		}
	}
	
	
	
}
