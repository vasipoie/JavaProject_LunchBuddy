package print;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import vo.BFListVo;
import vo.BFVo;

public class BFPrint extends Print{
	
	public void printDate (Calendar cal) {
		DateFormat df = new SimpleDateFormat("MM-dd EEE");

		if(cal.HOUR_OF_DAY>=13) cal.add(Calendar.DATE, 1);
		
		for(int i = 1; i<=7; i++) {
			cal.add(Calendar.DATE, 1);
			if(cal.get(Calendar.DAY_OF_WEEK)==1) cal.add(Calendar.DATE, 1);
			System.out.println(i + ". "+ df.format(cal.getTime()));
		}
	}
	
	public void print_bf_main() {
		System.out.println("점심 친구 찾기");
		printBar();
		System.out.println("1. 모집 중인 점심 친구 목록 보기");
		System.out.println("2. 점심 친구 모집하기");
		printLn(10);
		System.out.println("9. 홈으로가기     0.뒤로가기");
		printBar();
	}
	
	public void printBF(BFVo bobF, List<BFListVo> bfMemList) {
		System.out.println("점심친구!");
		printBar();
		System.out.println("[ " + bobF.getRes_name() + " ]  " + bobF.getBf_date());
		System.out.println(bobF.getBf_cont() + " ( " + (bfMemList.size()+1) + " / " + bobF.getBf_num() + " )" );
		printBar();
		System.out.println(bobF.getBf_cont());
		System.out.println(" - " + bobF.getMem_nick());
		System.out.print("현재 참석 인원 ( ");
		for (BFListVo bfp : bfMemList ) System.out.print(bfp.getMem_nick()+"  ");
		System.out.println();
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
	
}
