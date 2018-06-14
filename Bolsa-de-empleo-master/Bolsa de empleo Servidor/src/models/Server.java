package models;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import persistence.FileManager;
import utilities.Utilities;

public class Server extends Thread{
	
	private ServerSocket serverSocket;
	private ArrayList<Department> departmentList;
	private boolean serverUp;
	private ArrayList<Company> companyList;
	private ArrayList<Employee> employeeList;
	private ArrayList<JobOffer> jobOfferList;
	private ArrayList<Connection> connectionList;
	private FileManager fileManager;
	private static final Logger LOGGER = Logger.getAnonymousLogger();
	
	public Server(int port) throws IOException {
		fileManager = new FileManager();
		serverSocket = new ServerSocket(port);
		companyList = new ArrayList<>();
		employeeList = new ArrayList<>();
		jobOfferList = new ArrayList<>();
		connectionList = new ArrayList<>();
		departmentList = new ArrayList<>();
	}
	
	public ArrayList<Department> getDepartmentList(){
		return departmentList;
	}
	
	public void startServer() {
		serverUp = true;
		start();
	}
	
	public void loadInformationServerDepartment(ArrayList<Department> departmentList) {
		this.departmentList = departmentList;
	}
	
	public void loadInformationServerCompany(ArrayList<Company> companyList) {
		this.companyList = companyList;
	}
	
	public void loadInformationServerEmployee(ArrayList<Employee> employeeList) {
		this.employeeList = employeeList;
	}
	
	public void loadInformationServerJobOffer(ArrayList<JobOffer> jobOfferList) {
		this.jobOfferList = jobOfferList;
	}
	
	private void initServices(Socket socket) throws IOException {
		DataInputStream inputStream = new DataInputStream(socket.getInputStream());
		String option = inputStream.readUTF();
		try {
			switch (Request.valueOf(option)) {
			case DEPARTMENT_LIST:
				sendDepartmentList(socket);
				break;
			case CITY_LIST:
				String name = inputStream.readUTF();
				sendCityList(socket, name);
				break;
			case SEND_INFO_EMPLOYEE_ACCOUNT:
				createAccountEmployee(socket);
				break;
				
			case SEND_INFO_COMPANY_ACCOUNT:
				createAccountCompany(socket);
				break;
			default:
				
				break;
			}
		}catch (Exception e) {
			String type = option;
			String email = inputStream.readUTF();
			String password = inputStream.readUTF();
			signIn(type, email, password, socket);
		}
	}
	
	private void createAccountCompany(Socket socket) throws IOException {
		DataInputStream inputStream = new DataInputStream(socket.getInputStream());
		String email = inputStream.readUTF();
		String password = inputStream.readUTF();
		String numberPhone = inputStream.readUTF();
		String address = inputStream.readUTF();
		String city = inputStream.readUTF();
		String department = inputStream.readUTF();
		int id = inputStream.readInt();
		String name = inputStream.readUTF();
		String description = inputStream.readUTF();
		String nameImage = inputStream.readUTF();
		byte[] imageData = new byte[inputStream.readInt()];
		inputStream.readFully(imageData);
		Company company = new Company(email, password, nameImage, numberPhone, address, searchCityByName(department, city), id, name, description);
		companyList.add(company);
		fileManager.saveXMLCompany(companyList);
		connectionList.add(new Connection(socket, this, company));
	}
	
	private void createAccountEmployee(Socket socket) throws IOException {
		DataInputStream inputStream = new DataInputStream(socket.getInputStream());
		String email = inputStream.readUTF();
		String password = inputStream.readUTF();
		String numberPhone = inputStream.readUTF();
		String address = inputStream.readUTF();
		String city = inputStream.readUTF();
		String department = inputStream.readUTF();
		int id = inputStream.readInt();
		String firstName = inputStream.readUTF();
		String lastName = inputStream.readUTF();
		String birthDate = inputStream.readUTF();
		String jobTitle = inputStream.readUTF();
		String professionalPorfile = inputStream.readUTF();
		String nameImage = inputStream.readUTF();
		byte[] imageData = new byte[inputStream.readInt()];
		inputStream.readFully(imageData);
		Employee employee = new Employee(email, password, nameImage, numberPhone, address, searchCityByName(department, city), id, firstName, 
				lastName, Utilities.StringToDate(birthDate), jobTitle, professionalPorfile);
		Utilities.saveImage(imageData, nameImage);
		employeeList.add(employee);
		fileManager.saveXMLEmployee(employeeList);
		connectionList.add(new Connection(socket, this, employee));
	}
	
	private City searchCityByName(String department, String city) {
		for (Department d : departmentList) {
			if (d.getName().equals(department)) {
				for (City c : d.getCityList()) {
					if (c.getName().equals(city)) {
						return c;
					}
				}
			}
		}
		return null;
	}
	
	private void sendCityList(Socket socket, String name ) throws IOException {
		System.out.println(name + " Llego este departamento");
		DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
		for (Department department : departmentList) {
			if(department.getName().equals(name)) {
				outputStream.writeInt(department.getCityList().size());
				for (int i = 0; i < department.getCityList().size(); i++) {
					outputStream.writeUTF(department.getCityList().get(i).getName());
				}
			}
		}
		outputStream.close();
	}
	
	private void sendDepartmentList(Socket socket) throws IOException {
		DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
		outputStream.writeInt(departmentList.size());
		for (int i = 0; i < departmentList.size(); i++) {
			outputStream.writeUTF(departmentList.get(i).getName());
		}
		outputStream.close();
	}

	private void signIn(String type, String email, String password,Socket socket) {
		switch (type) {
		case "SELECT_JEMPLOYEE":
			signInEmployee(email, password, socket);
			break;
		case "SELECT_JCOMPANY" :
			singInCompany(email, password, socket);
			break;
		default:
			break;
		}
	}
	
	private void singInCompany(String email, String password, Socket socket) {
		for (Company company : companyList) {
			if (company.getEmail().equals(email) && company.getPassword().equals(password)) {
				try {
					connectionList.add(new Connection(socket, this, company));
				} catch (IOException e) {
					LOGGER.log(Level.SEVERE, e.getMessage());
				}
			}
		}
		wrongPassword(socket);
	}
	
	private void signInEmployee(String email, String password, Socket socket) {
		boolean result = true;
		for (Employee e : employeeList) {
			if (e.getEmail().equals(email) && e.getPassword().equals(password)) {
				try {
					System.out.println("Entro");
					connectionList.add(new Connection(socket, this, e));
					result = false;
				} catch (IOException e1) {
					LOGGER.log(Level.SEVERE, e1.getMessage());
				}
			}
		}
		if (result) {
			wrongPassword(socket);
		}
	} 
	
	private void wrongPassword(Socket socket) {
		DataOutputStream outputStream;
		try {
			outputStream = new DataOutputStream(socket.getOutputStream());
			outputStream.writeUTF(Request.WRONG_INFO.toString());
			outputStream.close();
			socket.close();
		} catch (IOException e1) {
			LOGGER.log(Level.SEVERE, e1.getMessage());
		}
	}
	
	private void removeClosedConnection() {
		for (int i = 0; i < connectionList.size(); i++) {
			if (!connectionList.get(i).isConnectionUp()) {
				companyList.remove(i);
				i--;
			}
		}
	}
	
	public ArrayList<City> getCitiesList(String name) {
		for (Department d : departmentList) {
			if (name.equals(d.getName())) {
				return d.getCityList();
			}
		}
		
		return null;
	}
	
	@Override
	public void run() {
		while(serverUp) {
			try {
				System.out.println("Esperando conexion");
				Socket socket = serverSocket.accept();
				System.out.println("Conectado: " + socket);
				removeClosedConnection();
				initServices(socket);
			} catch (IOException e) {
				LOGGER.log(Level.SEVERE, e.getMessage());
			}
		}
	}
}
