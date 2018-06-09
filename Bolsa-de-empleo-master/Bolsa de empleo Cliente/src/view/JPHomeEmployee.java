package view;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class JPHomeEmployee extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JTextArea first;
	private JTextArea second;
	private JTextArea three;
	private JPanelPhoto firstP;
	private JPanelPhoto secondP;
	private JPanelPhoto threeP;

	public JPHomeEmployee() {
		setLayout(null);
		first = new JTextArea("Bienvenido a la BOLSA DE EMPLEO JS. \nAplicación pensada en gente "
				+ "trabajadora ofreciendo una herramienta para buscar trabajos con relación a los "
				+ "gustos o criterios que tenga al momento de escogerlo de empresas que postulen "
				+ "sus ofertas de trabajo y siempre estar actualizado de cualquier novedad.");
		first.setEditable(false);
		first.setLineWrap(true);
		first.setWrapStyleWord(true);
		first.setFont(new Font("Arial", Font.PLAIN, 20));
		first.setForeground(Color.BLACK);
		first.setBackground(Color.decode("#E4E8E5"));
		first.setBounds(0, 0, 351, 425);
		first.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		
		try {
			firstP = new JPanelPhoto(ImageIO.read(new File("files/imagenes/home.png")), Color.decode("#E4E8E5"));
			firstP.setBounds(150, 380 , 40, 40);
			add(firstP);
			add(first);
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		second = new JTextArea("En el modulo de aplicaciones encontrara todo lo relacionado en las ofertas "
				+ "de trabajo donde se postulo, ademas de tener información sobre el estado de la oferta. "
				+ "Para el modulo de buscar ofertas se brinda de filtros para encontrar el trabajo acorde "
				+ "a sus necesidades y habilidades. Las notificaciones siempre lo tendrán actualizado de "
				+ "los cambios que hayan de las aplicaciones donde se postulo y Hoja de vida le permite "
				+ "realizar cambios en su información personal");
		second.setEditable(false);
		second.setLineWrap(true);
		second.setWrapStyleWord(true);
		second.setFont(new Font("Arial", Font.PLAIN, 20));
		second.setForeground(Color.BLACK);
		second.setBackground(Color.decode("#F2F2F2"));
		second.setBounds(351, 0, 351, 425);
		second.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		try {
			secondP = new JPanelPhoto(ImageIO.read(new File("files/imagenes/icono_informacion.png")), Color.decode("#F2F2F2"));
			secondP.setBounds(501, 380, 40, 40);
			add(secondP);
			add(second);
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		three = new JTextArea("Julián Camilo Serna Vargas diseñador, programador y animador :D");
		three.setLineWrap(true);
		three.setWrapStyleWord(true);
		three.setFont(new Font("Arial", Font.PLAIN, 20));
		three.setForeground(Color.BLACK);
		three.setBackground(Color.WHITE);
		three.setBounds(702, 0, 351, 425);
		three.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		
		try {
			threeP = new JPanelPhoto(ImageIO.read(new File("files/imagenes/user.png")), Color.WHITE);
			threeP.setBounds(852, 380,40,40);
			add(threeP);
			add(three);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
