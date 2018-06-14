package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPShortApplicationEmployee extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JLabel jLtitle;
	private JLabel jLnameCompany;
	private JLabel jLcity;
	private JButton jButtomInfo;
	private JLabel jBWait;
	private JLabel jBSelected;
	private JLabel jBRejected;

	public JPShortApplicationEmployee() {
		setLayout(null);
		setBackground(Color.WHITE);
		
		jLtitle = new JLabel("Nombre de la publicación");
		jLtitle.setForeground(Color.decode("#4F82C4"));
		jLtitle.setFont(new Font("Arial", Font.BOLD, 18));
		jLtitle.setBounds(10, 10, 700, 40);
		add(jLtitle);
		
		jLnameCompany = new JLabel("Nombre de la compañia");
		jLnameCompany.setForeground(Color.BLACK);
		jLnameCompany.setFont(new Font("Arial", Font.BOLD, 15));
		jLnameCompany.setBounds(10, 35, 300, 40);
		add(jLnameCompany);
		
		jLcity = new JLabel("Ciudad de la Publicacion");
		jLcity.setForeground(Color.decode("#83878F"));
		jLcity.setBounds(10, 60, 210, 40);
		add(jLcity);
		
		jBWait = new JLabel("En espera", JLabel.CENTER);
		jBWait.setBackground(Constant.COLOR_SELECTED);
		jBWait.setForeground(Color.WHITE);
		jBWait.setFont(new Font("Arial", Font.BOLD, 15));
		jBWait.setBorder(BorderFactory.createLineBorder(Constant.COLOR_SELECTED));
		jBWait.setOpaque(true);
		jBWait.setBounds(320, 35, 120, 40);
		add(jBWait);
		
		jBSelected = new JLabel("Seleccionado", JLabel.CENTER);
		jBSelected.setBackground(Color.decode("#EDEFF0"));
		jBSelected.setForeground(Color.decode("#A7A4A7"));
		jBSelected.setFont(new Font("Arial", Font.BOLD, 15));
		jBSelected.setBorder(BorderFactory.createLineBorder(Color.decode("#EDEFF0")));
		jBSelected.setOpaque(true);
		jBSelected.setBounds(440, 35, 120, 40);
		add(jBSelected);
		
		jBRejected = new JLabel("Rechazado", JLabel.CENTER);
		jBRejected.setBackground(Color.decode("#EDEFF0"));
		jBRejected.setForeground(Color.decode("#A7A4A7"));
		jBRejected.setFont(new Font("Arial", Font.BOLD, 15));
		jBRejected.setBorder(BorderFactory.createLineBorder(Color.decode("#EDEFF0")));
		jBRejected.setOpaque(true);
		jBRejected.setBounds(560, 35, 120, 40);
		add(jBRejected);
		
		
		jButtomInfo = new JButton("Más informacion");
		jButtomInfo.setBackground(Constant.COLOR_ORANGE);
		jButtomInfo.setForeground(Color.WHITE);
		jButtomInfo.setFont(new Font("Arial", Font.BOLD, 15));
		jButtomInfo.setBorder(BorderFactory.createLineBorder(Constant.COLOR_ORANGE));
		//jButtomInfo.addActionListener(controllerManager);
		//jBSignIn.setActionCommand(Events.SIGN_IN.toString());
		jButtomInfo.setBounds(730, 35, 180, 40);
		add(jButtomInfo);
	}
}
