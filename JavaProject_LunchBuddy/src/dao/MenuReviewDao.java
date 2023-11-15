package dao;

import java.util.List;

import util.ConvertUtils;
import util.JDBCUtil;
import vo.MenuReviewVo;

public class MenuReviewDao {
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	private static MenuReviewDao singleTon = null;

	public MenuReviewDao() {
	};

	public static MenuReviewDao getInstance() {
		if (singleTon == null) {
			singleTon = new MenuReviewDao();
		}
		return singleTon;
	}

	public List<MenuReviewVo> getMenuReview(String res_no, String mem_no) {
		String sql = "select (select menu_name from menu where menu_no  = a.menu_no) menu_name\r\n" + 
				"            , (select menu_price from menu where menu_no  = a.menu_no) menu_price\r\n" + 
				"            , a.mr_cont, a.mr_no\r\n" + 
				"from menureview a\r\n" + 
				"where (select res_no from menu where menu_no = a.menu_no) = " + res_no + 
				"    and a.mem_no = " + mem_no;
		return ConvertUtils.convertToList(jdbc.selectList(sql), MenuReviewVo.class);
	}
	
	
	
}
