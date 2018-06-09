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
import java.awt.image.ImageObserver;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.AttributedCharacterIterator;
import java.util.Properties;

import models.Client;
import view.JFSingIn;
import view.JFSingUp;

public class ClientController implements ActionListener {
	
	private Client client;
	private Properties prop;
	private InputStream input;
	private JFSingIn singIn;
	private JFSingUp singUp;
	
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
			client.signIn(email, password);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void singUp() {
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String option = e.getActionCommand();
		switch (Events.valueOf(option)) {
		case SING_IN:
			singIn();
			break;
		case SING_UP:
			singUp();
			break;
		case COME_BACK_CREATE:
			backToLogin();
			break;
		default:
			break;
		}
		
	}

}
