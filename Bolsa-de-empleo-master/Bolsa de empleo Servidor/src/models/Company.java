package models;

import java.util.ArrayList;

public class Company extends Account{

	private int idCompany;
	private String nameCompany;
	private String description;
	private ArrayList<NotificationCompany> notificationList;
	
	public Company(String email, String password, String namePhoto, String numberPhone, String address, City city,
			int idCompany, String nameCompany, String description, ArrayList<NotificationCompany> notificationList) {
		super(email, password, namePhoto, numberPhone, address, city);
		setIdCompany(idCompany);
		setNameCompany(nameCompany);
		setDescription(description);
		setNotificationList(notificationList);
	}
	
	public Company(String email, String password, String namePhoto, String numberPhone, String address, City city,
			int idCompany, String nameCompany, String description) {
		super(email, password, namePhoto, numberPhone, address, city);
		setIdCompany(idCompany);
		setNameCompany(nameCompany);
		setDescription(description);
		notificationList = new ArrayList<>();
	}
	
	public int getIdCompany() {
		return idCompany;
	}
	
	private void setIdCompany(int idCompany) {
		this.idCompany = idCompany;
	}
	
	public String getNameCompany() {
		return nameCompany;
	}
	
	public void setNameCompany(String nameCompany) {
		this.nameCompany = nameCompany;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public ArrayList<NotificationCompany> getNotificationList() {
		return notificationList;
	}
	
	public void setNotificationList(ArrayList<NotificationCompany> notificationList) {
		this.notificationList = notificationList;
	}
}
