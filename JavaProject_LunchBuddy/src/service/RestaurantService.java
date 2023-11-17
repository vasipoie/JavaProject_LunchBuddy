package service;

import java.util.List;
import java.util.Map;

import dao.RestaurantDao;
import vo.RestaurantVo;

public class RestaurantService {

	private static RestaurantService singleTon = null;
	
	public RestaurantService(){};
	
	public static RestaurantService getInstance() {
		if(singleTon == null) {
			singleTon = new RestaurantService();
		}
		return singleTon;
	}

	RestaurantDao resDao = RestaurantDao.getInstance();

//	public List<RestaurantVo> resList() {
//		return resDao.resList();
//	}

	//식당이름으로검색
	public List<RestaurantVo> resSearchResName(String name) {
		return resDao.resSearchResName(name);
	}

	//메뉴카테고리로검색
	public List<RestaurantVo> resSearchCategory(int category) {
		return resDao.resSearchCategory(category);
	}

	public void resAdd(List<Object> restAdd) {
		resDao.resAdd(restAdd);
	}

	public RestaurantVo resAddOneBefore(String cateNo) {
		RestaurantVo resAddOne = resDao.resAddOneBefore(cateNo);
		return resAddOne;
	}

	//회원이 등록한 식당 출력
	public RestaurantVo resAddOnePrint(String cateNo) {
		return resDao.resAddOnePrint(cateNo);
	}

	//식당 이름으로 검색할 때 상세보기
	public List<RestaurantVo> getResDetail(String res_no) {
		return resDao.getResDetail(res_no);
	}

	//식당등록 전 수정
	public void updateResName(String newResName, String res_no) {
		resDao.updateResName(newResName, res_no);
	}

	public void updateAdd(String newAdd, String res_no) {
		resDao.updateAdd(newAdd, res_no);
	}

	public void updatePhone(String newPhone, String res_no) {
		resDao.updatePhone(newPhone, res_no);
	}

	public void updateBookyn(String newBookyn, String res_no) {
		resDao.updateBookyn(newBookyn, res_no);
	}

	public void updatePrice(String newPrice, String res_no) {
		resDao.updatePrice(newPrice, res_no);		
	}
	
	//수정한 식당등록 보여주기
	public RestaurantVo modifyResAdd(String res_no) {
		return resDao.modifyResAdd(res_no);
	}

	
	
}
