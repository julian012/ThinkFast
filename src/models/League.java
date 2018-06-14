package models;

public enum League {
	NO_LEAGUE("Sin liga"), 
	BRONZE("Bronce"),
	PLATE("Plata"),
	GOLD("Oro"),
	RUBY("Ruby"),
	DIAMON("Diamante");
	
	private String name;
	
	private League (String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
