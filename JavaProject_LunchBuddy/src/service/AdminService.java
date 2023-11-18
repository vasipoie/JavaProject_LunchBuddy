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

	//관리자 로그인
	public boolean adminLogin(List<Object> param) {
		AdminVo ad = adminDao.adminLogin(param);
		if(ad != null) {
			sessionStorage.put("admin",ad);
			return true;
		}
		return false;
	}
	
	//관리자 등록된 식당 리스트
	public List<RestaurantVo> adminRegiResList() {
		return adminDao.adminRegiResList();
	}
	
	//등록된 식당 삭제
	public void adminResDelete(String res_no) {
		adminDao.adminResDelete(res_no);
	}

	//관리자 미등록 식당 리스트
	public List<RestaurantVo> adminStandbyResList() {
		return adminDao.adminStandbyResList();
	}

	//미등록식당 수정 update,select
	public void adminUpdateResName(String newResName, String res_no) {
		adminDao.adminUpdateResName(newResName, res_no);
	}
	public RestaurantVo adminSelectModifyDetail(String res_no) {
		return adminDao.adminSelectModifyDetail(res_no);
	}

	public void adminUpdateWalk(int newWalk, String res_no) {
		adminDao.adminUpdateWalk(newWalk, res_no);
	}
	public void adminUpdateBook(String newBookyn, String res_no) {
		adminDao.adminUpdateBook(newBookyn, res_no);
	}
	public void adminUpdateAdd(String newAdd, String res_no) {
		adminDao.adminUpdateAdd(newAdd, res_no);
	}
	public void adminUpdatePhone(String newPhone, String res_no) {
		adminDao.adminUpdatePhone(newPhone, res_no);
	}
	public void adminUpdateMenu(String newMenu, String res_no) {
		adminDao.adminUpdateMenu(newMenu, res_no);
	}
	public void adminUpdatePrice(String newPrice, String res_no) {
		adminDao.adminUpdatePrice(newPrice, res_no);
	}
	
	//미등록식당 등록완료
	public void adminResUpload(String res_no) {
		adminDao.adminResUpload(res_no);
	}

	

	


	

	
}