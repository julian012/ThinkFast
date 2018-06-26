package models;

import javax.swing.ImageIcon;

public class Result {
	
	private String id;
	private String nickName;
	private int money;
	private int points;
	private ImageIcon image;
	private boolean winner;
	
	public Result(String id, String nickName, int money, int points, boolean winner ,ImageIcon image) {
		this.id = id;
		this.nickName = nickName;
		this.money = money;
		this.points = points;
		this.image = image;
		this.winner = winner;
	}
	
	public boolean isWinner() {
		return winner;
	}
	
	public void setisWinrer(boolean status) {
		winner = status;
	}

	public String getId() {
		return id;
	}

	public String getNickName() {
		return nickName;
	}

	public int getMoney() {
		return money;
	}

	public int getPoints() {
		return points;
	}

	public ImageIcon getImage() {
		return image;
	}
}
