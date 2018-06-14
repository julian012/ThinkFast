package view;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import controller.ControllerManager;
import controller.Events;
import models.Request;

public class JFMainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPMenuEmployee menuEmployee;
	private JPMenuCompany menuCompany;
	private ControllerManager controllerManager;
	private JPHomeEmployee homeEmployee;
	private JPApplicationEmployee applicationEmployee;
	private JPSearchJobEmployee searchJobEmployee;
	private JPNotificationEmployee notificationEmployee;
	private JPCurriculumEmployee curriculumEmployee;

	public JFMainWindow(String typeAccount, String name, ControllerManager controllerManager) {
		addWindowListener(controllerManager);
		this.controllerManager = controllerManager;
		switch (Request.valueOf(typeAccount)) {
		case START_PROGRAM_COMPANY:

			break;
		case START_PROGRAM_EMPLOYEE:
			initEmployee(name);
			break;

		default:
			break;
		}
	}

	public void initEmployee(String name) {
		setIconImage(new ImageIcon("files/imagenes/icon.png").getImage());
		setTitle("Pagina principal Candidatos");
		setSize(1054, 535);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		menuEmployee = new JPMenuEmployee(name, controllerManager);
		add(menuEmployee, BorderLayout.NORTH);
		initAllPanelsEmployee();
		add(homeEmployee, BorderLayout.CENTER);
		setVisible(true);
	}

	private void initAllPanelsEmployee(){
		homeEmployee = new JPHomeEmployee();
		applicationEmployee = new JPApplicationEmployee();
		searchJobEmployee = new JPSearchJobEmployee();
		notificationEmployee = new JPNotificationEmployee();
		curriculumEmployee = new JPCurriculumEmployee();
	}

	private void closeAllPanelsEmployee() {
		homeEmployee.setVisible(false);
		applicationEmployee.setVisible(false);
		searchJobEmployee.setVisible(false);
		notificationEmployee.setVisible(false);
		curriculumEmployee.setVisible(false);
	}

	private void openPanelSelected(String value) {
		switch (Events.valueOf(value)) {
		case JBHOME_EMPLOYEE:
			homeEmployee.setVisible(true);
			add(homeEmployee, BorderLayout.CENTER);
			break;
		case JBAPPLICATION_EMPLOYEE:
			applicationEmployee.setVisible(true);
			add(applicationEmployee, BorderLayout.CENTER);
			break;
		case JBSEARCHJOB_EMPLOYEE:
			searchJobEmployee.setVisible(true);
			add(searchJobEmployee, BorderLayout.CENTER);
			break;
		case JBNOTIFICATION_EMPLOYEE:
			notificationEmployee.setVisible(true);
			add(notificationEmployee, BorderLayout.CENTER);
			break;
		case JBCURRICULUM_EMPLOYE:
			curriculumEmployee.setVisible(true);
			add(curriculumEmployee, BorderLayout.CENTER);
			break;
		default:
			break;
		}
	}

	public void selectedButtomEmployee(String value) {
		if (menuEmployee != null) {
			menuEmployee.selectedButtom(value);
			closeAllPanelsEmployee();
			openPanelSelected(value);
		}
	}
}
