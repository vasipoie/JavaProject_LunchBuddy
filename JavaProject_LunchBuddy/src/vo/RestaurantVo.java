package vo;

import lombok.Data;

@Data
public class RestaurantVo {

	 private String res_no;
	 private String res_name;
	 private String res_add;
	 private String res_phone;
	 private String res_bookyn;
	 private int res_walk;
	 private String res_postyn;
	 private String cat_no;
	 private String column2;
	 private String menu_name;
	 private String menu_price;
	 private int rev_star;
	 
	@Override
	public String toString() {
		return "["+res_name+"] 거리 :"+res_walk+"분 / 예약여부 : "+res_bookyn+"\n"+
				"(평점:"+rev_star+") 대표메뉴 :"+menu_name+" - "+menu_price+"원";
	}
	 
	 
	 
}
