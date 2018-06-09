package persistence;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import models.City;
import models.Company;
import models.Department;
import models.Employee;
import models.NotificationCompany;
import models.NotificationEmployee;
import models.StatusJobOffer;
import models.StatusOffer;
import utilities.Utilities;

public class FileManager {
	
	private static final String STATUS_JOB_OFFER = "statusJobOffer";
	private static final String ID_EMPLOYEE = "idEmployee";
	private static final String ID_JOB_OFFER = "idJobOffer";
	private static final String DESCRIPTION = "description";
	private static final String NAME = "name";
	private static final String COMPANY = "Company";
	private static final String COMPANY_LIST = "CompanyList";
	private static final String DATE = "date";
	private static final String STATUS_OFFER = "statusOffer";
	private static final String ID_JOB = "idJob";
	private static final String NOTIFICACIONES = "notificaciones";
	private static final String CITY = "city";
	private static final String DEPARTMENT = "department";
	private static final String ADDRESS = "address";
	private static final String NUMBER_PHONE = "numberPhone";
	private static final String PASSWORD = "password";
	private static final String PHOTO_PATH = "photoPath";
	private static final String EMAIL = "email";
	private static final String PROFESSIONAL_PORFILE = "professionalPorfile";
	private static final String JOB_TITLE = "jobTitle";
	private static final String BIRTH_DATE = "birthDate";
	private static final String LAST_NAME = "lastName";
	private static final String FIRST_NAME = "firstName";
	private static final String ID = "id";
	private static final String EMPLOYEE = "Employee";
	public static final String EMPLOYEE_LIST = "EmployeeList";
	public static final String PATH_DEPARTMENT_LIST = "files/ColombiaCities.json";
	public static final String PATH_EMPLOYEE_LIST = "files/EmployeeList.xml";
	public static final String PATH_COMPANY_LIST = "files/CompanyList.xml";

	@SuppressWarnings("unchecked")
	public ArrayList<Department> readDepartmentJson() throws FileNotFoundException, IOException, ParseException{
		ArrayList<Department> departmentList = new ArrayList<>();
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject)parser.parse(new FileReader(PATH_DEPARTMENT_LIST));
		JSONArray list = (JSONArray) jsonObject.get("Lista de despartamentos");
		Iterator<JSONObject> iterator = list.iterator();
		while(iterator.hasNext()) {
			ArrayList<City> arrayCity = new ArrayList<>();
			JSONObject info = iterator.next();
			int id = Integer.parseInt(String.valueOf(info.get(ID)));
			String name = String.valueOf(info.get("departamento"));
			JSONArray cities = (JSONArray) info.get("ciudades");
			Iterator<String> citiesList = cities.iterator();
			while (citiesList.hasNext()) {
				String city = citiesList.next();
				arrayCity.add(new City(city, name));
			}
			departmentList.add(new Department(id, name, arrayCity));
		}
		return departmentList;
	}
	
	public void saveXMLEmployee(ArrayList<Employee> employeeList) {
		Document document = new Document();
		Element root = new Element(EMPLOYEE_LIST);
		for (Employee employee : employeeList) {
			Element node = new Element(EMPLOYEE);
			Element idEmployee = new Element(ID);
			Element firstName = new Element(FIRST_NAME);
			Element lastName = new Element(LAST_NAME);
			Element birtDate = new Element(BIRTH_DATE);
			Element jobTitle = new Element(JOB_TITLE);
			Element professionalPorfile = new Element(PROFESSIONAL_PORFILE);
			Element email = new Element(EMAIL);
			Element photoPath = new Element(PHOTO_PATH);
			Element password = new Element(PASSWORD);
			Element numberPhone = new Element(NUMBER_PHONE);
			Element address = new Element(ADDRESS);
			Element department = new Element(DEPARTMENT);
			Element city = new Element(CITY);
			idEmployee.addContent(String.valueOf(employee.getIdEmployee()));
			firstName.addContent(employee.getFistName());
			lastName.addContent(employee.getLastName());
			birtDate.addContent(Utilities.dateToString(employee.getBirthDate()));
			jobTitle.addContent(employee.getJobTitle());
			professionalPorfile.addContent(employee.getProfessionalProfile());
			email.addContent(employee.getEmail());
			photoPath.addContent(employee.getNamePhoto());
			password.addContent(employee.getPassword());
			numberPhone.addContent(employee.getNumberPhone());
			address.addContent(employee.getAddress());
			city.addContent(employee.getCity().getName());
			department.addContent(employee.getCity().getDepartmentName());
			Element notification = new Element(NOTIFICACIONES);
			for (NotificationEmployee noti : employee.getNotification()) {
				Element idJob = new Element(ID_JOB);
				Element statusOffer = new Element(STATUS_OFFER);
				Element date = new Element(DATE);
				idJob.addContent(String.valueOf(noti.getIdJob()));
				statusOffer.addContent(noti.getJobOffer().toString());
				date.addContent(Utilities.dateToString(noti.getDate()));
				notification.addContent(idJob);
				notification.addContent(statusOffer);
				notification.addContent(date);
			}
			node.addContent(idEmployee);
			node.addContent(firstName);
			node.addContent(lastName);
			node.addContent(birtDate);
			node.addContent(jobTitle);
			node.addContent(professionalPorfile);
			node.addContent(email);
			node.addContent(photoPath);
			node.addContent(password);
			node.addContent(numberPhone);
			node.addContent(address);
			node.addContent(department);
			node.addContent(city);
			root.addContent(node);
		}
		document.setRootElement(root);
		XMLOutputter outputter = new XMLOutputter();
		outputter.setFormat(Format.getPrettyFormat());
		try {
			outputter.output(document, new FileWriter(new File(PATH_EMPLOYEE_LIST)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<Employee> loadXMLEmployee(ArrayList<Department> departmentList) throws JDOMException, IOException{
		SAXBuilder builder = new SAXBuilder();
		File file = new File(PATH_EMPLOYEE_LIST);
		ArrayList<Employee> list = new ArrayList<>();
		Document document = (Document) builder.build(file);
		Element rootNode = document.detachRootElement();
		List<Element> EmployeeList = rootNode.getChildren(EMPLOYEE);
		Iterator<Element> iterator = EmployeeList.iterator();
		while(iterator.hasNext()) {
			Element employee = iterator.next();
			int id = Integer.parseInt(employee.getChildText(ID));
			String firstName = employee.getChildText(FIRST_NAME);
			String lastName = employee.getChildText(LAST_NAME);
			Date birthDate = Utilities.StringToDate(employee.getChildText(BIRTH_DATE));
			String jobTitle = employee.getChildText(JOB_TITLE);
			String professionalPorfile = employee.getChildText(PROFESSIONAL_PORFILE);
			String email = employee.getChildText(EMAIL);
			String photoPath = employee.getChildText(PHOTO_PATH);
			String password = employee.getChildText(PASSWORD);
			String numberPhone = employee.getChildText(NUMBER_PHONE);
			String address = employee.getChildText(ADDRESS);
			String department = employee.getChildText(DEPARTMENT);
			String city = employee.getChildText(CITY);
			ArrayList<NotificationEmployee> notification = new ArrayList<>();
			List<Element> notificationList = rootNode.getChildren(NOTIFICACIONES);
			Iterator<Element> iteratorNoti = notificationList.iterator();
			while(iteratorNoti.hasNext()) {
				Element noti = iteratorNoti.next();
				int idJob = Integer.parseInt(noti.getChildText(ID_JOB));
				String statusOffer = noti.getChildText(STATUS_OFFER);
				Date date = Utilities.StringToDate(noti.getChildText(DATE));
				notification.add(new NotificationEmployee(idJob, date, StatusJobOffer.valueOf(statusOffer)));
			}
			
			list.add(new Employee(email, password, photoPath, numberPhone, address, getCity(departmentList, department, city), id, firstName, lastName, birthDate, jobTitle, professionalPorfile, notification));
		}
		return list;
	}
	
	public ArrayList<Company> loadXMLCompany(ArrayList<Department> departmentList) throws JDOMException, IOException{
		SAXBuilder builder = new SAXBuilder();
		File file = new File(PATH_COMPANY_LIST);
		ArrayList<Company> list = new ArrayList<>();
		Document document = (Document) builder.build(file);
		Element rootNode = document.detachRootElement();
		List<Element> companyList = rootNode.getChildren(COMPANY);
		Iterator<Element> iterator = companyList.iterator();
		while(iterator.hasNext()) {
			Element company = iterator.next();
			int idCompany = Integer.parseInt(company.getChildText(ID));
			String name = company.getChildText(NAME);
			String description = company.getChildText(DESCRIPTION);
			String email = company.getChildText(EMAIL);
			String photoPath = company.getChildText(PHOTO_PATH);
			String password = company.getChildText(PASSWORD);
			String numberPhone = company.getChildText(NUMBER_PHONE);
			String address = company.getChildText(ADDRESS);
			String department = company.getChildText(DEPARTMENT);
			String city = company.getChildText(CITY);
			ArrayList<NotificationCompany> notification = new ArrayList<>();
			List<Element> notificationList = rootNode.getChildren(NOTIFICACIONES);
			Iterator<Element> iteratorNoti = notificationList.iterator();
			while(iteratorNoti.hasNext()) {
				Element noti = iteratorNoti.next();
				int idJobOffer = Integer.parseInt(noti.getChildText(ID_JOB_OFFER));
				int idEmployee = Integer.parseInt(noti.getChildText(ID_EMPLOYEE));
				Date date = Utilities.StringToDate(noti.getChildText(DATE));
				String statusOffer = noti.getChildText(STATUS_JOB_OFFER);
				notification.add(new NotificationCompany(idJobOffer, idEmployee, date, StatusOffer.valueOf(statusOffer)));
			}
			
			list.add(new Company(email, password, photoPath, numberPhone, address, getCity(departmentList, department, city), idCompany, name, description, notification));
		}
		return list;
	}
	
	public City getCity(ArrayList<Department> departmentList,String department, String city) {
		for (Department element : departmentList) {
			if (element.getName().equals(department)) {
				return element.getCityByName(city);
			}
		}
		return null;
	}
	
	public void saveXMLCompany(ArrayList<Company> companyList) {
		Document document = new Document();
		Element root = new Element(COMPANY_LIST);
		for (Company company : companyList) {
			Element node = new Element(COMPANY);
			Element idCompany = new Element(ID);
			Element name = new Element(NAME);
			Element description = new Element(DESCRIPTION);
			Element email = new Element(EMAIL);
			Element photoPath = new Element(PHOTO_PATH);
			Element password = new Element(PASSWORD);
			Element numberPhone = new Element(NUMBER_PHONE);
			Element address = new Element(ADDRESS);
			Element department = new Element(DEPARTMENT);
			Element city = new Element(CITY);
			idCompany.addContent(String.valueOf(company.getIdCompany()));
			name.addContent(company.getNameCompany());
			description.addContent(company.getDescription());
			email.addContent(company.getEmail());
			photoPath.addContent(company.getNamePhoto());
			password.addContent(company.getPassword());
			numberPhone.addContent(company.getNumberPhone());
			address.addContent(company.getAddress());
			city.addContent(company.getCity().getName());
			department.addContent(company.getCity().getDepartmentName());
			Element notification = new Element(NOTIFICACIONES);
			for (NotificationCompany noti : company.getNotificationList()) {
				Element idJobOffer = new Element(ID_JOB_OFFER);
				Element idEmployee = new Element(ID_EMPLOYEE);
				Element date = new Element(DATE);
				Element statusOffer = new Element(STATUS_JOB_OFFER);
				idJobOffer.addContent(String.valueOf(noti.getJobOffer()));
				idEmployee.addContent(String.valueOf(noti.getIdEmployee()));
				date.addContent(Utilities.dateToString(noti.getDate()));
				statusOffer.addContent(noti.getOffer().toString());
				notification.addContent(idJobOffer);
				notification.addContent(idEmployee);
				notification.addContent(date);
				notification.addContent(statusOffer);
			}
			node.addContent(idCompany);
			node.addContent(name);
			node.addContent(description);
			node.addContent(email);
			node.addContent(photoPath);
			node.addContent(password);
			node.addContent(numberPhone);
			node.addContent(address);
			node.addContent(department);
			node.addContent(city);
			root.addContent(node);
		}
		document.setRootElement(root);
		XMLOutputter outputter = new XMLOutputter();
		outputter.setFormat(Format.getPrettyFormat());
		try {
			outputter.output(document, new FileWriter(new File(PATH_COMPANY_LIST)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
