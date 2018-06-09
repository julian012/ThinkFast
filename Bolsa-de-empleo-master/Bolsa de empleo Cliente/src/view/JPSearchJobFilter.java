package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import models.TypeContract;

public class JPSearchJobFilter extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private GridBagConstraints constraints;
	private JLabel jLTitle;
	private JLabel jLSubTitle;
	private JLabel jlKeyWord;
	private JLabel jlSalary;
	private JLabel jlType;
	private JLabel jlDepartment;
	private JLabel jlCity;
	private JTextField jTKeyWord;
	private JTextField jTSalaryMin;
	private JTextField jTSalatyMax;
	private JComboBox<String> jTType;
	private JComboBox<String> jTDepartment;
	private JComboBox<String> jTDCity;
	private TypeContract contract;
	
	public JPSearchJobFilter() {
		setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		setBackground(Color.decode("#F7F5F5"));
		initComponents();
	}
	
	public void initComponents() {
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridheight = 1;
		constraints.gridwidth = 10;
		constraints.weightx = 1;
		constraints.ipady = 40;
		constraints.gridy = 1;
		jLTitle = new JLabel("Ofertas de empleo", JLabel.LEFT);
		jLTitle.setFont(getFontA(22));
		add(jLTitle, constraints);
		constraints.ipady = 22;
		constraints.gridy = 2;
		jLSubTitle = new JLabel("Filtros");
		jLSubTitle.setFont(getFontA(18));
		jLSubTitle.setOpaque(true);
		jLSubTitle.setBackground(Constant.COLORBLUE);
		jLSubTitle.setForeground(Color.WHITE);
		add(jLSubTitle, constraints);
		constraints.ipady = 22;
		constraints.gridy = 3;
		constraints.gridwidth = 2;
		constraints.ipadx = 1;
		jlKeyWord = new JLabel("Palabra clave", JLabel.CENTER);
		jlKeyWord.setFont(getFontA(15));
		jlKeyWord.setOpaque(true);
		jlKeyWord.setBackground(Color.decode("#D9D9D9"));
		add(jlKeyWord, constraints);
		constraints.ipadx = 3;
		jlSalary = new JLabel("Salario", JLabel.CENTER);
		jlSalary.setFont(getFontA(15));
		jlSalary.setOpaque(true);
		jlSalary.setBackground(Color.decode("#D9D9D9"));
		add(jlSalary, constraints);
		constraints.ipadx = 5;
		jlType = new JLabel("Tipo de contrato", JLabel.CENTER);
		jlType.setFont(getFontA(15));
		jlType.setOpaque(true);
		jlType.setBackground(Color.decode("#D9D9D9"));
		add(jlType, constraints);
		constraints.ipadx = 7;
		jlDepartment = new JLabel("Departamento", JLabel.CENTER);
		jlDepartment.setFont(getFontA(15));
		jlDepartment.setOpaque(true);
		jlDepartment.setBackground(Color.decode("#D9D9D9"));
		add(jlDepartment, constraints);
		constraints.ipadx = 9;
		jlCity = new JLabel("Ciudad", JLabel.CENTER);
		jlCity.setFont(getFontA(15));
		jlCity.setOpaque(true);
		jlCity.setBackground(Color.decode("#D9D9D9"));
		add(jlCity, constraints);
		constraints.gridy = 4;
		constraints.gridwidth = 2;
		constraints.ipadx = 1;
		jTKeyWord = new JTextField();
		jTKeyWord.setBorder(BorderFactory.createLineBorder(Color.decode("#666666")));
		constraints.insets = new Insets(2, 10, 5, 10);
		add(jTKeyWord, constraints);
		constraints.gridwidth = 1;
		constraints.ipadx = 3;
		jTSalaryMin = new JTextField();
		jTSalaryMin.setBorder(BorderFactory.createLineBorder(Color.decode("#666666")));
		constraints.insets = new Insets(2, 20, 5, 5);
		add(jTSalaryMin, constraints);
		constraints.gridwidth = 1;
		constraints.ipadx = 4;
		jTSalatyMax = new JTextField();
		jTSalatyMax.setBorder(BorderFactory.createLineBorder(Color.decode("#666666")));
		constraints.insets = new Insets(2, 5, 5, 20);
		add(jTSalatyMax, constraints);
		constraints.gridwidth = 2;
		constraints.ipadx = 5;
		jTType = new JComboBox<>(getListTypeContract());
		jTType.setBorder(BorderFactory.createLineBorder(Color.decode("#666666")));
		jTType.setBackground(Color.WHITE);
		jTType.setForeground(Color.BLACK);
		constraints.insets = new Insets(2, 5, 5, 10);
		add(jTType, constraints);
		constraints.ipadx = 7;
		jTDepartment = new JComboBox<>();
		jTDepartment.setBorder(BorderFactory.createLineBorder(Color.decode("#666666")));
		jTDepartment.setBackground(Color.WHITE);
		jTDepartment.setForeground(Color.BLACK);
		add(jTDepartment, constraints);
		constraints.ipadx = 9;
		jTDCity = new JComboBox<>();
		jTDCity.setBorder(BorderFactory.createLineBorder(Color.decode("#666666")));
		jTDCity.setBackground(Color.WHITE);
		jTDCity.setForeground(Color.BLACK);
		add(jTDCity, constraints);
		
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridheight = 1;
		constraints.gridwidth = 10;
		constraints.gridy = 5;
		constraints.ipadx = 1;
		constraints.insets = new Insets(0, 0, 0, 0);
		JLabel jPanel = new JLabel("  ");
		add(jPanel, constraints);
	}
	
	public Font getFontA(int size) {
		return new Font("Arial", Font.BOLD, size);
	}
	
	public String[] getListTypeContract() {
		TypeContract[] values = TypeContract.values();
		String[] names = new String[values.length];
		for (int i = 0; i < names.length; i++) {
			names[i] = values[i].getValue();
		}
		return names;
	}
}
