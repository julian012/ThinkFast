package observer;

import connection.Connection;

public interface IObsever  {
	
	/**
	 * Operaciones realizadas al cambio de estado
	 */
	public void update();
	
	public void playOnevsOne(Connection connection);
	
	public void startGameOnevsOne();
}
