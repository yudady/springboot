package tk.tommy.springboot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Test {

	public static void main(String argv[]) throws Exception {

		String str = "2018-08-17 11:57:37.791";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
		LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
		System.out.println(dateTime);


		LocalDateTime target = LocalDateTime.parse("2018-08-17 13:00:00.373", formatter);
		LocalDateTime target2 = LocalDateTime.parse("2018-08-17 14:00:00.373", formatter);
		System.out.println(target2.compareTo(target));

	}
}