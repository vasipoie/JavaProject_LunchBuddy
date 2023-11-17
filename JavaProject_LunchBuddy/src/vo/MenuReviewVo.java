package vo;

import lombok.Data;

@Data
public class MenuReviewVo {
	private String menu_name;
	private String menu_price;
	private String mr_cont;
	private String mr_no;
	private String menu_no;
	private String mem_no;
	private String mr_postyn;
	
	@Override
	public String toString() {
		return "(" + menu_name + ") " + menu_price + " - " + mr_cont ;
	}
	
	
}
