package connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.Server;
import models.User;

public class Connection extends Thread{

	private Socket socket;
	private DataInputStream input;
	private DataOutputStream output;
	private boolean connectionUp;
	private Server server;
	private User user;
	private static final Logger LOGGER = Logger.getAnonymousLogger();
	
	public Connection(Socket socket, Server server, User user) throws IOException {
		this.socket = socket;
		input = new DataInputStream(socket.getInputStream());
		output = new DataOutputStream(socket.getOutputStream());
		connectionUp = true;
		this.server = server;
		this.user = user;
		output.writeUTF(Request.START_PROGRAM.toString());
		start();
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
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
		}
	}
	
	@Override
	public void run() {
		System.out.println("La aplicacion comenzo");
		while(connectionUp) {
			try {
				String option = input.readUTF();
				LOGGER.log(Level.FINE, "Llego: " + option);
//				switch (Request.valueOf(option)) {
//				case CLOSE_CONNECTION:
//					closeConnection();
//					break;
//				case USER_NAME:
//					reqUserName();
//					break;
//				default:
//					break;
//				}
			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, e.getMessage());
			}
		}
	}

}
