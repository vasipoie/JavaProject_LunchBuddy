package print;

import java.util.List;

public class Print {
	
	public void printVar() {
		System.out.println("-----------------------------------------------");
	}
	public void printLn(int num) {
		for(int i=0; i<num; i++)
			System.out.println();
	}

	public void printHome() {
		printVar();
		System.out.println("1. 관리자");
		System.out.println("2. 일반 회원");
		printLn(5);
		printVar();
	}
	
}
