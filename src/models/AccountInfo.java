package models;

import structure.SimpleList;
import view.Power;

public class AccountInfo {
	
	private int experience;
	private int money;
	private int games;
	private int totalGames;
	private SimpleList<String> questionList;
	private SimpleList<Power> powerList;
	private int usedPowers;
	private SimpleList<String> listIdFriends;
	private int onFire;
	
	public AccountInfo(int experience, int money, int games, int totalGames, SimpleList<String> questionList,
			SimpleList<Power> powerList, int usedPowers, SimpleList<String> listIdFriends, int onFire) {
		this.experience = experience;
		this.money = money;
		this.games = games;
		this.totalGames = totalGames;
		this.questionList = questionList;
		this.powerList = powerList;
		this.usedPowers = usedPowers;
		this.listIdFriends = listIdFriends;
		this.onFire = onFire;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}


	public int getGames() {
		return games;
	}

	public void setGames(int games) {
		this.games = games;
	}

	public int getUsedPowers() {
		return usedPowers;
	}

	public void setUsedPowers(int usedPowers) {
		this.usedPowers = usedPowers;
	}

	public int getTotalGames() {
		return totalGames;
	}

	public void setTotalGames(int totalGames) {
		this.totalGames = totalGames;
	}

	public SimpleList<String> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(SimpleList<String> questionList) {
		this.questionList = questionList;
	}

	public SimpleList<Power> getPowerList() {
		return powerList;
	}

	public void setPowerList(SimpleList<Power> powerList) {
		this.powerList = powerList;
	}


	public SimpleList<String> getListIdFriends() {
		return listIdFriends;
	}

	public void setListIdFriends(SimpleList<String> listIdFriends) {
		this.listIdFriends = listIdFriends;
	}

	public int getOnFire() {
		return onFire;
	}

	public void setOnFire(int onFire) {
		this.onFire = onFire;
	}
	
	public void addMoney(int m) {
		money += m;
	}
	
	public void addPoints(int p) {
		experience += p;
	}
	
	public void addGame() {
		games++;
	}
	
	public void addTotalGames() {
		totalGames++;
	}
}
