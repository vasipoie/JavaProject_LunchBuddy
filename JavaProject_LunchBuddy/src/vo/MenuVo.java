package vo;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class MenuVo {

	 private String menu_no;
	 private String menu_name;
	 private String menu_price;
	 private String menu_postyn;
	 private String res_no;
	 
	@Override
	public String toString() {
		return "MenuVo [menu_no=" + menu_no + ", menu_name=" + menu_name + ", menu_price=" + menu_price
				+ ", menu_postyn=" + menu_postyn + ", res_no=" + res_no + "]";
	}
}
