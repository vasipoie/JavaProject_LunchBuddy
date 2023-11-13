package service;

import dao.MemberDao;

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
			return false;
		}else if(id.length()<5||15<id.length()) { //입력 된 id의 길이가 5~15를 벗어남
			System.out.println("id는 5~15자로 입력해주세요.");
			return false;
		}else if(dao.idcheck(id)!=null) { // 기존에 있는 id인지 확인
			System.out.println("사용 중인 id입니다.");
			return false;
		}
		return true;
	}

	public boolean pwcheck(String pw) {
		// 8자 이상 15자 이하의 영어, 숫자
		if(!(pw.matches("^[a-zA-Z0-9]*$"))) { //영어, 숫자 아닌 문자가 입력
			System.out.println("pw는 영어 혹은 숫자만 입력 가능합니다.");
			return false;
		}else if(pw.length()<8||15<pw.length()) { //입력 된 pw의 길이가 8~15를 벗어남
			System.out.println("pw는 8~15자로 입력해주세요.");
			return false;
		}else
		return true;
	}
	
	

}
