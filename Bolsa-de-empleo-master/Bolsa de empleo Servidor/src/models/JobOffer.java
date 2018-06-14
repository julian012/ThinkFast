package models;

import java.util.ArrayList;
import java.util.Queue;

public class JobOffer {
	
	private int idJobOffer;
	private int idCompany;
	private Queue<Integer> pendingList;
	private double salary;
	private String jobTitle;
	private City city;
	private TypeContract typeContract;
	private ArrayList<Integer> employeeList;
	
	public JobOffer(int idJobOffer, int idCompany, Queue<Integer> pendingList, double salary, String jobTitle,
			City city, TypeContract typeContract, ArrayList<Integer> employeeList) {
		setIdJobOffer(idJobOffer);
		setIdCompany(idCompany);
		setPendingList(pendingList);
		setSalary(salary);
		setJobTitle(jobTitle);
		setCity(city);
		setTypeContract(typeContract);
		setEmployeeList(employeeList);
		
		this.idJobOffer = idJobOffer;
		this.idCompany = idCompany;
		this.pendingList = pendingList;
		this.salary = salary;
		this.jobTitle = jobTitle;
		this.city = city;
		this.typeContract = typeContract;
		this.employeeList = employeeList;
	}

	public int getIdJobOffer() {
		return idJobOffer;
	}

	private void setIdJobOffer(int idJobOffer) {
		this.idJobOffer = idJobOffer;
	}

	public int getIdCompany() {
		return idCompany;
	}

	private void setIdCompany(int idCompany) {
		this.idCompany = idCompany;
	}

	public Queue<Integer> getPendingList() {
		return pendingList;
	}

	public void setPendingList(Queue<Integer> pendingList) {
		this.pendingList = pendingList;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public TypeContract getTypeContract() {
		return typeContract;
	}

	public void setTypeContract(TypeContract typeContract) {
		this.typeContract = typeContract;
	}

	public ArrayList<Integer> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(ArrayList<Integer> employeeList) {
		this.employeeList = employeeList;
	}
}
