package models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.Connection;
import structure.NodeSimpleList;
import structure.SimpleList;

public class Game {

	private Connection playerA;
	private Result resultPlayerA;
	private Connection playerB;
	private Result resultPlayerB;
	private boolean nextQuestionPlayerA;
	private boolean nextQuestionPlayerB;
	private ArrayList<QuestionList> questionList;
	private boolean waitForPlayer;
	private boolean gameInProcess;
	private boolean gameFinish;
	private Server server;
	private static final Logger LOGGER = Logger.getAnonymousLogger();

	@SuppressWarnings("unchecked")
	public Game(Connection playerA, ArrayList<QuestionList> questionList, Server server) {
		this.server = server;
		this.playerA = playerA;
		this.questionList = (ArrayList<QuestionList>)questionList.clone();
		waitForPlayer = true;
		gameFinish = false;
	}

	public void addSecondPlayer(Connection connection) {
		this.playerB = connection;
		waitForPlayer = false;
		gameInProcess = true;
	}

	public boolean isWaitForPlayer() {
		return waitForPlayer;
	}

	public boolean isGameInProcess() {
		return gameInProcess;
	}

	public boolean isGameFinish() {
		return gameFinish;
	}

	public void startGame() {
		playerA.startGame();
		playerB.startGame();
		SimpleList<String> commonQuestionList = getCommonQuestions();
		removeNoCommondQuestions(commonQuestionList);
		Collections.shuffle(questionList);
		try {
			playerA.sendQuestionList(questionList);
			playerB.sendQuestionList(questionList);
			LOGGER.log(Level.SEVERE, "Se mando lista de preguntas");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public void removeNoCommondQuestions(SimpleList<String> commonQuestionList) {
		for (int i = 0; i < questionList.size(); i++) {
			if(valideQuestion(questionList.get(i), commonQuestionList)) {
				questionList.remove(i);
				i--;
			}
		}
	}

	public boolean valideQuestion(QuestionList question, SimpleList<String> commonQuestionList) {
		System.out.println("Tipo de pregunta: " + question.getTypeQuestion().toString());
		NodeSimpleList<String> actualNode = commonQuestionList.getHead();
		while(actualNode != null) {
			if(actualNode.getInfo().equals(question.getTypeQuestion().toString())) {
				return false;
			}
			actualNode = actualNode.getNext();
		}
		return true;
	}

	/**
	 * Obtener las preguntas en comun de ambos jugadores
	 */
	public SimpleList<String> getCommonQuestions() {
		SimpleList<String> commonQuestionList = new SimpleList<>();
		NodeSimpleList<String> actualNodePlayerA = playerA.getUser().getAccountInfo().getQuestionList().getHead();
		while(actualNodePlayerA != null) {
			NodeSimpleList<String> actualNodePlayerB = playerB.getUser().getAccountInfo().getQuestionList().getHead();
			while(actualNodePlayerB != null) {
				if(actualNodePlayerA.getInfo().equals(actualNodePlayerB.getInfo())) {
					commonQuestionList.addNode(new NodeSimpleList<String>(actualNodePlayerA.getInfo()));
					break;
				}
				actualNodePlayerB = actualNodePlayerB.getNext();
			}
			actualNodePlayerA = actualNodePlayerA.getNext();
		}
		return commonQuestionList;
	}

	public void getUserById(String id) {
		if(playerA.getUser().getId().equals(id) || id.equals(playerA.getUser().getNickname())) {
			nextQuestionPlayerA = true;
			System.out.println("Usuario A");
		}else if(playerB.getUser().getId().equals(id) || id.equals(playerB.getUser().getNickname())) {
			nextQuestionPlayerB = true;
			System.out.println("Usuario B");
		}
		validateChangeQuestion();
	}
	
	public void addResultToPlayer(Result result) {
		if(result.getId().equals(playerA.getUser().getId())) {
			resultPlayerA = result;
		}else if(result.getId().equals(playerB.getUser().getId())) {
			resultPlayerB = result;
		}
		validateFinishGame();
	}
	
	public void validateFinishGame() {
		if(resultPlayerA != null && resultPlayerB != null) {
			LOGGER.log(Level.SEVERE, "Termino el juego se empieza a mandar los resultados");
			if(resultPlayerA.getPoints() > resultPlayerB.getPoints()) {
				resultPlayerA.setisWinrer(true);
			}else {
				resultPlayerB.setisWinrer(true);
			}
			gameInProcess = false;
			gameFinish = true;
			playerA.getResultsFinalGameOnevsOne(resultPlayerA, resultPlayerB);
			playerB.getResultsFinalGameOnevsOne(resultPlayerA, resultPlayerB);
			server.updateValuesInUsers(resultPlayerA, resultPlayerB);
		}
	}
	
	public Connection getPlayerA() {
		return playerA;
	}

	public void validateChangeQuestion() {
		if(nextQuestionPlayerA && nextQuestionPlayerB) {
			playerA.nextQuestion();
			playerB.nextQuestion();
			nextQuestionPlayerA = false;
			nextQuestionPlayerB = false;
		}else {
			if(nextQuestionPlayerA) {
				/**
				 * El usuario A ya consesto
				 */
				playerB.opponentAnswered();
			}else {
				/**
				 * EL usuario b a contesto
				 */
				playerA.opponentAnswered();
			}
		}
	}
}
