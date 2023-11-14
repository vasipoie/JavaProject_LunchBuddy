package service;

import java.util.List;
import java.util.Map;

import controller.AdminController;
import dao.AdminDao;
import vo.AdminVo;


public class AdminService {
	static public Map<String, Object> sessionStorage = AdminController.sessionStorage;

	private static AdminService singleTon = null;
	
	private AdminService(){};
	
	public static AdminService getInstance() {
		if(singleTon == null) {
			singleTon = new AdminService();
		}
		return singleTon;
	}

	
	AdminDao adminDao = AdminDao.getInstance();

	public boolean adminLogin(List<Object> param) {
		AdminVo ad = adminDao.adminLogin(param);
		if(ad != null) {
			sessionStorage.put("admin",ad);
			return true;
		}
		return false;
	}
	
}