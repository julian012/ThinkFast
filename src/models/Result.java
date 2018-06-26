package models;

import java.awt.image.BufferedImage;

public class Result {
	
	private String id;
	private String nickName;
	private int money;
	private int points;
	private BufferedImage image;
	
	public Result(String id, String nickName, int money, int points, BufferedImage image) {
		this.id = id;
		this.nickName = nickName;
		this.money = money;
		this.points = points;
		this.image = image;
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

	public BufferedImage getImage() {
		return image;
	}
}
