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

	//'%'||?||'%'"
	public List<Map<String, Object>> resSearchResName(List<Object> param) {
		String sql = "with data as\r\n" + 
				"(select re.*, round(nvl(avg(r.rev_star),0)) rev_star\r\n" + 
				"from review r right outer join restaurant re\r\n" + 
				"on r.res_no = re.res_no\r\n" + 
				"group by re.res_no, re.res_name, re.res_add, re.res_phone, re.res_bookyn, \r\n" + 
				"re.res_walk, re.res_postyn, re.cat_no, re.column1, re.column2)\r\n" + 
				"select rownum, data.res_name, data.res_walk, data.res_bookyn,\r\n" + 
				"data.rev_star,\r\n" + 
				"menu.menu_name, menu.menu_price\r\n" + 
				"from data, menu\r\n" + 
				"where data.res_no = menu.res_no\r\n" + 
				"and menu.menu_no like '%001'\r\n" + 
				"and res_name like '%'||?||'%'";
		List<Map<String, Object>> list = jdbc.selectList(sql,param);
		return list;
	}

	public List<Map<String, Object>> resSearchCategory(int category) {
		String sql = "with data as\r\n" + 
						"(select re.*, round(nvl(avg(r.rev_star),0)) rev_star\r\n" + 
						"from review r right outer join restaurant re\r\n" + 
						"on r.res_no = re.res_no\r\n" + 
						"group by re.res_no, re.res_name, re.res_add, re.res_phone, re.res_bookyn, \r\n" + 
						"re.res_walk, re.res_postyn, re.cat_no, re.column1, re.column2)\r\n" + 
					"select rownum, data.res_name, data.res_walk, data.res_bookyn,\r\n" + 
					"data.rev_star,\r\n" + 
					"menu.menu_name, menu.menu_price\r\n" + 
					"from data, menu\r\n" + 
					"where data.res_no = menu.res_no\r\n" + 
					"and menu.menu_no like '%001'\r\n" + 
					"and cat_no = 0"+category;
		List<Map<String, Object>> list = jdbc.selectList(sql);
		return list;
	}

	public Map<String, Object> resAdd(List<Object> restAdd) {
		String sql = "insert into restaurant (res_no, res_name, res_add, res_phone, res_bookyn, cat_no)\r\n" + 
					"values(0? || lpad( (select nvl(max(no),0)+1 from\r\n" + 
					"(select substr(res_no,3) no from restaurant where cat_no = 0?)\r\n" + 
					"),3,'0')\r\n" + 
					",?,?,?,?,0?)";
		Map<String, Object> map = jdbc.selectOne(sql, restAdd);
		return map;
	}

	
	
}
