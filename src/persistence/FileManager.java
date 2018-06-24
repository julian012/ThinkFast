package persistence;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import models.AccountInfo;
import models.QuestionList;
import models.TypeQuestion;
import models.User;
import structure.Node;
import structure.NodeSimpleList;
import structure.SimpleList;
import utilities.Utilities;
import view.Power;

public class FileManager {

	public static final String PATH_FILE_USER = "data/userInfo.xml";
	public static final String PATH_FILE_QUESTION = "data/QuestionsData.xml";
	public static final String USER_ROOT = "userList";
	public static final String USER = "user";
	public static final String ID_USER = "id";
	public static final String NAME_USER = "name";
	public static final String NICKNAME_USER = "nickname";
	public static final String BIRTHDATE_USER = "birthdate";
	public static final String EMAIL_USER = "email";
	public static final String PASSWORD_USER = "password";
	public static final String PATH_IMAGE_USER = "pathImageUser";
	//ACCOUNT INFO
	public static final String ACCOUNT_INFO_ROOT = "accountInfo";
	public static final String EXPERIENCE = "experience";
	public static final String MONEY = "money";
	public static final String GAMES = "games";
	public static final String TOTAL_GAMES = "totalGames";
	public static final String QUESTION_LIST = "questionList";
	public static final String QUESTION = "question";
	public static final String MESSAGE = "message";
	public static final String OPTION = "option";
	public static final String TOPIC = "topic";
	public static final String ANSWER = "answer";
	public static final String POWERLIST = "powerList";
	public static final String POWER = "power";
	public static final String NAME_POWER = "namePower";
	public static final String NUMBER_POWER = "numberPower";
	public static final String USED_POWERS = "usedPowers";
	public static final String LIST_ID_FRIENDS = "listIdFriends";
	public static final String ID_FRIEND = "idFriends";
	public static final String ON_FIRE = "onFire";
	
	public ArrayList<QuestionList> readQuestionFile(File file){
		SAXBuilder builder = new SAXBuilder();
		ArrayList<QuestionList> list = new ArrayList<>();
		Document document;
		try {
			document = (Document) builder.build(file);
			Element rootNode = document.getRootElement();
			List<Element> info = rootNode.getChildren(QUESTION);
			Iterator<Element> iteratorList = info.iterator();
			while(iteratorList.hasNext()) {
				Element infoNode = (Element) iteratorList.next();
				TypeQuestion topic = TypeQuestion.valueOf(infoNode.getChildText(TOPIC));
				String message = infoNode.getChildText(MESSAGE);
				String answer = infoNode.getChildText(ANSWER);
				List<Element> options = infoNode.getChildren(OPTION);
				String optionA = options.get(0).getText();
				String optionB = options.get(1).getText();
				String optionC = options.get(2).getText();
				String optionD = options.get(3).getText();
				QuestionList questionList = new QuestionList(topic, message, optionA, optionB, optionC, optionD, answer);
				list.add(questionList);
			}
		} catch (JDOMException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return list;
	}
	
	public ArrayList<QuestionList> readQuestionFile(){
		SAXBuilder builder = new SAXBuilder();
		File archive = new File(PATH_FILE_QUESTION);
		ArrayList<QuestionList> list = new ArrayList<>();
		Document document;
		try {
			document = (Document) builder.build(archive);
			Element rootNode = document.getRootElement();
			List<Element> info = rootNode.getChildren(QUESTION);
			Iterator<Element> iteratorList = info.iterator();
			while(iteratorList.hasNext()) {
				Element infoNode = (Element) iteratorList.next();
				TypeQuestion topic = TypeQuestion.valueOf(infoNode.getChildText(TOPIC));
				String message = infoNode.getChildText(MESSAGE);
				String answer = infoNode.getChildText(ANSWER);
				List<Element> options = infoNode.getChildren(OPTION);
				String optionA = options.get(0).getText();
				String optionB = options.get(1).getText();
				String optionC = options.get(2).getText();
				String optionD = options.get(3).getText();
				QuestionList questionList = new QuestionList(topic, message, optionA, optionB, optionC, optionD, answer);
				list.add(questionList);
			}
		} catch (JDOMException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return list;
	}

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
					User user = new User(id, name, nickname, email, password, birthDate, pathImageUser, readAccountInfo(id));
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
	
	public AccountInfo getAccountInfoByFile(File archive) throws JDOMException, IOException {
		SAXBuilder builder = new SAXBuilder();
		AccountInfo accountInfo;
		Document document = (Document) builder.build(archive);
		Element rootNode = document.getRootElement();
		int experience = Integer.parseInt(rootNode.getChildText(EXPERIENCE));
		int money = Integer.parseInt(rootNode.getChildText(MONEY));
		int games = Integer.parseInt(rootNode.getChildText(GAMES));
		int totalGames = Integer.parseInt(rootNode.getChildText(TOTAL_GAMES));
		SimpleList<String> questionList = new SimpleList<>();
		Element questionListUser = rootNode.getChild(QUESTION_LIST);
		List<Element> typeList = questionListUser.getChildren(QUESTION);
		Iterator<Element> iteratorList = typeList.iterator();
		while(iteratorList.hasNext()) {
			Element infoNode = (Element) iteratorList.next();
			String value = infoNode.getText();
			questionList.addNode(new NodeSimpleList<String>(value));
		}
		Element powerListUser = rootNode.getChild(POWERLIST);
		List<Element> powers = powerListUser.getChildren(POWER);
		Iterator<Element> iteratorPower = powers.iterator();
		SimpleList<Power> powerList = new SimpleList<>();
		while(iteratorPower.hasNext()) {
			Element infoNode = (Element) iteratorList.next();
			String name = infoNode.getChildText(NAME_POWER);
			int count = Integer.parseInt(infoNode.getChildText(NUMBER_POWER));
			powerList.addNode(new NodeSimpleList<Power>(new Power(name, count)));
		}
		int usedPowers = Integer.parseInt(rootNode.getChildText(USED_POWERS));
		Element listIdFriendsUser = rootNode.getChild(LIST_ID_FRIENDS);
		List<Element> idFriends = listIdFriendsUser.getChildren(ID_FRIEND);
		Iterator<Element> iteratorFriends = idFriends.iterator();
		SimpleList<String> listIdFriends = new SimpleList<>();
		while(iteratorFriends.hasNext()) {
			Element infoNode = (Element) iteratorList.next();
			String value = infoNode.getText();
			listIdFriends.addNode(new NodeSimpleList<String>(value));
		}
		int onFire = Integer.parseInt(rootNode.getChildText(ON_FIRE));
		accountInfo = new AccountInfo(experience, money, games, totalGames, questionList, powerList, usedPowers, listIdFriends, onFire);
		return accountInfo;
	}

	public AccountInfo readAccountInfo(String id) throws JDOMException, IOException {
		File archive = new File("data/infoUsers/" +  id +"/accountInfo.xml");
		return getAccountInfoByFile(archive);
	}
	
	public static void saveToXMLQuestionList(ArrayList<QuestionList> list, User user) {
		Document doc = new Document();
		Element root = new Element(QUESTION_LIST);
		for (int i = 0; i < list.size(); i++) {
			QuestionList question = list.get(i);
			Element q = new Element(QUESTION);
			Element typeQuestion = new Element(TOPIC);
			typeQuestion.addContent(question.getTypeQuestion().toString());
			Element message = new Element(MESSAGE);
			message.addContent(question.getMessage());
			String[] options = question.getOptions();
			for (int j = 0; j < options.length; j++) {
				Element option = new Element(OPTION);
				option.addContent(options[j]);
				q.addContent(option);
			}
			Element answer = new Element(ANSWER);
			answer.addContent(question.getAnswer());
			q.addContent(typeQuestion);
			q.addContent(answer);
			q.addContent(message);
			root.addContent(q);
		}
		doc.setRootElement(root);
		XMLOutputter outter = new XMLOutputter();
		outter.setFormat(Format.getPrettyFormat());
		try {
			outter.output(doc, new FileWriter(new File("data/infoUsers/" + user.getId() + "/questions.xml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveToXMLUserInfo(SimpleList<User> list) {
		Document doc = new Document();
		Element root = new Element(USER_ROOT);
		NodeSimpleList<User> actualNode = list.getHead();
		while(actualNode != null) {
			User user = actualNode.getInfo();
			saveToXMLAccountInfo(user.getId(), user.getAccountInfo());
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
	
	
	public void saveToXMLAccountInfo(String id, AccountInfo accountInfo) {
		Document doc = new Document();
		Element root = new Element(ACCOUNT_INFO_ROOT);
		
		Element experience = new Element(EXPERIENCE);
		experience.addContent(String.valueOf(accountInfo.getExperience()));
		
		Element money = new Element(MONEY);
		money.addContent(String.valueOf(accountInfo.getMoney()));
		
		Element games = new Element(GAMES);
		games.addContent(String.valueOf(accountInfo.getGames()));
		
		Element totalGames = new Element(TOTAL_GAMES);
		totalGames.addContent(String.valueOf(accountInfo.getTotalGames()));
		
		NodeSimpleList<String> actualNodeQuestion = accountInfo.getQuestionList().getHead();
		Element questionList = new Element(QUESTION_LIST);
		while(actualNodeQuestion != null) {
			Element question = new Element(QUESTION);
			question.addContent(actualNodeQuestion.getInfo());
			questionList.addContent(question);
			actualNodeQuestion = actualNodeQuestion.getNext();
		}
		
		NodeSimpleList<Power> actualNodePowerList = accountInfo.getPowerList().getHead();
		Element powerList = new Element(POWERLIST);
		while(actualNodePowerList != null) {
			Element power = new Element(POWER);
			Element name = new Element(NAME_POWER);
			Element count = new Element(NUMBER_POWER);
			name.addContent(actualNodePowerList.getInfo().getNamePower());
			count.addContent(String.valueOf(actualNodePowerList.getInfo().getCountPower()));
			power.addContent(name);
			power.addContent(count);
			powerList.addContent(power);
			actualNodePowerList = actualNodePowerList.getNext();
		}
		
		Element usedPowers = new Element(USED_POWERS);
		usedPowers.addContent(String.valueOf(accountInfo.getUsedPowers()));
		
		NodeSimpleList<String> actualNodeIdFriends = accountInfo.getListIdFriends().getHead();
		Element idFriends = new Element(LIST_ID_FRIENDS);
		while(actualNodeIdFriends != null) {
			Element idFriend = new Element(ID_FRIEND);
			idFriend.addContent(actualNodeIdFriends.getInfo());
			idFriends.addContent(idFriend);
			actualNodeIdFriends = actualNodeIdFriends.getNext();
		}
		
		Element onFire = new Element(ON_FIRE);
		onFire.addContent(String.valueOf(accountInfo.getOnFire()));
		
		root.addContent(onFire);
		root.addContent(idFriends);
		root.addContent(usedPowers);
		root.addContent(powerList);
		root.addContent(questionList);
		root.addContent(totalGames);
		root.addContent(games);
		root.addContent(money);
		root.addContent(experience);
		
		doc.setRootElement(root);
		XMLOutputter outter = new XMLOutputter();
		outter.setFormat(Format.getPrettyFormat());
		try {
			outter.output(doc, new FileWriter(new File("data/infoUsers/" +  id +"/accountInfo.xml")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
