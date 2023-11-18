package util;

import java.util.Scanner;

public class ScanUtil {
	// 스캐너를 손쉽게 사용할 수 있는 static 메서드를 가지고있음
	static Scanner sc = new Scanner(System.in);
	
	//(String print) : 강제로 안내문구 넣게 함
	public static String nextLine(String print) {
		System.out.print(print);
		return nextLine();
	}
	
	 //static:사용자가 사용 못하게 막음
	 static String nextLine() {
		return sc.nextLine();
	 }
	 
	public static int nextInt(String print) {
		System.out.print(print);
		return nextInt();
	}
	
	 static int nextInt() {
		while(true) {
			try {
				int result = Integer.parseInt(sc.nextLine());
				return result;
			}catch (NumberFormatException e) {
				System.out.println("숫자만 입력해주세요.");
				System.out.print("메뉴 선택>>");
			}
		}
	}
	
	//Restaurant - 식당등록 - 카테고리
	public static int nextIntC(String print) {
		System.out.print(print);
		return nextIntC();
	}

	 static int nextIntC() {
		 while(true) {
			try {
				int result = Integer.parseInt(sc.nextLine());
				return result;
			}catch (NumberFormatException e) {
				System.out.println("잘못 입력했습니다. 다시 입력해주세요");
				System.out.print("카테고리 번호 : ");
			}
		 }
	 }
	 
	//Restaurant - 식당등록 - 가격
	public static int nextIntP(String print) {
		System.out.print(print);
		return nextIntP();
	}
	 
	 static int nextIntP() {
		 while(true) {
			 try {
				 int result = Integer.parseInt(sc.nextLine());
				 return result;
			 }catch (NumberFormatException e) {
				 System.out.println("숫자만 입력해주세요");
				 System.out.print("가격 : ");
			 }
		 }
	 }
	 
	//Restaurant - 식당등록 - 가격
	public static String nextLineP(String print) {
		System.out.print(print);
		return nextLineP();
	}
		
	 static String nextLineP() {
		 while (true) {
		 	 try {
		 		 String result = sc.nextLine();
		 		 return result;
		 	 } catch (NumberFormatException e) {
		 		 System.out.println("숫자만 입력해주세요");
		 		 System.out.print("가격 : ");
		 	 }
		 }
	 }
	
	//Restaurant - 식당등록 - 예약가능여부
	public static int nextIntB(String print) {
		System.out.print(print);
		return nextIntB();
	}

	 static int nextIntB() {
		 while(true) {
			 try {
				 int result = Integer.parseInt(sc.nextLine());
				 return result;
			 }catch (NumberFormatException e) {
				 System.out.println("숫자 1,2,3 중 한 개를 입력해주세요");
				 int book = ScanUtil.nextInt("예약가능여부(1.가능/2.불가능/3.미확인) : ");
				 return sc.nextInt();
			 }
		 }
	 }

	//admin - 등록대기중식당정보수정 - 이동시간
	public static int nextIntW(String print) {
		System.out.print(print);
		return nextIntW();
	}
	 
	 static int nextIntW() {
		 while(true) {
			 try {
				 int result = Integer.parseInt(sc.nextLine());
				 return result;
			 }catch (NumberFormatException e) {
				 System.out.println("숫자만 입력해주세요");
				 System.out.print("이동시간 : ");
			 }
		 }
	 }
 
}
