package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

import controller.ClientController;
import controller.Events;

public class JFMainWindowGame extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;
	
	private BufferedImage imageLogo;
	private BufferedImage xpImage;
	private JLabel xpText;
	private BufferedImage moneyImage;
	private JLabel moneyText;
	private BufferedImage friendsImage;
	private JLabel friendsText;
	private BufferedImage powerImage;
	private JLabel powerText;
	private ImageIcon exitImage;
	private JButton jBExit;
	private JButton jBGameOnevsOne;
	private ImageIcon onevsOne;
	private JButton jBChoiceChallenge;
	private ImageIcon choiceChallenge;
	private JPanel jPanelInfoUser;
	private JLabel jTitle;

	private ImageIcon exitedImage;

	private ImageIcon enteredImage;
	
	public JFMainWindowGame(ClientController clientControllers) {
		setUIManager();
		initImages();
		setIconImage(imageLogo);
		setTitle(Constraints.TITLE);
		setSize(850, 475);
		setResizable(false);
		addWindowListener(clientControllers);
		setLocationRelativeTo(null);
		getContentPane().setBackground(Constraints.COLOR_DARK_GREY);
		setLayout(null);
		initComponents(clientControllers);
		setVisible(true);
		timeToLoad();
	}
	
	public void timeToLoad() {
		try {
			Thread.sleep(1000);
			revalidate();
			repaint();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void initComponents(ClientController clientController) {
		xpText = new JLabel(Constraints.LABEL_XP  + ": 145000");
		xpText.setFont(Constraints.FONT_MAIN_WINDOW_LABELS_SMALL);
		xpText.setBounds(57, 0, 160, 50);
		this.add(xpText);
		
		moneyText = new JLabel(Constraints.LABEL_MONEY  + ": 145000");
		moneyText.setFont(Constraints.FONT_MAIN_WINDOW_LABELS_SMALL);
		moneyText.setBounds(270, 0, 160, 50);
		this.add(moneyText);
		
		friendsText = new JLabel(Constraints.LABEL_FRIENDS);
		friendsText.setFont(Constraints.FONT_MAIN_WINDOW_LABELS_SMALL);
		friendsText.setBounds(470, 0, 128, 50);
		this.add(friendsText);
		
		powerText = new JLabel(Constraints.LABEL_POWERS, JLabel.CENTER);
		powerText.setFont(Constraints.FONT_MAIN_WINDOW_LABELS_SMALL);
		powerText.setBounds(620, 0, 128, 50);
		this.add(powerText);
		
		exitImage = new ImageIcon(getClass().getResource("../img/Cerrar negrito.png"));
		jBExit = new JButton(new ImageIcon(exitImage.getImage().getScaledInstance(55, 55,Image.SCALE_SMOOTH)));
		jBExit.setOpaque(false);
		jBExit.setContentAreaFilled(false);
		jBExit.setBorderPainted(false);
		jBExit.setToolTipText(Constraints.TOOT_TIP_TEXT_EXIT);
		jBExit.setCursor(Constraints.HAND);
		jBExit.addMouseListener(this);
		jBExit.addActionListener(clientController);
		jBExit.setActionCommand(Events.CLOSE_APP.toString());
		jBExit.setBounds(785, 0, 55, 55);
		this.add(jBExit);
		
		jPanelInfoUser = new JPanel();
		jPanelInfoUser.setBackground(new Color(102, 102, 102, 100));
		jPanelInfoUser.setBounds(46,65,313,378);
		jPanelInfoUser.setBorder(BorderFactory.createLineBorder(Color.decode("#666666")));
		this.add(jPanelInfoUser);
		
		
		showGameModes(clientController);
	}
	
	public void showGameModes(ClientController clientController) {
		
		jTitle = new JLabel(Constraints.LABEL_TITLE_GAMES, JLabel.CENTER);
		jTitle.setFont(Constraints.FONT_MAIN_WINDOW_TITLE);
		jTitle.setBounds(391,67, 424 ,50);
		this.add(jTitle);
		
		onevsOne = new ImageIcon(getClass().getResource("../img/1 vs 1 con texto.png"));
		jBGameOnevsOne = new JButton(new ImageIcon(onevsOne.getImage().getScaledInstance(424, 128,Image.SCALE_SMOOTH)));
		jBGameOnevsOne.setOpaque(false);
		jBGameOnevsOne.setContentAreaFilled(false);
		jBGameOnevsOne.setBorderPainted(false);
		jBGameOnevsOne.setToolTipText(Constraints.TOOT_TIP_TEXT_EXIT);
		jBGameOnevsOne.setCursor(Constraints.HAND);
		//jBExit.addActionListener(clientController);
		//jBExit.setActionCommand(Events.CLOSE_APP.toString());
		jBGameOnevsOne.setBounds(391, 129, 424, 128);
		this.add(jBGameOnevsOne);
		
		choiceChallenge = new ImageIcon(getClass().getResource("../img/reto eleccion con texto.png"));
		jBChoiceChallenge = new JButton(new ImageIcon(choiceChallenge.getImage().getScaledInstance(424, 128,Image.SCALE_SMOOTH)));
		jBChoiceChallenge.setOpaque(false);
		jBChoiceChallenge.setContentAreaFilled(false);
		jBChoiceChallenge.setBorderPainted(false);
		jBChoiceChallenge.setToolTipText(Constraints.TOOT_TIP_TEXT_EXIT);
		jBChoiceChallenge.setCursor(Constraints.HAND);
		//jBExit.addActionListener(clientController);
		//jBExit.setActionCommand(Events.CLOSE_APP.toString());
		jBChoiceChallenge.setBounds(391, 270, 424, 128);
		this.add(jBChoiceChallenge);
	}

	public void setUIManager() {
		UIManager.put("Label.foreground", Color.WHITE);
		UIManager.put("Label.font", Constraints.FONT_MAIN_WINDOW_LABELS);
	}
	
	public void initImages() {
		exitedImage = new ImageIcon(getClass().getResource("../img/Cerrar negrito.png"));
		enteredImage = new ImageIcon(getClass().getResource("../img/Cerrar.png"));
		try {
			xpImage = ImageIO.read(getClass().getResource("../img/Experiencia NEGRITO.png"));
			moneyImage = ImageIO.read(getClass().getResource("../img/Dinero 3.png"));
			friendsImage = ImageIO.read(getClass().getResource("../img/Mundo 3.png"));
			powerImage = ImageIO.read(getClass().getResource("../img/Poderes.png"));
			imageLogo = ImageIO.read(getClass().getResource("../img/Logo.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(xpImage, 1, 33, 57, 57, null);
		g.drawImage(moneyImage, 190, 34, 73, 55, null);
		g.drawImage(friendsImage, 410, 33, 57, 57, null);
		g.drawImage(powerImage, 585, 33, 57, 57, null);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		jBExit.setIcon((new ImageIcon(enteredImage.getImage().getScaledInstance(55, 55,Image.SCALE_SMOOTH))));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		jBExit.setIcon((new ImageIcon(exitedImage.getImage().getScaledInstance(55, 55,Image.SCALE_SMOOTH))));
	}
}
