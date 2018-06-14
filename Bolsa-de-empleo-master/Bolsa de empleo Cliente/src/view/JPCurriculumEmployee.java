package view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class JPCurriculumEmployee extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JPInformationAccountEmployee informationAccountEmployee;
	

	public JPCurriculumEmployee() {
		informationAccountEmployee = new JPInformationAccountEmployee();
		setBackground(Color.decode("#F7F5F5"));
		setLayout(new BorderLayout());
		JScrollPane jScrollPane = new JScrollPane(informationAccountEmployee);
		add(jScrollPane, BorderLayout.CENTER);
		
	}
}
