package models;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.jdom2.JDOMException;

import com.sun.javafx.property.adapter.ReadOnlyPropertyDescriptor;

import connection.Request;
import persistence.FileManager;
import utilities.Utilities;

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
	private User user;
	private FileManager fileManager;
	
	public Client(String host, int port) {
		fileManager = new FileManager();
		this.host = host;
		this.port = port;
	}
	
	public String signIn(String email, String password) throws UnknownHostException, IOException {
		socket = new Socket(host, port);
		input = new DataInputStream(socket.getInputStream());
		output = new DataOutputStream(socket.getOutputStream());
		output.writeUTF(Request.LOG_IN.toString());
		output.writeUTF(email);
		output.writeUTF(password);
		return input.readUTF();
	}
	
	public String singUp(String email, String nickname) throws UnknownHostException, IOException {
		socket = new Socket(host, port);
		input = new DataInputStream(socket.getInputStream());
		output = new DataOutputStream(socket.getOutputStream());
		
		output.writeUTF(Request.CREATE_ACCOUNT_USER.toString());
		output.writeUTF(email);
		output.writeUTF(nickname);
		return input.readUTF();
	}
	
	public String sendInfoToCreateAccount(String name, String nickname, String email, LocalDate birthdate, String password, File image) throws IOException {
		BufferedImage buffer = ImageIO.read(image);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ImageIO.write(buffer, image.getName().substring(image.getName().lastIndexOf(".") + 1), outputStream);
		byte[] imageData = outputStream.toByteArray();
		output.writeUTF(email);
		output.writeUTF(nickname);
		output.writeUTF(name);
		output.writeUTF(Utilities.localDateToString(birthdate));
		output.writeUTF(password);
		output.writeUTF(image.getName());
		output.writeInt(imageData.length);
		output.write(imageData);
		return input.readUTF();
	}
	
	public void initConnection() {
		connectionUp = true;
		start();
	}
	
	public void closeApp() throws IOException {
		output.writeUTF(Request.CLOSE_CONNECTION.toString());
		connectionUp = false;
		input.close();
		output.close();
		socket.close();
	}
	
	public void resSendInfoUser() {
		try {
			String name = input.readUTF();
			String id = input.readUTF();
			String nickname = input.readUTF();
			String email = input.readUTF();
			String password = input.readUTF();
			LocalDate birthDate = Utilities.stringToLocalDate(input.readUTF());
			String nameImage = input.readUTF();
			byte[] image = new byte[input.readInt()];
			input.readFully(image);
			File file = new File(input.readUTF());
			readFile(file);
			ImageIcon imageUser = new ImageIcon(image);
			AccountInfo accountInfo = fileManager.getAccountInfoByFile(file);
			file.delete();
			user = new User(id,
					, nameImage
					, nickname, email, password, birthDate, 
					imageUser
					, accountInfo);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (JDOMException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void readFile(File file) throws IOException {
		byte[] fileArray = new byte[input.readInt()];
		input.readFully(fileArray);
		writeFile(file, fileArray);
	}

	private void writeFile(File file, byte[] array) throws IOException {
		FileOutputStream fOutputStream = new FileOutputStream(file);
		fOutputStream.write(array);
		fOutputStream.close();
	}
	
	@Override
	public void run() {
		System.out.println("La aplicacion comenzo");
		while(connectionUp) {
			try {
				String option = input.readUTF();
				switch (Request.valueOf(option)) {
				case SEND_INFO_USER_AFTER_LOGIN:
					resSendInfoUser();
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
