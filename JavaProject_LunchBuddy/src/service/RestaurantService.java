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

	public List<RestaurantVo> resList() {
		return resDao.resList();
	}

	public List<Map<String, Object>> resSearchResName(List<Object> param) {
		return resDao.resSearchResName(param);
	}

	public List<Map<String, Object>> resSearchCategory(int category) {
		return resDao.resSearchCategory(category);
	}

	public void resAdd(List<Object> restAdd) {
		resDao.resAdd(restAdd);
	}

	public RestaurantVo resAddOne() {
		RestaurantVo resAddOne = resDao.resAddOne();
		//쿼리 보낸거 받아와서 cat_no 한글로 바꾼 후 리턴
		String changeKorea = resAddOne.getCat_no();
		switch(changeKorea) {
		case "01": resAddOne.getCat_no().replace("01", "한식"); break;
		case "02": resAddOne.getCat_no().replace("02", "양식"); break;
		case "03": resAddOne.getCat_no().replace("03", "아시아음식"); break;
		case "04": resAddOne.getCat_no().replace("04", "일식"); break;
		case "05": resAddOne.getCat_no().replace("05", "중식"); break;
		case "06": resAddOne.getCat_no().replace("06", "분식"); break;
		case "07": resAddOne.getCat_no().replace("07", "카페"); break;
		case "08": resAddOne.getCat_no().replace("08", "뷔페"); break;
		case "09": resAddOne.getCat_no().replace("09", "기타"); break;
		}
		return resAddOne;
	}

	
	
}
