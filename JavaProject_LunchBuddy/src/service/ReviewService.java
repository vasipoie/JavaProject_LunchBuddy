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

	public List<ReviewVo> get_review_list_by_writer(String mem_no) {
		return dao.get_review_list_by_writer(mem_no);
	}

	public List<ReviewVo> review_by_writer(String mem_no) {
		return dao.review_by_writer(mem_no);
	}

	public void add_review(List<Object> param) {
		dao.add_review(param);
		
	}

	public ReviewVo review_just_wrote() {
		return dao.review_just_wrote();
	}

	public List<ReviewVo> review_by_res(String res_no) {
		return dao.review_by_res(res_no);
	}
}
