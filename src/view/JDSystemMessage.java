package view;

import java.awt.Color;
import java.awt.Image;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.UIManager;

import controller.ClientController;

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
	private boolean listener;
	private String buttonMessage;
	private String actionCommand;

	public JDSystemMessage(String request, ClientController controller) {
		setModal(true);
		this.setModalityType(DEFAULT_MODALITY_TYPE);
		loadProperties(request);
		setSize(446,225);
		setLocationRelativeTo(null);
		setTitle(valueTile);
		setResizable(false);
		setLayout(null);
		initComponents(controller);
	}
	
	public JDSystemMessage(JFrame frame,String request, ClientController controller) {
		super(frame, true);
		this.setModalityType(DEFAULT_MODALITY_TYPE);
		loadProperties(request);
		setSize(446,225);
		setLocationRelativeTo(null);
		setTitle(valueTile);
		setResizable(false);
		setLayout(null);
		initComponents(controller);
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
			listener = Boolean.parseBoolean(prop.getProperty("listener"));
			validateListener();
			getContentPane().setBackground(Color.decode("#" + prop.getProperty("background_color")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void validateListener() {
		if(listener) {
			buttonMessage = prop.getProperty("buttonMessage");
			actionCommand = prop.getProperty("actionCommand");
		}
	}
	
	public JDSystemMessage getJDSystemMessage() {
		return this;
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

	public void closeWindow() {
		this.setVisible(false);
		this.dispose();
	}

	public void initComponents(ClientController controller) {
		jLTitle = new JLabel(valueTile, JLabel.CENTER);
		jLTitle.setBounds(193,33,253,29);
		this.add(jLTitle);
		jTAMessage = new JTextArea(valueMessage);
		jTAMessage.setBounds(193, 73, 253, 50);
		jTAMessage.setEditable(false);
		jTAMessage.setLineWrap(true);
		jTAMessage.setWrapStyleWord(true);
		this.add(jTAMessage);
		if(listener) {
			jBExit = new JButton(buttonMessage);
			jBExit.addActionListener(controller);
			jBExit.setActionCommand(actionCommand);
		}else {
			jBExit = new JButton("Aceptar");
			jBExit.addActionListener(e -> this.dispose());
		}
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
