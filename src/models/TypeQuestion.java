package models;

public enum TypeQuestion {
	SPORTS("Deportes"), 
	MUSIC("Musica"), 
	HISTORY("Historia"), 
	MOVIES("Peliculas"), 
	ENTERTAIMENT("Peliculas"), 
	GEOGRAPHY("Geografia"), 
	SCIENCE("Ciencia"), 
	LITERATURE("Literatura"), 
	PHILOSOPHY("Filosofia"), 
	ART("Arte");
	
	public String value;
	
	private TypeQuestion(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
