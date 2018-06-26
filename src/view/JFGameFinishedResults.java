package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import javax.swing.UIManager;



import controller.ClientController;
import controller.Events;
import models.Result;
import view.Constraints;

public class JFGameFinishedResults extends JFrame{

	private static final long serialVersionUID = 1L;
	//Logo de la ventana
	private BufferedImage imageLogo;
	private BufferedImage imageBackGround;
	
	//New Icons
	private ImageIcon backImage;
	private JButton jBBackHome;
	private JPanel pnPlayerWinner, pnPlayerLooser;
	private JLabel lblWinnerTitle, lblLooserTitle;
	private JLabel lblWinnerName, lblLooserName;
	private JLabel cashWinner, cashLooser;
	private JLabel expWinner, expLooser;
	
	private ImageIcon userImage1, userImage2;
	
	private BufferedImage imagePoints1, imagePoints2, imageCash1, imageCash2;
	
	public JFGameFinishedResults(ClientController clientController, Result playerA, Result playerB) {
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
		initComponents(clientController);
		setInfoResults(playerA,playerB);
		setVisible(true);
		timeToLoad();
	}
	
	private void setInfoResults(Result playerA, Result playerB) {
		if(playerA.getPoints() == playerB.getPoints()) {
			if(playerA.getMoney() > playerB.getMoney()) {
				try {
					setImagesOfPlayer(playerA.getImage(), playerB.getImage());
					setValuesWinnerAndLooser(playerA.getMoney(), playerB.getMoney(), playerA.getPoints(), playerB.getPoints());
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}else {
				try {
					setImagesOfPlayer(playerB.getImage(), playerA.getImage());
					setValuesWinnerAndLooser(playerB.getMoney(), playerA.getMoney(), playerB.getPoints(), playerA.getPoints());
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			}
		}else if(playerA.getPoints() > playerB.getPoints()) {
			try {
				setImagesOfPlayer(playerA.getImage(), playerB.getImage());
				setValuesWinnerAndLooser(playerA.getMoney(), playerB.getMoney(), playerA.getPoints(), playerB.getPoints());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}else {
			try {
				setImagesOfPlayer(playerB.getImage(), playerA.getImage());
				setValuesWinnerAndLooser(playerB.getMoney(), playerA.getMoney(), playerB.getPoints(), playerA.getPoints());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		
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
			//pathImageUser = new File("data/Adivina quien cambio de fondo.png");
			//userImage1 = ImageIO.read(pathImageUser);
			//userImage2 = ImageIO.read(pathImageUser);
			imageCash1 = ImageIO.read(getClass().getResource("../img/Dinero ganador.png"));
			imageCash2 = ImageIO.read(getClass().getResource("../img/Dinero perdedor.png"));
			imagePoints1 = ImageIO.read(getClass().getResource("../img/Experiencia ganador.png"));
			imagePoints2 = ImageIO.read(getClass().getResource("../img/Experiencia perdedor.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initComponents(ClientController clientController) {
		/*uploadImage = new JButton(Constraints.BUTTON_UPLOAD_IMAGE);
		uploadImage.setBackground(Constraints.COLOR_GREEN);
		uploadImage.addActionListener(this);
		uploadImage.setBounds(347, 184, 169, 29);
		this.add(uploadImage);*/

		lblWinnerTitle = new JLabel(Constraints.LBL_TITLE_WINNER);
		lblWinnerTitle.setForeground(Color.WHITE);
		lblWinnerTitle.setFont(new Font("Gadugi", Font.PLAIN, 26));
		lblWinnerTitle.setBounds(368, 20, 120, 30);
		this.add(lblWinnerTitle);
		
		lblLooserTitle = new JLabel(Constraints.LBL_TITLE_LOOSER);
		lblLooserTitle.setForeground(Color.WHITE);
		lblLooserTitle.setFont(new Font("Gadugi", Font.PLAIN, 26));
		lblLooserTitle.setBounds(368, 242, 120, 30);
		this.add(lblLooserTitle);
	
		cashWinner = new JLabel("+100");
		cashWinner.setForeground(Color.WHITE);
		cashWinner.setFont(new Font("Gadugi", Font.PLAIN, 18));
		cashWinner.setBounds(520, 180, 120, 30);
		this.add(cashWinner);
		
		cashLooser = new JLabel("+100");
		cashLooser.setForeground(Color.WHITE);
		cashLooser.setFont(new Font("Gadugi", Font.PLAIN, 18));
		cashLooser.setBounds(520, 400, 120, 30);
		this.add(cashLooser);
		
		expWinner = new JLabel("+100");
		expWinner.setForeground(Color.WHITE);
		expWinner.setFont(new Font("Gadugi", Font.PLAIN, 18));
		expWinner.setBounds(375, 180, 120, 30);
		this.add(expWinner);
		
		expLooser = new JLabel("+100");
		expLooser.setForeground(Color.WHITE);
		expLooser.setFont(new Font("Gadugi", Font.PLAIN, 18));
		expLooser.setBounds(375, 400, 120, 30);
		this.add(expLooser);
		
		backImage = new ImageIcon(getClass().getResource("../img/Botones/back_home.png"));
		jBBackHome = new JButton(new ImageIcon(backImage.getImage().getScaledInstance(45, 45,Image.SCALE_SMOOTH)));
		jBBackHome.setOpaque(false);
		jBBackHome.setContentAreaFilled(false);
		jBBackHome.setBorderPainted(false);
		jBBackHome.setToolTipText("Volver al inicio");
		jBBackHome.addActionListener(clientController);
		jBBackHome.setActionCommand(Events.COME_BACK_HOMEMENU.toString());
		jBBackHome.setCursor(Constraints.HAND);
		jBBackHome.setBounds(155, 10, 45, 45);
		this.add(jBBackHome);
		
		pnPlayerWinner = new JPanel();
		pnPlayerWinner.setBackground(Constraints.COLOR_GREEN);
		pnPlayerWinner.setBounds(0, 0, 920, 220);
		this.add(pnPlayerWinner);
		
		pnPlayerLooser = new JPanel();
		pnPlayerLooser.setBackground(Constraints.COLOR_RED);
		pnPlayerLooser.setBounds(0, 220, 920, 230);
		this.add(pnPlayerLooser);
		
		
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
		g.drawImage(userImage1.getImage(), 367, 81, 115, 115, null);
		g.drawImage(userImage2.getImage(), 367, 301, 115, 115, null);
		g.drawImage(imageCash1, 467, 205, 46, 32, null);
		g.drawImage(imageCash2, 467, 425, 46, 32, null);
		g.drawImage(imagePoints1, 327, 205, 46, 32, null);
		g.drawImage(imagePoints2, 327, 425, 46, 32, null);
	}

	//Setea las imagenes para los jugadores
	public void setImagesOfPlayer(ImageIcon ImageUser1, ImageIcon ImageUser2) throws IOException {
		userImage1 = ImageUser1;
		userImage2 = ImageUser2;
		this.revalidate();
		this.repaint();
		
	}
	
	public void setValuesWinnerAndLooser(int cashWinner, int cashLooser, int expWinner, int expLooser){
		this.cashWinner.setText("+"+cashWinner);
		this.expWinner.setText("+"+expWinner);
		this.cashLooser.setText("+"+cashLooser);
		this.expLooser.setText("+"+expLooser);
	}

}
