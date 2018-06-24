package view;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.ClientController;
import models.TypeQuestion;
import models.User;
import structure.NodeSimpleList;
import structure.SimpleList;
import utilities.Utilities;

public class JPQuestionList extends JPanel {

	private static final long serialVersionUID = 1L;
	private SimpleList<String> list;
	
	public JPQuestionList(ClientController clientController, User user) {
		setBackground(Constraints.COLOR_DARK_GREY);
		setSize(447, 474);
		setLayout(new GridLayout(5, 2));
		list =user.getAccountInfo().getQuestionList();
		loadImages(list);
	}
	
	public void loadImages(SimpleList<String> list) {
		for(int i = 0; i < TypeQuestion.values().length; i++) {
			String nameImage = getNameQuestion(TypeQuestion.values()[i].toString(), list);
			ImageIcon image = new ImageIcon(getClass().getResource("../img/Tipo de preguntas/"+nameImage));
			JButton button = new JButton(new ImageIcon(image.getImage().getScaledInstance(234, 91, Image.SCALE_SMOOTH)));
			button.setOpaque(false);
			button.setContentAreaFilled(false);
			button.setBorderPainted(false);
			button.setName(nameImage);
			button.setCursor(Constraints.HAND);
			button.addActionListener(e -> this.changeImage(button));
			this.add(button);
		}
	}
	
	public void changeImage(JButton button) {
		String value = Utilities.getExtencionName(button.getName());
		String result = "";
		if(value.equals("No.PNG")) {
			result = Utilities.getNoName(button.getName());
			ImageIcon image = new ImageIcon(getClass().getResource("../img/Tipo de preguntas/" + result + ".PNG"));
			button.setIcon(new ImageIcon(image.getImage().getScaledInstance(234, 91, Image.SCALE_SMOOTH)));
			button.setName(Utilities.getNoName(button.getName()) + ".PNG");
		}else {
			result = Utilities.getWithOutExtencionName(button.getName());
			ImageIcon image = new ImageIcon(getClass().getResource("../img/Tipo de preguntas/" + result  + "No.PNG"));
			button.setIcon(new ImageIcon(image.getImage().getScaledInstance(234, 91, Image.SCALE_SMOOTH)));
			button.setName(Utilities.getWithOutExtencionName(button.getName())  + "No.PNG");
		}
		updateInfo(result);
	}
	
	public void updateInfo(String value) {
		System.out.println("Llego: " +  value);
		if(!searchNode(value)) {
			list.deleteNode(value);
		}else {
			list.addNode(new NodeSimpleList<String>(value));
		}
	}
	
	public boolean searchNode(String name) {
		NodeSimpleList<String> actualNode = list.getHead();
		while(actualNode != null) {
			if(actualNode.getInfo().equals(name)){
				return false;
			}else {
				actualNode = actualNode.getNext();
			}
		}
		return true;
	}
	
	public String getNameQuestion(String value, SimpleList<String> list) {
		NodeSimpleList<String> actualNode = list.getHead();
		while(actualNode != null) {
			if(actualNode.getInfo().equals(value)) {
				return value + ".PNG";
			}
			actualNode = actualNode.getNext();
		}
		return value + "No.PNG";
	}
}
