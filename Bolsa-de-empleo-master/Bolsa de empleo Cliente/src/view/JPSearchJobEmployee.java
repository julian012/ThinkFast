package view;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;

public class JPSearchJobEmployee extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPSearchJobFilter jobFilter;
	
	public JPSearchJobEmployee() {
		setLayout(new BorderLayout());
		setBackground(Color.decode("#F7F5F5"));
		jobFilter = new JPSearchJobFilter();
		add(jobFilter, BorderLayout.NORTH);
	}
	
	
}
