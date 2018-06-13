package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.text.AttributedCharacterIterator;
import java.util.Date;
import java.util.Properties;

import javax.swing.JOptionPane;

import connection.Request;
import models.Client;
import utilities.Utilities;
import view.JDSystemMessage;
import view.JFMainWindowGame;
import view.JFSingIn;
import view.JFSingUp;

public class ClientController implements ActionListener, WindowListener {

	private Client client;
	private Properties prop;
	private InputStream input;
	private JFSingIn singIn;
	private JFSingUp singUp;
	private JDSystemMessage systemMessage;
	private JFMainWindowGame mainWindowGame;


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
			mainWindowGame = new JFMainWindowGame(this);
		}else {
			new JDSystemMessage(result);
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
		systemMessage = new JDSystemMessage(message);
		client.initConnection();
		singUp.setVisible(false);
		mainWindowGame = new JFMainWindowGame(this);
	}

	private void singUpResult(String result) {
		switch (Request.valueOf(result)) {
		case EMAIL_OK_NICKNAME_OK:
			createAccount();
			break;
		default:
			systemMessage = new JDSystemMessage(result);
			break;
		}
	}
	
	private void closeApp() {
		try {
			client.closeApp();
			System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	}

}
