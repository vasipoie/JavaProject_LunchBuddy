package vo;

import java.util.Date;

import lombok.Data;

@Data
public class ReviewVo {
	 private String rev_no;
	 private int rev_star;
	 private String rev_cont;
	 private String rev_postyn;	 
	 private String res_no;	
	 private String mem_no;
	 private String rev_date;
	 private String res_name;
	 private String mem_nick; 
	 
	@Override
	public String toString() {
		return "[" + res_name + "]  " + stars(rev_star) + " (" + rev_star + ")\n" +
				mem_nick + ")  " + rev_cont;
	}	 
	 

	public String stars(int num) {
		String stars = "";
		for (int i = 0; i < 10; i++) {
			if (i < num)
				stars += "★";
			else
				stars += "☆";
		}
		return stars;
	}
	
	public String adminRev() {
		return "[" + res_name + "]  " + stars(rev_star) + " (" + rev_star + ")\n" +
				mem_nick + ")  " + rev_cont+"\n"+
				"게시여부 : "+rev_postyn;
	}
	 
}
