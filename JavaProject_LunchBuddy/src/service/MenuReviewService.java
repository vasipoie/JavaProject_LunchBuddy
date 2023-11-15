package service;

import java.util.List;

import dao.MenuReviewDao;
import vo.MenuReviewVo;

public class MenuReviewService {
	MenuReviewDao dao = new MenuReviewDao();
	
	private static MenuReviewService singleTon = null;

	public MenuReviewService() {
	};

	public static MenuReviewService getInstance() {
		if (singleTon == null) {
			singleTon = new MenuReviewService();
		}
		return singleTon;
	}

	public List<MenuReviewVo> getMenuReview(String res_no, String mem_no) {
		return dao.getMenuReview(res_no,mem_no);
	}
	
	
}