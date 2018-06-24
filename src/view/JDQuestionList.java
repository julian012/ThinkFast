package view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import controller.ClientController;
import controller.Events;
import models.User;

public class JDQuestionList extends JDialog  {

	private static final long serialVersionUID = 1L;
	private JPQuestionList questionListPanel;
	private JButton jBAccept;

	public JDQuestionList(JFrame frame, ClientController clientController, User user) {
		super(frame, true);
		setUndecorated(true);
		getContentPane().setBackground(Constraints.COLOR_DARK_GREY);
		setSize(500, 500);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		questionListPanel = new JPQuestionList(clientController, user);
		this.add(questionListPanel, BorderLayout.CENTER);
		jBAccept = new JButton(Constraints.BUTTOM_SAVE);
		jBAccept.setBackground(Constraints.COLOR_GREEN);
		jBAccept.addActionListener(clientController);
		jBAccept.setActionCommand(Events.SAVE_QUESTIONS.toString());
		this.add(jBAccept, BorderLayout.SOUTH);
	}
}
