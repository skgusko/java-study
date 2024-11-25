package chapter04;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class CalendarTest {

	public static void main(String[] args) {
//		Locale aLocale = Locale.getDefault(Locale.Category.FORMAT);
//		TimeZone tz = TimeZone.getDefault();
//		System.out.println(aLocale + ":" + tz);
		
		Calendar cal = Calendar.getInstance();
		//System.out.println(cal);
		printDate(cal);
		
		//날짜 지정 가능
		cal.set(Calendar.YEAR, 2024);
		cal.set(Calendar.MONTH, 11); //12월
		cal.set(Calendar.DATE, 25);
		printDate(cal);
		
		cal.set(2001, 3, 20); //year, month, date
		cal.add(Calendar.DATE, 100); //2001-04-20 에서 100일 뒤 날짜 
		printDate(cal);
	}

	private static void printDate(Calendar cal) {
		final String[] DAYS = {"일", "월", "화", "수", "목", "금", "토"}; //final은 대문자로 써야 함
		
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH); //0-11, +1
		int date = cal.get(Calendar.DATE);
		int day = cal.get(Calendar.DAY_OF_WEEK); //요일. 1(일)-7(토) (0 없음! 1이 일요일)
		int hours = cal.get(Calendar.HOUR);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		
		System.out.println(
				year + "-" +
				(month + 1) + "-" +
				date + " " +
				DAYS[day-1] + "요일 " +
				hours + ":" +
				minute + ":" +
				second + " ");
		
	}

}
