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

	public RestaurantVo resAddOneBefore(String cateNo) {
		RestaurantVo resAddOne = resDao.resAddOneBefore(cateNo);
		//쿼리 보낸거 받아와서 cat_no 한글로 바꾼 후 리턴
//		String changeKorea = resAddOne.getCat_no();
//		switch(changeKorea) {
//		case "01": resAddOne.setCat_no("한식"); break;
//		case "02": resAddOne.setCat_no("양식"); break;
//		case "03": resAddOne.setCat_no("아시아음식"); break;
//		case "04": resAddOne.setCat_no("일식"); break;
//		case "05": resAddOne.setCat_no("중식"); break;
//		case "06": resAddOne.setCat_no("분식"); break;
//		case "07": resAddOne.setCat_no("카페"); break;
//		case "08": resAddOne.setCat_no("뷔페"); break;
//		case "09": resAddOne.setCat_no("기타"); break;
//		}
//		switch(resAddOne.getCat_no()) {
//		case "01": changeKorea = "한식"; break;
//		case "02": changeKorea = "양식"; break;
//		case "03": changeKorea = "아시아음식"; break;
//		case "04": changeKorea = "일식"; break;
//		case "05": changeKorea = "중식"; break;
//		case "06": changeKorea = "분식"; break;
//		case "07": changeKorea = "카페"; break;
//		case "08": changeKorea = "뷔페"; break;
//		case "09": changeKorea = "기타"; break;
//		}
		return resAddOne;
	}

	public RestaurantVo resAddOnePrint(String cateNo) {
		return resDao.resAddOnePrint(cateNo);
	}

	
	
}
