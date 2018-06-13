package view;

public class Power {
	
	private String namePower;
	private int countPower;
	
	public Power(String namePower, int countPower) {
		this.namePower = namePower;
		this.countPower = countPower;
	}

	public String getNamePower() {
		return namePower;
	}

	public void setNamePower(String namePower) {
		this.namePower = namePower;
	}

	public int getCountPower() {
		return countPower;
	}

	public void setCountPower(int countPower) {
		this.countPower = countPower;
	}
}
