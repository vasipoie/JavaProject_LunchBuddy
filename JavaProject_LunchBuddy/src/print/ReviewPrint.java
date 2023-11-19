package print;

import java.util.List;

import controller.Controller;
import vo.MenuReviewVo;
import vo.ReviewVo;

public class ReviewPrint extends Print {
	
	public void print_add_review(String res_name, int score, String review, boolean b) {
		printBar();
		System.out.println("\t\t\t\t리뷰 등록");
		printLn(5);
		System.out.println("\t\t[ "+ res_name+" ] "+stars(score));
		System.out.println();
		System.out.println("\t\t 후기 : "+review);
		if(b) printLn(6);
		else {
			printLn(3);
			System.out.println("\t\t\t    등록하시겠습니까?");
			System.out.println();
			System.out.println("\t\t\t1.등록하기        0.뒤로가기");
		}
		printBar();
	}

	public void printReviewHome() {
		printBar();
		System.out.println("1. 리뷰 등록");
		System.out.println("2. 리뷰 상세보기");
		System.out.println("3. 리뷰 리스트");
		printBar();
	}

	public void print_review_for_list(ReviewVo review) {
		System.out.println(
				"[" + review.getRes_name() + "] \t" + stars(review.getRev_star()) + " (" + review.getRev_star() + ")");
		System.out.println(review.getMem_nick() + ")  " + review.getRev_cont());
	}

	public String stars(int num) {
		String stars = "";
		for (int i = 0; i < 10; i++) {
			if (i < num)
				stars += "★";
			else
				stars += "☆";
		}
		return stars;
	}

	public void print_review_detail(ReviewVo review) {
		printBar();
		System.out.println("\t\t\t\t리뷰 상세 보기");
		System.out.println();
		System.out.println("\t\t[ " + review.getRes_name() + " ] " + stars(review.getRev_star()));
		System.out.println("\t\t작성자 : "+ review.getMem_nick() + "\t 작성 시간 : " + review.getRev_date());
		System.out.println();
		System.out.println("\t\t"+review.getRev_cont());
		printBar();
	}

	public void print_menuReview_for_reviewDetail(List<MenuReviewVo> menuReviewList) {
		for (int i = 0; i < 4; i++) {
			if (menuReviewList.size() <= i) {
				System.out.println();
			} else {
				MenuReviewVo menuReview = menuReviewList.get(i);
				System.out.print("\t\t(" + menuReview.getMenu_name() + ") ");
				System.out.print(menuReview.getMenu_price());
				System.out.println(" - " + menuReview.getMr_cont());
			}
		}
		if(menuReviewList.size()>4) System.out.println("\t\t....");
		else System.out.println();
	}
	
	public void print_select_for_reviewDetail() {
		printBar();
		System.out.println("\t\t1.식당 정보 보기   2.작성자 리뷰 더보기  3. 메뉴 리뷰 더보기");
		System.out.println("\t\t\t      9.홈       0.뒤로가기");
		printBar();
	}
	
	public void print_menu_review() {
		
	}

}
