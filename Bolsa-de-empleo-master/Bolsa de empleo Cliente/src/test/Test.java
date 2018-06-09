package test;

import java.awt.GridLayout;

import javax.swing.JFrame;

import view.JPCurriculumEmployee;

public class Test extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JPCurriculumEmployee homeEmployee;

	
	public Test() {
		setLayout(new GridLayout(1, 1));
		setSize(903,435);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		homeEmployee = new JPCurriculumEmployee();
		add(homeEmployee);
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new Test();
	}
	
}
