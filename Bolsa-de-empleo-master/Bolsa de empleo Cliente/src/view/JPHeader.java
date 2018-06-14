package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPHeader extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JLabel jLTitle;
	private JPanelPhoto photoLogo;
	private JLabel jLSpace;
	private JPanelPhoto photoUser;
	private JLabel jLNamePerson;
	private JPanelPhoto closeSession;
	
	public JPHeader(String name, BufferedImage image) {
		setLayout(null);
		jLTitle = new JLabel("Bolsa de empleo JS", JLabel.CENTER);
		jLTitle.setBackground(Constant.COLORBLUE);
		jLTitle.setOpaque(true);
		jLTitle.setForeground(Color.white);
		jLTitle.setFont(new Font("Arial", Font.BOLD, 17));
		jLTitle.setBounds(0, 0, 200, 50);
		add(jLTitle);
		
		try {
			photoLogo = new JPanelPhoto(ImageIO.read(new File("files/imagenes/icon.png")), Constant.COLORBLUE);
			photoLogo.setBounds(200, 0, 60, 50);
			add(photoLogo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		jLSpace = new JLabel("  ");
		jLSpace.setOpaque(true);
		jLSpace.setBackground(Constant.COLORBLUE);
		jLSpace.setBounds(260, 0, 400, 50);
		add(jLSpace);
		
		try {
			photoUser = new JPanelPhoto(ImageIO.read(new File("files/imagenes/icon.png")), Constant.COLORBLUE);
			photoUser.setBounds(660, 0, 60, 50);
			add(photoUser);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		jLNamePerson = new JLabel(name, JLabel.CENTER);
		jLNamePerson.setBackground(Constant.COLORBLUE);
		jLNamePerson.setOpaque(true);
		jLNamePerson.setForeground(Color.white);
		jLNamePerson.setFont(new Font("Arial", Font.BOLD, 18));
		jLNamePerson.setBounds(720, 0, 274, 50);
		add(jLNamePerson);
		
		try {
			closeSession = new JPanelPhoto(ImageIO.read(new File("files/imagenes/ajustes.png")), Constant.COLORBLUE);
			closeSession.setBounds(994, 0, 60, 50);
			add(closeSession);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
