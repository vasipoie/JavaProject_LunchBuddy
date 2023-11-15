package dao;

import java.util.List;

import util.ConvertUtils;
import util.JDBCUtil;
import vo.ReviewVo;

public class ReviewDao {
	JDBCUtil jdbc = JDBCUtil.getInstance();
	private static ReviewDao singleTon = null;

	public ReviewDao() {
	};

	public static ReviewDao getInstance() {
		if (singleTon == null) {
			singleTon = new ReviewDao();
		}
		return singleTon;
	}

	public List<ReviewVo> recent_review() {
		String sql = "select (select res_name from restaurant where res_no = a.res_no) res_name, rev_star\r\n" + 
				"        , (select mem_nick from member where mem_no = a.mem_no) mem_nick\r\n" + 
				"        , rev_cont, rev_no, rev_postyn, to_char(rev_date,'yy.mm.dd hh24:mi') rev_date\r\n" + 
				"        , a.res_no, a.mem_no\r\n" + 
				"from review a\r\n" + 
				"order by rev_date desc";
		return ConvertUtils.convertToList(jdbc.selectList(sql), ReviewVo.class);
	}

	public List<ReviewVo> get_review_list_by_writer(String mem_no) {
		String sql = "select (select res_name from restaurant where res_no = a.res_no) res_name, rev_star\r\n" + 
				"        , (select mem_nick from member where mem_no = a.mem_no) mem_nick\r\n" + 
				"        , rev_cont, rev_no, rev_postyn, to_char(rev_date,'yy.mm.dd hh24:mi') rev_date\r\n" + 
				"        , a.res_no, a.mem_no\r\n" + 
				"from review a\r\n" + 
				"where mem_no = " + mem_no;
		return ConvertUtils.convertToList(jdbc.selectList(sql), ReviewVo.class);
	}
}
