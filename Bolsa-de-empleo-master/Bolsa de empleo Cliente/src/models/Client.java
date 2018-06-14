package models;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import utilities.Utilities;

public class Client extends Thread{
	
	private Socket socket;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	public boolean connectionUp;
	private String host;
	private int port;
	private String resultConnection;
	private String nameUser;
	private static final Logger LOGGER = Logger.getAnonymousLogger();
	
	public Client(String host, int port){
		this.host = host;
		this.port = port;
	}
	
	public void signIn(String email, String password, String type) throws UnknownHostException, IOException {
		socket = new Socket(host, port);
		inputStream = new DataInputStream(socket.getInputStream());
		outputStream = new DataOutputStream(socket.getOutputStream());
		outputStream.writeUTF(type);
		outputStream.writeUTF(email);
		outputStream.writeUTF(password);
		resultConnection = inputStream.readUTF();
		if (!resultConnection.equals(Request.WRONG_INFO.toString())) {
			initConnection();
		}
	}
	
	public String getUserName() {
		return nameUser;
	}
	
	public String getResultConnection() {
		return resultConnection;
	}
	
	public void initConnection() {
		connectionUp = true;
		start();
	}
	
	public boolean isConnection() {
		return connectionUp;
	}
	

	public String[] getDeparmentList() throws IOException {
		socket = new Socket(host, port);
		inputStream = new DataInputStream(socket.getInputStream());
		outputStream = new DataOutputStream(socket.getOutputStream());
		outputStream.writeUTF(Request.DEPARTMENT_LIST.toString());
		String[] values = new String[inputStream.readInt()];
		for (int i = 0; i < values.length; i++) {
			values[i] = inputStream.readUTF();
		}
		return values;
	}
	
	public String[] getCitiesList(String department) throws IOException {
		socket = new Socket(host, port);
		inputStream = new DataInputStream(socket.getInputStream());
		outputStream = new DataOutputStream(socket.getOutputStream());
		outputStream.writeUTF(Request.CITY_LIST.toString());
		outputStream.writeUTF(department);
		String[] values = new String[inputStream.readInt()];
		for (int i = 0; i < values.length; i++) {
			values[i] = inputStream.readUTF();
		}
		return values;
	}
	
	public void createAccountEmployee(String email, String photoPath, String password, String numberPhone,
			String address, String city, String department, int id, String firstName, String lastName,
			Date birthDate, String jobTitle, String professionalPorfile) throws IOException {
		socket = new Socket(host, port);
		inputStream = new DataInputStream(socket.getInputStream());
		outputStream = new DataOutputStream(socket.getOutputStream());
		outputStream.writeUTF(Request.SEND_INFO_EMPLOYEE_ACCOUNT.toString());
		outputStream.writeUTF(email);
		outputStream.writeUTF(password);
		outputStream.writeUTF(numberPhone);
		outputStream.writeUTF(address);
		outputStream.writeUTF(city);
		outputStream.writeUTF(department);
		outputStream.writeInt(id);
		outputStream.writeUTF(firstName);
		outputStream.writeUTF(lastName);
		outputStream.writeUTF(Utilities.dateToString(birthDate));
		outputStream.writeUTF(jobTitle);
		outputStream.writeUTF(professionalPorfile);
		sendImage(photoPath, outputStream);
		initConnection();
	}
	
	public void sendImage(String path, DataOutputStream output) {
		File image = new File(path);
		BufferedImage buffer;
		try {
			buffer = ImageIO.read(image);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			ImageIO.write(buffer, image.getName().substring(image.getName().lastIndexOf(".") + 1), outputStream);
			byte[] imageData = outputStream.toByteArray();
			output.writeUTF(image.getName());
			output.writeInt(imageData.length);
			output.write(imageData);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
		}
	}
	
	public void createAccountCompany(String email, String photoPath, String password, String numberPhone,
			String address, String city, String department, int id, String name, String description) throws IOException{
		socket = new Socket(host, port);
		inputStream = new DataInputStream(socket.getInputStream());
		outputStream = new DataOutputStream(socket.getOutputStream());
		outputStream.writeUTF(Request.SEND_INFO_COMPANY_ACCOUNT.toString());
		outputStream.writeUTF(email);
		outputStream.writeUTF(password);
		outputStream.writeUTF(numberPhone);
		outputStream.writeUTF(address);
		outputStream.writeUTF(city);
		outputStream.writeUTF(department);
		outputStream.writeInt(id);
		outputStream.writeUTF(name);
		outputStream.writeUTF(description);
		sendImage(photoPath, outputStream);
		initConnection();
	}
	
	public void requestUserName() {
		try {
			outputStream.writeUTF(Request.USER_NAME.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void resUserName() {
		try {
			nameUser = inputStream.readUTF();
			System.out.println("LLego el nombre " + nameUser);
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
		}
	}
	
	public void closeConnection() {
		try {
			outputStream.writeUTF(Request.CLOSE_CONNECTION.toString());
			connectionUp = false;
			outputStream.close();
			inputStream.close();
			socket.close();
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
		}
	}
	
	@Override
	public void run() {
		System.out.println("La aplicacion comenzo");
		while(connectionUp) {
			try {
				String option = inputStream.readUTF();
				switch (Request.valueOf(option)) {
				case USER_NAME:
					resUserName();
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
