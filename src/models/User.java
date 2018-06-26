package models;

import java.time.LocalDate;

import javax.swing.ImageIcon;

import structure.SimpleList;

public class User {
	
	private String id;
	private String name;
	private String nickname;
	private String email;
	private String password;
	private LocalDate birthDate;
	private String pathImageUser;
	private ImageIcon imageUser;
	private AccountInfo accountInfo;
	
	/**
	 * Constructor del servidor
	 */
	public User(String id, String name, String nickname, String email, String password, LocalDate birthDate,
			String pathImageUser, AccountInfo accountInfo) {
		this.id = id;
		this.name = name;
		this.nickname = nickname;
		this.email = email;
		this.password = password;
		this.birthDate = birthDate;
		this.pathImageUser = pathImageUser;
		this.accountInfo = accountInfo;
	}
	
	/**
	 * Constructor del cliente
	 * @return
	 */
	public User(String id, String name, String nickname, String email, String password, LocalDate birthDate,
			ImageIcon imageUser, AccountInfo accountInfo) {
		this.id = id;
		this.name = name;
		this.nickname = nickname;
		this.email = email;
		this.password = password;
		this.birthDate = birthDate;
		this.imageUser = imageUser;
		this.accountInfo = accountInfo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getPathImageUser() {
		return pathImageUser;
	}

	public void setPathImageUser(String pathImageUser) {
		this.pathImageUser = pathImageUser;
	}

	public AccountInfo getAccountInfo() {
		return accountInfo;
	}

	public void setAccountInfo(AccountInfo accountInfo) {
		this.accountInfo = accountInfo;
	}

	public ImageIcon getImageUser() {
		return imageUser;
	}

	public void setImageUser(ImageIcon imageUser) {
		this.imageUser = imageUser;
	}
	
	public void setQuestionList(SimpleList<String> questionList) {
		accountInfo.setQuestionList(questionList);
	}
}
