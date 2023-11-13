package controller;

import java.util.ArrayList;
import java.util.List;

import print.MemberPrint;
import service.MemberService;
import util.ScanUtil;
import util.View;

public class MemberController extends MemberPrint{
	
	
	MemberService memberService = new MemberService();

	public View join() {
		print_join();
		View view;
		
		String id;
		while(true) {
			id = ScanUtil.nextLine("id :");
			if(memberService.idcheck(id)) break;
			else {
				System.out.println("1. 재시도   0. 뒤로가기");
				int select = ScanUtil.nextInt("선택 >> ");
				switch (select) {
				case 0:
					return Controller.goBack();
				default:
					break;
				}
			}
		}
		

		String pw;
		while(true) {
			pw = ScanUtil.nextLine("pw :");
			if(memberService.pwcheck(pw)) break;
			else {
				view = askBack();
				if(view==null) {
					break;
				}
			}
		}

		String name;
		while(true) {
			name = ScanUtil.nextLine("name :");
			if(memberService.namecheck(name)) break;
			else {
				view = askBack();
				if(view==null) {
					break;
				}
			}
		}
		

		String phone;
		while(true) {
			phone = ScanUtil.nextLine("전화번호 :");
			if(memberService.phonecheck(phone)) break;
			else {
				view = askBack();
				if(view==null) {
					break;
				}
			}
		}
		
		String nick;
		while(true) {
			nick = ScanUtil.nextLine("닉네임 :");
			if(memberService.nickcheck(nick)) break;
			else {
				view = askBack();
				if(view==null) {
					break;
				}
			}
		}
		String idQue = ScanUtil.nextLine("본인 확인 질문 :");
		String idAns = ScanUtil.nextLine("본인 확인 질문 답변 :");

		List<Object> param = new ArrayList();
		param.add(id);
		param.add(pw);
		param.add(name);
		param.add(phone);
		param.add(nick);
		param.add(idQue);
		param.add(idAns);
		
		memberService.join(param);
		
		
		
		
		return null;
	}
	
	private View askBack() {
		System.out.println("1. 재시도   0. 뒤로가기");
		int select = ScanUtil.nextInt("선택 >> ");
		switch (select) {
		case 0:
			return Controller.goBack();
		default:
			return null;
		}
		
	}

	
}
