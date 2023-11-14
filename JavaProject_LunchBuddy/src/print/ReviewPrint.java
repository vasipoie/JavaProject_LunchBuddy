package print;

public class ReviewPrint {
	
	public void printBar() {
		System.out.println("-----------------------------------------------");
	}

	public void printLn(int num) {
		for (int i = 0; i < num; i++)
			System.out.println();
	}

	public void printReviewHome() {
		printBar();
		System.out.println("1. 리뷰 등록");
		System.out.println("2. 리뷰 상세보기");
		System.out.println("3. 리뷰 리스트");
		printBar();
	}
	
}
