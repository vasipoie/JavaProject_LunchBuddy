package service;

import java.util.Map;

import controller.Controller;
import dao.Dao;


public class Service {
	//sessionStorage 얕은 복사
	static public Map<String, Object> sessionStorage = Controller.sessionStorage;

	private static Service singleTon = null;
	
	private Service(){};
	
	public static Service getInstance() {
		if(singleTon == null) {
			singleTon = new Service();
		}
		return singleTon;
	}
	
	Dao dao = Dao.getInstance();
	
}