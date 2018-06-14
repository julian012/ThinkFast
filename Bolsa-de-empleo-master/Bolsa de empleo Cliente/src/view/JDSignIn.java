package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.ControllerManager;
import controller.Events;

public class JDSignIn extends JFrame implements MouseListener, WindowListener, ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton jbCompany;
	private JButton jbEmployee;
	private JButton jbCreateAccount;
	private boolean company,employee, createAccount;
	private JTextField jTFEmail;
	private JPasswordField jTFPassword;
	private JButton jBSignIn;
	private GridBagConstraints constraints;
	private int xCompany, xEmployee, xCreateAccount;
	private ControllerManager controllerManager;
	private String getActualButtom;

	public JDSignIn(ControllerManager controllerManager) {
		this.controllerManager = controllerManager;
		setTitle(Constant.TITLE);
		this.getContentPane().setBackground(Constant.BACKGROUND);
		setSize(543, 225);
		setIconImage(new ImageIcon("files/imagenes/icon.png").getImage());
		setUndecorated(false);
		setLocationRelativeTo(null);
		setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		addWindowListener(this);
		initComponents();
		initValues();
		setResizable(false);
		setVisible(true);
	}

	public void initValues() {
		employee = false;
		company = true;
		createAccount = false;
		getActualButtom = Events.SELECT_JCOMPANY.toString();
		changeColorCompany(company);
	}

	public void initComponents() {
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridheight = 1;
		constraints.gridwidth = 2;
		constraints.weightx = 1;
		constraints.ipady = 28;
		constraints.gridy = 1;
		addCompany();
		addEmployee();
		addCreateAccount();
		addJTextField();
	}

	public void addJTextField() {
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridx = 1;
		constraints.gridwidth = 6;
		constraints.ipady = 10;
		constraints.gridy = 2;
		constraints.insets = new Insets(30, 139, 5, 139);
		jTFEmail = new JTextField("julicamnet@gmail.com");
		jTFEmail.setBorder(BorderFactory.createTitledBorder("Email"));
		add(jTFEmail, constraints);
		constraints.gridy = 3;
		constraints.insets = new Insets(0, 139, 5, 139);
		jTFPassword = new JPasswordField("julian012");
		jTFPassword.setBorder(BorderFactory.createTitledBorder("Contraseña"));
		add(jTFPassword, constraints);
		constraints.gridy = 4;
		constraints.insets = new Insets(0, 139, 10, 139);
		jBSignIn = new JButton(Constant.BUTTOM_SIGNIN);
		jBSignIn.setBackground(Constant.COLOR_ORANGE);
		jBSignIn.setForeground(Color.WHITE);
		jBSignIn.setFont(Constant.JBUTTOM_SIGNIN);
		jBSignIn.setBorder(BorderFactory.createLineBorder(Constant.COLOR_ORANGE));
		jBSignIn.addActionListener(controllerManager);
		jBSignIn.setActionCommand(Events.SIGN_IN.toString());
		add(jBSignIn, constraints);
	}

	public void addCompany() {
		constraints.gridx = 1;
		jbCompany = new JButton(Constant.BUTTOM_COMPANY);
		jbCompany.setBackground(Constant.COLORBLUE);
		jbCompany.setBorder(BorderFactory.createLineBorder(Constant.COLORBLUE));
		jbCompany.setFont(Constant.FONT_MENU);
		jbCompany.setForeground(Color.WHITE);
		jbCompany.addMouseListener(this);
		jbCompany.addActionListener(this);
		jbCompany.setActionCommand(Events.SELECT_JCOMPANY.toString());
		add(jbCompany, constraints);
	}

	public void addEmployee() {
		constraints.gridx = 3;
		jbEmployee = new JButton(Constant.BUTTOM_EMPOLYEE);
		jbEmployee.setBackground(Constant.COLORBLUE);
		jbEmployee.setBorder(BorderFactory.createLineBorder(Constant.COLORBLUE));
		jbEmployee.setFont(Constant.FONT_MENU);
		jbEmployee.setForeground(Color.WHITE);
		jbEmployee.addMouseListener(this);
		jbEmployee.addActionListener(this);
		jbEmployee.setActionCommand(Events.SELECT_JEMPLOYEE.toString());
		add(jbEmployee, constraints);
	}

	public void addCreateAccount() {
		constraints.gridx = 5;
		jbCreateAccount = new JButton(Constant.BUTTOM_CREATE_ACCOUNT);
		jbCreateAccount.setBackground(Constant.COLORBLUE);
		jbCreateAccount.setBorder(BorderFactory.createLineBorder(Constant.COLORBLUE));
		jbCreateAccount.setFont(Constant.FONT_MENU);
		jbCreateAccount.setForeground(Color.WHITE);
		jbCreateAccount.addMouseListener(this);
		jbCreateAccount.addActionListener(this);
		jbCreateAccount.setActionCommand(Events.SELECT_CREATEACCOUNT.toString());
		add(jbCreateAccount, constraints);
	}

	public void changeColorButtom(int x) {
		if (x == xCompany) {
			changeColorCompany(true);
		}else if(x == xEmployee) {
			changeColorEmployee(true);
		}else if (x == xCreateAccount) {
			changeColorCreateAccount(true);
		}
	}

	public void goBackChangeColor(int x) {
		if (x == xCompany) {
			changeColorCompany(false);
		}else if(x == xEmployee) {
			changeColorEmployee(false);
		}else if (x == xCreateAccount) {
			changeColorCreateAccount(false);
		}
	}

	public void changeColorCompany(boolean value) {
		if (value) {
			jbCompany.setBackground(Constant.COLOR_SELECTED);
			jbCompany.setBorder(BorderFactory.createLineBorder(Constant.COLOR_SELECTED));
		}else {
			if (company) {
				jbCompany.setBackground(Constant.COLOR_SELECTED);
				jbCompany.setBorder(BorderFactory.createLineBorder(Constant.COLOR_SELECTED));
				jBSignIn.setText(Constant.BUTTOM_SIGNIN);
			}else {
				jbCompany.setBackground(Constant.COLORBLUE);
				jbCompany.setBorder(BorderFactory.createLineBorder(Constant.COLORBLUE));
			}
		}
	}

	public void changeColorEmployee(boolean value) {
		if (value) {
			jbEmployee.setBackground(Constant.COLOR_SELECTED);
			jbEmployee.setBorder(BorderFactory.createLineBorder(Constant.COLOR_SELECTED));
		}else {
			if (employee) {
				jbEmployee.setBackground(Constant.COLOR_SELECTED);
				jbEmployee.setBorder(BorderFactory.createLineBorder(Constant.COLOR_SELECTED));
				jBSignIn.setText(Constant.BUTTOM_SIGNIN);
			}else {
				jbEmployee.setBackground(Constant.COLORBLUE);
				jbEmployee.setBorder(BorderFactory.createLineBorder(Constant.COLORBLUE));
			}
		}
	}

	public void changeColorCreateAccount(boolean value) {
		if (value) {
			jbCreateAccount.setBackground(Constant.COLOR_SELECTED);
			jbCreateAccount.setBorder(BorderFactory.createLineBorder(Constant.COLOR_SELECTED));
		}else {
			if (createAccount) {
				jbCreateAccount.setBackground(Constant.COLOR_SELECTED);
				jbCreateAccount.setBorder(BorderFactory.createLineBorder(Constant.COLOR_SELECTED));
				jBSignIn.setText("Continuar");
			}else {
				jbCreateAccount.setBackground(Constant.COLORBLUE);
				jbCreateAccount.setBorder(BorderFactory.createLineBorder(Constant.COLORBLUE));
			}
		}
	}

	public void setSelectedButtom(String value) {
		getActualButtom = value;
	}

	public String getSelectedButtom() {
		return getActualButtom;
	}

	public String getEmail() {
		return jTFEmail.getText();
	}

	public char[] getPassword() {
		return jTFPassword.getPassword();
	}
	
	public void selectedButtom(String value) {
		if (value.equals(Events.SELECT_JEMPLOYEE.toString())) {
			employee = true;
			company = false;
			createAccount = false;
		}else if(value.equals(Events.SELECT_JCOMPANY.toString())) {
			employee = false;
			company = true;
			createAccount = false;
		}else if (value.equals(Events.SELECT_CREATEACCOUNT.toString())) {
			employee = false;
			company = false;
			createAccount = true;
		}
		changeColorEmployee(false);
		changeColorCompany(false);
		changeColorCreateAccount(false);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		changeColorButtom(e.getComponent().getX());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		goBackChangeColor(e.getComponent().getX());
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void paint(Graphics arg0) {
		xCompany = jbCompany.getX();
		xEmployee = jbEmployee.getX();
		xCreateAccount = jbCreateAccount.getX();
	}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowClosed(WindowEvent e) {
		System.exit(0);
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void actionPerformed(ActionEvent e) {
		String value = e.getActionCommand();
		getActualButtom = value;
		selectedButtom(value);
	}

}
