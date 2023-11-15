package controller;

import java.util.ArrayList;
import java.util.List;

import print.ReviewPrint;
import util.ScanUtil;
import util.View;

public class ReviewController extends ReviewPrint{

	public static void main(String[] args) {
		
	}
	
	void start() {
		View view = View.HOME;
		while (true) {
			switch (view) {
			case REVIEW:
				view = review();
				break;
			case ADD_REVIEW:
				view = addReview();
				break;
			case REVIEW_DETAIL:
//				view = reviewDetail();
				break;
			case REVIEW_LIST:
//				view = reviewList();
				break;
			
			}
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
}
