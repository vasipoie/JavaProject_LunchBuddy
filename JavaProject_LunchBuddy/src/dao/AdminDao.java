package dao;

import java.util.List;
import java.util.Map;

import util.ConvertUtils;
import util.JDBCUtil;
import vo.AdminVo;

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
}