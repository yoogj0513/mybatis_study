package mybatis_study;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calendar newDate = GregorianCalendar.getInstance();
		System.out.printf("%tF",newDate);
		newDate.set(1990, 2, 28);
		System.out.printf("%tF",newDate);
	}

}
