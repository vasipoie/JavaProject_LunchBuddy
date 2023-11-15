package service;

import java.util.List;

import dao.ReviewDao;
import vo.ReviewVo;

public class ReviewService {
	
	ReviewDao dao = new ReviewDao();
	private static ReviewService singleTon = null;

	public ReviewService() {
	};

	public static ReviewService getInstance() {
		if (singleTon == null) {
			singleTon = new ReviewService();
		}
		return singleTon;
	}

	public List<ReviewVo> recent_review() {
		// TODO Auto-generated method stub
		return dao.recent_review();
	}
}
