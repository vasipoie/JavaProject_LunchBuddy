package dao;

import java.util.List;
import java.util.Map;

import util.ConvertUtils;
import util.JDBCUtil;
import vo.BFListVo;

public class BFListDao {
	
	String sql_ = "select bflist_no, mem_no, bf_no\r\n" + 
			"        ,(select mem_nick from member where mem_no = a.mem_no) mem_nick\r\n" + 
			"from bflist a  ";
	
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	private static BFListDao singleTon = null;

	public BFListDao() {
	};

	public static BFListDao getInstance() {
		if (singleTon == null) {
			singleTon = new BFListDao();
		}
		return singleTon;
	}

	public List<BFListVo> getmems(String bf_no) {
		String sql = sql_ + "where bf_no = " + bf_no;
		return ConvertUtils.convertToList(jdbc.selectList(sql), BFListVo.class);
	}

	public void parti(String mem_no, String bf_no) {
		String sql = "insert into bflist (bflist_no, mem_no, bf_no)\r\n" + 
				"values\r\n" + 
				"(\r\n" + 
				"    '" + bf_no + "' || lpad(    (select nvl( max(no),0 )+1\r\n" + 
				"                                    from (select substr(bflist_no,11) no from bflist where bflist_no like '" + bf_no + "'||'%'\r\n" + 
				"                                            )\r\n" + 
				"                            )\r\n" + 
				"                    ,2,'0')\r\n" + 
				"    , '" + mem_no + "','" + bf_no + "'\r\n" + 
				")";
		jdbc.update(sql);
	}

	public Map<String, Object> checkParti(String mem_no, String bf_no) {
		String sql = "select * from bflist\r\n" + 
				"where mem_no = " + mem_no +
				" and bf_no = " + bf_no;
		return jdbc.selectOne(sql);
	}

}