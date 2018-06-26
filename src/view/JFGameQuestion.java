package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import java.io.IOException;


import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

import javax.swing.UIManager;



import controller.ClientController;
import controller.Events;

public class JFGameQuestion extends JFrame{

	private static final long serialVersionUID = 1L;
	//Logo de la ventana
	private BufferedImage imageLogo;
	private BufferedImage imageBackGround;
	
	//New Icons
	private ImageIcon backImage;
	private JButton jBBackHome;
	private ImageIcon gameInstructionsImage;
	private JButton btnGameInstructions;
	private JProgressBar pbMaxTime;
	private int time;
	private String answer;
	private int correct;
	private int money;
	private boolean rivalAnswering;
	
	private JLabel lblRivalAnswering;
	private JButton btnQuestion;
	private JButton btnAnswer1, btnAnswer2, btnAnswer3, btnAnswer4;
	private JLabel lblPowers;
	private ImageIcon powersInfoImage;
	private JButton btnPowersInfo;
	private ImageIcon power1Image, power2Image, power3Image;
	private JButton btnPower1, btnPower2, btnPower3;

	public JFGameQuestion(ClientController clientController, String[] question, String answer) {
		this.answer = answer;
		System.out.println("Inicio ventana de preguntas");
		setUIManager();
		initImages();
		setIconImage(imageLogo);
		setTitle(Constraints.TITLE);
		setSize(850, 475);
		setResizable(false);
		setLocationRelativeTo(null);
		//setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Constraints.COLOR_BACKGROUNDGAME_MARFIL);
		setLayout(null);
		initComponents(clientController);
		setQuestionAnswer(question, answer);
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

	public void initComponents(ClientController clientController) {
		backImage = new ImageIcon(getClass().getResource("../img/Botones/back_home.png"));
		jBBackHome = new JButton(new ImageIcon(backImage.getImage().getScaledInstance(45, 45,Image.SCALE_SMOOTH)));
		jBBackHome.setOpaque(false);
		jBBackHome.setContentAreaFilled(false);
		jBBackHome.setBorderPainted(false);
		jBBackHome.setToolTipText("Volver al inicio");
		jBBackHome.addActionListener(clientController);
		//jBBackHome.setActionCommand(Events.COME_BACK_HOMEMENU.toString());
		jBBackHome.setCursor(Constraints.HAND);
		jBBackHome.setBounds(155, 10, 45, 45);
		this.add(jBBackHome);
		
		
		gameInstructionsImage = new ImageIcon(getClass().getResource("../img/Botones/game_instructions.png"));
		btnGameInstructions = new JButton(new ImageIcon(gameInstructionsImage.getImage().getScaledInstance(45, 45,Image.SCALE_SMOOTH)));
		btnGameInstructions.setOpaque(false);
		btnGameInstructions.setContentAreaFilled(false);
		btnGameInstructions.setBorderPainted(false);
		btnGameInstructions.setToolTipText("Instrucciones de juego");
		btnGameInstructions.addActionListener(clientController);
		//btnGameInstructions.setActionCommand(Events.SHOW_JDGAMEINSTRUCTIONS.toString());
		btnGameInstructions.setCursor(Constraints.HAND);
		btnGameInstructions.setBounds(645, 10, 45, 45);
		this.add(btnGameInstructions);
		
		pbMaxTime = new JProgressBar(JProgressBar.HORIZONTAL, 0, 20);
		//pbMaxTime.setValue(10);
		pbMaxTime.setBounds(240, 20, 365, 30);
		this.add(pbMaxTime);
		
		/*jLNickName = new JLabel(Constraints.JLABEL_NICKNAME);
		jLNickName.setBounds(185, 300, 200, 25);
		this.add(jLNickName);

		jTFNickName = new JTextField();
		jTFNickName.setBounds(185, 330, 231, 25);
		this.add(jTFNickName);*/
		
		lblRivalAnswering = new JLabel(Constraints.RIVAL_NOT_ANSWERING);
		lblRivalAnswering.setFont(Constraints.FONT_MAIN_WINDOW_LABELS_SMALL);
		lblRivalAnswering.setForeground(Color.red);
		lblRivalAnswering.setBounds(260, 80, 365, 30);
		this.add(lblRivalAnswering);
		
		btnQuestion = new JButton();
		btnQuestion.setBackground(Constraints.COLOR_ORANGE);
		btnQuestion.setFont(Constraints.FONT_MAIN_WINDOW_LABELS_SMALL);
		btnQuestion.setBounds(262, 130, 320, 70);
		btnQuestion.setEnabled(false);
		this.add(btnQuestion);
		
		btnAnswer1 = new JButton();
		btnAnswer1.setBackground(Constraints.COLOR_BLUE);
		btnAnswer1.setFont(Constraints.FONT_MAIN_WINDOW_LABELS_SMALL);
		btnAnswer1.addActionListener(e -> validateQuestion(btnAnswer1));
		btnAnswer1.setBounds(282, 220, 280, 50);
		this.add(btnAnswer1);
		
		btnAnswer2 = new JButton();
		btnAnswer2.setBackground(Constraints.COLOR_BLUE);
		btnAnswer2.setFont(Constraints.FONT_MAIN_WINDOW_LABELS_SMALL);
		btnAnswer2.addActionListener(e -> validateQuestion(btnAnswer2));
		btnAnswer2.setBounds(282, 275, 280, 50);
		this.add(btnAnswer2);
		
		btnAnswer3 = new JButton();
		btnAnswer3.setBackground(Constraints.COLOR_BLUE);
		btnAnswer3.setFont(Constraints.FONT_MAIN_WINDOW_LABELS_SMALL);
		btnAnswer3.addActionListener(e -> validateQuestion(btnAnswer3));
		btnAnswer3.setBounds(282, 330, 280, 50);
		this.add(btnAnswer3);
		
		btnAnswer4 = new JButton();
		btnAnswer4.setBackground(Constraints.COLOR_BLUE);
		btnAnswer4.setFont(Constraints.FONT_MAIN_WINDOW_LABELS_SMALL);
		btnAnswer4.addActionListener(e -> validateQuestion(btnAnswer4));
		btnAnswer4.setBounds(282, 385, 280, 50);
		this.add(btnAnswer4);
		
		powersInfoImage = new ImageIcon(getClass().getResource("../img/Botones/Powers/Poderes.png"));
		btnPowersInfo = new JButton(new ImageIcon(powersInfoImage.getImage().getScaledInstance(65, 65,Image.SCALE_SMOOTH)));
		btnPowersInfo.setOpaque(false);
		btnPowersInfo.setContentAreaFilled(false);
		btnPowersInfo.setBorderPainted(false);
		btnPowersInfo.setToolTipText("Poderes disponibles: ");
		btnPowersInfo.setBounds(170, 200, 65, 65);
		this.add(btnPowersInfo);
		
		lblPowers = new JLabel(Constraints.LBL_POWERS_INFO);
		lblPowers.setForeground(Constraints.COLOR_PURPLE);
		lblPowers.setFont(new Font("Gadugi", Font.PLAIN, 14));
		lblPowers.setBounds(175, 257, 80, 30);
		this.add(lblPowers);
		
		power1Image = new ImageIcon(getClass().getResource("../img/Botones/Powers/cambiar.png"));
		btnPower1 = new JButton(new ImageIcon(power1Image.getImage().getScaledInstance(35, 35,Image.SCALE_SMOOTH)));
		btnPower1.setOpaque(false);
		btnPower1.setContentAreaFilled(false);
		btnPower1.setBorderPainted(false);
		btnPower1.setToolTipText("Cambiar pregunta");
		btnPower1.addActionListener(clientController);
		//btnPower1.setActionCommand(Events.PRESS_POWER_CHANGEQUESTION.toString());
		btnPower1.setBounds(182, 285, 35, 35);
		this.add(btnPower1);
		
		power2Image = new ImageIcon(getClass().getResource("../img/Botones/Powers/tiempo.png"));
		btnPower2 = new JButton(new ImageIcon(power2Image.getImage().getScaledInstance(35, 35,Image.SCALE_SMOOTH)));
		btnPower2.setOpaque(false);
		btnPower2.setContentAreaFilled(false);
		btnPower2.setBorderPainted(false);
		btnPower2.setToolTipText("Agregar tiempo");
		btnPower2.addActionListener(clientController);
		//btnPower2.setActionCommand(Events.PRESS_POWER_CHANGEQUESTION.toString());
		btnPower2.setBounds(182, 330, 35, 35);
		this.add(btnPower2);
		
		power3Image = new ImageIcon(getClass().getResource("../img/Botones/Powers/50.png"));
		btnPower3 = new JButton(new ImageIcon(power3Image.getImage().getScaledInstance(35, 35,Image.SCALE_SMOOTH)));
		btnPower3.setOpaque(false);
		btnPower3.setContentAreaFilled(false);
		btnPower3.setBorderPainted(false);
		btnPower3.setToolTipText("50/50");
		btnPower3.addActionListener(clientController);
		//btnPower3.setActionCommand(Events.PRESS_POWER_CHANGEQUESTION.toString());
		btnPower3.setBounds(182, 375, 35, 35);
		this.add(btnPower3);

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

	//Maneja el cambio de texto en caso de que el contrincante ya haya respondido
	public void changeRivalAnsweringStatus(Boolean status) {
		rivalAnswering = status;
		if (rivalAnswering) {
			lblRivalAnswering.setText(Constraints.RIVAL_ANSWERING);
		} else {
			lblRivalAnswering.setText(Constraints.RIVAL_NOT_ANSWERING);
		}
	}
	
	//Asigna el texto de cada ronda de preguntas a los botones
	public void setQuestionAnswer(String[] question, String answer) {
		rivalAnswering = false;
		pbMaxTime.setValue(0);
		gobackBlueColor();
		btnQuestion.setText(question[0]);
		btnAnswer1.setText(question[1]);
		btnAnswer2.setText(question[2]);
		btnAnswer3.setText(question[3]);
		btnAnswer4.setText(question[4]);
		this.answer = answer;
		statusquestions(true);
		reloadTime();
	}
	
	public void gobackBlueColor() {
		btnAnswer1.setBackground(Constraints.COLOR_BLUE);
		btnAnswer2.setBackground(Constraints.COLOR_BLUE);
		btnAnswer3.setBackground(Constraints.COLOR_BLUE);
		btnAnswer4.setBackground(Constraints.COLOR_BLUE);
	}
	
	//Cambia de color a verde el boton en cuestion si la respuesta es correcta
	public void manageCorrectAnswer(JButton answerTrue) {
		answerTrue.setBackground(Constraints.COLOR_GREEN);
	}
	
	//Cambia de color a rojo el boton en cuestion si la respuesta es incorrecta
	public void manageIncorrectAnswer(JButton wrongAnswer) {
		wrongAnswer.setBackground(Constraints.COLOR_RED);
	}
	
	//metodo independiente para cambiar la pregunta
	public void changeQuestion(String newQuestion) {
		btnQuestion.setText(newQuestion);
	}
	
	public boolean progressBar() {
		time++;
		pbMaxTime.setValue(time);
		return time < pbMaxTime.getMaximum();
	}
	
	public void reloadTime() {
		time = 0;
	}
	
	public void statusquestions(boolean status) {
		btnAnswer1.setEnabled(status);
		btnAnswer2.setEnabled(status);
		btnAnswer3.setEnabled(status);
		btnAnswer4.setEnabled(status);
	}
	
	public void validateQuestion(JButton button) {
		statusquestions(false);
		if(button.getText().equals(answer)) {
			manageCorrectAnswer(button);
			money +=50;
			if(rivalAnswering) {
				correct += 100;
			}else {
				correct += 50;
			}
		}else {
			manageIncorrectAnswer(button);
			changeColonCorrectAnswer();
			money +=20;
		}
		time = 19;
	}
	
	public void changeColonCorrectAnswer() {
		if(btnAnswer1.getText().equals(answer)) {
			manageCorrectAnswer(btnAnswer1);
			return;
		}else if(btnAnswer2.getText().equals(answer)) {
			manageCorrectAnswer(btnAnswer2);
			return;
		}else if(btnAnswer3.getText().equals(answer)) {
			manageCorrectAnswer(btnAnswer3);
			return;
		}else {
			manageCorrectAnswer(btnAnswer4);
		}
	}
	
	public int getMoney() {
		return money;
	}
	
	public int getCorrect() {
		return correct;
	}
}
