package dao;

import java.util.ArrayList;
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

//	public List<RestaurantVo> resList() {
//		String sql = "select re.res_name, re.res_walk, re.res_bookyn \r\n" + 
//					   ",(select round(nvl(avg(rev_star),0)) rev_star \r\n" + 
//					    "from review \r\n" + 
//					    "where review.res_no = re.res_no) rev_star\r\n" + 
//					  ",m.menu_name, m.menu_price\r\n" + 
//					 "from  restaurant re, menu m\r\n" + 
//					 "where m.res_no = re.res_no\r\n" + 
//					 "and m.menu_no like '%001'\r\n" + 
//					 "and re.res_postyn = 'N'\r\n" + 
//					 "order by m.menu_price";
//		List<Map<String, Object>> resList = jdbc.selectList(sql);
//		return ConvertUtils.convertToList(resList, RestaurantVo.class);
//	}

	//'%'||?||'%'"
//	like '"+cateNo+"%' 
	//식당이름으로검색
	public List<RestaurantVo> resSearchResName(String name) {
		String sql = "with data as\r\n" + 
				     "(select re.*, round(nvl(avg(r.rev_star),0),1) rev_star\r\n" + 
				       "from review r right outer join restaurant re\r\n" + 
				         "on r.res_no = re.res_no\r\n" + 
				       "group by re.res_no, re.res_name, re.res_add, re.res_phone, re.res_bookyn \r\n" + 
				                ", re.res_walk, re.res_postyn, re.cat_no, re.res_date, re.column2)\r\n" + 
				      "select rownum, data.*\r\n" + 
				             ", menu.menu_name, menu.menu_price\r\n" + 
				      "from data, menu\r\n" + 
				      "where data.res_no = menu.res_no\r\n" + 
				      "and menu.menu_no like '%001'\r\n" + 
				      "and data.res_name like '%"+name+"%'\r\n"+
				      "and data.res_postyn = 'Y'";
		return ConvertUtils.convertToList(jdbc.selectList(sql), RestaurantVo.class);
	}

	//메뉴카테고리로검색
	public List<RestaurantVo> resSearchCategory(int category) {
		String sql = "with data as\r\n" + 
					 "(select re.*, round(nvl(avg(r.rev_star),0)) rev_star\r\n" + 
					  "from review r right outer join restaurant re\r\n" + 
					    "on r.res_no = re.res_no\r\n" + 
					  "group by re.res_no, re.res_name, re.res_add, re.res_phone, re.res_bookyn \r\n" + 
							   ",re.res_walk, re.res_postyn, re.cat_no, re.res_date, re.column2)\r\n" + 
					 "select rownum, data.*\r\n" + 
					        ",menu.menu_name, menu.menu_price\r\n" + 
					 "from data, menu\r\n" + 
					 "where data.res_no = menu.res_no\r\n" + 
					 "and menu.menu_no like '%001'\r\n" +
					 "and data.res_postyn = 'Y'\r\n"+
					 "and data.cat_no = 0"+category;
		List<Map<String, Object>> list = jdbc.selectList(sql);
		return ConvertUtils.convertToList(jdbc.selectList(sql), RestaurantVo.class);
	}

	//회원이 입력한 식당등록 중 식당테이블 관련 정보
	public void resAdd(List<Object> restAdd) {
		String sql1 = "select '"+restAdd.remove(0)+"' || lpad(aa,3,'0')from(select nvl(max(no),0)+1 aa\r\n"+
					  "                  from(select substr(res_no,3) no\r\n" +
					  "                       from restaurant where cat_no ='"+restAdd.remove(0)+"'))";
		 
		String sql = "insert into restaurant (res_no, res_name, res_add, res_phone, res_bookyn, cat_no)\r\n" + 
					 "values(("+sql1+"),'"+restAdd.get(0)+"','"+restAdd.get(1)+"','"+restAdd.get(2)+"','"+restAdd.get(3)+"','"+restAdd.get(4)+"')";
		
//		String sql = 
//				"insert into restaurant (res_no, res_name, res_add, res_phone, res_bookyn, cat_no)\r\n" + 
//				"values("+cateNo+"|| lpad((select count(no)+1\r\n" + 
//				"                          from(select substr(res_no,3) no \r\n" + 
//				"                               from restaurant where cat_no ="+cateNo+")\r\n" + 
//				"                         ),3,'0'\r\n" + 
//				"                        ),?,?,?,?,"+cateNo+")";
		jdbc.update(sql);
	}
	
	//회원이 입력한 식당등록 값 중 res_no 사용을 위해
	public RestaurantVo resAddOneBefore(String cateNo) {
		String sql = "select res_no, res_name, cat_no, res_add, res_phone, res_bookyn\r\n" + 
					 "from restaurant\r\n" + 
					 "where res_no like '"+cateNo+"%'\r\n" + 
					 "AND ROWNUM = 1\r\n" + 
					 "order by res_no desc";
		Map<String, Object> map = jdbc.selectOne(sql);
		return ConvertUtils.convertToVo(map, RestaurantVo.class);
	}

	//회원이 등록한 식당 출력
	public RestaurantVo resAddOnePrint(String cateNo) {
		String sql = "select res_no, res_name, cat_no, res_add, res_phone, res_bookyn\r\n" + 
				",(select menu_name from menu where res_no=a.res_no and menu_no like '%001') menu_name\r\n" + 
				",(select menu_price from menu where res_no=a.res_no and menu_no like '%001') menu_price\r\n" + 
				"from (select * from restaurant where res_no like '"+cateNo+"%' order by res_no desc) a\r\n" + 
				"where ROWNUM = 1";
		Map<String, Object> map = jdbc.selectOne(sql);
		return ConvertUtils.convertToVo(map, RestaurantVo.class);
	}

	//식당 이름으로 검색할 때 상세보기
	public List<RestaurantVo> getResDetail(String res_no) {
		String sql = "select *,(select menu_name from menu where res_no=a.res_no) menu_name,(select menu_price from menu where res_no=a.res_no) menu price";
		return null;
	}

	public RestaurantVo getRes_by_resNo(String res_no) {
		String sql = "select r.res_no, r.res_name, r.res_add, r.res_phone, r.res_bookyn, r.res_walk\r\n"
				+ "    ,r.res_postyn, r.cat_no, to_char(r.res_date,'yymmdd') res_date\r\n"
				+ "    , mn.menu_name\r\n"
				+ "    , mn.menu_price\r\n"
				+ "    ,(select round(nvl(avg(rev_star),0),0)  from review where res_no = "+res_no+") rev_star\r\n"
				+ "from restaurant r \r\n"
				+ "    ,(select * from menu \r\n"
				+ "                where res_no = "+res_no
				+ "                    and menu_no like '%001') mn\r\n"
				+ "    where r. res_no = "+res_no;
		return ConvertUtils.convertToVo(jdbc.selectOne(sql), RestaurantVo.class);
	}
	

//	public RestaurantVo resAdd(List<Object> restAdd) {
//		String sql = "insert into restaurant (res_no, res_name, res_add, res_phone, res_bookyn, cat_no)\r\n" + 
//				"values( ? || lpad((select nvl(max(no),0)+1 \r\n" + 
//				"from(select substr(res_no,3) no from restaurant where cat_no = ?)),3,'0')\r\n" + 
//				",?,?,?,?,?)";
//		Map<String, Object> map = jdbc.selectOne(sql, restAdd);
//		return ConvertUtils.convertToVo(map, RestaurantVo.class);
//	}
	
}
