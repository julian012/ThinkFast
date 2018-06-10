package utilities;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utilities {
	
	public static String localDateToString(LocalDate date){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return date.format(formatter);
	}

	public static LocalDate stringToLocalDate(String date) {
		final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return LocalDate.parse(date, dtf);
	}
	
	public static String saveImage(byte[] binaryData, String name) {
		System.out.println("name");
		File file = new File("files/imagenes/cuentas/" + name);
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
