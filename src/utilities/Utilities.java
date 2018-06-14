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

import models.League;
import models.TypeQuestion;
import structure.NodeSimpleList;
import structure.SimpleList;

public class Utilities {
	
	public static final int BRONZE = 1500;
	public static final int PLATE = 2500;
	public static final int GOLD = 3500;
	public static final int RUBY = 5000;
	public static final int DIAMONT = 7000;

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
	
	public static SimpleList<String> getQuestionList(){
		SimpleList<String> questionList = new  SimpleList<>();
		TypeQuestion[] list = TypeQuestion.values();
		for (TypeQuestion typeQuestion : list) {
			questionList.addNode(new NodeSimpleList<String>(typeQuestion.toString()));
		}
		return questionList;
	}
	
	public static String getLeague(int experience) {
		if(experience < BRONZE) {
			return League.NO_LEAGUE.getName();
		}else if(BRONZE <= experience && experience < PLATE) {
			return League.PLATE.getName();
		}else if(PLATE <= experience && experience < GOLD) {
			return League.PLATE.getName();
		}else if(GOLD <= experience && experience < RUBY) {
			return League.GOLD.getName();
		}else if(RUBY <= experience && experience < GOLD) {
			return League.RUBY.getName();
		}else {
			return League.DIAMON.getName();
		}
	}
}
