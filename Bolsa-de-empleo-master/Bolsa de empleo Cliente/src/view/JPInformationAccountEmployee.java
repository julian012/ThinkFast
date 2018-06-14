package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.Events;

public class JPInformationAccountEmployee extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private GridBagConstraints constraints;
	private JLabel jLTitle;
	private JLabel jLSubTitle;
	private JTextField jTid;
	private JTextField jTFistName;
	private JTextField jTLastName;
	private JTextField birthDate;
	private JTextField jTJobTile;
	private JTextArea jTAProfessionalPorfile;
	private JTextField jTEmail;
	private JTextField jTAddress;
	private JTextField jTNumberPhone;
	private JTextField jCDepartmentList;
	private JTextField jCCitiesList;
	private JPasswordField jTPassword;
	public static final int PADDING = 130;
	

	public JPInformationAccountEmployee() {
		setBackground(Color.WHITE);
		setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		initComponents();
	}
	
	public void initComponents() {
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridheight = 1;
		constraints.gridwidth = 1;
		constraints.weightx = 1;
		constraints.ipady = 40;
		constraints.gridy = 1;
		jLTitle = new JLabel("Hoja de vida");
		jLTitle.setFont(new Font("Arial", Font.BOLD, 22));
		jLTitle.setOpaque(true);
		jLTitle.setBackground(Color.decode("#F7F5F5"));
		add(jLTitle, constraints);
		
		constraints.gridy = 2;
		jLSubTitle = new JLabel("Datos personales");
		jLSubTitle.setFont(new Font("Arial", Font.BOLD, 17));
		jLSubTitle.setBackground(Color.decode("#F9F9F9"));
		jLSubTitle.setBorder(BorderFactory.createLineBorder(Color.decode("#CCCCCC")));
		jLSubTitle.setOpaque(true);
		add(jLSubTitle, constraints);
		
		constraints.ipady = 10;
		constraints.gridy = 3;
		constraints.insets = new Insets(30, PADDING, 5, PADDING);
		jTid = new JTextField();
		jTid.setBorder(BorderFactory.createTitledBorder("Documento"));
		add(jTid, constraints);
		constraints.insets = new Insets(0, PADDING, 5, PADDING);
		constraints.gridy = 4;
		jTFistName = new JTextField();
		jTFistName.setBorder(BorderFactory.createTitledBorder("Nombres"));
		add(jTFistName, constraints);
		constraints.gridy = 5;
		jTLastName = new JTextField();
		jTLastName.setBorder(BorderFactory.createTitledBorder("Apellidos"));
		add(jTLastName, constraints);
		constraints.gridy = 6;
		birthDate = new JTextField();
		birthDate.setBorder(BorderFactory.createTitledBorder("Fecha de nacimiento"));
		add(birthDate, constraints);
		
		constraints.gridy = 7;
		jTEmail = new JTextField();
		jTEmail.setEnabled(false);
		jTEmail.setBorder(BorderFactory.createTitledBorder("Email"));
		add(jTEmail, constraints);
		
		constraints.gridy = 8;
		jTAddress = new JTextField();
		jTAddress.setBorder(BorderFactory.createTitledBorder("Direccion"));
		add(jTAddress, constraints);
		
		constraints.gridy = 9;
		jTNumberPhone = new JTextField();
		jTNumberPhone.setBorder(BorderFactory.createTitledBorder("Numero de telefono"));
		add(jTNumberPhone, constraints);
		
		constraints.gridy = 10;
		jCDepartmentList = new JTextField();
		jCDepartmentList.setBorder(BorderFactory.createTitledBorder("Departamento"));
		jCDepartmentList.setActionCommand(Events.GET_DEPARTMENT_EMPLOYEE.toString());
		jCDepartmentList.setEditable(false);
		add(jCDepartmentList, constraints);
		
		constraints.gridy = 11;
		jCCitiesList = new JTextField();
		jCCitiesList.setEnabled(false);
		jCCitiesList.setBorder(BorderFactory.createTitledBorder("Ciudad"));
		jCCitiesList.setEnabled(false);
		add(jCCitiesList, constraints);
		
		constraints.gridy = 12;
		jTJobTile = new JTextField();
		jTJobTile.setBorder(BorderFactory.createTitledBorder("Titulo profesional"));
		add(jTJobTile, constraints);
		
		constraints.ipady = 40;
		constraints.gridy = 13;
		jTAProfessionalPorfile = new JTextArea();
		jTAProfessionalPorfile.setWrapStyleWord(true);
		jTAProfessionalPorfile.setLineWrap(true);
		JScrollPane jScrollPane = new JScrollPane(jTAProfessionalPorfile);
		jScrollPane.setBorder(BorderFactory.createTitledBorder("perfil profesional"));
		add(jScrollPane, constraints);
		
		constraints.ipady = 10;
		constraints.gridy = 14;
		jTPassword = new JPasswordField();
		jTPassword.setBorder(BorderFactory.createTitledBorder("Contraseña"));
		jTPassword.setEditable(true);
		add(jTPassword, constraints);
		
	}
}
