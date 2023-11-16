package dao;

import util.JDBCUtil;

public class BFDao {
	
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	private static BFDao singleTon = null;

	public BFDao() {
	};

	public static BFDao getInstance() {
		if (singleTon == null) {
			singleTon = new BFDao();
		}
		return singleTon;
	}
	
	

}
