package dao;

import java.util.List;
import java.util.Map;

import util.ConvertUtils;
import util.JDBCUtil;
import vo.AdminVo;
import vo.RestaurantVo;
import vo.ReviewVo;

public class AdminDao {
	private static AdminDao singleTon = null;
	
	private AdminDao(){};
	
	public static AdminDao getInstance() {
		if(singleTon == null) {
			singleTon = new AdminDao();
		}
		return singleTon;
	}

	JDBCUtil jdbc = JDBCUtil.getInstance();

	//관리자 로그인
	public AdminVo adminLogin(List<Object> param) {
		String sql = " select * from admin where adm_id =? and adm_pw =? ";
		Map<String, Object> map = jdbc.selectOne(sql, param);
		if(map == null) return null;
		AdminVo ad = ConvertUtils.convertToVo(map, AdminVo.class);
		return ad;
	}
	
	//관리자 등록된 식당 리스트
	public List<RestaurantVo> adminRegiResList() {
		String sql = "select distinct res.*, m.*\r\n"
					+ "from restaurant res\r\n"
					+ "    ,menu m\r\n"
					+ "where res.res_no = m.res_no(+)\r\n"
					+ "and res.res_postyn ='Y'\r\n"
					+ "and m.menu_no like '%001'\r\n"
					+ "order by res.res_date desc";
		return ConvertUtils.convertToList(jdbc.selectList(sql), RestaurantVo.class);
	}
	
	//등록된 식당 삭제
	public void adminResDelete(String res_no) {
		String sql = "update restaurant\r\n"
					+ "set RES_POSTYN = 'N'\r\n"
					+ ",column2 = '삭제'\r\n"
					+ "where RES_NO = "+res_no;
		jdbc.update(sql);
	}

	//관리자 미등록 식당 리스트
	public List<RestaurantVo> adminStandbyResList() {
		String sql = "select distinct res.*, m.*\r\n" + 
					 "from restaurant res\r\n" + 
					 "    ,menu m\r\n" + 
					 "where res.res_no = m.res_no(+)\r\n" + 
					 "and res.res_postyn ='N'\r\n" +
					 "and column2 is null\r\n"+
					 "order by res.res_date desc";
		return ConvertUtils.convertToList(jdbc.selectList(sql), RestaurantVo.class);
	}

	//미등록 식당 수정 update,select
	public void adminUpdateResName(String newResName, String res_no) {
		String sql = "UPDATE restaurant \r\n"
					+ "SET res_name = '"+newResName+"'\r\n"
					+ "where res_no = "+res_no;
		jdbc.update(sql);
	}
	 //미등록식당 수정 후 select 전체통일
	public RestaurantVo adminSelectModifyDetail(String res_no) {
		String sql = "SELECT RES.*, M.*\r\n"
					+ "FROM RESTAURANT RES, MENU M\r\n"
					+ "WHERE RES.RES_NO = M.RES_NO\r\n"
					+ "AND RES.RES_NO = "+res_no;
		Map<String, Object> map = jdbc.selectOne(sql);
		return ConvertUtils.convertToVo(map, RestaurantVo.class);
	}

	public void adminUpdateWalk(int newWalk, String res_no) {
		String sql = "UPDATE restaurant \r\n"
				   + "SET res_walk = '"+newWalk+"'\r\n"
				   + "where res_no = "+res_no;
		jdbc.update(sql);
	}
	public void adminUpdateBook(String newBookyn, String res_no) {
		String sql = "update restaurant\r\n"
					+ "set RES_BOOKYN = '"+newBookyn+"'\r\n"
					+ "where res_no= "+res_no;
		jdbc.update(sql);
	}
	public void adminUpdateAdd(String newAdd, String res_no) {
		String sql = "update restaurant\r\n"
					+ "set res_add = '"+newAdd+"'\r\n"
					+ "where res_no= "+res_no;
		jdbc.update(sql);		
	}
	public void adminUpdatePhone(String newPhone, String res_no) {
		String sql = "update restaurant\r\n"
					+ "set res_phone = '"+newPhone+"'\r\n"
					+ "where res_no= "+res_no;
		jdbc.update(sql);		
	}
	public void adminUpdateMenu(String newMenu, String res_no) {
		String sql = "update menu\r\n"
					+ "set MENU_NAME = '"+newMenu+"'\r\n"
					+ "where res_no = "+res_no;
		jdbc.update(sql);		
	}
	public void adminUpdatePrice(String newPrice, String res_no) {
		String sql = "update menu\r\n"
					+ "set MENU_PRICE = '"+newPrice+"'\r\n"
					+ "where res_no = "+res_no;
		jdbc.update(sql);		
	}
	
	//미등록식당 등록완료
	public void adminResUpload(String res_no) {
		String sql = "UPDATE restaurant\r\n"
					+ "SET res_postyn = 'Y'\r\n"
					+ "where res_no="+res_no;
		jdbc.update(sql);
	}

	//관리자 리뷰관리(리스트)
	public List<ReviewVo> adminReviewList() {
		String sql = "select (select res_name from restaurant where res_no = a.res_no) res_name, rev_star\r\n" + 
					 "        , (select mem_nick from member where mem_no = a.mem_no) mem_nick\r\n" + 
					 "        , rev_cont, rev_no, rev_postyn, to_char(rev_date,'yy.mm.dd hh24:mi') rev_date\r\n" + 
					 "        , a.res_no, a.mem_no\r\n" + 
					 "from review a\r\n"+
					 "order by rev_date desc";
		return ConvertUtils.convertToList(jdbc.selectList(sql), ReviewVo.class);
	}
	
	//관리자 리뷰 게시하기(Y)
	public void adminReviewPostY(String rev_no) {
		String sql = "update review\r\n"
				+ "set rev_postyn = 'Y'\r\n"
				+ "where rev_no = '"+rev_no+"'";
		jdbc.update(sql);
	}

	//관리자 리뷰 블라인드 처리(N)
	public void adminReviewBlind(String rev_no) {
		String sql = "update review\r\n"
				+ "set rev_postyn = 'N'\r\n"
				+ "where rev_no = '"+rev_no+"'";
		jdbc.update(sql);
	}
	
	//관리자 리뷰 게시여부 수정확인(Y/N)
	public ReviewVo adminRevPostYNCheck(String rev_no) {
		String sql = "select (select res_name from restaurant where res_no = a.res_no) res_name, rev_star\r\n"
				+ "        , (select mem_nick from member where mem_no = a.mem_no) mem_nick\r\n"
				+ "		, rev_cont, rev_no, rev_postyn, to_char(rev_date,'yy.mm.dd hh24:mi') rev_date\r\n"
				+ "		, a.res_no, a.mem_no\r\n"
				+ "from review a\r\n"
				+ "where a.rev_no = '"+rev_no+"'\r\n"
				+ "order by rev_date desc";
		return ConvertUtils.convertToVo(jdbc.selectOne(sql), ReviewVo.class);
	}
	
	
	//관리자 리뷰 블라인드 처리 확인
//	public ReviewVo adminRevBlindCheck(String rev_no) {
//		String sql = "select (select res_name from restaurant where res_no = a.res_no) res_name, rev_star\r\n"
//				+ "        , (select mem_nick from member where mem_no = a.mem_no) mem_nick\r\n"
//				+ "		, rev_cont, rev_no, rev_postyn, to_char(rev_date,'yy.mm.dd hh24:mi') rev_date\r\n"
//				+ "		, a.res_no, a.mem_no\r\n"
//				+ "from review a\r\n"
//				+ "where a.rev_no = '"+rev_no+"'\r\n"
//				+ "order by rev_date desc";
//		return ConvertUtils.convertToVo(jdbc.selectOne(sql), ReviewVo.class);
//	}

	//관리자 리뷰검색-식당이름
	public List<ReviewVo> adminReviewSearchResname(String resName) {
		String sql = "select rev.res_no, res.res_name, rev.rev_star, \r\n"
					+ "       mem.mem_nick, mem.mem_no, rev.rev_cont, \r\n"
					+ "       rev.rev_no, rev.rev_postyn, \r\n"
					+ "       to_char(rev.rev_date,'yy.mm.dd hh24:mi') rev_date\r\n"
					+ "from restaurant res\r\n"
					+ "    ,review rev\r\n"
					+ "    ,member mem\r\n"
					+ "where res.res_no(+) = rev.res_no\r\n"
					+ "and rev.mem_no = mem.mem_no(+)\r\n"
					+ "and res.res_name like '%"+resName+"%'";
		return ConvertUtils.convertToList(jdbc.selectList(sql), ReviewVo.class);
	}

	//관리자 리뷰검색-닉네임
	public List<ReviewVo> adminReviewSearchNickname(String nickName) {
		String sql = "select rev.res_no, res.res_name, rev.rev_star, \r\n"
				+ "       mem.mem_nick, mem.mem_no, rev.rev_cont, \r\n"
				+ "       rev.rev_no, rev.rev_postyn, \r\n"
				+ "       to_char(rev.rev_date,'yy.mm.dd hh24:mi') rev_date\r\n"
				+ "from restaurant res\r\n"
				+ "    ,review rev\r\n"
				+ "    ,member mem\r\n"
				+ "where res.res_no(+) = rev.res_no\r\n"
				+ "and rev.mem_no = mem.mem_no(+)\r\n"
				+ "and mem.mem_nick like '%"+nickName+"%'";
		return ConvertUtils.convertToList(jdbc.selectList(sql), ReviewVo.class);
	}

	

	
	




	
	
	
}