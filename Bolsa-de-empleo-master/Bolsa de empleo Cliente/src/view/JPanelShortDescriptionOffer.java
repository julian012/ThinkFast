package view;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class JPanelShortDescriptionOffer extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel jLtitle;
	private JLabel jLnameCompany;
	private JLabel jLcity;
	private JTextArea jTShorDescriptionOffer;
	private JLabel jLDate;
	private JPanelPhoto jPhotoCompany;
	private JButton jButtomInfo;
	
	public JPanelShortDescriptionOffer() {
		setLayout(null);
		setBackground(Color.WHITE);
		
		jLtitle = new JLabel("Nombre de la publicación");
		jLtitle.setForeground(Color.decode("#4F82C4"));
		jLtitle.setFont(new Font("Arial", Font.BOLD, 18));
		jLtitle.setBounds(10, 0, 700, 40);
		add(jLtitle);
		
		jLnameCompany = new JLabel("Nombre de la compañia");
		jLnameCompany.setForeground(Color.decode("#4F82C4"));
		jLnameCompany.setFont(new Font("Arial", Font.BOLD, 15));
		jLnameCompany.setBounds(10, 30, 700, 40);
		add(jLnameCompany);
		
		jTShorDescriptionOffer = new JTextArea("Los objetivos que trazan en la siguiente investigación son: "
				+ "Desarrollar una propuesta de metodología de enseñanza de algoritmos orientada en el perfil "
				+ "del estudiante usando el cuestionario VARK, y la identificación de las herramientas "
				+ "apropiadas y materiales que deberían ser usadas en clase, también acorde al perfil"
				+ " de los estudiantes. La metodología que emplea el autor fue analizar los resultados "
				+ "de los estudiantes al usar el cuestionario VARK y así aplicar el conocimiento "
				+ "adquirido a las aulas de clase, los profesores, y al desarrollo de algoritmos. "
				+ "Da fin con la conclusión que proponer la metodología de cuestionario VARK"
				+ " define el perfil del estudiante y por consiguiente cuáles son los materiales y herramientas "
				+ "apropiadas cuando se dicta algoritmos. Esto ayuda en la presente investigación para saber "
				+ "que hay deficiencias en las metodologías usadas, a la vez dejar en claro que es iniciativa "
				+ "del docente como del estudiante encontrar las herramientas adecuadas para un buen aprendizaje "
				+ "de la materia. [2]\n" );
		jTShorDescriptionOffer.setEditable(false);
		jTShorDescriptionOffer.setLineWrap(true);
		jTShorDescriptionOffer.setWrapStyleWord(true);
		jTShorDescriptionOffer.setFont(new Font("Arial", Font.BOLD, 12));
		jTShorDescriptionOffer.setBounds(10, 60, 450, 58);
		add(jTShorDescriptionOffer);
		
		jLDate = new JLabel("Fecha de publicacion");
		jLDate.setFont(new Font("Arial", Font.BOLD, 15));
		jLDate.setForeground(Color.decode("#83878F"));
		jLDate.setBounds(10, 120, 210, 40);
		add(jLDate);
		
		jLcity = new JLabel("Ciudad de la Publicacion");
		jLcity.setForeground(Color.decode("#83878F"));
		jLcity.setBounds(460, 50, 180, 40);
		add(jLcity);
		
		try {
			jPhotoCompany = new JPanelPhoto(ImageIO.read(new File("files/imagenes/ajustes.png")), Color.WHITE);
			jPhotoCompany.setBounds(650, 35, 70, 70);
			add(jPhotoCompany);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		jButtomInfo = new JButton("Más informacion");
		jButtomInfo.setBackground(Constant.COLOR_ORANGE);
		jButtomInfo.setForeground(Color.WHITE);
		jButtomInfo.setFont(new Font("Arial", Font.BOLD, 15));
		jButtomInfo.setBorder(BorderFactory.createLineBorder(Constant.COLOR_ORANGE));
		//jButtomInfo.addActionListener(controllerManager);
		//jBSignIn.setActionCommand(Events.SIGN_IN.toString());
		jButtomInfo.setBounds(730, 50, 180, 40);
		add(jButtomInfo);
	}
	
}
