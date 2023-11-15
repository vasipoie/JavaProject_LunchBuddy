package service;

import java.util.List;

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

	public List<RestaurantVo> resSearchResName(List<Object> param) {
		return resDao.resSearchResName(param);
	}
	
	
}
