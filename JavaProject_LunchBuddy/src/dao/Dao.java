package dao;

import java.util.List;
import java.util.Map;

import util.ConvertUtils;
import util.JDBCUtil;

public class Dao {
	private static Dao singleTon = null;
	
	private Dao(){};
	
	public static Dao getInstance() {
		if(singleTon == null) {
			singleTon = new Dao();
		}
		return singleTon;
	}

	JDBCUtil jdbc = JDBCUtil.getInstance();

	public List<Map<String, Object>> getMenuList(String res_no) {
		String sql = "select * from menu where res_no =" + res_no ;
		return jdbc.selectList(sql);
	}
	
}