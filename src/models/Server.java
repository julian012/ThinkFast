package models;

import java.io.DataInput;
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

		default:
			break;
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
			createAccountUserOk(inputStream, outputStream);
		}
	}
	
	public void createAccountUserOk(DataInputStream inputStream, DataOutputStream outputStream) throws IOException {
		String email = inputStream.readUTF();
		String nickname = inputStream.readUTF();
		String name = inputStream.readUTF();;
		LocalDate birthdate = Utilities.stringToLocalDate(inputStream.readUTF());
		String password = inputStream.readUTF();
		String nameImage = inputStream.readUTF();
		byte[] image = new byte[inputStream.readInt()];
		inputStream.readFully(image);
		userList.addNode(new NodeSimpleList<User>(new User(UUID.randomUUID().toString(), 
				name, nickname, email, password, birthdate, Utilities.saveImage(image, nameImage))));
		notifyChange();
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
