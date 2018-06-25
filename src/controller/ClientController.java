package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Properties;

import connection.Connection;
import connection.Request;
import models.Client;
import models.QuestionList;
import observer.IObsevable;
import observer.IObsever;
import utilities.Utilities;
import view.JDSystemMessage;
import view.JFGameQuestion;
import view.JFMainWindowGame;
import view.JFSingIn;
import view.JFSingUp;

public class ClientController implements ActionListener, WindowListener, IObsever {

	private Client client;
	private Properties prop;
	private InputStream input;
	private JFSingIn singIn;
	private JFSingUp singUp;
	private JDSystemMessage systemMessage;
	private IObsevable obsevable;
	private JFMainWindowGame mainWindowGame;
	private JFGameQuestion jfGame;
	private boolean statusGameOneOne; 

	public ClientController() {
		loadProperties();
		singIn = new JFSingIn(this);
	}

	public void loadProperties() {
		prop = new Properties();
		try {
			input = new FileInputStream("config.properties");
			prop.load(input);
			client = new Client(prop.getProperty("host"), Integer.parseInt(prop.getProperty("port")));
			obsevable = client;
			obsevable.addObserver(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void singIn() {
		String email = singIn.getEmail();
		String password = new String(singIn.getPassword());
		try {
			validateInfoUser(client.signIn(email, password));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void validateInfoUser(String result) {
		if(result.equals(Request.LOGING_ACCEPTED.toString())) {
			client.initConnection();
			singIn.setVisible(false);
			try {
				Thread.sleep(2000);
				mainWindowGame = new JFMainWindowGame(this, client.getUser());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			systemMessage = new JDSystemMessage(singIn,result,this);
			systemMessage.setVisible(true);
		}
	}

	public void changeTosingUp() {
		singIn.setVisible(false);
		if(singUp == null) {
			singUp = new JFSingUp(this);
		}else {
			singUp.showF();
		}
	}

	public void backToLogin() {
		singUp.setVisible(false);
		singIn.showF();
	}

	private void singUp() {
		String email = singUp.getEmail();
		String nickname = singUp.getNickname();
		try {
			singUpResult(client.singUp(email, nickname));
		} catch (UnknownHostException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private void createAccount() {
		String email = singUp.getEmail();
		String nickname = singUp.getNickname();
		String name = singUp.getNameUser();
		Date birthdate = singUp.getBirthdate();
		String password = new String(singUp.getPassword());
		File image = singUp.getImageUser();
		try {
			showMessage(client.sendInfoToCreateAccount(name, nickname, email, Utilities.dateToLocalDate(birthdate), password, image));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private void showMessage(String message) {
		client.initConnection();
		singUp.setVisible(false);
		try {
			Thread.sleep(2000);
			mainWindowGame = new JFMainWindowGame(this, client.getUser());
			systemMessage = new JDSystemMessage(mainWindowGame,message,this);
			systemMessage.setVisible(true);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}

	private void singUpResult(String result) {
		switch (Request.valueOf(result)) {
		case EMAIL_OK_NICKNAME_OK:
			createAccount();
			break;
		default:
			systemMessage = new JDSystemMessage(singUp,result,this);
			systemMessage.setVisible(true);
			break;
		}
	}

	private void closeApp() {
		try {
			client.closeApp();
			System.exit(0);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void saveQuestions() {
		try {
			mainWindowGame.closeJDQuestionList();
			client.sendQuestionList();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private void showQuestionPanel() {
		mainWindowGame.showQuestionPanel(this, client.getUser());
	}

	private void playOneVsOne() {
		try {
			client.startGameOneVsOne();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		systemMessage = new JDSystemMessage(Events.WAITING_OPPONENT.toString(), this);
		systemMessage.setVisible(true);
	}

	public void cancelWaittingOpponent() {
		try {
			systemMessage.dispose();
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String option = e.getActionCommand();
		switch (Events.valueOf(option)) {
		case SING_IN:
			singIn();
			break;
		case CHANGE_TO_SING_UP:
			changeTosingUp();
			break;
		case SING_UP:
			singUp();
			break;
		case COME_BACK_CREATE:
			backToLogin();
			break;
		case CLOSE_APP:
			closeApp();
			break;
		case QUESTION_PANEL:
			showQuestionPanel();
			break;
		case SAVE_QUESTIONS:
			saveQuestions();
			break;
		case PLAY_ONE_VS_ONE:
			playOneVsOne();
			break;
		case WAITING_OPPONENT_CANCEL:
			cancelWaittingOpponent();
			break;
		default:
			break;
		}

	}

	@Override
	public void windowOpened(WindowEvent e) {	}

	@Override
	public void windowClosing(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
		closeApp();
	}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {
		//closeApp();
	}

	@Override
	public void update() {
		//		mainWindowGame.initComponents(
		//				this
		//				, client.getUser());
	}

	@Override
	public void playOnevsOne(Connection connection) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Ya se conectaron los dos jugadores ya se puede iniciar la partida
	 */
	@Override
	public void startGameOnevsOne() {
		try {
			systemMessage.dispose();
			Thread.sleep(3000);
			//mainWindowGame.setVisible(false);
			Thread.sleep(5000);
			statusGameOneOne = true;
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		/**
		 * Se activa la ventana y empieza a responder las preguntas
		 */
		
	}
	
	public void startTheadTime() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(statusGameOneOne) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(!jfGame.progressBar()) {
						statusGameOneOne = false;
						System.out.println("El tiempo acabo entonces va a pedir cambio de pregunta");
						client.changeQuestion();
					}
				}
			}
		});
		statusGameOneOne = true;
		thread.start();
	}

	@Override
	public void sendQuestionOnevsOne() {
		QuestionList questionList = client.removeFirstQuestion();
		if(jfGame != null) {
			jfGame.reloadTime();
			jfGame.setQuestionAnswer(questionList.getQuestion(),questionList.getAnswer());
			startTheadTime();
		}else{
			jfGame = new JFGameQuestion(this,questionList.getQuestion(),questionList.getAnswer());
			jfGame.reloadTime();
			startTheadTime();		
		}
		jfGame.setVisible(true);
	}

	@Override
	public void changeQuestion(String id) {
		// TODO Auto-generated method stub
		
	}

}
