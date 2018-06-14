package models;

import java.util.ArrayList;

public class Department {
	
	private int id;
	private String name;
	private ArrayList<City> cityList;
	
	public Department(int id, String name, ArrayList<City> cityList) {
		this.id = id;
		this.name = name;
		this.cityList = cityList;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public ArrayList<City> getCityList() {
		return cityList;
	}
	
	public City getCityByName(String name) {
		for (City city : cityList) {
			if (city.getName().equals(name)) {
				return city;
			}
		}
		return null;
	}
}
