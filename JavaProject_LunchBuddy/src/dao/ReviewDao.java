package dao;

import java.util.List;

import util.ConvertUtils;
import util.JDBCUtil;
import vo.ReviewVo;

public class ReviewDao {
	
	String sql_ = "select (select res_name from restaurant where res_no = a.res_no) res_name, rev_star\r\n" + 
			"        , (select mem_nick from member where mem_no = a.mem_no) mem_nick\r\n" + 
			"        , rev_cont, rev_no, rev_postyn, to_char(rev_date,'yy.mm.dd hh24:mi') rev_date\r\n" + 
			"        , a.res_no, a.mem_no\r\n" + 
			"from review a\r\n";
	
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
		String sql = sql_ + "order by rev_date desc";
		return ConvertUtils.convertToList(jdbc.selectList(sql), ReviewVo.class);
	}

	public List<ReviewVo> get_review_list_by_writer(String mem_no) {
		String sql = sql_ +  "where mem_no = " + mem_no;
		return ConvertUtils.convertToList(jdbc.selectList(sql), ReviewVo.class);
	}

	public List<ReviewVo> review_by_writer(String mem_no) {
		String sql = sql_ +  "where mem_no = " + mem_no;
		return ConvertUtils.convertToList(jdbc.selectList(sql), ReviewVo.class);
	}

	public void add_review(List<Object> param) {
		String sql = "insert into review (rev_no, rev_star, rev_cont, res_no, mem_no)\r\n" + 
				"values( ? || 'R' || ? || to_char(sysdate,'yymmdd') ||\r\n" + 
				"lpad(\r\n" + 
				"(select nvl(max(no),0)+1 from(\r\n" + 
				"select substr(rev_no,18) no from review where rev_no like ? || 'R' || ?\r\n" + 
				"||to_char(sysdate,'yymmdd')||'%')\r\n" + 
				"),3,'0')\r\n" + 
				", ? , ? , ? , ? )";
		jdbc.update(sql, param);
		
	}

	public ReviewVo review_just_wrote() {
		String sql = "select (select res_name from restaurant where res_no = a.res_no) res_name, rev_star\r\n" + 
				"        , (select mem_nick from member where mem_no = a.mem_no) mem_nick\r\n" + 
				"        , rev_cont, rev_no, rev_postyn, to_char(rev_date,'yy.mm.dd hh24:mi') rev_date\r\n" + 
				"        , a.res_no, a.mem_no\r\n" + 
				"from (select* from review order by rev_date desc) a\r\n" + 
				"where rownum =1";
		return ConvertUtils.convertToVo(jdbc.selectOne(sql), ReviewVo.class);
	}

	public List<ReviewVo> review_by_res(String res_no) {
		String sql = sql_+"where res_no = " + res_no;
		return ConvertUtils.convertToList(jdbc.selectList(sql), ReviewVo.class);
	}
}
