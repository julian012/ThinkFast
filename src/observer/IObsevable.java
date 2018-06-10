package observer;

public interface IObsevable {
	
	public void addObserver(IObsever obsever);
	
	public void removeObserver(IObsever obsever);
}
