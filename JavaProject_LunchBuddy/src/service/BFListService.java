package service;

import java.util.List;
import java.util.Map;

import dao.BFListDao;
import vo.BFListVo;

public class BFListService {
	
	BFListDao dao = new BFListDao();
	
	private static BFListService singleTon = null;

	public BFListService() {
	};

	public static BFListService getInstance() {
		if (singleTon == null) {
			singleTon = new BFListService();
		}
		return singleTon;
	}

	public List<BFListVo> getmems(String bf_no) {
		return dao.getmems(bf_no);
	}

	public void parti(String mem_no, String bf_no) {
		dao.parti(mem_no, bf_no);
	}

	public boolean checkParti(String mem_no, String bf_no) {
		Map<String, Object> bfl = dao.checkParti(mem_no, bf_no);
		if(bfl == null) return true;
		else return false;
	}

}
