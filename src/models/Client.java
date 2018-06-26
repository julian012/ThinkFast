package models;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.jdom2.JDOMException;


import connection.Request;
import observer.IObsevable;
import observer.IObsever;
import persistence.FileManager;
import structure.SimpleList;
import utilities.Utilities;

public class Client extends Thread implements IObsevable {
	
	private Socket socket;
	private DataInputStream input;
	private DataOutputStream output;
	public boolean connectionUp;
	private String host;
	private int port;
	private static final Logger LOGGER = Logger.getAnonymousLogger();
	private User user;
	private FileManager fileManager;
	private ArrayList<IObsever> obseverList;
	private ArrayList<QuestionList> questionList;
	private QuestionList actualQuestion;
	private int countQuestion;
	private int money;
	private int xp;
	private int games;
	private int totalGames;
	
	public Client(String host, int port) {
		obseverList = new ArrayList<>();
		fileManager = new FileManager();
		this.host = host;
		this.port = port;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setQuestionList(SimpleList<String> questionList) {
		user.setQuestionList(questionList);
	}
	
	public String signIn(String email, String password) throws UnknownHostException, IOException {
		socket = new Socket(host, port);
		input = new DataInputStream(socket.getInputStream());
		output = new DataOutputStream(socket.getOutputStream());
		output.writeUTF(Request.LOG_IN.toString());
		output.writeUTF(email);
		output.writeUTF(password);
		return input.readUTF();
	}
	
	public String singUp(String email, String nickname) throws UnknownHostException, IOException {
		socket = new Socket(host, port);
		input = new DataInputStream(socket.getInputStream());
		output = new DataOutputStream(socket.getOutputStream());
		
		output.writeUTF(Request.CREATE_ACCOUNT_USER.toString());
		output.writeUTF(email);
		output.writeUTF(nickname);
		return input.readUTF();
	}
	
	public String sendInfoToCreateAccount(String name, String nickname, String email, LocalDate birthdate, String password, File image) throws IOException {
		BufferedImage buffer = ImageIO.read(image);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ImageIO.write(buffer, image.getName().substring(image.getName().lastIndexOf(".") + 1), outputStream);
		byte[] imageData = outputStream.toByteArray();
		output.writeUTF(email);
		output.writeUTF(nickname);
		output.writeUTF(name);
		output.writeUTF(Utilities.localDateToString(birthdate));
		output.writeUTF(password);
		output.writeUTF(image.getName());
		output.writeInt(imageData.length);
		output.write(imageData);
		return input.readUTF();
	}
	
	public void initConnection() {
		connectionUp = true;
		start();
	}
	
	public void closeApp() throws IOException {
		output.writeUTF(Request.CLOSE_CONNECTION.toString());
		connectionUp = false;
		input.close();
		output.close();
		socket.close();
	}
	
	public void resSendInfoUser() {
		try {
			String id = input.readUTF();
			String name = input.readUTF();
			String nickname = input.readUTF();
			String email = input.readUTF();
			String password = input.readUTF();
			LocalDate birthDate = Utilities.stringToLocalDate(input.readUTF());
			input.readUTF();
			byte[] image = new byte[input.readInt()];
			input.readFully(image);
			File file = new File(input.readUTF());
			readFile(file);
			ImageIcon imageUser = new ImageIcon(image);
			AccountInfo accountInfo = fileManager.getAccountInfoByFile(file);
			file.delete();
			user = new User(id, name , nickname, email, password, birthDate, imageUser , accountInfo);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (JDOMException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void readFile(File file) throws IOException {
		byte[] fileArray = new byte[input.readInt()];
		input.readFully(fileArray);
		writeFile(file, fileArray);
	}

	private void writeFile(File file, byte[] array) throws IOException {
		FileOutputStream fOutputStream = new FileOutputStream(file);
		fOutputStream.write(array);
		fOutputStream.close();
	}
	
	public void notifyChange() {
		for (IObsever o : obseverList) {
			o.update();
		}
	}
	
	public void startGameOneVsOne() throws IOException {
		output.writeUTF(Request.PLAY_ONE_VS_ONE.toString());
	}
	
	public void sendQuestionList() throws IOException {
		output.writeUTF(Request.CHANGE_QUESTION_LIST.toString());
		output.writeUTF(Utilities.simpleListToString(user.getAccountInfo().getQuestionList()));
	}
	
	public void getQuestionsGame() throws IOException {
		File file = new File(input.readUTF());
		readFile(file);
		questionList = fileManager.readQuestionFile(file);
		System.out.println("Llegaron:" + questionList.size() + "preguntas");
		sendQuestionNameGameOnevsOne();
	}
	
	public void sendQuestionNameGameOnevsOne() {
		for (IObsever o : obseverList) {
			o.sendQuestionOnevsOne();
		}
	}
	
	public void startGameOnevsOne() {
		countQuestion = 0;
		for (IObsever o : obseverList) {
			o.startGameOnevsOne();
		}
	}
	
	public QuestionList removeFirstQuestion() {
		QuestionList question = questionList.get(0);
		questionList.remove(0);
		return question;
	}
	
	public QuestionList getQuestion() {
		countQuestion++;
		if(countQuestion < 10) {
			actualQuestion = removeFirstQuestion();
			return actualQuestion;
		}else {
			return null;
		}
	}
	
	public void opponentAnswered() {
		for (IObsever o : obseverList) {
			o.opponentAnswered();
		}
	}
	
	public void getResultsGameOnevsOne() throws IOException{
		LOGGER.log(Level.SEVERE, "Llegaron resultados");
//		String idA = input.readUTF();
//		String nickNameA = input.readUTF();
//		int moneyA = input.readInt();
//		int pointsA = input.readInt();
		Result playerA = new Result(input.readUTF(), input.readUTF(), input.readInt(), input.readInt(),input.readBoolean(), getImage());
		Result playerB = new Result(input.readUTF(), input.readUTF(), input.readInt(), input.readInt(),input.readBoolean(), getImage());
		for (IObsever o : obseverList) {
			o.setResultsGameOneVsOne(playerA, playerB);
		}
	}
	
	public ImageIcon getImage() throws IOException {
		input.readUTF();
		byte[] image = new byte[input.readInt()];
		input.readFully(image);
		ImageIcon imageUser = new ImageIcon(image);
		return imageUser;
	}
	
	public void comeBackHomeMenu() {
		try {
			output.writeUTF(Request.RELOAD_INFO_USER.toString());
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
		}
	}
	
	public void reloadInfoUser() throws IOException {
		xp = input.readInt();
		money = input.readInt();
		games = input.readInt();
		totalGames = input.readInt();
		for (IObsever o : obseverList) {
			o.update();
		}
	}
	
	public int getGames() {
		return games;
	}
	
	public int getTotalGames() {
		return totalGames;
	}
	
	public int getXp() {
		return xp;
	}
	
	public int getMoney() {
		return money;
	}
	
	@Override
	public void run() {
		System.out.println("La aplicacion comenzo");
		while(connectionUp) {
			try {
				String option = input.readUTF();
				switch (Request.valueOf(option)) {
				case SEND_INFO_USER_AFTER_LOGIN:
					resSendInfoUser();
					break;
				case START_GAME_ONE_VS_ONE:
					startGameOnevsOne();
					break;
				case SEND_QUESTIONS_GAME_ONE_VS_ONE:
					getQuestionsGame();
					break;
				case SET_NEXT_QUESTION_ONE_VS_ONE:
					sendQuestionNameGameOnevsOne();
					break;
				case OPPONENT_ANSWERED:
					opponentAnswered();
					break;
				case SEND_RESULTS_GAME_ONE_VS_ONE:
					getResultsGameOnevsOne();
					break;
				case RELOAD_INFO_USER:
					reloadInfoUser();
					break;
				default:
					break;
				}
			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, e.getMessage());
			}
		}
	}
	
	public void changeQuestion() {
		try {
			output.writeUTF(Request.CHANGE_QUESTION_ONE_VS_ONE.toString());
			output.writeUTF(user.getId());
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
		}
	}
	
	public void gameFinishOnevsOne(int money, int points) {
		try {
			output.writeUTF(Request.GAME_FINISH_ONE_VS_ONE.toString());
			output.writeUTF(user.getId());
			output.writeInt(money);
			output.writeInt(points);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
		}
	}
	
	

	@Override
	public void addObserver(IObsever obsever) {
		obseverList.add(obsever);
	}

	@Override
	public void removeObserver(IObsever obsever) {
		obseverList.remove(obsever);
	}

	public void clientCancelOpponentOnevsOne() {
		try {
			output.writeUTF(Request.CANCEL_WAIT_OPPONENT_ONE_VS_ONE.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
