package models;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Connection extends Thread{

	private Socket socket;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private boolean connectionUp;
	private Server server;
	private Employee employee;
	private Company company;
	private static final Logger LOGGER = Logger.getAnonymousLogger();

	public Connection(Socket socket, Server server, Employee employee) throws IOException {
		this.socket = socket;
		inputStream = new DataInputStream(socket.getInputStream());
		outputStream = new DataOutputStream(socket.getOutputStream());
		connectionUp = true;
		this.server = server;
		this.employee = employee;
		outputStream.writeUTF(Request.START_PROGRAM_EMPLOYEE.toString());
		start();
	}

	public Connection(Socket socket, Server server, Company company) throws IOException {
		this.socket = socket;
		inputStream = new DataInputStream(socket.getInputStream());
		outputStream = new DataOutputStream(socket.getOutputStream());
		connectionUp = true;
		this.server = server;
		this.company = company;
		outputStream.writeUTF(Request.START_PROGRAM_COMPANY.toString());
		start();
	}

	public boolean isConnectionUp() {
		return connectionUp;
	}
	
	public void closeConnection() {
		connectionUp = false;
		try {
			socket.close();
			outputStream.close();
			inputStream.close();
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
		}
	}
	
	public void reqUserName() {
		if (employee != null) {
			try {
				outputStream.writeUTF(Request.USER_NAME.toString());
				outputStream.writeUTF(employee.getFistName());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				outputStream.writeUTF(Request.USER_NAME.toString());
				outputStream.writeUTF(company.getNameCompany());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		System.out.println("La aplicacion comenzo");
		while(connectionUp) {
			try {
				String option = inputStream.readUTF();
				LOGGER.log(Level.FINE, "Llego: " + option);
				switch (Request.valueOf(option)) {
				case CLOSE_CONNECTION:
					closeConnection();
					break;
				case USER_NAME:
					reqUserName();
					break;
				default:
					break;
				}
			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, e.getMessage());
			}
		}
	}

}
