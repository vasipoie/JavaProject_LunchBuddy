package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import print.Print;
import service.Service;
import util.ScanUtil;
import util.View;

public class Controller extends Print {
	static public Map<String, Object> sessionStorage = new HashMap<>();

	Service memberService = Service.getInstance();
	
	
	public static void main(String[] args) {
		new Controller().start();
	}

	 void start() {
		View view = View.HOME;
		while (true) {
			switch (view) {
			case HOME:
				view = home();
				break;
			}
		}
	}

	 View home() {
		printHome();
		int select = ScanUtil.nextInt("메뉴를 선택하세요\s");
		switch (select) {
		case 1:
			return View.HOME;
		case 2:
			return View.HOME;
		default :
			return View.HOME;
		}
	}
}
