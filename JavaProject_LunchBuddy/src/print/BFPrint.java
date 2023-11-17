package print;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import service.BFService;
import service.Service;
import vo.BFListVo;
import vo.BFVo;

public class BFPrint extends Print {

	BFService bfService = new BFService();
	Service service = Service.getInstance();

	public void printDate(Calendar cal) {
		DateFormat df = new SimpleDateFormat("MM-dd EEE");

		if (cal.HOUR_OF_DAY >= 13)
			cal.add(Calendar.DATE, 1);

		for (int i = 1; i <= 7; i++) {
			cal.add(Calendar.DATE, 1);
			if (cal.get(Calendar.DAY_OF_WEEK) == 1)
				cal.add(Calendar.DATE, 1);
			System.out.println(i + ". " + df.format(cal.getTime()));
		}
	}

	public void print_bf_main() {
		System.out.println("점심 친구 찾기");
		printBar();
		System.out.println("[ 모집 중인 점심 친구 ]");
		print_bf_for_home(8);
		printBar();
		System.out.println("1. 모집 중인 점심 친구 목록 전체 보기");
		System.out.println("2. 점심 친구 모집하기");
		System.out.println("9. 홈으로가기     0.뒤로가기");
		printBar();
	}

	/*
	 * 점심친구!
------------------------------------------------------------
23.11.18  부부바지락칼국수 팟 구해요 ( 2 / 3 ) - 나콩
------------------------------------------------------------
11월 14일 점심 칼국수 드실 분 1층에서 모여서 가요
현재 참석 인원 : 나콩  만만두
------------------------------------------------------------
[ 부부바지락칼국수  ]
칼국수 7000
손만두국 8000
떡만두국 8000

------------------------------------------------------------
1. 참석하기
9.홈으로 가기     0.뒤로가기
------------------------------------------------------------
메뉴 선택 >> 
	 */
	public void printBF(BFVo bobF, List<BFListVo> bfMemList) {
		System.out.println("점심친구!");
		printBar();
		System.out.println(bobF.getBf_date() + "  " + bobF.getBf_name() 
						+ " ( " + bfMemList.size() + " / " + bobF.getBf_num() + " ) - " + bobF.getMem_nick());
		printBar();
		System.out.println(bobF.getBf_cont());
		System.out.print("현재 참석자 : ");
		for (BFListVo bfp : bfMemList) System.out.print(bfp.getMem_nick() + " ");
		System.out.println();
		printBar();
		System.out.println("[ "+bobF.getRes_name()+" ]");
		List<Map<String,Object>> list = service.getMenuList(bobF.getRes_no());
		for(int i =0; i<4; i++) {
			if(list==null || list.size() <= i) System.out.println();
			else if(list.size()>i) {
				Map<String,Object> map = list.get(i);
				System.out.println(map.get("MENU_NAME")+" "+map.get("MENU_PRICE"));
			}
		}
		printBar();
	}

	public void print_delete_sucess() {
		printLn(5);
		System.out.println("삭제 완료 되었습니다");
		System.out.println("3초 후 홈으로 돌아갑니다.");
		printLn(4);
		pause();
	}

	public void print_already_part() {
		printLn(5);
		System.out.println("이미 참여한 모임입니다.");
		System.out.println("3초 후 페이지로 돌아갑니다.");
		printLn(4);
		pause();

	}

	public void print_bf_for_home(int line) {
		List<BFVo> bfList = bfService.get_bfList();

		for (int i=0; i<line; i++) {
			if (bfList.size() > i) {
				BFVo bf = bfList.get(i);
				String resName = bf.getRes_name();
				if (bf.getRes_name().length() < 4) resName += "\t";
				System.out.println(" " + resName + " \t" + bf.getBf_date() 
						+ " - " + bf.getBf_name() 
						+ " ( " + bf.getPart_num() + " / " + bf.getBf_num() + " )");
			} else System.out.println();
		}

	}

}
