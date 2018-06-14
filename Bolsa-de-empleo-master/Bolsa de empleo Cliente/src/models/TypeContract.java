package models;

public enum TypeContract {
	OBRA_O_LABOR("Obra o labor"),
	TERMINO_INDEFINIDO("Termino indefinido"),
	TERMINO_FIJO("Termino fijo"),
	PRESTACION_DE_SERVICIOS("Prestación de servicios"),
	OCASIONAL("Ocasional");

	private String value;

	private TypeContract(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
