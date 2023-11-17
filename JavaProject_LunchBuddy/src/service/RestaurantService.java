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

	public List<RestaurantVo> resSearchResName(String name) {
		return resDao.resSearchResName(name);
	}

	public List<Map<String, Object>> resSearchCategory(int category) {
		return resDao.resSearchCategory(category);
	}

	public void resAdd(List<Object> restAdd) {
		resDao.resAdd(restAdd);
	}

	public RestaurantVo resAddOneBefore(String cateNo) {
		RestaurantVo resAddOne = resDao.resAddOneBefore(cateNo);
		return resAddOne;
	}

	public RestaurantVo resAddOnePrint(String cateNo) {
		return resDao.resAddOnePrint(cateNo);
	}

//	public List<RestaurantVo> getResDetail(String res_no) {
//		return resDao.getResDetail(res_no);
//	}

	
	
}
