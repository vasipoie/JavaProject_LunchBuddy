package service;

import java.util.List;

import dao.MenuDao;

public class MenuService {

	private static MenuService singleTon = null;
	
	private MenuService(){};
	
	public static MenuService getInstance() {
		if(singleTon == null) {
			singleTon = new MenuService();
		}
		return singleTon;
	}
	
	MenuDao menuDao = MenuDao.getInstance();

	public void menuAdd(List<Object> menuAdd) {
		menuDao.menuAdd(menuAdd);
	}
}
