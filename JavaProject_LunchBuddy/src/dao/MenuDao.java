package dao;

import java.math.BigDecimal;
import java.util.List;

import util.ConvertUtils;
import util.JDBCUtil;
import vo.MenuVo;

public class MenuDao {

	private static MenuDao singleTon = null;
	
	private MenuDao(){};
	
	public static MenuDao getInstance() {
		if(singleTon == null) {
			singleTon = new MenuDao();
		}
		return singleTon;
	}
	
	JDBCUtil jdbc = JDBCUtil.getInstance();

	//식당등록할 때 입력받는 대표메뉴, 가격 insert
	public void menuAdd(List<Object> menuAdd) {
		String sql = "insert into menu (menu_no, menu_name, menu_price, res_no)\r\n" + 
					 "values( ?||'M'|| lpad((select nvl(max(no),0)+1 \r\n" + 
				"                              from(select substr(menu_no,7) no \r\n" + 
				"                                   from menu where menu_no like ?||'%')\r\n" + 
				"                              ),3,'0')\r\n" + 
				"        ,?,?,?)";
		jdbc.update(sql, menuAdd);
	}

	public int count_menu() {
		String sql = "select count(*) amount from menu where menu_postyn='Y' or menu_postyn='y'";
		return ((BigDecimal)jdbc.selectOne(sql).get("AMOUNT")).intValue();
	}

	public List<MenuVo> get_all_menu_list() {
		String sql = "select * from menu \n"
				+ "where (menu_postyn='Y' or menu_postyn='y')";
		return ConvertUtils.convertToList(jdbc.selectList(sql), MenuVo.class);
	}

	public List<MenuVo> menuList_by_res(String res_no) {
		String sql = "select * from menu \n"
				+ "where (menu_postyn='Y' or menu_postyn='y')\n"
				+ "and res_no = "+res_no;
		return ConvertUtils.convertToList(jdbc.selectList(sql), MenuVo.class);
	}

	public MenuVo get_menu_by_menuNo(String menuNo) {
		String sql = "select * from menu \n"
				+ "where (menu_postyn='Y' or menu_postyn='y')\n"
				+ "and menu_no = '"+menuNo+"'";
		return ConvertUtils.convertToVo(jdbc.selectOne(sql), MenuVo.class);
	}

}
