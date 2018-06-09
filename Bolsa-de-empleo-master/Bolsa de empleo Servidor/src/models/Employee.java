package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;

public class Employee extends Account {

	private int idEmployee;
	private String fistName;
	private String lastName;
	private Date birthDate;
	private String jobTitle;
	private String professionalProfile;
	private ArrayList<NotificationEmployee> notification;
	
	public Employee(String email, String password, String namePhoto, String numberPhone, String address, City city,
			int idEmployee, String fistName, String lastName, Date birthDate, String jobTitle,
			String professionalProfile, ArrayList<NotificationEmployee> notification) {
		super(email, password, namePhoto, numberPhone, address, city);
		setIdEmployee(idEmployee);
		setFistName(fistName);
		setLastName(lastName);
		setBirthDate(birthDate);
		setJobTitle(jobTitle);
		setProfessionalProfile(professionalProfile);
		setNotification(notification);
	}
	
	public Employee(String email, String password, String namePhoto, String numberPhone, String address, City city,
			int idEmployee, String fistName, String lastName, Date birthDate, String jobTitle,
			String professionalProfile) {
		super(email, password, namePhoto, numberPhone, address, city);
		setIdEmployee(idEmployee);
		setFistName(fistName);
		setLastName(lastName);
		setBirthDate(birthDate);
		setJobTitle(jobTitle);
		setProfessionalProfile(professionalProfile);
		notification = new ArrayList<>();
	}

	public int getIdEmployee() {
		return idEmployee;
	}

	private void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}

	public String getFistName() {
		return fistName;
	}

	public void setFistName(String fistName) {
		this.fistName = fistName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getProfessionalProfile() {
		return professionalProfile;
	}

	public void setProfessionalProfile(String professionalProfile) {
		this.professionalProfile = professionalProfile;
	}

	public ArrayList<NotificationEmployee> getNotification() {
		return notification;
	}

	public void setNotification(ArrayList<NotificationEmployee> notification) {
		this.notification = notification;
	}
}
