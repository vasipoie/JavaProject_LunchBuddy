package service;

import dao.BFDao;

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

}
