package print;

import vo.MemberVo;

public class MemberPrint extends Print{

	public void print_member() {
		System.out.println("로그인");
		printBar();
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("3. 아이디, 비밀번호 찾기");
		System.out.println("0. 뒤로 가기");
		printLn(2);
		printBar();
	}
	
	public void print_join() {
		printLn(7);
		System.out.println("회원가입");
		printBar();
	}
	
	public void print_login() {
		printLn(7);
		System.out.println("로그인");
		printBar();
	}
	
	public void print_my_page() {
		System.out.println("MY PAGE");
		printBar();
		System.out.println(" 1. 내 정보 보기");
		System.out.println(" 2. 내가 쓴 리뷰 보기");
		System.out.println(" 3. 탈퇴");
		System.out.println(" 9. 홈");
		System.out.println(" 0. 뒤로가기");
		printLn(1);
		printBar();
	}
	
	public void print_my_info(MemberVo member) {
		System.out.println("MY INFO");
		printBar();
		System.out.println("id \t: "+member.getMem_id());
		System.out.println("이름 \t: "+member.getMem_name());
		System.out.println("전화번호 \t: "+member.getMem_phone());
		System.out.println("닉네임 \t: "+member.getMem_nick());
		System.out.println("본인 확인 질문 \n: "+member.getMem_idque());
		System.out.println("답변 \t: "+member.getMem_idans());
		printBar();
		System.out.println("1. 회원 정보 수정\t9. 홈\t0. 뒤로가기");
	}
	
	public void print_ask_pw() {
		printLn(7);
		System.out.println("pass word를 입력하세요.");
		printBar();
	}
	
	public void print_modify_my_info() {
		System.out.println("내 정보 수정");
		printBar();
		System.out.println("1. 비밀번호 수정");
		System.out.println("2. 이름 수정");
		System.out.println("3. 전화번호 수정");
		System.out.println("4. 닉네임 수정");
		System.out.println("5. 본인 확인 질문 수정");
		System.out.println("6. 답변 수정");
		System.out.println("0. 뒤로가기");
		printBar();
	}
	
	public void print_byebye() {
		System.out.println("탈퇴");
		printBar();
		System.out.println("\n정말로 탈퇴하시겠습니까?");
		System.out.println("탈퇴하셔도 리뷰 기록은 남게 되며 탈퇴 후 리뷰 기록 삭제는 불가합니다.\n");
		printBar();
		System.out.println("1. 탈퇴");
		System.out.println("0. 뒤로가기");
		printBar();
	}
	
	public void print_find() {
		System.out.println("id, pw 찾기");
		printBar();
		System.out.println("1. id 찾기");
		System.out.println("2. pw 찾기");
		System.out.println("9. HOME");
		System.out.println("0. 뒤로가기");
		printLn(3);
		printBar();
		
	}

	public void print_find_id() {
		System.out.println("id 찾기");
		printBar();
	}
	
	public void print_find_pw() {
		System.out.println("pw 찾기");
		printBar();
	}

}
