package dao;

import java.util.List;
import java.util.Map;

import util.ConvertUtils;
import util.JDBCUtil;
import vo.AdminVo;
import vo.RestaurantVo;

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




	
	
	
}