package dao;

import java.util.List;

import util.JDBCUtil;

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
}
