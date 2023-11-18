package service;

import java.util.List;

import dao.BFDao;
import vo.BFVo;

public class BFService {
	
	BFDao dao = new BFDao();
	private static BFService singleTon = null;

	public BFService() {
	};

	public static BFService getInstance() {
		if (singleTon == null) {
			singleTon = new BFService();
		}
		return singleTon;
	}

	public BFVo bf_make(List<Object> param) {
		return dao.bf_make(param);
	}

	public BFVo getBF_just_wrote() {
		return dao.getBF_just_wrote();
	}

	public void delete(String bf_no) {
		dao.delete(bf_no);
	}

	public List<BFVo> get_bfList() {
		return dao.get_bfList();
	}


}
