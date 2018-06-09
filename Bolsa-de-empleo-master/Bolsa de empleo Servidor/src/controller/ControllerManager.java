package controller;

import java.io.IOException;

import org.jdom2.JDOMException;
import org.json.simple.parser.ParseException;

import models.Server;
import persistence.FileManager;

public class ControllerManager {
	
	private Server server;
	private FileManager fileManager;
	
	public ControllerManager() throws IOException, ParseException {
		fileManager = new FileManager();
		server = new Server(3011);
		server.startServer();
		server.loadInformationServerDepartment(fileManager.readDepartmentJson());
		loadEmployee();
		loadCompany();
		
	}
	
	public void loadEmployee() {
		try {
			server.loadInformationServerEmployee(fileManager.loadXMLEmployee(server.getDepartmentList()));
		} catch (JDOMException e) {
			System.out.println("NO hay archivo de empleado");
		} catch (IOException e) {
			System.out.println("NO hay archivo de empleado");
		}
	}
	
	public void loadCompany() {
		try {
			server.loadInformationServerCompany(fileManager.loadXMLCompany(server.getDepartmentList()));
		} catch (JDOMException e) {
			System.out.println("NO hay archivo de compañia");
		} catch (IOException e) {
			System.out.println("NO hay archivo de compañia");
		}
	}
	
	public static void main(String[] args) {
		try {
			new ControllerManager();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
