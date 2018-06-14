package view;

import java.awt.BorderLayout;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import controller.ControllerManager;


public class JFCreateAccount extends JFrame{

	private static final long serialVersionUID = 1L;
	private String type;
	private JPEmployeeAccount employeeAccount;
	private JPCompanyAccount companyAccount;
	private JScrollPane jScrollPane;
	private ControllerManager controllerManager;
	
	//Employee
	public JFCreateAccount(String email, String password, String type, ControllerManager controllerManager, String[] departmentList) {
		this.controllerManager = controllerManager;
		this.type = type;
		initComponents();
		employeeAccount = new JPEmployeeAccount(email, password, controllerManager, departmentList);
		jScrollPane = new JScrollPane(employeeAccount);
		add(jScrollPane, BorderLayout.CENTER);
		setVisible(true);
	}
	
	//Company
	public JFCreateAccount(String email, String password, ControllerManager controllerManager, String[] departmentList) {
		this.controllerManager = controllerManager;
		type = "Empresa";
		initComponents();
		companyAccount = new JPCompanyAccount(email, password, controllerManager,departmentList);
		jScrollPane = new JScrollPane(companyAccount);
		add(jScrollPane, BorderLayout.CENTER);
		setVisible(true);
	}
	
	public void initComponents() {
		setIconImage(new ImageIcon("files/imagenes/icon.png").getImage());
		addWindowListener(controllerManager);
		this.getContentPane().setBackground(Constant.BACKGROUND);
		setTitle("Nuevo " + type);
		setResizable(false);
		setSize(543, 650);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
	}
	
	public String getDepartmentCompany() {
		return companyAccount.getDepartment();
	}
	
	public void setCititesListEmployee(String[] values) {
		employeeAccount.setCitiesList(values);
	}
	
	public void setCititesListCompany(String[] values) {
		companyAccount.setCitiesList(values);
	}
	
	public int getIdCompany() {
		return companyAccount.getId();
	}
	
	public String getNameCompany() {
		return companyAccount.getName();
	}
	
	public String getDecriptionCompany() {
		return companyAccount.getDecription();
	}
	
	public String getEmailCompany() {
		return companyAccount.getEmail();
	}
	
	public String getPhotoPathCompany() {
		return companyAccount.getPhotoPath();
	}
	
	public String getPasswordCompany() {
		return companyAccount.getPassword();
	}
	
	public String getNumerPhoneCompany() {
		return companyAccount.getNumerPhone();
	}
	
	public String getAddressCompany() {
		return companyAccount.getAddress();
	}
	
	public String getCityCompany() {
		return companyAccount.getCity();
	}
	
	public String getDepartmentEmployee() {
		return employeeAccount.getDepartment();
	}
	
	public int getIdEmployee() {
		return employeeAccount.getId();
	}
	
	public String pathPhotoEmployee() {
		return employeeAccount.pathPhoto();
	}
	
	public String getEmailEmployee() {
		return employeeAccount.getEmail();
	}
	
	public String getPasswordEmployee() {
		return employeeAccount.getPassword();
	}
	
	public String getNumberPhoneEmployee() {
		return employeeAccount.getNumberPhone();
	}
	
	public String getAddressEmployee() {
		return employeeAccount.getAddress();
	}
	
	public String getCityEmployee() {
		return employeeAccount.getCity();
	}
	
	public String firstNameEmployee() {
		return employeeAccount.firstName();
	}
	
	public String lastNameEmployee() {
		return employeeAccount.lastName();
	}
	
	public Date getBirthDateEmployee() {
		return employeeAccount.getBirthDate();
	}
	
	public String getJobTitleEmployee() {
		return employeeAccount.getJobTitle();
	}
	
	public String getProfesionalPofileEmployee() {
		return employeeAccount.getProfesionalPofile();
	}
}
