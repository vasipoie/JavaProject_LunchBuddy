package vo;

import lombok.Data;

@Data
public class BFVo {
	 private String bf_no;
	 private String mem_no;
	 private String bf_name;
	 private String bf_cont;
	 private int bf_num;
	 private String bf_date;
	 private String bf_delyn;
	 private String res_no;
	 private String mem_nick;
	 private String res_name;
	 private int part_num;
	 
	@Override
	public String toString() {
		return "[ " + res_name + " ]  " + bf_date
				+ "\n\t\t" + mem_nick + " - " + bf_name
				+ " ( " + part_num + " / " + bf_num + " )" ;
	}

	 
	 
}
