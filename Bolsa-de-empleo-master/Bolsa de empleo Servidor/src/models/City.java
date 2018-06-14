package models;

public class City {
	
	private String name;
	private String nameDepartent;
	
	public City(String name, String nameDepartment) {
		this.name = name;
		this.nameDepartent = nameDepartment;
	}
	
	public String getDepartmentName() {
		return nameDepartent;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "City [name=" + name + "]";
	}
	
	
}
