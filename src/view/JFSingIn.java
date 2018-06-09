package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import controller.ClientController;
import controller.Events;

public class JFSingIn extends JFrame {

	private static final long serialVersionUID = 1L;
	private BufferedImage imageBackGround;
	private BufferedImage imageLogo;
	private JLabel jLTitle;
	private JLabel jLEmail;
	private JTextField jTFEmail;
	private JLabel jLPassword;
	private JPasswordField jPFPassword;
	private JButton jBSingIn;
	private JButton jBSingUp;
	
	
	public JFSingIn(ClientController clientController) {
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
		setVisible(true);
	}
	
	public void setUIManager() {
		UIManager.put("Label.foreground", Color.white);
		UIManager.put("Label.font", Constraints.fontSingIn);
		UIManager.put("TextField.font", Constraints.fontSingIn);
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
			System.out.println("No dio error");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void initComponents(ClientController clientController) {
		jLTitle = new JLabel(Constraints.TITLE);
		jLTitle.setBounds(628, 100, 150, 30);
		this.add(jLTitle);
		
		jLEmail = new JLabel(Constraints.LABEL_EMAIL);
		jLEmail.setForeground(Constraints.COLOR_LABEL);
		jLEmail.setFont(Constraints.fontSingInLabel);
		jLEmail.setBounds(567, 157, 200, 18);
		this.add(jLEmail);
		
		jTFEmail = new JTextField();
		jTFEmail.setBounds(566, 187, 231, 38);
		this.add(jTFEmail);
		
		jLPassword = new JLabel(Constraints.LABEL_PASSWORD);
		jLPassword.setForeground(Constraints.COLOR_LABEL);
		jLPassword.setFont(Constraints.fontSingInLabel);
		jLPassword.setBounds(567, 241, 200, 18);
		this.add(jLPassword);
		
		jPFPassword = new JPasswordField();
		jPFPassword.setBounds(566, 269, 231, 38);
		this.add(jPFPassword);
		
		jBSingIn = new JButton(Constraints.BUTTON_SINGIN);
		jBSingIn.setBackground(Constraints.COLOR_GREEN);
		jBSingIn.addActionListener(clientController);
		jBSingIn.setActionCommand(Events.SING_IN.toString());
		jBSingIn.setBounds(567, 332, 231, 29);
		this.add(jBSingIn);
		
		jBSingUp = new JButton(Constraints.BUTTON_SINGUP);
		jBSingUp.setBackground(Constraints.COLOR_PURPLE);
		jBSingUp.addActionListener(clientController);
		jBSingUp.setActionCommand(Events.SING_UP.toString());
		jBSingUp.setBounds(567, 372, 231, 29);
		this.add(jBSingUp);
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
		g.drawImage(imageLogo, 637, 33, 90, 90, null);
		g.drawImage(imageBackGround, 1, 28, 497, 448, null);
	}
}
