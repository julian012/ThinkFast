package models;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.Connection;

public class Server extends Thread{
	
	private ServerSocket serverSocket;
	private boolean serverUp;
	private ArrayList<Connection> connectionList;
	private static final Logger LOGGER = Logger.getAnonymousLogger();
	
	public Server(int port) throws IOException {
		serverSocket = new ServerSocket(port);
	}
	
	public void startServer() {
		serverUp = true;
		start();
	}
	
	@Override
	public void run() {
		while(serverUp) {
			try {
				System.out.println("Esperando conexion");
				Socket socket = serverSocket.accept();
				System.out.println("Conectado: " + socket);
//				initServices(socket);
			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, e.getMessage());
			}
		}
	}
}
