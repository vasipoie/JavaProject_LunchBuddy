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

	public AdminVo adminLogin(List<Object> param) {
		String sql = " select * from admin where adm_id =? and adm_pw =? ";
		Map<String, Object> map = jdbc.selectOne(sql, param);
		if(map == null) return null;
		AdminVo ad = ConvertUtils.convertToVo(map, AdminVo.class);
		return ad;
	}

	//관리자용 등록 대기 중 식당 리스트
	public List<RestaurantVo> adminResList() {
		String sql = "select distinct res.*, m.*\r\n" + 
					 "from restaurant res\r\n" + 
					 "    ,menu m\r\n" + 
					 "where res.res_no = m.res_no(+)\r\n" + 
					 "and res.res_postyn ='N'\r\n" + 
					 "order by res.res_date desc";
		return ConvertUtils.convertToList(jdbc.selectList(sql), RestaurantVo.class);
	}
}