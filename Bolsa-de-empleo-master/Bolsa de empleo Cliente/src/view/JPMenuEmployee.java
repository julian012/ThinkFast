package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.ControllerManager;
import controller.Events;

public class JPMenuEmployee extends JPanel implements MouseListener{
	
	private static final long serialVersionUID = 1L;
	private boolean home,aplication, searchJob, notification, curriculum;
	private int xHome, xAplication, xSearchJob, xNotification, xCurriculum;
	private JButton jBHome;
	private JButton jBApplication;
	private JButton jBSearchJob;
	private JButton jBNotification;
	private JButton jBCurriculum;
	private String nameUser;
	private GridBagConstraints constraints;
	private JPHeader jpHeader;
	private ControllerManager controllerManager;
	
	public JPMenuEmployee(String nameUser, ControllerManager controllerManager) {
		this.controllerManager = controllerManager;
		this.nameUser = nameUser;
		constraints = new GridBagConstraints();
		setLayout(new GridBagLayout());
		initHeader();
		initValues();
	}
	
	public void initValues() {
		aplication = false;
		home = true;
		searchJob = false;
		notification = false;
		curriculum = false;
		changeColorHome(home);
	}
	
	public void initHeader() {
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridheight = 1;
		constraints.gridwidth = 5;
		constraints.weightx = 1;
		constraints.ipady = 50;
		constraints.gridy = 1;
		constraints.gridx = 1;
		jpHeader = new JPHeader(nameUser, null);
		add(jpHeader, constraints);
		
		constraints.gridwidth = 1;
		constraints.gridy = 2;
		constraints.ipady = 35;
		constraints.gridx = 1;
		jBHome = new JButton("Inicio");
		jBHome.setBackground(Color.WHITE);
		jBHome.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		jBHome.setFont(Constant.FONT_MENU_FRAME);
		jBHome.setForeground(Constant.COLOR_TEXT_GRAY);
		jBHome.addMouseListener(this);
		jBHome.addActionListener(controllerManager);
		jBHome.setActionCommand(Events.JBHOME_EMPLOYEE.toString());
		add(jBHome, constraints);
		
		constraints.gridx = 2;
		jBApplication = new JButton("Aplicaciones");
		jBApplication.setBackground(Color.WHITE);
		jBApplication.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		jBApplication.setFont(Constant.FONT_MENU_FRAME);
		jBApplication.setForeground(Constant.COLOR_TEXT_GRAY);
		jBApplication.addMouseListener(this);
		jBApplication.addActionListener(controllerManager);
		jBApplication.setActionCommand(Events.JBAPPLICATION_EMPLOYEE.toString());
		add(jBApplication, constraints);
		
		constraints.gridx = 3;
		jBSearchJob = new JButton("Buscar ofertas");
		jBSearchJob.setBackground(Color.WHITE);
		jBSearchJob.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		jBSearchJob.setFont(Constant.FONT_MENU_FRAME);
		jBSearchJob.setForeground(Constant.COLOR_TEXT_GRAY);
		jBSearchJob.addMouseListener(this);
		jBSearchJob.addActionListener(controllerManager);
		jBSearchJob.setActionCommand(Events.JBSEARCHJOB_EMPLOYEE.toString());
		add(jBSearchJob, constraints);
		
		constraints.gridx = 4;
		jBNotification= new JButton("Notificaciones");
		jBNotification.setBackground(Color.WHITE);
		jBNotification.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		jBNotification.setFont(Constant.FONT_MENU_FRAME);
		jBNotification.setForeground(Constant.COLOR_TEXT_GRAY);
		jBNotification.addMouseListener(this);
		jBNotification.addActionListener(controllerManager);
		jBNotification.setActionCommand(Events.JBNOTIFICATION_EMPLOYEE.toString());
		add(jBNotification, constraints);
		
		constraints.gridx = 5;
		jBCurriculum = new JButton("Hoja de vida");
		jBCurriculum.setBackground(Color.WHITE);
		jBCurriculum.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		jBCurriculum.setFont(Constant.FONT_MENU_FRAME);
		jBCurriculum.setForeground(Constant.COLOR_TEXT_GRAY);
		jBCurriculum.addMouseListener(this);
		jBCurriculum.addActionListener(controllerManager);
		jBCurriculum.setActionCommand(Events.JBCURRICULUM_EMPLOYE.toString());
		add(jBCurriculum, constraints);
		
	}
	
	public void changeColorHome(boolean value) {
		if (value) {
			jBHome.setBackground(Constant.COLOR_SELECTED);
			jBHome.setBorder(BorderFactory.createLineBorder(Constant.COLOR_SELECTED));
			jBHome.setForeground(Color.WHITE);
		}else {
			if (home) {
				jBHome.setBackground(Constant.COLOR_SELECTED);
				jBHome.setBorder(BorderFactory.createLineBorder(Constant.COLOR_SELECTED));
				jBHome.setForeground(Color.WHITE);
			}else {
				jBHome.setBackground(Color.WHITE);
				jBHome.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				jBHome.setForeground(Constant.COLOR_TEXT_GRAY);
			}
		}
	}
	
	public void changeColorApplication(boolean value) {
		if (value) {
			jBApplication.setBackground(Constant.COLOR_SELECTED);
			jBApplication.setBorder(BorderFactory.createLineBorder(Constant.COLOR_SELECTED));
			jBApplication.setForeground(Color.WHITE);
		}else {
			if (aplication) {
				jBApplication.setBackground(Constant.COLOR_SELECTED);
				jBApplication.setBorder(BorderFactory.createLineBorder(Constant.COLOR_SELECTED));
				jBApplication.setForeground(Color.WHITE);
			}else {
				jBApplication.setBackground(Color.WHITE);
				jBApplication.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				jBApplication.setForeground(Constant.COLOR_TEXT_GRAY);
			}
		}
	}
	
	public void changeColorSearchJob(boolean value) {
		if (value) {
			jBSearchJob.setBackground(Constant.COLOR_SELECTED);
			jBSearchJob.setBorder(BorderFactory.createLineBorder(Constant.COLOR_SELECTED));
			jBSearchJob.setForeground(Color.WHITE);
		}else {
			if (searchJob) {
				jBSearchJob.setBackground(Constant.COLOR_SELECTED);
				jBSearchJob.setBorder(BorderFactory.createLineBorder(Constant.COLOR_SELECTED));
				jBSearchJob.setForeground(Color.WHITE);
			}else {
				jBSearchJob.setBackground(Color.WHITE);
				jBSearchJob.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				jBSearchJob.setForeground(Constant.COLOR_TEXT_GRAY);
			}
		}
	}
	
	public void changeColorNotification(boolean value) {
		if (value) {
			jBNotification.setBackground(Constant.COLOR_SELECTED);
			jBNotification.setBorder(BorderFactory.createLineBorder(Constant.COLOR_SELECTED));
			jBNotification.setForeground(Color.WHITE);
		}else {
			if (notification) {
				jBNotification.setBackground(Constant.COLOR_SELECTED);
				jBNotification.setBorder(BorderFactory.createLineBorder(Constant.COLOR_SELECTED));
				jBNotification.setForeground(Color.WHITE);
			}else {
				jBNotification.setBackground(Color.WHITE);
				jBNotification.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				jBNotification.setForeground(Constant.COLOR_TEXT_GRAY);
			}
		}
	}
	
	public void changeColorCurriculum(boolean value) {
		if (value) {
			jBCurriculum.setBackground(Constant.COLOR_SELECTED);
			jBCurriculum.setBorder(BorderFactory.createLineBorder(Constant.COLOR_SELECTED));
			jBCurriculum.setForeground(Color.WHITE);
		}else {
			if (curriculum) {
				jBCurriculum.setBackground(Constant.COLOR_SELECTED);
				jBCurriculum.setBorder(BorderFactory.createLineBorder(Constant.COLOR_SELECTED));
				jBCurriculum.setForeground(Color.WHITE);
			}else {
				jBCurriculum.setBackground(Color.WHITE);
				jBCurriculum.setBorder(BorderFactory.createLineBorder(Color.WHITE));
				jBCurriculum.setForeground(Constant.COLOR_TEXT_GRAY);
			}
		}
	}
	
	public void changeColorButtom(int x) {
		if (x == xHome) {
			changeColorHome(true);
		}else if(x == xAplication) {
			changeColorApplication(true);
		}else if (x == xSearchJob) {
			changeColorSearchJob(true);
		}else if (x == xNotification) {
			changeColorNotification(true);
		}else if (x == xCurriculum) {
			changeColorCurriculum(true);
		}
	}

	public void goBackChangeColor(int x) {
		if (x == xHome) {
			changeColorHome(false);
		}else if(x == xAplication) {
			changeColorApplication(false);
		}else if (x == xSearchJob) {
			changeColorSearchJob(false);
		}else if (x == xNotification) {
			changeColorNotification(false);
		}else if (x == xCurriculum) {
			changeColorCurriculum(false);
		}
	}
	
	public void selectedButtom(String value) {
		if (value.equals(Events.JBHOME_EMPLOYEE.toString())) {
			aplication = false;
			home = true;
			searchJob = false;
			notification = false;
			curriculum = false;
		}else if(value.equals(Events.JBAPPLICATION_EMPLOYEE.toString())) {
			aplication = true;
			home = false;
			searchJob = false;
			notification = false;
			curriculum = false;
		}else if (value.equals(Events.JBSEARCHJOB_EMPLOYEE.toString())) {
			aplication = false;
			home = false;
			searchJob = true;
			notification = false;
			curriculum = false;
		}else if (value.equals(Events.JBNOTIFICATION_EMPLOYEE.toString())) {
			aplication = false;
			home = false;
			searchJob = false;
			notification = true;
			curriculum = false;
		}else if (value.equals(Events.JBCURRICULUM_EMPLOYE.toString())) {
			aplication = false;
			home = false;
			searchJob = false;
			notification = false;
			curriculum = true;
		}
		changeColorApplication(false);
		changeColorCurriculum(false);
		changeColorHome(false);
		changeColorNotification(false);
		changeColorSearchJob(false);
	}
	
	@Override
	public void paint(Graphics arg0) {
		super.paint(arg0);
		xHome = jBHome.getX();
		xAplication = jBApplication.getX();
		xSearchJob = jBSearchJob.getX();
		xNotification = jBNotification.getX();
		xCurriculum = jBCurriculum.getX();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		changeColorButtom(e.getComponent().getX());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		goBackChangeColor(e.getComponent().getX());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
