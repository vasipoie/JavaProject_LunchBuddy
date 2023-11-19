package vo;

import lombok.Data;

@Data
public class MemberVo {
	 private String mem_no;
	 private String mem_id;
	 private String mem_pw;
	 private String mem_name;
	 private String mem_phone;
	 private String mem_nick;
	 private String mem_idque;
	 private String mem_idans;
	 private String mem_delyn;
	
	 @Override
	public String toString() {
		return "id : "+mem_id+" 닉네임 : "+mem_nick+" 탈퇴여부 : " + mem_delyn;
	}
	 
}