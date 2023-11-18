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

		printBar();
		System.out.println("\t\t\t\t점심 친구 모집하기");
		System.out.println();
		System.out.println("\t\t\t\t날짜를 선택해주세요");
		System.out.println("\t\t\t일주일 뒤의 날짜까지 선택이 가능합니다.");
		printLn(2);
		for (int i = 1; i <= 7; i++) {
			cal.add(Calendar.DATE, 1);
			if (cal.get(Calendar.DAY_OF_WEEK) == 1)
				cal.add(Calendar.DATE, 1);
			System.out.println("\t\t\t\t"+i + ". " + df.format(cal.getTime()));
		}
		printLn(2);
		printBar();
	}

	public void print_bf_main() {
		System.out.println("\t\t\t점심 친구 찾기");
		printBar();
		System.out.println("\t\t[ 모집 중인 점심 친구 ]");
		print_bf_for_home(8);
		printBar();
		System.out.println("\t\t1. 모집 중인 점심 친구 목록 전체 보기");
		System.out.println("\t\t2. 점심 친구 모집하기");
		System.out.println("\t\t9. 홈으로가기     0.뒤로가기");
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
		printBar();
		System.out.println("\t\t\t\t점심친구!");
		printBar();
		System.out.println("\t  "+bobF.getBf_date() + "  " + bobF.getBf_name() 
						+ " ( " + bfMemList.size() + " / " + bobF.getBf_num() + " ) - " + bobF.getMem_nick());
		printBar();
		System.out.println("\t  "+bobF.getBf_cont());
		System.out.print("\t  현재 참석자 : ");
		for (BFListVo bfp : bfMemList) System.out.print(bfp.getMem_nick() + " ");
		System.out.println();
		printBar();
		System.out.println("\t  [ "+bobF.getRes_name()+" ]");
		List<Map<String,Object>> list = service.getMenuList(bobF.getRes_no());
		for(int i =0; i<4; i++) {
			if(list==null || list.size() <= i) System.out.println();
			else if(list.size()>i) {
				Map<String,Object> map = list.get(i);
				System.out.println("\t  "+map.get("MENU_NAME")+" "+map.get("MENU_PRICE"));
			}
		}
		printBar();
	}
	
	public void print_make_bf(String resName, String bfTitle, String bfCont, int bfNum, String bfDate, boolean b) {
		printBar();
		System.out.println("\t\t\t\t점심 친구 모집하기");
		printLn(4);
		System.out.println("\t\t\t식당\t: "+resName);
		System.out.println("\t\t\t모임 이름\t: "+bfTitle);
		System.out.println("\t\t\t모임 내용\t: "+bfCont);
		if (bfNum==0) System.out.println("\t\t\t인원수\t: ");
		else System.out.println("\t\t\t인원수\t: "+bfNum);
		System.out.println("\t\t\t모임 날짜\t: "+bfDate);
		if(b) printLn(5);
		else {
			printLn(2);
			System.out.println("\t\t\t    등록하시겠습니까?");
			System.out.println();
			System.out.println("\t\t\t1. 등록하기   0. 등록 취소");
		}
		printBar();
	}

	public void print_delete_sucess() {
		printBar();
		printLn(7);
		System.out.println("\t\t\t\t삭제 완료 되었습니다");
		System.out.println("\t\t\t      잠시 후 홈으로 돌아갑니다.");
		printLn(6);
		printBar();
		pause();
	}

	public void print_already_part() {
		printBar();
		printLn(7);
		System.out.println("\t\t\t     이미 참여한 모임입니다.");
		System.out.println("\t\t\t   잠시 후 페이지로 돌아갑니다.");
		printLn(6);
		printBar();
		pause();

	}

	public void print_bf_for_home(int line) {
		List<BFVo> bfList = bfService.get_bfList();

		for (int i=0; i<line; i++) {
			if (bfList.size() > i) {
				BFVo bf = bfList.get(i);
				String resName = bf.getRes_name();
				if (bf.getRes_name().length() < 6) resName += "\t";
				String bf_name = bf.getBf_name();
				if(bf_name.length()<12) bf_name += "\t";
				String str = "" + resName + " \t" + bf.getBf_date() 
				+ " - " + bf_name 
				+ " ( " + bf.getPart_num() + " / " + bf.getBf_num() + " )\t";
				System.out.println("\t    - " + str);
				
			} else System.out.println();
		}

	}
	

}
