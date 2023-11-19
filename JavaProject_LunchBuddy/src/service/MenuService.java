package service;

import java.util.List;

import dao.MenuDao;
import vo.MenuVo;

public class MenuService {

	private static MenuService singleTon = null;
	
	public MenuService(){};
	
	public static MenuService getInstance() {
		if(singleTon == null) {
			singleTon = new MenuService();
		}
		return singleTon;
	}
	
	MenuDao menuDao = MenuDao.getInstance();

	//식당등록할 때 입력받는 대표메뉴, 가격 insert
	public void menuAdd(List<Object> menuAdd) {
		menuDao.menuAdd(menuAdd);
	}

	public int count_menu() {
		return menuDao.count_menu();
	}

	public List<MenuVo> get_all_menu_list() {
		return menuDao.get_all_menu_list();
	}

	public List<MenuVo> menuList_by_res(String res_no) {
		return menuDao.menuList_by_res(res_no);
	}
	public MenuVo get_menu_by_menuNo(String menuNo) {
		return menuDao.get_menu_by_menuNo(menuNo);
	}
	

}
