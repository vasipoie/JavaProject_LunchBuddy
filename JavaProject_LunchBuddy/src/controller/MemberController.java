package controller;

import java.util.ArrayList;
import java.util.List;

import print.MemberPrint;
import service.MemberService;
import util.ScanUtil;
import util.View;
import vo.MemberVo;

public class MemberController extends MemberPrint {

	MemberService memberService = new MemberService();

	public View memberController(View view) {
		while (true) {
			Controller.newPage(view);
			switch (view) {
			case MEMBER:
				view = member();
				break;
			case SELECT_LOGIN:
				view = select_login();
				break;
			case LOG_IN:
				view = log_in();
				break;
			case JOIN:
				view = join();
				break;
			case MY_PAGE:
				view = my_page();
				break;
			case MY_INFO:
				view = my_info();
				break;
			case BYEBYE:
				view = byebye();
				break;
			case MODIFY_MY_INFO:
				view = modify_my_info();
				break;
			case FIND:
				view = find();
				break;
			case FIND_ID:
				view = find_id();
				break;
			case FIND_PW:
				view = find_pw();
				break;
			case RESET_PW:
				view = reset_pw();
				break;

			default:
				return view;
			}
		}
	}

	private View reset_pw() {
		MemberVo member = (MemberVo) Controller.sessionStorage.get("member_reseting_pw");
		String pw = ScanUtil.nextLine(" 재설정할 비밀번호 : ");
		memberService.reset_pw(pw, member.getMem_no());
		System.out.println("비밀번호 재설정 완료");
		Controller.removeHistory();
		return View.MEMBER;
	}

	private View find_pw() {
		String id = ScanUtil.nextLine(" id : ");
		print_find_pw(id);
		MemberVo member = memberService.find_pw(id);
		if (member == null) {
			System.out.println("존재하지 않는 id입니다.");
		} else if (member.getMem_delyn().equals("y") || member.getMem_delyn().equals("Y")) {
			System.out.println("탈퇴한 사용자 입니다.");
		} else {
			System.out.println("본인 확인 질문 : " + member.getMem_idque());
			String idAns = ScanUtil.nextLine("답변 : ");
			if (member.getMem_idans().equals(idAns)) {
				Controller.sessionStorage.put("member_reseting_pw", member);
				return View.RESET_PW;
			}
		}
		Controller.removeHistory();
		find_pw();
		return null;
	}

	private View find_id() {
		String name = "";
		String phone = "";
		print_find_id(name, phone);
		name = ScanUtil.nextLine(" 이름 : ");
		print_find_id(name, phone);
		phone = ScanUtil.nextLine(" 전화번호 : ");
		List<Object> param = new ArrayList();
		param.add(name);
		param.add(phone);
		MemberVo member = memberService.find(param);
		if (member == null) {
			print_user_not_found();
			switch (ScanUtil.nextInt("선택 >> ")) {
			case 0 :
				return Controller.goBack();
			case 9 :
				return View.HOME;
			default:
				Controller.removeHistory();
				return View.FIND_ID;
			}
		} else if (member.getMem_delyn().equals("y") || member.getMem_delyn().equals("Y")) {
			print_user_gone();
			switch (ScanUtil.nextInt("선택 >> ")) {
			case 0 :
				return Controller.goBack();
			case 9 :
				return View.HOME;
			default:
				Controller.removeHistory();
				return View.FIND_ID;
			}
		} else {
			String id = member.getMem_id();
			print_found_id(id,name,phone);
		}
		while (true) {
			int select = ScanUtil.nextInt("선택 >> ");
			switch (select) {
			case 1:
				return View.LOG_IN;
			case 2:
				return View.FIND_PW;
			case 9 :
				return View.HOME;
			case 0:
				return Controller.goBack();
			default:
				print_wrong_input();
				break;
			}
		}
	}

	private View find() {
		print_find();
		int select = ScanUtil.nextInt("선택 >> ");
		switch (select) {
		case 1:
			return View.FIND_ID;
		case 2:
			return View.FIND_PW;
		case 9:
			return View.HOME;
		case 0:
			return Controller.goBack();

		default:
			break;
		}
		return null;
	}

	private View byebye() {
		MemberVo member = (MemberVo) Controller.sessionStorage.get("log_in_member");
		print_byebye();
		int select = ScanUtil.nextInt("메뉴 선택 >> ");
		switch (select) {
		case 1:
			String pw = ScanUtil.nextLine("비밀번호 입력 : ");
			if (member.getMem_pw().equals(pw)) {
				print_goodbye();
				Controller.sessionStorage.remove("log_in_member");
				Controller.pageHistory.clear();
				return View.HOME;
			} else {
				System.out.println("비밀 번호가 틀렸습니다.");
				View view = askBack();
				if (view == null) {
					Controller.removeHistory();
					return View.BYEBYE;
				} else
					return view;
			}
		case 9:
			return View.ADD_REVIEW.HOME;
		case 0:
			return Controller.goBack();
		default:
			byebye();
		}
		return null;
	}

	private View modify_my_info() {
		// 현재 로그인된 회원 있는지 확인
		if (Controller.sessionStorage.get("log_in_member") == null) {
			print_wrong_acess();
			return Controller.goBack();
		}
		MemberVo member = (MemberVo) Controller.sessionStorage.get("log_in_member");
		// 비밀번호 확인
		print_ask_pw();
		String pw = ScanUtil.nextLine("pw : ");
		List<Object> param = new ArrayList();
		param.add(member.getMem_id());
		param.add(pw);
		MemberVo memcheck = memberService.log_in(param);
		if (memcheck == null) {
			System.out.println("비밀번호가 틀렸습니다.");
			return Controller.goBack();
		}

		// 회원 정보 수정
		print_modify_my_info();
		int select = ScanUtil.nextInt("메뉴 선택 >> ");
		switch (select) {
		case 1:
			String new_pw = ScanUtil.nextLine("pw : ");
			memberService.update_pw(new_pw, member.getMem_no());
			break;
		case 2:
			String name = ScanUtil.nextLine("name : ");
			memberService.update_name(name, member.getMem_no());
			break;
		case 3:
			String phone = ScanUtil.nextLine("phone : ");
			memberService.update_phone(phone, member.getMem_no());
			break;
		case 4:
			String nick = ScanUtil.nextLine("닉네임 : ");
			memberService.update_nick(nick, member.getMem_no());
			break;
		case 5:
			String idque = ScanUtil.nextLine("본인 확인 질문 : ");
			memberService.update_idque(idque, member.getMem_no());
			break;
		case 6:
			String idans = ScanUtil.nextLine("답변 : ");
			memberService.update_idans(idans, member.getMem_no());
			break;
		default:
			modify_my_info();
		}

		member = memberService.update(member.getMem_no());
		Controller.sessionStorage.replace("log_in_member", member);
		return View.MY_INFO; // 완료 후 내 정보 보기
	}

	private View my_info() {
		// 로그인 된 멤버 있는지 확인
		if (Controller.sessionStorage.get("log_in_member") == null) {
			print_wrong_acess();
			return Controller.goBack();
		}
		MemberVo member = (MemberVo) Controller.sessionStorage.get("log_in_member");
		print_my_info(member);
		int select = ScanUtil.nextInt("메뉴 선택 >> ");
		switch (select) {
		case 1:
			return View.MODIFY_MY_INFO;
		case 9:
			return View.HOME;
		case 0:
			return Controller.goBack();
		default:
			Controller.pageHistory.remove(Controller.pageHistory.size());
			return View.MY_INFO;
		}
	}

	private View member() {
		if (Controller.sessionStorage.get("log_in_member") != null) {
			return View.MY_PAGE;
		} else
			return View.SELECT_LOGIN;
	}

	private View select_login() {
		if (Controller.sessionStorage.get("log_in_member") != null) {
			return Controller.goBack();
		}
		print_member();
		int select = ScanUtil.nextInt("메뉴 선택 >> ");
		switch (select) {
		case 1:
			return View.LOG_IN;
		case 2:
			return View.JOIN;
		case 3:
			return View.FIND;
		case 9:
			return View.HOME;
		case 0:
			return Controller.goBack();
		default:
			Controller.pageHistory.remove(Controller.pageHistory.size());
			return View.SELECT_LOGIN;
		}
	}

	public View join() {

		View view;

		String id = "";
		String pw = "";
		String name = "";
		String phone = "";
		String nick = "";
		String idQue = "";
		String idAns = "";
		
		while (true) {
			print_join(id,pw,name,phone,nick,idQue,idAns);
			id = ScanUtil.nextLine("id : ");
			if (memberService.idcheck(id))
				break;
			else {
				print_join_error(id,pw,name,phone,nick,idQue,idAns);
				id = "";
				view = askBack();
				if (view != null) {
					Controller.removeHistory();
					return view;
				}
			}
		}
		
		while (true) {
			print_join(id,pw,name,phone,nick,idQue,idAns);
			pw = ScanUtil.nextLine("pw : ");
			if (memberService.pwcheck(pw))
				break;
			else {
				print_join_error(id,pw,name,phone,nick,idQue,idAns);
				pw = "";
				view = askBack();
				if (view != null) {
					Controller.removeHistory();
					return view;
				}
			}
		}

		while (true) {
			print_join(id,pw,name,phone,nick,idQue,idAns);
			name = ScanUtil.nextLine("name : ");
			if (memberService.namecheck(name))
				break;
			else {
				print_join_error(id,pw,name,phone,nick,idQue,idAns);
				name = "";
				view = askBack();
				if (view != null) {
					Controller.removeHistory();
					return view;
				}
			}
		}

		while (true) {
			print_join(id,pw,name,phone,nick,idQue,idAns);
			phone = ScanUtil.nextLine("전화번호 : ");
			if (memberService.phonecheck(phone))
				break;
			else {
				print_join_error(id,pw,name,phone,nick,idQue,idAns);
				phone = "";
				view = askBack();
				if (view != null) {
					Controller.removeHistory();
					return view;
				}
			}
		}

		while (true) {
			print_join(id,pw,name,phone,nick,idQue,idAns);
			nick = ScanUtil.nextLine("닉네임 : ");
			if (memberService.nickcheck(nick))
				break;
			else {
				print_join_error(id,pw,name,phone,nick,idQue,idAns);
				nick = "";
				view = askBack();
				if (view != null) {
					Controller.removeHistory();
					return view;
				}
			}
		}

		print_join(id,pw,name,phone,nick,idQue,idAns);
		idQue = ScanUtil.nextLine("본인 확인 질문 : ");
		
		print_join(id,pw,name,phone,nick,idQue,idAns);
		idAns = ScanUtil.nextLine("본인 확인 질문 답변 : ");

		List<Object> param = new ArrayList();
		param.add(id);
		param.add(pw);
		param.add(name);
		param.add(phone);
		param.add(nick);
		param.add(idQue);
		param.add(idAns);

		memberService.join(param);
		print_join_sucess();

		return Controller.goBack();
	}

	

	public View log_in() {
		print_login_for_id();
		List<Object> param = new ArrayList();
		String id = ScanUtil.nextLine("id : ");
		print_login_for_pw(id);
		String pw = ScanUtil.nextLine("pw : ");
		param.add(id);
		param.add(pw);
		MemberVo member = memberService.log_in(param);
		if (member == null) {
			while (true) {
				print_login_fail("id 혹은 pw가 잘못되었습니다.",id,pw);
				switch (ScanUtil.nextInt("선택 >> ")) {
				case 1:
					Controller.removeHistory();
					return View.LOG_IN;
				case 0:
					return Controller.goBack();
				case 9 :
					return View.HOME;
				default:
					break;
				}
			}
		} else if (member.getMem_delyn().equals("y") || member.getMem_delyn().equals("Y")) {
			while (true) {
				print_login_fail("탈퇴한 회원입니다.",id,pw);
				switch (ScanUtil.nextInt("선택 >> ")) {
				case 1:
					Controller.removeHistory();
					return View.LOG_IN;
				case 0:
					return Controller.goBack();
				case 9 :
					return View.HOME;
				default:
					break;
				}
			}
		}
		Controller.sessionStorage.put("log_in_member", member);
		print_login_sucess(member.getMem_nick());
		return Controller.goBack();
	}

	/**
	 * 로그인/ 회원가입 오류 시 재시도 혹은 뒤로가기 재시도 선택 시 null 뒤로가기 선택시 직전 view 출력
	 * 
	 * @return
	 */
	private View askBack() {
		System.out.println("\t   1. 재시도            9. 홈           0. 뒤로가기");
		printBar();
		int select = ScanUtil.nextInt("선택 >> ");
		switch (select) {
		case 0:
			return Controller.goBack();
		case 9:
			return View.HOME;
		default:
			return null;
		}

	}

	public View my_page() {
		if (Controller.sessionStorage.get("log_in_member") == null) {
			print_wrong_acess();
			return Controller.goBack();
		}
		print_my_page();
		int select = ScanUtil.nextInt("선택 >> ");
		switch (select) {
		case 1:
			return View.MY_INFO;
		case 2:
		case 3:
			return View.BYEBYE;
		case 4:
			Controller.sessionStorage.remove("log_in_member");
			Controller.pageHistory.clear();
			print_logout();
			return View.HOME;
		case 9:
			return View.HOME;
		case 0:
			return Controller.goBack();
		default:
			Controller.removeHistory();
			my_page();
		}
		return null;
	}

}
