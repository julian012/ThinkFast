package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

public class JFSingIn extends JFrame {

	private static final long serialVersionUID = 1L;
	private BufferedImage imageBackGround;
	private BufferedImage imageLogo;
	private JLabel jLTitle;
	private JLabel jLEmail;
	
	
	public JFSingIn() {
		setUIManager();
		initImages();
		setTitle(Constraints.TITLE);
		setSize(850, 475);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.decode("#0E1D47"));
		setLayout(null);
		initComponents();
		setVisible(true);
	}
	
	public void setUIManager() {
		UIManager.put("Label.foreground", Color.white);
		UIManager.put("Label.font", Constraints.fontSingIn);
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
	
	public void initComponents() {
		jLTitle = new JLabel(Constraints.TITLE);
		jLTitle.setBounds(628, 100, 109, 30);
		this.add(jLTitle);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(imageLogo, 637, 33, 90, 90, null);
		g.drawImage(imageBackGround, 1, 28, 497, 448, null);
	}
	
	
	public static void main(String[] args) {
		new JFSingIn();
	}
}
