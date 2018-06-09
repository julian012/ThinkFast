package models;

public enum StatusOffer {
	POSTULATE("Postulado"),
	CANCELLED("Cancelado");
	
	private String value;
	
	private StatusOffer(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
