package print;

import controller.Controller;
import vo.MemberVo;

public class MemberPrint extends Print {

	public void print_member() {
		printBar();
		System.out.println("\t\t\t\t           ☆★점심 친구☆★\t\t\t\t");
		printLn(2);
		System.out.println("\t\t\t\t1. 로그인");
		System.out.println("\t\t\t\t2. 회원가입");
		System.out.println("\t\t\t\t3. id, pw 찾기\t");
		printLn(6);
		System.out.println("\t\t\t\t9. 홈");
		System.out.println("\t\t\t\t0. 뒤로 가기");
		System.out.println();
		printBar();
	}

	public void print_login_sucess(String nick) {
		printBar();
		printLn(6);
		System.out.println("\t\t\t           " + nick + "님 로그인이 완료되었습니다.");
		System.out.println("\t\t\t\t          반가워용~");
		printLn(7);
		printBar();
		pause();

	}

	public void print_join(String id, String pw, String name, String phone, String nick, String idQue, String idAns) {
		printBar();
		String pws = "";
		for (int i = 0; i < pw.length(); i++)
			pws += "*";
		System.out.println("\t\t\t\t           회원가입\t\t\t\t");
		printLn(2);
		System.out.println("\t\t   - id\t\t: " + id);
		System.out.println("\t\t   - pw\t\t: " + pws);
		System.out.println("\t\t   - 이름\t\t: " + name);
		System.out.println("\t\t   - 전화번호\t: " + phone);
		System.out.println("\t\t   - 닉네임\t: " + nick);
		System.out.println("\t\t   - 본인 확인 질문\t: " + idQue);
		System.out.println("\t\t   - 답변\t\t: " + idAns);
		printLn(5);
		printBar();
	}

	public void print_join_error(String id, String pw, String name, String phone, String nick, String idQue,
			String idAns) {
		String pws = "";
		for (int i = 0; i < pw.length(); i++)
			pws += "*";
		String error = (String) Controller.sessionStorage.get("error message");
		printBar();
		System.out.println("\t\t\t\t           회원가입\t\t\t\t");
		printLn(2);
		System.out.println("\t\t   - id\t\t: " + id);
		System.out.println("\t\t   - pw\t\t: " + pws);
		System.out.println("\t\t   - 이름\t\t: " + name);
		System.out.println("\t\t   - 전화번호\t: " + phone);
		System.out.println("\t\t   - 닉네임\t: " + nick);
		System.out.println("\t\t   - 본인 확인 질문\t: " + idQue);
		System.out.println("\t\t   - 답변\t\t: " + idAns);
		printLn(2);
		System.out.println("\t\t\t\t" + error);
		System.out.println();
//		System.out.println("\t   1. 재시도            9. 홈           0. 뒤로가기");
//		printBar();
	}

	public void print_join_sucess() {
		printBar();
		printLn(4);
		System.out.println("\t\t\t\t    /\\_/\\  \r\n" + "\t\t\t\t   ( o.o ) \r\n" + "\t\t\t\t    > ^ <");
		System.out.println("\t\t\t\t가입이 완료되었습니다");
		System.out.println("\t\t\t\t     로그인해주세요");
		System.out.println("\t\t\t\t          •‧:❤️:‧:❤️:‧•");
		printLn(4);
		printBar();
		System.out.println();
		pause();

	}

	public void print_login_for_id() {
		printBar();
		System.out.println("\t\t\t\t           로그인\t\t\t\t");
		printLn(5);
		System.out.println("\t   - id\t\t: ");
		System.out.println("\t   - pw\t\t: ");
		printLn(7);
		printBar();
	}

	public void print_login_for_pw(String id) {
		printBar();
		System.out.println("\t\t\t\t           로그인\t\t\t\t");
		printLn(5);
		System.out.println("\t   - id\t\t: " + id);
		System.out.println("\t   - pw\t\t: ");
		printLn(7);
		printBar();
	}

	public void print_login_fail(String why, String id, String pw) {
		String pws = "";
		for (int i = 0; i < pw.length(); i++)
			pws += "*";
		printBar();
		System.out.println("\t\t\t\t         로그인실패\t\t\t\t");
		printLn(5);
		System.out.println("\t\t\t   - id\t\t: " + id);
		System.out.println("\t\t\t   - pw\t\t: " + pws);
		printLn(4);
		System.out.println("\t\t\t   " + why + " 다시 시도해 주세요.");
//		System.out.println("\t       다시 시도해 주세요.");
		System.out.println();
		System.out.println("\t\t\t  1. 재시도           9. 홈           0. 뒤로가기");
		printBar();
	}

	public void print_my_page() {
		printBar();
		System.out.println("\t\t\t\t    MY PAGE\t\t\t\t");
		printLn(3);
		System.out.println("\t\t\t\t 1. 내 정보 보기");
		System.out.println("\t\t\t\t 2. 내가 쓴 리뷰 보기");
		System.out.println("\t\t\t\t 3. 탈퇴");
		System.out.println("\t\t\t\t 4. 로그아웃");
		System.out.println("\t\t\t\t 9. 홈");
		System.out.println("\t\t\t\t 0. 뒤로가기");
		printLn(5);
		printBar();
	}

	// ·̇·̣̇̇·̣̣̇·̣̇̇·̇ •๑♡๑•୨୧┈┈┈୨୧•๑♡๑• ·̇·̣̇̇·̣̣̇·̣̇̇·̇
	public void print_my_info(MemberVo member) {
		printBar();
		System.out.println("\t\t\t\t    MY INFO\t\t\t\t");
		printLn(3);
		System.out.println("\t\t\t• id \t: " + member.getMem_id());
		System.out.println("\t\t\t• 이름 \t: " + member.getMem_name());
		System.out.println("\t\t\t• 전화번호 \t: " + member.getMem_phone());
		System.out.println("\t\t\t• 닉네임 \t: " + member.getMem_nick());
		System.out.println("\t\t\t• 본인 확인 질문\t: " + member.getMem_idque());
		System.out.println("\t\t\t• 답변 \t: " + member.getMem_idans());
		printLn(4);
		System.out.println("\t\t     1. 내 정보 수정            9. 홈                 0. 뒤로가기");
		printBar();
	}

	public void print_ask_pw() {
		printBar();
		System.out.println("\t\t\t\t           로그인\t\t\t\t");
		printLn(6);
		System.out.println("\t\t\t       pass word를 입력하세요. ");
		printLn(7);
		printBar();
	}

	public void print_modify_my_info() {
		printBar();
		System.out.println("\t\t\t\t   내 정보 수정\t\t\t\t");
		printLn(3);
		System.out.println("\t\t\t\t1. 비밀번호 수정");
		System.out.println("\t\t\t\t2. 이름 수정");
		System.out.println("\t\t\t\t3. 전화번호 수정");
		System.out.println("\t\t\t\t4. 닉네임 수정");
		System.out.println("\t\t\t\t5. 본인 확인 질문 수정");
		System.out.println("\t\t\t\t6. 답변 수정");
		System.out.println("\t\t\t\t9. 홈");
		System.out.println("\t\t\t\t0. 뒤로가기");
		printLn(3);
		printBar();
	}

	public void print_byebye() {
		printBar();
		System.out.println("\t\t\t\t              탈퇴\t\t\t\t");
		printLn(5);
		System.out.println("\t\t\t               정말로 탈퇴하시겠습니까?");
		System.out.println("\t\t탈퇴하셔도 리뷰 기록은 남게 되며 탈퇴 후 리뷰 기록 삭제는 불가합니다.");
		printLn(5);
		System.out.println("\t\t\t1. 탈퇴하기             9. 홈            0.뒤로 가기");
		printBar();
	}

	public void print_find() {
		printBar();
		System.out.println("\t\t\t\t   id, pw 찾기\t\t\t\t");
		printLn(5);
		System.out.println("\t\t\t\t  1. id 찾기");
		System.out.println("\t\t\t\t  2. pw 재설정");
		System.out.println("\t\t\t\t  9. HOME");
		System.out.println("\t\t\t\t  0. 뒤로가기");
		printLn(5);
		printBar();

	}

	public void print_find_id(String name, String phone) {
		printBar();
		System.out.println("\t\t\t\t id 찾기");
		printLn(6);
		System.out.println("\t\t\t     이름\t : " + name);
		System.out.println("\t\t\t     전화번호\t : " + phone);
		printLn(6);
		printBar();
	}

	/**
	 * 존재하지 않는 사용자 입니다. (1재시도 9홈 0뒤로가기)
	 * 
	 * @param title
	 */
	public void print_user_not_found(String title) {
		printBar();
		System.out.println("\t\t\t\t    " + title);
		printLn(6);
		System.out.println("\t\t\t            존재하지 않는 사용자 입니다.");
		printLn(6);
		System.out.println("\t\t\t1. 재시도             9. 홈            0.뒤로 가기");
		printBar();

	}

	/**
	 * 탈퇴한 사용자 입니다. (1재시도 9홈 0뒤로가기)
	 * 
	 * @param title
	 */
	public void print_user_gone(String title) {
		printBar();
		System.out.println("\t\t\t\t    " + title);
		printLn(4);
		System.out.println("\t\t\t\t    /\\_/\\  \r\n" + "\t\t\t\t   ( `ω´ )\r\n" + "\t\t\t\t    >   <");
		System.out.println("\t\t\t                  탈퇴한 사용자 입니다.");
		printLn(5);
		System.out.println("\t\t\t1. 재시도             9. 홈            0.뒤로 가기");
		printBar();

	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param phone
	 */
	public void print_found_id(String id, String name, String phone) {
		printBar();
		System.out.println("\t\t\t\t    id 찾기");
		printLn(5);
		System.out.println("\t\t\t            이름       : " + name);
		System.out.println("\t\t\t            전화번호 : " + phone);
		printLn(1);
		System.out.println("\t\t\t " + name + "님의 id는  " + id.substring(0, id.length() - 3) + "*** 입니다.");
		printLn(4);
		System.out.println("\t\t1. 로그인          2. 비밀번호 재설정           9. 홈            0.뒤로 가기");
		printBar();

	}

	public void print_find_pw(String id, String idQue) {
		printBar();
		System.out.println("\t\t\t\t pw 재설정");
		printLn(6);
		System.out.println("\t\t\t     id\t : " + id);
		if (idQue.length() == 0) {
			printLn(2);
		} else {
			System.out.println();
			System.out.println("\t\t\t본인 확인 질문 : " + idQue);
		}
		printLn(5);
		printBar();
	}

	/**
	 * 잘못된 답변입니다. (1이id재시도 2다른id시도 9홈 0뒤로가기)
	 * 
	 * @param id
	 * @param idQue
	 */
	public void print_wrong_ans(String id, String idQue) {
		printBar();
		System.out.println("\t\t\t\t pw 재설정");
		printLn(6);
		System.out.println("\t\t\t     id\t : " + id);
		if (idQue.length() == 0) {
			printLn(2);
		} else {
			System.out.println();
			System.out.println("\t\t\t본인 확인 질문 : " + idQue);
		}
		printLn(2);
		System.out.println("\t\t\t\t  잘못된 답변입니다.");
		System.out.println();
		System.out.println("\t\t\t1. 이 id 재시도             2. 다른 id로 찾기             9. 홈            0.뒤로 가기");
		printBar();

	}

	/**
	 * 비밀번호 재설정
	 * @param id
	 * @param pw
	 * @param b
	 */
	public void print_reset_pw(String id, String pw, boolean b) {
		String pwBlind = "";
		for(int i=0; i<pw.length(); i++) pwBlind += "*";
		printBar();
		System.out.println("\t\t\t\t pw 재설정");
		printLn(6);
		System.out.println("\t\t\t     id\t : " + id);
		System.out.println();
		System.out.println("\t\t\t     pw\t : " + pwBlind);
		if(b) {
			printLn(5);
		}else {
			printLn(2);
			System.out.println("\t\t\t\t입력하신 비밀번호와 다릅니다");
			System.out.println();
			System.out.println("\t\t\t1. 재시도             9. 홈            0.뒤로 가기");
		}
		printBar();

	}
	
	public void print_sucess_reseting(boolean login) {
		printBar();
		printLn(5);
		System.out.println("\t\t\t\t    /\\_/\\  \r\n" + "\t\t\t\t   ( o.o ) \r\n" + "\t\t\t\t    > ^ <");
		System.out.println("\t\t\t\t  pw 재설정 완료!");
		if(login) System.out.println();
		else System.out.println("\t\t\t\t     로그인해주세요");
		System.out.println("\t\t\t\t          •‧:❤️:‧:❤️:‧•");
		printLn(4);
		printBar();
		System.out.println();
		pause();
	}
	
	public void wrong_pw(String title) {
		printBar();
		System.out.println("\t\t\t\t    " + title);
		printLn(5);
		System.out.println("\t\t\t\t    /\\_/\\  \r\n" + "\t\t\t\t   ( `ω´ )\r\n" + "\t\t\t\t    >   <");
		System.out.println("\t\t\t                  비밀번호가 틀렸습니다");
		printLn(5);
		printBar();
		pause();
	}
	
	public void print_modify_one(String str) {
		printBar();
		System.out.println("\t\t\t\t         "+str+" 변경");
		printLn(7);
		System.out.println("\t\t\t\t "+str+" : ");
		printLn(6);
		printBar();
	}

	public void print_logout() {
		printBar();
		printLn(2);
		System.out.println("로그아웃 되었습니다.");
		System.out.println("잠시 후 홈으로 이동");
		printLn(3);
		printBar();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void print_goodbye() {
		printBar();
		printLn(2);
		System.out.println("Bye~!");
		System.out.println("잠시 후 홈으로 이동");
		printLn(3);
		printBar();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
