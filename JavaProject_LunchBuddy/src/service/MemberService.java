package service;

import java.util.List;

import dao.MemberDao;
import vo.MemberVo;

public class MemberService {
	
	MemberDao dao = new MemberDao();
	private static MemberService singleTon = null;

	public MemberService() {
	};

	public MemberService getInstance() {
		if (singleTon == null) {
			singleTon = new MemberService();
		}
		return singleTon;
	}

	public boolean idcheck(String id) {
		// 5자 이상 15자 이하의 영어, 숫자
		if(!(id.matches("^[a-zA-Z0-9]*$"))) { //영어, 숫자 아닌 문자가 입력
			System.out.println("id는 영어 혹은 숫자만 입력 가능합니다.");
		}else if(id.length()<5||15<id.length()) { //입력 된 id의 길이가 5~15를 벗어남
			System.out.println("id는 5~15자로 입력해주세요.");
		}else if(dao.idcheck(id)!=null) { // 기존에 있는 id인지 확인
			System.out.println("사용 중인 id입니다.");
		} else return true;
		return false;
	}

	public boolean pwcheck(String pw) {
		// 8자 이상 15자 이하의 영어, 숫자
		if(!(pw.matches("^[a-zA-Z0-9]*$"))) { //영어, 숫자 아닌 문자가 입력
			System.out.println("pw는 영어 혹은 숫자만 입력 가능합니다.");
		}else if(pw.length()<8||15<pw.length()) { //입력 된 pw의 길이가 8~15를 벗어남
			System.out.println("pw는 8~15자로 입력해주세요.");
		}else return true;
		return false;
	}

	public boolean namecheck(String name) {
		// 2~10자의 한글
		if(!(name.matches("^[가-힣]*$"))) {
			System.out.println("이름은 한글만 입력 가능합니다.");
		}else if(name.length()<2||name.length()>10) {
			System.out.println("이름은 2~10자만 입력 가능합니다.");
		}else return true;
		return false;
	}

	public boolean phonecheck(String phone) {
		// 10~15자 숫자
		if(!(phone.matches("^[0-9]*$"))) {
			System.out.println("숫자만 입력하세요.");
		}else if(phone.length()<10||phone.length()>15) {
			System.out.println("10~15자의 숫자를 입력하세요.");
		}else return true;
		return false;
	}

	public boolean nickcheck(String nick) {
		//  2자 이상 5자 이하의 한글
		if(!nick.matches("^[a-zA-Z0-9가-힣]*$")) {
			System.out.println("한글, 영어, 숫자만 입력해주세요.");
		}else if(nick.length()<2||nick.length()>5) {
			System.out.println("닉네임은 2~5자입니다.");
		}else return true;
		return false;
	}

	public void join(List<Object> param) {
		
		dao.join(param);
		
	}

	public MemberVo log_in(List<Object> param) {
		return dao.log_in(param);
	}

	public void update_pw(String pw, String mem_no) {
		dao.update_pw(pw,mem_no);
	}

	public void update_name(String name, String mem_no) {
		dao.update_name(name,mem_no);
	}

	public void update_phone(String phone, String mem_no) {
		dao.update_phone(phone,mem_no);
	}

	public void update_nick(String nick, String mem_no) {
		dao.update_nick(nick,mem_no);
	}

	public void update_idque(String idque, String mem_no) {
		dao.update_idque(idque,mem_no);
	}

	public void update_idans(String idans, String mem_no) {
		dao.update_idans(idans,mem_no);
	}

	public void byebye(MemberVo member) {
		dao.byebye(member.getMem_no());
	}

	public MemberVo update(String mem_no) {
		return dao.update(mem_no);
	}

	public MemberVo find(List<Object> param) {
		return dao.find(param);
	}

	public MemberVo find_pw(String id) {
		return dao.find_pw(id);
	}

	public void reset_pw(String pw, String mem_no) {
		dao.reset_pw(pw,mem_no);
	}
	
	

}
