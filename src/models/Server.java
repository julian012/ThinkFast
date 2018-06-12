package models;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.Connection;
import connection.Request;
import observer.IObsevable;
import observer.IObsever;
import structure.NodeSimpleList;
import structure.SimpleList;
import utilities.Utilities;

public class Server extends Thread implements IObsevable{
	
	private ArrayList<IObsever> obseverList;
	private ServerSocket serverSocket;
	private boolean serverUp;
	private ArrayList<Connection> connectionList;
	private SimpleList<User> userList;
	private static final Logger LOGGER = Logger.getAnonymousLogger();
	
	public Server(SimpleList<User> userList, int port) throws IOException {
		obseverList = new ArrayList<>();
		serverSocket = new ServerSocket(port);
		connectionList = new ArrayList<>();
		this.userList = userList;
	}
	
	public SimpleList<User> getUserList(){
		return userList;
	}
	
	public void startServer() {
		serverUp = true;
		start();
	}
	
	private void initServices(Socket socket) throws IOException {
		DataInputStream inputStream = new DataInputStream(socket.getInputStream());
		String option = inputStream.readUTF();
		switch (Request.valueOf(option)) {
		case CREATE_ACCOUNT_USER:
			createAccountUser(socket,inputStream);
			break;
		case LOG_IN:
			logIn(socket, inputStream);
			break;
		default:
			break;
		}
	}
	
	private void logIn(Socket socket, DataInputStream inputStream) throws IOException {
		DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
		String email = inputStream.readUTF();
		String password = inputStream.readUTF();
		validateLogin(outputStream, socket, email, password);
	}
	
	private void validateLogin(DataOutputStream outputStream, Socket socket, String email, String password) throws IOException {
		User user = validateEmailAndPassword(email, password);
		if(user != null ) {
			outputStream.writeUTF(Request.LOGING_ACCEPTED.toString());
			connectionList.add(new Connection(socket, this, user));
		}else {
			outputStream.writeUTF(Request.LOGING_FAIL.toString());
		}
	}
	
	public void createAccountUser(Socket socket, DataInputStream inputStream) throws IOException {
		String email = inputStream.readUTF();
		String nickname = inputStream.readUTF();
		boolean resultEmail = validateEmailCreateUser(email);
		boolean resultNickname = validateNicknameCreateUser(nickname);
		DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
		if(resultEmail == true && resultNickname == true) {
			outputStream.writeUTF(Request.EMAIL_OK_NICKNAME_OK.toString());
			createAccountUserOk(socket,inputStream, outputStream);
		}else if(resultEmail == false && resultNickname == true) {
			outputStream.writeUTF(Request.EMAIL_BAD_NICKNAME_OK.toString());
		}else if(resultEmail == true && resultNickname == false) {
			outputStream.writeUTF(Request.EMAIL_OK_NICKNAME_BAD.toString());
		}else {
			outputStream.writeUTF(Request.EMAIL_BAD_NICKNAME_BAD.toString());
		}
	}
	
	public void createAccountUserOk(Socket socket,DataInputStream inputStream, DataOutputStream outputStream) throws IOException {
		String email = inputStream.readUTF();
		String nickname = inputStream.readUTF();
		String name = inputStream.readUTF();;
		LocalDate birthdate = Utilities.stringToLocalDate(inputStream.readUTF());
		String password = inputStream.readUTF();
		String nameImage = inputStream.readUTF();
		byte[] image = new byte[inputStream.readInt()];
		inputStream.readFully(image);
		String id = UUID.randomUUID().toString();
		User user = new User(id, 
				name, nickname, email, password, birthdate, Utilities.saveImage(image, nameImage, id));
		userList.addNode(new NodeSimpleList<User>(user));
		notifyChange();
		outputStream.writeUTF(Request.USER_CREATED_CORRECTLY.toString());
		connectionList.add(new Connection(socket, this, user));
	}
	
	public boolean validateEmailCreateUser(String email) {
		NodeSimpleList<User> actualNode = userList.getHead();
		while(actualNode != null) {
			if(actualNode.getInfo().getEmail().equals(email)) {
				return false;
			}
			actualNode = actualNode.getNext();
		}
		return true;
	}
	
	public boolean validateNicknameCreateUser(String nickname) {
		NodeSimpleList<User> actualNode = userList.getHead();
		while(actualNode != null) {
			if(actualNode.getInfo().getNickname().equals(nickname)) {
				return false;
			}
			actualNode = actualNode.getNext();
		}
		return true;
	}
	
	public User validateEmailAndPassword(String email, String password) {
		NodeSimpleList<User> actualNode = userList.getHead();
		while(actualNode != null) {
			if(actualNode.getInfo().getEmail().equals(email) && actualNode.getInfo().getPassword().equals(password)) {
				return actualNode.getInfo();
			}
			actualNode = actualNode.getNext();
		}
		return null;
	}
	
	public void notifyChange() {
		for (IObsever o : obseverList) {
			o.update();
		}
	}
	
	@Override
	public void run() {
		while(serverUp) {
			try {
				System.out.println("Esperando conexion");
				Socket socket = serverSocket.accept();
				System.out.println("Conectado: " + socket);
				initServices(socket);
			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, e.getMessage());
			}
		}
	}

	@Override
	public void addObserver(IObsever obsever) {
		obseverList.add(obsever);
	}

	@Override
	public void removeObserver(IObsever obsever) {
		obseverList.remove(obsever);
	}
}
