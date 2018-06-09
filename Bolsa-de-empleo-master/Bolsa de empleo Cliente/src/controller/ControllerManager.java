package controller;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import models.Client;
import models.Request;
import view.JDSignIn;
import view.JFCreateAccount;
import view.JFMainWindow;

public class ControllerManager implements ActionListener, WindowListener{

	private JDSignIn login;
	private Client client;
	private JFCreateAccount createAccount;
	private JFMainWindow mainWindow;

	public ControllerManager() throws FileNotFoundException, IOException {
		login = new JDSignIn(this);
		initClient();
	}

	public void initClient() throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream("config.init"));
		String host = properties.getProperty("host");
		int port = Integer.parseInt((properties.getProperty("port")));
		client = new Client(host, port);
	}

	public void singIn() {

		String type = login.getSelectedButtom();
		String email = login.getEmail();
		String password = new String(login.getPassword());
		if (!type.equals(Events.SELECT_CREATEACCOUNT.toString())) {
			try {
				client.signIn(email, password, type);
				Thread.sleep(2000);
				validateResponse();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			newAccount(email, password);
		}
	}

	public void newAccount(String email, String password) {
		String[] options = {"Candidato","Compañia"};
		Image image = new ImageIcon("files/imagenes/crear_cuenta.png").getImage();
		int option = JOptionPane.showOptionDialog(login, "Seleccione el tipo de cuenta que desea crear", "Crear cuenta", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon(image.getScaledInstance(75, 75, Image.SCALE_DEFAULT)), options, options[0]);
		if (option >= 0) {
			if (options[option].equals(options[1])) {
				try {
					String[] departmentList = client.getDeparmentList();
					createAccount = new JFCreateAccount(email, password, this, departmentList);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				try {
					String[] departmentList = client.getDeparmentList();
					createAccount = new JFCreateAccount(email, password, options[option], this, departmentList);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			login.setVisible(false);
		}
	}

	public void validateResponse() {
		if (client.getResultConnection().equals(Request.WRONG_INFO.toString())) {
			JOptionPane.showMessageDialog(login, "Email o contraeña incorrecta");
		}else {
			try {
				client.requestUserName();
				Thread.sleep(3000);
				mainWindow = new JFMainWindow(client.getResultConnection(), client.getUserName(), this);
				//createAccount.setVisible(false);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void setCitiesListCompany() {
		String name = createAccount.getDepartmentCompany();
		try {
			createAccount.setCititesListCompany(client.getCitiesList(name));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void setCitiesListEmployee() {
		String name = createAccount.getDepartmentEmployee();
		try {

			createAccount.setCititesListEmployee(client.getCitiesList(name));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String value = e.getActionCommand();
		if (mainWindow != null) {
			mainWindow.selectedButtomEmployee(value);
		}
		switch (Events.valueOf(value)) {
		case SIGN_IN:
			singIn();
			break;
		case CREATE_ACCOUNT_COMPANY:
			createAccountCompany();
			break;
		case CREATE_ACCOUNT_EMPLOYEE:
			createAccountEmployee();
			break;
		case GET_DEPARTMENT_COMPANY:
			setCitiesListCompany();
			break;
		case GET_DEPARTMENT_EMPLOYEE:
			setCitiesListEmployee();
			break;
		default:
			break;
		}
	}



	private void createAccountEmployee() {
		String email = createAccount.getEmailEmployee();
		String photoPath = createAccount.pathPhotoEmployee();
		String password = createAccount.getPasswordEmployee();
		String numberPhone = createAccount.getNumberPhoneEmployee();
		String address = createAccount.getAddressEmployee();
		String city = createAccount.getCityEmployee();
		String department = createAccount.getDepartmentEmployee();
		int id = createAccount.getIdEmployee();
		String firstName = createAccount.firstNameEmployee();
		String lastName = createAccount.lastNameEmployee();
		Date birthDate = createAccount.getBirthDateEmployee();
		String jobTitle = createAccount.getJobTitleEmployee();
		String professionalPorfile = createAccount.getProfesionalPofileEmployee();
		try {
			client.createAccountEmployee(email, photoPath, password, numberPhone, address, city, department, id, firstName, lastName, birthDate, jobTitle, professionalPorfile);
			Thread.sleep(4000);
			validateStart(client.isConnection());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void validateStart(boolean result) {
		if (result) {
			System.out.println("Buena");
		}
	}

	private void createAccountCompany() {
		int id = createAccount.getIdCompany();
		String email = createAccount.getEmailCompany();
		String photoPath = createAccount.getPhotoPathCompany();
		String numberPhone = createAccount.getNumerPhoneCompany();
		String address = createAccount.getAddressCompany();
		String city = createAccount.getCityCompany();
		String department = createAccount.getDepartmentCompany();
		String name = createAccount.getNameCompany();
		String description = createAccount.getDecriptionCompany();
		String password = createAccount.getPasswordCompany();
		try {
			client.createAccountCompany(email, photoPath, password, numberPhone, address, city, department, id, name, description);
			Thread.sleep(4000);
			validateStart(client.isConnection());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			new ControllerManager();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) {
		if(mainWindow != null) {
			client.closeConnection();
		}else {
			createAccount.setVisible(false);
			login.setVisible(true);
		}
	}

	@Override
	public void windowDeactivated(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowOpened(WindowEvent e) {}
}
