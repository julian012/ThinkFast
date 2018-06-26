package observer;

import connection.Connection;
import models.Result;

public interface IObsever  {
	
	/**
	 * Operaciones realizadas al cambio de estado
	 */
	public void update();
	
	public void playOnevsOne(Connection connection);
	
	public void startGameOnevsOne();
	
	public void sendQuestionOnevsOne();
	
	public void changeQuestion(String id);
	
	public void opponentAnswered();
	
	public void setResultsGameOneVsOne(Result playerA,Result playerB);

}
