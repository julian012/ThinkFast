package models;

public enum StatusJobOffer {
	SELECTED("Seleccionado"),
	REJECTED("Rechazado");
	
	private String value;
	
	private StatusJobOffer(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
