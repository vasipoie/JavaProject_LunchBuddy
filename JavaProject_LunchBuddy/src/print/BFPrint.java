package print;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;

public class BFPrint extends Print{
	
	public void printDate() {
		LocalDate now = LocalDate.now();
		if (LocalTime.now().getHour() >= 13) {
			for(int i = 1; i<=7; i++) {
				now = now.plusDays(i);
				System.out.println(i + ". " + now.getMonthValue() + "/"
									+ now.getDayOfMonth());
			}
		}else {
			for(int i = 1; i<=7; i++) {
				now = now.plusDays(i-1);
				System.out.println(i + ". " + now.getMonthValue() + "/"
									+ now.getDayOfMonth());
			}
		}
	}
	
	public void printDate (Calendar cal) {
		
	}
	
}
