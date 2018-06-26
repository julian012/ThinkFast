package connection;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import models.QuestionList;
import models.Result;
import models.Server;
import models.User;
import observer.IObsevable;
import observer.IObsever;
import persistence.FileManager;
import structure.SimpleList;
import utilities.Utilities;

public class Connection extends Thread implements IObsevable{

	private Socket socket;
	private DataInputStream input;
	private DataOutputStream output;
	private boolean connectionUp;
	private Server server;
	private User user;
	private ArrayList<IObsever> obseverList;
	private static final Logger LOGGER = Logger.getAnonymousLogger();

	public Connection(Socket socket, Server server, User user) throws IOException {
		obseverList = new ArrayList<>();
		this.socket = socket;
		input = new DataInputStream(socket.getInputStream());
		output = new DataOutputStream(socket.getOutputStream());
		connectionUp = true;
		this.server = server;
		this.server.getId();
		this.user = user;
		output.writeUTF(Request.START_PROGRAM.toString());
		start();
	}
	
	public User getUser() {
		return user;
	}

	public boolean isConnectionUp() {
		return connectionUp;
	}

	public void closeConnection() {
		connectionUp = false;
		try {
			socket.close();
			output.close();
			input.close();
			notifyChange();
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
		}
	}
	public void notifyChange() {
		for (IObsever o : obseverList) {
			o.update();
		}
	}

	public void sendInfoUser() {
		try {
			output.writeUTF(Request.SEND_INFO_USER_AFTER_LOGIN.toString());
			output.writeUTF(user.getId());
			output.writeUTF(user.getName());
			output.writeUTF(user.getNickname());
			output.writeUTF(user.getEmail());
			output.writeUTF(user.getPassword());
			output.writeUTF(Utilities.localDateToString(user.getBirthDate()));
			//Enviar imagen
			File image = new File(user.getPathImageUser());
			BufferedImage buffer = ImageIO.read(image);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			ImageIO.write(buffer, image.getName().substring(image.getName().lastIndexOf(".") + 1), outputStream);
			byte[] imageData = outputStream.toByteArray();
			output.writeUTF(image.getName());
			output.writeInt(imageData.length);
			output.write(imageData);
			//Enviar xml informacion
			File file = new File("data/infoUsers/" + user.getId() + "/accountInfo.xml");
			byte[] array = new byte[(int) file.length()];
			readFileBytes(file, array);
			output.writeUTF(file.getName());
			output.writeInt(array.length);
			output.write(array);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void readFileBytes(File file, byte[] array) throws IOException {
		FileInputStream fInputStream = new FileInputStream(file);
		fInputStream.read(array);
		fInputStream.close();
	}
	
	private void changeQuestionList() throws IOException {
		SimpleList<String> list = Utilities.stringToSimpleList(input.readUTF());
		user.getAccountInfo().setQuestionList(list);
		notifyChange();
	}
	
	private void playOneVsOne() {
		for (IObsever o : obseverList) {
			o.playOnevsOne(this);
		}
	}
	
	public void sendQuestionList(ArrayList<QuestionList> list) throws IOException {
		FileManager.saveToXMLQuestionList(list, getUser());
		File file = new File("data/infoUsers/" + getUser().getId() + "/questions.xml");
		byte[] array = new byte[(int) file.length()];
		readFileBytes(file, array);
		output.writeUTF(Request.SEND_QUESTIONS_GAME_ONE_VS_ONE.toString());
		output.writeUTF(user.getId() + "-"+file.getName());
		output.writeInt(array.length);
		output.write(array);
		file.delete();
	}
	
	private  void changeQuestion() throws IOException {
		for (IObsever o : obseverList) {
			o.changeQuestion(input.readUTF());
		}
	}
	
	public void nextQuestion() {
		try {
			output.writeUTF(Request.SET_NEXT_QUESTION_ONE_VS_ONE.toString());
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
		}
	}
	
	public void opponentAnswered() {
		try {
			output.writeUTF(Request.OPPONENT_ANSWERED.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void gameFinishOnevsOne() throws IOException {
		String id = input.readUTF();
		int money = input.readInt();
		int points = input.readInt();
		//boolean winner = input.readBoolean();
		String nickName = user.getName();
		ImageIcon image = new ImageIcon(ImageIO.read(new File(user.getPathImageUser())));
		Result result = new Result(id, nickName, money, points,false, image);
		server.gameFinishOnevsOne(result);
	}
	
	public void getResultsFinalGameOnevsOne(Result playerA, Result playerB) {
		LOGGER.log(Level.SEVERE, "Llego los resultados al jugador");
		try {
			output.writeUTF(Request.SEND_RESULTS_GAME_ONE_VS_ONE.toString());
			//Informacion usuario A
			output.writeUTF(playerA.getId());
			output.writeUTF(playerA.getNickName());
			output.writeInt(playerA.getMoney());
			output.writeInt(playerA.getPoints());
			output.writeBoolean(playerA.isWinner());
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			BufferedImage imagePlayerA = (BufferedImage) playerA.getImage().getImage();
			ImageIO.write(imagePlayerA, playerA.getId(), outputStream);
			byte[] imageData = outputStream.toByteArray();
			output.writeUTF(playerA.getId() + ".png");
			output.writeInt(imageData.length);
			output.write(imageData);
			LOGGER.log(Level.SEVERE, "Mando Jugador");
			//Informacion usuario B
			output.writeUTF(playerB.getId());
			output.writeUTF(playerB.getNickName());
			output.writeInt(playerB.getMoney());
			output.writeInt(playerB.getPoints());
			output.writeBoolean(playerB.isWinner());
			ByteArrayOutputStream outputStreamB = new ByteArrayOutputStream();
			BufferedImage imagePlayerB = (BufferedImage) playerB.getImage().getImage();
			ImageIO.write(imagePlayerB, playerB.getId(), outputStream);
			byte[] imageDataB = outputStreamB.toByteArray();
			output.writeUTF(playerB.getId() + ".png");
			output.writeInt(imageDataB.length);
			output.write(imageDataB);
			LOGGER.log(Level.SEVERE, "Mando Jugador 2");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		 * Estos valores son mandados a al cliente para que sean mostrado de una manera visual los resultados
		 */
	}
	
	private void reloadInfoUser() throws IOException {
		output.writeUTF(Request.RELOAD_INFO_USER.toString());
		output.writeInt(user.getAccountInfo().getExperience());
		output.writeInt(user.getAccountInfo().getMoney());
		output.writeInt(user.getAccountInfo().getGames());
		output.writeInt(user.getAccountInfo().getTotalGames());
	}
	
	private void cancelWaitOpponentonevsOne() {
		server.cancelWaitOpponentonevsOne(this);
	}
	
	@Override
	public void run() {
		System.out.println("La aplicacion comenzo");
		sendInfoUser();
		while(connectionUp) {
			try {
				String option = input.readUTF();
				LOGGER.log(Level.FINE, "Llego: " + option);
				switch (Request.valueOf(option)) {
				case CLOSE_CONNECTION:
					closeConnection();
					break;
				case CHANGE_QUESTION_LIST:
					changeQuestionList();
					break;
				case PLAY_ONE_VS_ONE:
					playOneVsOne();
					break;
				case CHANGE_QUESTION_ONE_VS_ONE:
					changeQuestion();
					break;
				case GAME_FINISH_ONE_VS_ONE:
					gameFinishOnevsOne();
					break;
				case RELOAD_INFO_USER:
					reloadInfoUser();
					break;
				case CANCEL_WAIT_OPPONENT_ONE_VS_ONE:
					cancelWaitOpponentonevsOne();
					break;
				default:
					break;
				}
			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, e.getMessage());
			}
		}
	}
	
	public void startGame() {
		try {
			output.writeUTF(Request.START_GAME_ONE_VS_ONE.toString());
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
	

}
