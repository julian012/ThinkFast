package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import controller.Events;



public class JDSystemMessage extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel jLTitle;
	private JTextArea jTAMessage;
	private JButton jBExit;
	private InputStream input;
	private Properties prop;
	private ImageIcon image;
	private String valueTile;
	private String valueMessage;
	private JButton imageMessage;
	
	public JDSystemMessage(String request, JFrame frame) {
		super(frame, true);
		loadProperties(request);
		setSize(446,225);
		setLocationRelativeTo(null);
		setTitle(valueTile);
		setResizable(false);
		setLayout(null);
		initComponents();
		timeToLoad();
	}
	
	public void timeToLoad() {
		try {
			Thread.sleep(1000);
			revalidate();
			repaint();
			setVisible(true);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void loadProperties(String request) {
		prop = new Properties();
		try {
			input = new FileInputStream("data/messageClientData/" +  request + ".properties");
			prop.load(input);
			image = new ImageIcon(getClass().getResource("../img/" + prop.getProperty("nameImage")));
			configSystemUI();
			valueTile = prop.getProperty("title");
			valueMessage = prop.getProperty("message");
			getContentPane().setBackground(Color.decode("#" + prop.getProperty("background_color")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void configSystemUI() {
		UIManager.put("Button.background", Color.decode("#" + prop.getProperty("buttom_color")));
		UIManager.put("Button.border", BorderFactory.createEmptyBorder());
		UIManager.put("Button.font", Constraints.fontSingInLabel);
		UIManager.put("Button.foreground", Color.white);
		UIManager.put("Label.font", Constraints.fontSingIn);
		UIManager.put("Label.foreground", Color.decode("#" + prop.getProperty("foreground_color")));
		UIManager.put("TextArea.font", Constraints.fontSingInLabel);
		UIManager.put("TextArea.foreground", Color.decode("#" + prop.getProperty("foreground_color")));
		UIManager.put("TextArea.background", Color.decode("#" + prop.getProperty("background_color")));
		UIManager.put("TextArea.border", BorderFactory.createEmptyBorder());
	}
	
	public void initComponents() {
		jLTitle = new JLabel(valueTile, JLabel.CENTER);
		jLTitle.setBounds(193,33,253,29);
		this.add(jLTitle);
		jTAMessage = new JTextArea(valueMessage);
		jTAMessage.setBounds(193, 73, 253, 50);
		jTAMessage.setEditable(false);
		jTAMessage.setLineWrap(true);
		jTAMessage.setWrapStyleWord(true);
		this.add(jTAMessage);
		jBExit = new JButton("Aceptar");
		jBExit.addActionListener(e -> this.setVisible(false));
		jBExit.setBounds(193, 136, 231, 29);
		this.add(jBExit);
		
		imageMessage = new JButton(new ImageIcon(image.getImage().getScaledInstance(193, 193,Image.SCALE_SMOOTH)));
		imageMessage.setOpaque(false);
		imageMessage.setContentAreaFilled(false);
		imageMessage.setBorderPainted(false);
		imageMessage.setBounds(0, 0, 193, 193);
		this.add(imageMessage);
	}
}
