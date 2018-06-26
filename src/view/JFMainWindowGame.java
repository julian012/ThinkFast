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
import models.User;
import utilities.Utilities;

public class JFMainWindowGame extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;

	private BufferedImage imageLogo;
	private JButton jBxpImage;
	private ImageIcon xpImage;
	private ImageIcon xpSelectedImage;
	private JLabel xpText;
	private JButton jBMoney;
	private ImageIcon moneyImage;
	private ImageIcon moneySelectedImage;
	private JLabel moneyText;
	private JButton jBFriends;
	private ImageIcon friendsSelectedImage;
	private ImageIcon friendsImage;
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
	private JLabel jLLeague;
	private JLabel jLTotalGames;
	private JLabel jLGames;
	private JButton jBGoals;
	private JButton jBQuestions;
	private ImageIcon userImage;
	private ImageIcon exitedImage;
	private ImageIcon enteredImage;
	private ImageIcon goalsImage;
	private ImageIcon goalsSelectedImage;
	private ImageIcon questionImage;
	private ImageIcon questionSelectedImage;
	public JDQuestionList questionList;

	public JFMainWindowGame(ClientController clientControllers, User user) {
		setUIManager();
		initImages(user);
		setIconImage(imageLogo);
		setTitle(Constraints.TITLE);
		setSize(850, 475);
		setResizable(false);
		addWindowListener(clientControllers);
		setLocationRelativeTo(null);
		getContentPane().setBackground(Constraints.COLOR_DARK_GREY);
		setLayout(null);
		initComponents(clientControllers, user);
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

	private void setButtomXP(ClientController clientController, User user) {
		jBxpImage = new JButton(new ImageIcon(xpImage.getImage().getScaledInstance(57, 57, Image.SCALE_SMOOTH)));
		jBxpImage.setBounds(0, 0, 57, 57);
		jBxpImage.setName(Constraints.LABEL_XP);
		jBxpImage.addMouseListener(this);
		this.add(jBxpImage);
		xpText = new JLabel(Constraints.LABEL_XP + user.getAccountInfo().getExperience());
		xpText.setFont(Constraints.FONT_MAIN_WINDOW_LABELS_SMALL);
		xpText.setBounds(57, 0, 160, 50);
		this.add(xpText);
	}

	private void setButtonMoney(ClientController clientController, User user) {
		jBMoney = new JButton(new ImageIcon(moneyImage.getImage().getScaledInstance(73, 55, Image.SCALE_SMOOTH)));
		jBMoney.setBounds(190, 0, 73, 57);
		jBMoney.setName(Constraints.LABEL_MONEY);
		jBMoney.addMouseListener(this);
		this.add(jBMoney);
		moneyText = new JLabel(Constraints.LABEL_MONEY + user.getAccountInfo().getMoney());
		moneyText.setFont(Constraints.FONT_MAIN_WINDOW_LABELS_SMALL);
		moneyText.setBounds(270, 0, 160, 50);
		this.add(moneyText);
	}
	
	private void setButtonFriends(ClientController clientController, User user) {
		jBFriends = new JButton(new ImageIcon(friendsImage.getImage().getScaledInstance(57, 57, Image.SCALE_SMOOTH)));
		jBFriends.setBounds(400, 0, 57, 57);
		jBFriends.setName(Constraints.LABEL_FRIENDS);
		jBFriends.addMouseListener(this);
		this.add(jBFriends);
		friendsText = new JLabel(Constraints.LABEL_FRIENDS);
		friendsText.setFont(Constraints.FONT_MAIN_WINDOW_LABELS_SMALL);
		friendsText.setBounds(470, 0, 128, 50);
		this.add(friendsText);
	}

	public void initComponents(ClientController clientController, User user) {
		setButtomXP(clientController, user);
		setButtonMoney(clientController, user);
		setButtonFriends(clientController, user);
		

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
		jBExit.setName(Constraints.TOOT_TIP_TEXT_EXIT);
		this.add(jBExit);

		jLLeague = new JLabel(Constraints.LABEL_LEAGUE + Utilities.getLeague(user.getAccountInfo().getExperience()));
		jLLeague.setBounds(60,218,200,25);
		this.add(jLLeague);

		jLTotalGames = new JLabel(Constraints.LABEL_TOTAL_GAMES + user.getAccountInfo().getTotalGames());
		jLTotalGames.setBounds(60,243,200,25);
		this.add(jLTotalGames);

		jLGames = new JLabel(Constraints.LABEL_GAMES + user.getAccountInfo().getGames());
		jLGames.setBounds(60,268,200,25);
		this.add(jLGames);

		jBGoals = new JButton(new ImageIcon(goalsImage.getImage().getScaledInstance(251, 57, Image.SCALE_SMOOTH)));
		jBGoals.setOpaque(false);
		jBGoals.setContentAreaFilled(false);
		jBGoals.setBorderPainted(false);
		jBGoals.addMouseListener(this);
		jBGoals.setName(Constraints.BUTTOM_GOALS);
		jBGoals.setToolTipText(Constraints.BUTTOM_GOALS);
		jBGoals.setCursor(Constraints.HAND);
		jBGoals.addActionListener(clientController);
		//jBGoals.setActionCommand(Events.CLOSE_APP.toString());
		jBGoals.setBounds(78, 305, 251, 57);
		this.add(jBGoals);

		jBQuestions = new JButton(new ImageIcon(questionImage.getImage().getScaledInstance(251, 57, Image.SCALE_SMOOTH)));
		jBQuestions.setOpaque(false);
		jBQuestions.setContentAreaFilled(false);
		jBQuestions.setBorderPainted(false);
		jBQuestions.addMouseListener(this);
		jBQuestions.setName(Constraints.BUTTOM_QUESTIONS);
		jBQuestions.setToolTipText(Constraints.BUTTOM_QUESTIONS);
		jBQuestions.setCursor(Constraints.HAND);
		jBQuestions.addActionListener(clientController);
		jBQuestions.setActionCommand(Events.QUESTION_PANEL.toString());
		jBQuestions.setBounds(78, 375, 251, 57);
		this.add(jBQuestions);

		showGameModes(clientController);
	}

	public void showGameModes(ClientController clientController) {

		jPanelInfoUser = new JPanel();
		jPanelInfoUser.setBackground(new Color(102, 102, 102, 100));
		jPanelInfoUser.setBounds(46,65,313,378);
		jPanelInfoUser.setBorder(BorderFactory.createLineBorder(Color.decode("#666666")));
		this.add(jPanelInfoUser);

		jTitle = new JLabel(Constraints.LABEL_TITLE_GAMES, JLabel.CENTER);
		jTitle.setFont(Constraints.FONT_MAIN_WINDOW_TITLE);
		jTitle.setBounds(391,67, 424 ,50);
		this.add(jTitle);

		onevsOne = new ImageIcon(getClass().getResource("../img/1 vs 1 con texto.png"));
		jBGameOnevsOne = new JButton(new ImageIcon(onevsOne.getImage().getScaledInstance(424, 128,Image.SCALE_SMOOTH)));
		jBGameOnevsOne.setOpaque(false);
		jBGameOnevsOne.setContentAreaFilled(false);
		jBGameOnevsOne.setBorderPainted(false);
		//jBGameOnevsOne.setToolTipText(Constraints.TOOT_TIP_TEXT_EXIT);
		jBGameOnevsOne.setCursor(Constraints.HAND);
		jBGameOnevsOne.addActionListener(clientController);
		jBGameOnevsOne.setActionCommand(Events.PLAY_ONE_VS_ONE.toString());
		jBGameOnevsOne.setBounds(391, 129, 424, 128);
		this.add(jBGameOnevsOne);

		choiceChallenge = new ImageIcon(getClass().getResource("../img/reto eleccion con texto.png"));
		jBChoiceChallenge = new JButton(new ImageIcon(choiceChallenge.getImage().getScaledInstance(424, 128,Image.SCALE_SMOOTH)));
		jBChoiceChallenge.setOpaque(false);
		jBChoiceChallenge.setContentAreaFilled(false);
		jBChoiceChallenge.setBorderPainted(false);
		//jBChoiceChallenge.setToolTipText(Constraints.TOOT_TIP_TEXT_EXIT);
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

	public void initImages(User user) {
		exitedImage = new ImageIcon(getClass().getResource("../img/Cerrar negrito.png"));
		enteredImage = new ImageIcon(getClass().getResource("../img/Cerrar.png"));
		goalsSelectedImage = new ImageIcon(getClass().getResource("../img/Botones/Lista de logros seleccionado2.PNG"));
		goalsImage = new ImageIcon(getClass().getResource("../img/Botones/Lista de logros sin seleccionar.PNG"));
		questionImage = new ImageIcon(getClass().getResource("../img/Botones/Categoria de preguntas sin seleccionar.PNG"));
		questionSelectedImage = new ImageIcon(getClass().getResource("../img/Botones/Categoria de pregintas seleccionadas.PNG"));
		userImage = user.getImageUser();
		xpImage = new ImageIcon(getClass().getResource("../img/Experiencia NEGRITO.png"));
		xpSelectedImage = new ImageIcon(getClass().getResource("../img/Experiencia.png"));
		moneyImage = new ImageIcon(getClass().getResource("../img/Dinero 3.png"));
		moneySelectedImage = new ImageIcon(getClass().getResource("../img/Dinero ganador.png"));
		friendsImage = new ImageIcon(getClass().getResource("../img/Mundo 3.png"));
		friendsSelectedImage = new ImageIcon(getClass().getResource("../img/Mundo.png"));
		try {
			powerImage = ImageIO.read(getClass().getResource("../img/Poderes.png"));
			imageLogo = ImageIO.read(getClass().getResource("../img/Logo.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.drawImage(powerImage, 585, 33, 57, 57, null);
		g.drawImage(userImage.getImage(),128,110 ,143, 143, null);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		JButton name = (JButton) e.getSource();
		switch (name.getName()) {
		case Constraints.TOOT_TIP_TEXT_EXIT:
			jBExit.setIcon((new ImageIcon(enteredImage.getImage().getScaledInstance(55, 55,Image.SCALE_SMOOTH))));
			break;
		case Constraints.BUTTOM_GOALS:
			jBGoals.setIcon((new ImageIcon(goalsSelectedImage.getImage().getScaledInstance(251, 57, Image.SCALE_SMOOTH))));
			break;
		case Constraints.BUTTOM_QUESTIONS:
			jBQuestions.setIcon(new ImageIcon(questionSelectedImage.getImage().getScaledInstance(251, 57, Image.SCALE_SMOOTH)));
			break;
		case Constraints.LABEL_XP:
			jBxpImage.setIcon(new ImageIcon(xpSelectedImage.getImage().getScaledInstance(57, 57, Image.SCALE_SMOOTH)));
			break;
		case Constraints.LABEL_MONEY:
			jBMoney.setIcon(new ImageIcon(moneySelectedImage.getImage().getScaledInstance(73, 57, Image.SCALE_SMOOTH)));
			break;
		case Constraints.LABEL_FRIENDS:
			jBFriends.setIcon(new ImageIcon(friendsSelectedImage.getImage().getScaledInstance(57, 57, Image.SCALE_SMOOTH)));
			break;
		default:
			break;
		}

	}
	
	public void updateValues(int money, int xp, int game, int totalGames) {
		xpText.setText(Constraints.LABEL_XP + String.valueOf(xp));
		moneyText.setText(Constraints.LABEL_MONEY + String.valueOf(money));
		jLLeague.setText((Constraints.LABEL_LEAGUE + Utilities.getLeague(xp)));
		jLTotalGames.setText(Constraints.LABEL_TOTAL_GAMES + String.valueOf(totalGames));
		jLGames.setText(Constraints.LABEL_GAMES + String.valueOf(game));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JButton name = (JButton) e.getSource();
		switch (name.getName()) {
		case Constraints.TOOT_TIP_TEXT_EXIT:
			jBExit.setIcon((new ImageIcon(exitedImage.getImage().getScaledInstance(55, 55,Image.SCALE_SMOOTH))));
			break;
		case Constraints.BUTTOM_GOALS:
			jBGoals.setIcon((new ImageIcon(goalsImage.getImage().getScaledInstance(251, 57, Image.SCALE_SMOOTH))));
			break;
		case Constraints.BUTTOM_QUESTIONS:
			jBQuestions.setIcon(new ImageIcon(questionImage.getImage().getScaledInstance(251, 57, Image.SCALE_SMOOTH)));
			break;
		case Constraints.LABEL_XP:
			jBxpImage.setIcon(new ImageIcon(xpImage.getImage().getScaledInstance(57, 57, Image.SCALE_SMOOTH)));
			break;
		case Constraints.LABEL_MONEY:
			jBMoney.setIcon(new ImageIcon(moneyImage.getImage().getScaledInstance(73, 57, Image.SCALE_SMOOTH)));
			break;
		case Constraints.LABEL_FRIENDS:
			jBFriends.setIcon(new ImageIcon(friendsImage.getImage().getScaledInstance(57, 57, Image.SCALE_SMOOTH)));
			break;
		default:
			break;
		}
	}
	
	public ImageIcon getImage(String path) {
		return new ImageIcon(getClass().getResource(path));
	}
	
	public void showQuestionPanel(ClientController controller, User user) {
		questionList = new JDQuestionList(this, controller, user);
		questionList.setVisible(true);
	}
	
	public void closeJDQuestionList() {
		questionList.dispose();
	}
}
