package service;

import java.util.List;
import java.util.Map;

import controller.Controller;
import dao.AdminDao;
import vo.AdminVo;
import vo.RestaurantVo;


public class AdminService {
	static public Map<String, Object> sessionStorage = Controller.sessionStorage;

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

	//관리자용 등록 대기 중 식당 리스트
	public List<RestaurantVo> adminResList() {
		return adminDao.adminResList();
	}

	public void adminUpdateResName(String newResName, String resNo) {
		adminDao.adminUpdateResName(newResName, resNo);
	}

	public RestaurantVo adminSelectModifyResDetail(String resNo) {
		return adminDao.adminSelectModifyResDetail(resNo);
	}

	public void adminUpdateWalkName(int newWalk, String resNo) {
		adminDao.adminUpdateWalkName(newWalk, resNo);
	}

	public RestaurantVo adminSelectModifyWalkDetail(String res_no) {
		return adminDao.adminSelectModifyWalkDetail(res_no);;
	}

	public void adminResUpload(String res_no) {
		adminDao.adminResUpload(res_no);
	}
	
}