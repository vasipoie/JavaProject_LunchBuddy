package dao;

import java.util.List;

import util.ConvertUtils;
import util.JDBCUtil;
import vo.MenuReviewVo;

public class MenuReviewDao {
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	String sql_ = "select (select menu_name from menu where menu_no  = a.menu_no) menu_name\r\n" + 
			"            , (select menu_price from menu where menu_no  = a.menu_no) menu_price\r\n" + 
			"            , a.*\r\n" + 
			"from menureview a ";
	
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
		String sql = sql_ + 
				"where (select res_no from menu where menu_no = a.menu_no) = " + res_no + 
				"    and a.mem_no = " + mem_no;
		return ConvertUtils.convertToList(jdbc.selectList(sql), MenuReviewVo.class);
	}

	public List<MenuReviewVo> see_menu_review_by_menu(String menu_no) {
		String sql = sql_ + "where menu_no = '"+menu_no+"'";
		return ConvertUtils.convertToList(jdbc.selectList(sql), MenuReviewVo.class);
	}

	public void add_menureview(String menu_no, String cont, String mem_no) {
		String sql = "insert into menureview (mr_no, mr_cont, mem_no, menu_no)\r\n" + 
				"values ('"+menu_no+"'||'MR'||'"+mem_no+"'||to_char(sysdate,'yymmdd')||\r\n" + 
				"                    lpad(\r\n" + 
				"                        (select nvl(max(no),0)+1\r\n" + 
				"                            from(select substr(mr_no,23) no from menureview \r\n" + 
				"                                    where mr_no like '"+menu_no+"'||'MR'||'"+mem_no+"'||to_char(sysdate,'yymmdd')||'%')),3,'0')\r\n" + 
				"            , '"+cont+"','"+mem_no+"','"+menu_no+"')";
		jdbc.update(sql);
	}
	
	
	
}
