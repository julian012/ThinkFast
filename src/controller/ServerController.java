package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.Connection;
import models.Result;
import models.Server;
import observer.IObsevable;
import observer.IObsever;
import persistence.FileManager;
import view.JFServer;

public class ServerController implements IObsever, ActionListener {
	
	private Server server;
	private Properties prop;
	private InputStream input;
	private FileManager fileManager;
	private IObsevable obsevable;
	private JFServer jfServer;
	
	private static final Logger LOGGER = Logger.getAnonymousLogger();
	
	public ServerController() {
		jfServer = new JFServer(this);
		jfServer.setVisible(true);
		
	}
	
	public void loadProperties() {
		prop = new Properties();
		fileManager = new FileManager();
		try {
			input = new FileInputStream("config.properties");
			prop.load(input);
			server = new Server(fileManager.readQuestionFile(),fileManager.readUserFile(),Integer.parseInt(prop.getProperty("port")));
			server.startServer();
			obsevable = server;
			obsevable.addObserver(this);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
		}finally {
			try {
				input.close();
			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, e.getMessage());
			}
		}
	}

	@Override
	public void update() {
		fileManager.saveToXMLUserInfo(server.getUserList());
	}

	@Override
	public void playOnevsOne(Connection connection) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startGameOnevsOne() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendQuestionOnevsOne() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeQuestion(String id) {
		
		
	}

	@Override
	public void opponentAnswered() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setResultsGameOneVsOne(Result playerA, Result playerB) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String option = e.getActionCommand();
		switch (Events.valueOf(option)) {
		case INIT_SERVER:
			loadProperties();
			jfServer.dispose();
		default:
			break;
		}

	}
}
