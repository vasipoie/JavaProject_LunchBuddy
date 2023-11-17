package dao;

import java.util.List;

import util.ConvertUtils;
import util.JDBCUtil;
import vo.BFVo;

public class BFDao {
	
	String sql_ = "select bf_no, mem_no, bf_name, bf_cont, bf_num, to_char(bf_date,'yy.mm.dd') bf_date, res_no, bf_delyn\r\n" + 
			"        , (select mem_nick from member where mem_no = b.mem_no) mem_nick\r\n" + 
			"        , (select res_name from restaurant where res_no = b.res_no) res_name\r\n" + 
			"        , (select count(*) from bflist where  bf_no = b.bf_no) part_num\r\n" + 
			"from bobfriend b\r\n";
	
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

	public void bf_make(List<Object> param) {
		System.out.println(param);
		String sql = "INSERT INTO BOBFRIEND ( BF_NO, MEM_NO, BF_NAME, BF_CONT, BF_NUM, BF_DATE, RES_NO)\r\n" + 
				"VALUES\r\n" + 
				"(\r\n" + 
				"    ? || LPAD(( SELECT NVL(MAX(NO),0)+1\r\n" + 
				"                    FROM ( SELECT SUBSTR(BF_NO,7) NO FROM BOBFRIEND WHERE BF_NO LIKE ? ||'%')\r\n" + 
				"                    ),4,'0')\r\n" + 
				"    , ? , ? , ? , ? , TO_DATE(?,'YYMMDDHH24') , ?\r\n" + 
				")";
		jdbc.update(sql, param);
	}

	public BFVo getBF_just_wrote() {
		String sql = "select bf_no, mem_no, bf_name, bf_cont, bf_num, to_char(bf_date,'yy.mm.dd') bf_date, res_no, bf_delyn\r\n" + 
				"        , (select mem_nick from member where mem_no = b.mem_no) mem_nick\r\n" + 
				"        , (select res_name from restaurant where res_no = b.res_no) res_name\r\n" + 
				"        , (select count(*) from bflist where  bf_no = b.bf_no) part_num\r\n" + 
				"from (select * from bobfriend order by bf_no desc) b where rownum=1";
		return ConvertUtils.convertToVo(jdbc.selectOne(sql), BFVo.class);
	}

	public void delete(String bf_no) {
		String sql = "update bobfriend\r\n" + 
				"set bf_delyn = 'Y'\r\n" + 
				"where bf_no =" + bf_no;
		jdbc.update(sql);
	}

	public List<BFVo> get_bfList() {
		String sql = sql_ + "where bf_date > sysdate order by bf_no";
		
		return ConvertUtils.convertToList(jdbc.selectList(sql), BFVo.class);
	}
	
	

}
