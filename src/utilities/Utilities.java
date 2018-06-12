package utilities;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.swing.filechooser.FileNameExtensionFilter;

public class Utilities {

	public static String localDateToString(LocalDate date){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return date.format(formatter);
	}

	public static LocalDate stringToLocalDate(String date) {
		final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(date, dtf);
	}

	public static LocalDate dateToLocalDate(Date birthdate) {
		return birthdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static String saveImage(byte[] binaryData, String name, String id) {
		System.out.println("name");
		File folder = new File("data/infoUsers/" + id);
		folder.mkdirs();
		File file = new File("data/infoUsers/" + id + "/" + name.replaceFirst("[.][^.]+$", "") + ".png");
		try {
			FileOutputStream imageOutputStream = new FileOutputStream(file);
			imageOutputStream.write(binaryData);
			imageOutputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return file.getPath();
	}
}
