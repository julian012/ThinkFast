package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class JPanelPhoto extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	
	public JPanelPhoto(BufferedImage image, Color background) {
		this.image = image;
		setBackground(background);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(image, 1, 1, this.getWidth(),this.getHeight(),this);
	}
}

