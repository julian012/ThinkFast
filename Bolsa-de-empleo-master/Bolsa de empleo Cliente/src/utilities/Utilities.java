package utilities;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {

	public static String dateToString(Date date){
		Format formatter = new SimpleDateFormat("dd/MM/yyyy");
		String s = formatter.format(date);
		return s;
	}

	public static Date StringToDate(String dateInString) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = formatter.parse(dateInString);
		System.out.println(date);
		System.out.println(formatter.format(date));
		return date;

	}
}
