package service;

import java.util.List;
import java.util.Map;

import dao.RestaurantDao;
import vo.RestaurantVo;

public class RestaurantService {

	private static RestaurantService singleTon = null;
	
	private RestaurantService(){};
	
	public static RestaurantService getInstance() {
		if(singleTon == null) {
			singleTon = new RestaurantService();
		}
		return singleTon;
	}

	RestaurantDao resDao = RestaurantDao.getInstance();

	public List<RestaurantVo> resList() {
		return resDao.resList();
	}

	public List<Map<String, Object>> resSearchResName(List<Object> param) {
		return resDao.resSearchResName(param);
	}

	public List<Map<String, Object>> resSearchCategory(int category) {
		return resDao.resSearchCategory(category);
	}

	public RestaurantVo resAdd(List<Object> restAdd) {
		return resDao.resAdd(restAdd);
	}

	
	
}
