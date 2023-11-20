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
		return menu_name + "   " + menu_price
				+ "원";
	}
}
