package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import controller.ControllerManager;
import controller.Events;

public class JPEmployeeAccount extends JPanel implements MouseListener, KeyListener{

	public static final int PADDING = 119;
	private static final long serialVersionUID = 1L;
	private JTextField jTid;
	private JTextField jTFistName;
	private JTextField jTLastName;
	private JDateChooser birthDate;
	private JTextField jTJobTile;
	private JTextArea jTAProfessionalPorfile;
	private JTextField jTEmail;
	private JTextField jTAddress;
	private JTextField jTNumberPhone;
	private JComboBox<String> jCDepartmentList;
	private JComboBox<String> jCCitiesList;
	private JTextField jTPath;
	private JPasswordField jTPassword;
	private JPasswordField jTPasswordConfirm;
	private GridBagConstraints constraints;
	private JButton jSave;
	private String email;
	private String password;
	private JLabel jlTitle;
	private ControllerManager controllerManager;
	private String[] departmentList;
	
	public JPEmployeeAccount(String email, String password, ControllerManager controllerManager, String[] departmentList) {
		this.departmentList = departmentList;
		this.controllerManager = controllerManager;
		this.email = email;
		this.password = password;
		initComponents();
		employeeForm();
		setVisible(true);
	}
	
	public void initComponents() {
		setBackground(Constant.BACKGROUND);
		setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
	}
	
	public void employeeForm() {
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridheight = 1;
		constraints.gridwidth = 4;
		constraints.weightx = 1;
		constraints.ipady = 50;
		
		constraints.gridy = 1;
		jlTitle = new JLabel("Nuevo candidato", JLabel.CENTER);
		jlTitle.setOpaque(true);
		jlTitle.setBackground(Constant.COLORBLUE);
		jlTitle.setBorder(BorderFactory.createLineBorder(Constant.COLORBLUE));
		jlTitle.setFont(new Font("Arial", Font.BOLD, 30));
		jlTitle.setForeground(Color.WHITE);
		add(jlTitle, constraints);
		
		constraints.ipady = 10;
		constraints.gridy = 2;
		constraints.insets = new Insets(30, PADDING, 5, PADDING);
		jTid = new JTextField();
		jTid.setBorder(BorderFactory.createTitledBorder("Documento"));
		add(jTid, constraints);
		constraints.insets = new Insets(0, PADDING, 5, PADDING);
		constraints.gridy = 3;
		jTFistName = new JTextField();
		jTFistName.setBorder(BorderFactory.createTitledBorder("Nombres"));
		add(jTFistName, constraints);
		constraints.gridy = 4;
		jTLastName = new JTextField();
		jTLastName.setBorder(BorderFactory.createTitledBorder("Apellidos"));
		add(jTLastName, constraints);
		constraints.gridy = 5;
		birthDate = new JDateChooser();
		birthDate.setBorder(BorderFactory.createTitledBorder("Fecha de nacimiento"));
		add(birthDate, constraints);
		
		constraints.gridy = 6;
		jTEmail = new JTextField(email);
		jTEmail.setEnabled(false);
		jTEmail.setBorder(BorderFactory.createTitledBorder("Email"));
		add(jTEmail, constraints);
		
		constraints.gridy = 7;
		jTAddress = new JTextField();
		jTAddress.setBorder(BorderFactory.createTitledBorder("Direccion"));
		add(jTAddress, constraints);
		
		constraints.gridy = 8;
		jTNumberPhone = new JTextField();
		jTNumberPhone.setBorder(BorderFactory.createTitledBorder("Numero de telefono"));
		add(jTNumberPhone, constraints);
		
		constraints.gridy = 9;
		jCDepartmentList = new JComboBox<>(departmentList);
		jCDepartmentList.setBorder(BorderFactory.createTitledBorder("Departamento"));
		jCDepartmentList.addActionListener(controllerManager);
		jCDepartmentList.setActionCommand(Events.GET_DEPARTMENT_EMPLOYEE.toString());
		add(jCDepartmentList, constraints);
		
		constraints.gridy = 10;
		jCCitiesList = new JComboBox<>();
		jCCitiesList.setEnabled(false);
		jCCitiesList.setBorder(BorderFactory.createTitledBorder("Ciudad"));
		jCCitiesList.setEnabled(false);
		add(jCCitiesList, constraints);
		
		constraints.gridy = 11;
		jTJobTile = new JTextField();
		jTJobTile.setBorder(BorderFactory.createTitledBorder("Titulo profesional"));
		add(jTJobTile, constraints);
		
		constraints.ipady = 40;
		constraints.gridy = 12;
		jTAProfessionalPorfile = new JTextArea();
		jTAProfessionalPorfile.setWrapStyleWord(true);
		jTAProfessionalPorfile.setLineWrap(true);
		JScrollPane jScrollPane = new JScrollPane(jTAProfessionalPorfile);
		jScrollPane.setBorder(BorderFactory.createTitledBorder("perfil profesional"));
		add(jScrollPane, constraints);
		
		constraints.ipady = 20;
		constraints.gridy = 13;
		jTPath = new JTextField();
		jTPath.addMouseListener(this);
		jTPath.setText("click para seleccional la imagen");
		JScrollPane panePath = new JScrollPane(jTPath);
		panePath.setBorder(BorderFactory.createTitledBorder("Seleccionar imagen"));
		add(panePath, constraints);
		
		constraints.ipady = 10;
		constraints.gridy = 14;
		jTPassword = new JPasswordField(password);
		jTPassword.setBorder(BorderFactory.createTitledBorder("Contraseña"));
		jTPassword.setEditable(false);
		add(jTPassword, constraints);
		
		constraints.gridy = 15;
		jTPasswordConfirm = new JPasswordField();
		jTPasswordConfirm.setBorder(BorderFactory.createTitledBorder("Confirmar contraseña"));
		jTPasswordConfirm.addKeyListener(this);
		add(jTPasswordConfirm, constraints);
		
		constraints.gridy = 16;
		jSave = new JButton(Constant.BUTTOM_CREATE_ACCOUNT);
		jSave.setBackground(Constant.COLOR_ORANGE);
		jSave.setForeground(Color.WHITE);
		jSave.setEnabled(false);
		jSave.setFont(Constant.JBUTTOM_SIGNIN);
		jSave.setBorder(BorderFactory.createLineBorder(Constant.COLOR_ORANGE));
		jSave.setActionCommand(Events.CREATE_ACCOUNT_EMPLOYEE.toString());
		jSave.addActionListener(controllerManager);
		add(jSave, constraints);
		
	}
	
	public void setCitiesList(String[] values) {
		jCCitiesList.setVisible(false);
		constraints.ipady = 10;
		constraints.gridy = 10;
		jCCitiesList = new JComboBox<>(values);
		jCCitiesList.setBorder(BorderFactory.createTitledBorder("Ciudad"));
		add(jCCitiesList, constraints);
	}
	
	public String getDepartment() {
		return String.valueOf(jCDepartmentList.getSelectedItem());
	}
	
	public int getId() {
		return Integer.parseInt(jTid.getText());
	}
	
	public String pathPhoto() {
		return jTPath.getText();
	}
	
	public String getEmail() {
		return jTEmail.getText();
	}
	
	public String getPassword() {
		return new String(jTPassword.getPassword());
	}
	
	public String getNumberPhone() {
		return jTNumberPhone.getText();
	}
	
	public String getAddress() {
		return jTAddress.getText();
	}
	
	public String getCity() {
		return String.valueOf(jCCitiesList.getSelectedItem());
	}
	
	public String firstName() {
		return jTFistName.getText();
	}
	
	public String lastName() {
		return jTLastName.getText();
	}
	
	public Date getBirthDate() {
		return birthDate.getDate();
	}
	
	public String getJobTitle() {
		return jTJobTile.getText();
	}
	
	public String getProfesionalPofile() {
		return jTAProfessionalPorfile.getText();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JDFileChooser chooser = new JDFileChooser();
		try {
			String path = chooser.getPathFile();
			jTPath.setText(path);
			jTPath.setEditable(false);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		String values = new String(jTPasswordConfirm.getPassword());
		if (values.equals(password)) {
			jSave.setEnabled(true);
		}else {
			jSave.setEnabled(false);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
