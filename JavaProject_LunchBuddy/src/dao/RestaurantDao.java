package dao;

import java.util.List;
import java.util.Map;

import util.ConvertUtils;
import util.JDBCUtil;
import vo.RestaurantVo;

public class RestaurantDao {

	private static RestaurantDao singleTon = null;
	
	private RestaurantDao(){};
	
	public static RestaurantDao getInstance() {
		if(singleTon == null) {
			singleTon = new RestaurantDao();
		}
		return singleTon;
	}
	
	JDBCUtil jdbc = JDBCUtil.getInstance();

	public List<RestaurantVo> resList() {
		String sql = "select re.res_name, re.res_walk, re.res_bookyn, \r\n" + 
					"(select round(nvl(avg(rev_star),0)) rev_star \r\n" + 
						" from review \r\n" + 
						" where review.res_no = re.res_no) rev_star,\r\n" + 
					"m.menu_name, m.menu_price\r\n" + 
					"from  restaurant re, menu m\r\n" + 
					"where m.res_no = re.res_no\r\n" + 
					"and m.menu_no like '%001'\r\n" + 
					"and re.res_postyn = 'N'\r\n" + 
					"order by m.menu_price";
		List<Map<String, Object>> resList = jdbc.selectList(sql);
		return ConvertUtils.convertToList(resList, RestaurantVo.class);
	}

	public List<RestaurantVo> resSearchResName(List<Object> param) {
		String sql = "";
		return null;
	}
	
	
}
