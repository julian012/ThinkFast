package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.Server;
import observer.IObsevable;
import observer.IObsever;
import persistence.FileManager;

public class ServerController implements IObsever {
	
	private Server server;
	private Properties prop;
	private InputStream input;
	private FileManager fileManager;
	private IObsevable obsevable;
	private static final Logger LOGGER = Logger.getAnonymousLogger();
	
	public ServerController() {
		loadProperties();
	}
	
	public void loadProperties() {
		prop = new Properties();
		fileManager = new FileManager();
		try {
			input = new FileInputStream("config.properties");
			prop.load(input);
			server = new Server(fileManager.readUserFile(),Integer.parseInt(prop.getProperty("port")));
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
}
