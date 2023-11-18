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

	//식당등록할 때 입력받는 대표메뉴, 가격 insert
	public void menuAdd(List<Object> menuAdd) {
		menuDao.menuAdd(menuAdd);
	}
}
