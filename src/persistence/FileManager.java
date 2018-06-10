package persistence;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import models.User;
import structure.NodeSimpleList;
import structure.SimpleList;
import utilities.Utilities;

public class FileManager {
	
	public static final String PATH_FILE_USER = "data/userInfo.xml";
	public static final String USER_ROOT = "userList";
	public static final String USER = "user";
	public static final String ID_USER = "id";
	public static final String NAME_USER = "name";
	public static final String NICKNAME_USER = "nickname";
	public static final String BIRTHDATE_USER = "birthdate";
	public static final String EMAIL_USER = "email";
	public static final String PASSWORD_USER = "password";
	public static final String PATH_IMAGE_USER = "pathImageUser";
	
	public SimpleList<User> readUserFile(){
		SAXBuilder builder = new SAXBuilder();
		File archive = new File(PATH_FILE_USER);
		SimpleList<User> userList = new SimpleList<>();
		if(archive.exists()) {
			try {
				Document document = (Document) builder.build(archive);
				Element rootNode = document.getRootElement();
				List<Element> info = rootNode.getChildren(USER);
				Iterator<Element> iteratorList = info.iterator();
				while(iteratorList.hasNext()) {
					Element infoNode = (Element) iteratorList.next();
					String id = infoNode.getChildText(ID_USER);
					String name = infoNode.getChildText(NAME_USER);
					String nickname = infoNode.getChildText(NICKNAME_USER);
					String email = infoNode.getChildText(EMAIL_USER);
					String password = infoNode.getChildText(PASSWORD_USER);
					String pathImageUser = infoNode.getChildText(PATH_IMAGE_USER);
					LocalDate birthDate = Utilities.stringToLocalDate(infoNode.getChildText(BIRTHDATE_USER));
					User user = new User(id, name, nickname, email, password, birthDate, pathImageUser);
					userList.addNode(new NodeSimpleList<User>(user));
				}
			} catch (JDOMException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			return userList;
		}
		return userList;
	}
	
	public void saveToXMLUserInfo(SimpleList<User> list) {
		Document doc = new Document();
		Element root = new Element(USER_ROOT);
		NodeSimpleList<User> actualNode = list.getHead();
		while(actualNode != null) {
			User user = actualNode.getInfo();
			Element child1 = new Element(USER);
			Element child2 = new Element(ID_USER);
			child2.addContent(user.getId());
			Element child3 = new Element(NAME_USER);
			child3.addContent(user.getName());
			Element child4 = new Element(NICKNAME_USER);
			child4.addContent(user.getNickname());
			Element child5 = new Element(EMAIL_USER);
			child5.addContent(user.getEmail());
			Element child6 = new Element(PASSWORD_USER);
			child6.addContent(user.getPassword());
			Element child7 = new Element(PATH_IMAGE_USER);
			child7.addContent(user.getPathImageUser());
			Element child8 = new Element(BIRTHDATE_USER);
			child8.addContent(Utilities.localDateToString(user.getBirthDate()));
			child1.addContent(child2);
			child1.addContent(child3);
			child1.addContent(child4);
			child1.addContent(child5);
			child1.addContent(child6);
			child1.addContent(child7);
			child1.addContent(child8);
			root.addContent(child1);
			actualNode = actualNode.getNext();
		}
		doc.setRootElement(root);
		XMLOutputter outter = new XMLOutputter();
		outter.setFormat(Format.getPrettyFormat());
		try {
			outter.output(doc, new FileWriter(new File(PATH_FILE_USER)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
