package models;

public class Account {
	
	private String email;
	private String password;
	private String namePhoto;
	private String numberPhone;
	private String address;
	private City city;
	
	public Account(String email, String password, String namePhoto, String numberPhone, String address, City city) {
		setEmail(email);
		setPassword(password);
		setNamePhoto(namePhoto);
		setNumberPhone(numberPhone);
		setAddress(address);
		setCity(city);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNamePhoto() {
		return namePhoto;
	}

	public void setNamePhoto(String namePhoto) {
		this.namePhoto = namePhoto;
	}

	public String getNumberPhone() {
		return numberPhone;
	}

	public void setNumberPhone(String numberPhone) {
		this.numberPhone = numberPhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
}
