package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.toedter.calendar.JDateChooser;

import controller.ClientController;
import controller.Events;

public class JFSingUp extends JFrame implements ActionListener{


	private static final long serialVersionUID = 1L;
	private BufferedImage imageBackGround;
	private BufferedImage imageLogo;
	private BufferedImage userImage;
	private ImageIcon backImage;
	private JButton uploadImage;
	private JLabel jLName;
	private JTextField jTFName;
	private JLabel jLNickName;
	private JTextField jTFNickName;
	private JLabel jLBirthdate;
	private JDateChooser jDCBirthDate;
	private JLabel jLEmail;
	private JTextField jTFEmail;
	private JLabel jLPassword;
	private JPasswordField jPFPassword;
	private JButton jBSingUp;
	private JDFileChooser fileChooser;
	private File pathImageUser;
	private JButton jBBack;

	public JFSingUp(ClientController clientController) {
		fileChooser = new JDFileChooser();
		setUIManager();
		initImages();
		setIconImage(imageLogo);
		setTitle(Constraints.TITLE);
		setSize(850, 475);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Constraints.COLOR_BACKGROUND);
		setLayout(null);
		initComponents(clientController);
		revalidate();
		repaint();
		setVisible(true);
	}

	public void setUIManager() {
		UIManager.put("Label.foreground", Constraints.COLOR_LABEL);
		UIManager.put("Label.font", Constraints.fontSingInLabel);
		UIManager.put("TextField.font", Constraints.fontSingInLabel);
		UIManager.put("TextField.border", BorderFactory.createEmptyBorder());
		UIManager.put("PasswordField.font", Constraints.fontSingIn);
		UIManager.put("PasswordField.border", BorderFactory.createEmptyBorder());
		UIManager.put("Button.font", Constraints.fontSingInLabel);
		UIManager.put("Button.foreground", Color.WHITE);
		UIManager.put("Button.border", BorderFactory.createEmptyBorder());
	}

	public void initImages() {
		try {
			imageBackGround = ImageIO.read(getClass().getResource("../img/background.png"));
			imageLogo = ImageIO.read(getClass().getResource("../img/Logo.png"));
			pathImageUser = new File("data/Adivina quien cambio de fondo.png");
			userImage = ImageIO.read(pathImageUser);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void initComponents(ClientController clientController) {
		uploadImage = new JButton(Constraints.BUTTON_UPLOAD_IMAGE);
		uploadImage.setBackground(Constraints.COLOR_GREEN);
		uploadImage.addActionListener(this);
		uploadImage.setBounds(347, 184, 169, 29);
		this.add(uploadImage);

		jLName = new JLabel(Constraints.JLABEL_NAME);
		jLName.setBounds(185, 240, 200, 25);
		this.add(jLName);

		jTFName = new JTextField();
		jTFName.setBounds(185, 270, 231, 25);
		this.add(jTFName);

		jLNickName = new JLabel(Constraints.JLABEL_NICKNAME);
		jLNickName.setBounds(185, 300, 200, 25);
		this.add(jLNickName);

		jTFNickName = new JTextField();
		jTFNickName.setBounds(185, 330, 231, 25);
		this.add(jTFNickName);

		jLBirthdate = new JLabel(Constraints.JLABEL_BIRTHDATE);
		jLBirthdate.setBounds(185,360,220,25);
		this.add(jLBirthdate);

		jDCBirthDate = new JDateChooser();
		jDCBirthDate.setBounds(185, 390, 231, 25);
		this.add(jDCBirthDate);

		jLEmail = new JLabel(Constraints.LABEL_EMAIL);
		jLEmail.setBounds(452, 240, 200, 25);
		this.add(jLEmail);

		jTFEmail = new JTextField();
		jTFEmail.setBounds(452, 270, 231, 25);
		this.add(jTFEmail);

		jLPassword = new JLabel(Constraints.LABEL_PASSWORD);
		jLPassword.setBounds(452, 300, 200, 25);
		this.add(jLPassword);

		jPFPassword = new JPasswordField();
		jPFPassword.setBounds(452, 330, 231, 25);
		this.add(jPFPassword);

		jBSingUp = new JButton(Constraints.BUTTON_SINGUP);
		jBSingUp.setBackground(Constraints.COLOR_PURPLE);
		jBSingUp.setBounds(477, 383, 180, 36);
		this.add(jBSingUp);

		backImage = new ImageIcon(getClass().getResource("../img/volver.png"));
		jBBack = new JButton(new ImageIcon(backImage.getImage().getScaledInstance(45, 45,Image.SCALE_SMOOTH)));
		jBBack.setOpaque(false);
		jBBack.setContentAreaFilled(false);
		jBBack.setBorderPainted(false);
		jBBack.setToolTipText("Volver");
		jBBack.addActionListener(clientController);
		jBBack.setActionCommand(Events.COME_BACK_CREATE.toString());
		jBBack.setBounds(149, 0, 45, 45);
		this.add(jBBack);
	}

	public void showF() {
		revalidate();
		repaint();
		setVisible(true);
	}
	
	public String getNameUser() {
		return jTFName.getText();
	}
	
	public String getNickname() {
		return jTFNickName.getText();
	}
	
	public Date getBirthdate() {
		return jDCBirthDate.getDate();
	}
	
	public String getEmail() {
		return jTFEmail.getText();
	}
	
	public char[] getPassword() {
		return jPFPassword.getPassword();
	}


	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(imageBackGround.getSubimage(0, 0, 450, 1350), 1, 28, 148, 448, null);
		g.drawImage(imageBackGround.getSubimage(1050, 0, 450, 1350), 704, 28, 148, 448, null);
		g.drawImage(userImage, 347, 41, 175, 175, null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			pathImageUser = new File(fileChooser.getPathFile());
			userImage = ImageIO.read(pathImageUser);
			revalidate();
			repaint();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
