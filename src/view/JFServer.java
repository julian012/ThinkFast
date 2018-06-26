package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import java.awt.image.BufferedImage;
import java.io.IOException;


import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.UIManager;


import controller.Events;
import controller.ServerController;
import view.Constraints;

public class JFServer extends JFrame{

	private static final long serialVersionUID = 1L;
	//Logo de la ventana
	private BufferedImage imageLogo;
	private BufferedImage imageBackGround;
	
	//New Icons
	private ImageIcon backImage;
	private JButton jBBackToGame;
	private JPanel pnBackgroundCenter;
	
	private ImageIcon serverInitImage;
	private JButton btnInitServer;
	private JLabel lblInitServer;

	
	public JFServer(ServerController serverController) {
		setUIManager();
		initImages();
		setIconImage(imageLogo);
		setTitle(Constraints.TITLE);
		setSize(850, 475);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Constraints.COLOR_BACKGROUNDGAME_MARFIL);
		setLayout(null);
		initComponents(serverController);
		setVisible(true);
		timeToLoad();
	}
	
	public void timeToLoad() {
		try {
			Thread.sleep(1000);
			revalidate();
			repaint();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initComponents(ServerController serverController) {

		backImage = new ImageIcon(getClass().getResource("../view/back.png"));
		jBBackToGame = new JButton(new ImageIcon(backImage.getImage().getScaledInstance(45, 45,Image.SCALE_SMOOTH)));
		jBBackToGame.setOpaque(false);
		jBBackToGame.setContentAreaFilled(false);
		jBBackToGame.setBorderPainted(false);
		jBBackToGame.setToolTipText("Volver al juego");
		jBBackToGame.addActionListener(serverController);
		jBBackToGame.setActionCommand(Events.INSTRUCTIONS_COME_BACK_TO_GAME.toString());
		jBBackToGame.setCursor(Constraints.HAND);
		jBBackToGame.setBounds(155, 10, 45, 45);
		this.add(jBBackToGame);
		
		serverInitImage = new ImageIcon(getClass().getResource("../view/server.png"));
		btnInitServer = new JButton(new ImageIcon(serverInitImage.getImage().getScaledInstance(192, 192,Image.SCALE_SMOOTH)));
		btnInitServer.setOpaque(false);
		btnInitServer.setContentAreaFilled(false);
		btnInitServer.setBorderPainted(false);
		btnInitServer.setToolTipText("Iniciar servidor");
		btnInitServer.addActionListener(serverController);
		btnInitServer.setActionCommand(Events.INIT_SERVER.toString());
		btnInitServer.setCursor(Constraints.HAND);
		btnInitServer.setBounds(320, 120, 192, 192);
		this.add(btnInitServer);
		
		lblInitServer = new JLabel("Iniciar Servidor ThinkFast");
		lblInitServer.setFont(Constraints.FONT_MAIN_WINDOW_TITLE);
		lblInitServer.setBounds(270, 80, 300, 30);
		this.add(lblInitServer);
		
		pnBackgroundCenter = new JPanel();
		pnBackgroundCenter.setBackground(Constraints.COLOR_BACKGROUNDGAME_MARFIL);
		pnBackgroundCenter.setBounds(0, 0, 920, 500);
		this.add(pnBackgroundCenter);
		

		
	}

	public void showF() {
		revalidate();
		repaint();
		setVisible(true);
	}
	

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(imageBackGround.getSubimage(0, 0, 450, 1350), 1, 28, 148, 448, null);
		g.drawImage(imageBackGround.getSubimage(1050, 0, 450, 1350), 704, 28, 148, 448, null);
		
	}



}
