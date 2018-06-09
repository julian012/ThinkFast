package models;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.Request;

public class Client extends Thread {
	
	private Socket socket;
	private DataInputStream input;
	private DataOutputStream output;
	public boolean connectionUp;
	private String host;
	private int port;
	private String resultConnection;
	private String nameUser;
	private static final Logger LOGGER = Logger.getAnonymousLogger();
	
	public Client(String host, int port) {
		this.host = host;
		this.port = port;
	}
	
	public void signIn(String email, String password) throws UnknownHostException, IOException {
		socket = new Socket(host, port);
		input = new DataInputStream(socket.getInputStream());
		output = new DataOutputStream(socket.getOutputStream());
		//resultConnection = input.readUTF();
		initConnection();
	}
	
	public void initConnection() {
		connectionUp = true;
		start();
	}
	
	@Override
	public void run() {
		System.out.println("La aplicacion comenzo");
		while(connectionUp) {
			try {
				String option = input.readUTF();
				switch (Request.valueOf(option)) {
//				case USER_NAME:
//					resUserName();
//					break;
//
//				default:
//					break;
				}
			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, e.getMessage());
			}
		}
	}
}
