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

	//회원이 입력한 식당등록 값 중 res_no 사용을 위해
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

	public RestaurantVo getRes_by_resNo(String res_no) {
		return resDao.getRes_by_resNo(res_no);
	}

}
