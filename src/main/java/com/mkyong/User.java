package com.mkyong;

public class User {

	private int id;
	private String username;
	private String password;
	private String location;
	private String mood;
	
	public User(String username, String password, String location, String mood) {
		super();
		this.username = username;
		this.password = password;
		this.location = location;
		this.mood = mood;
	}
	
	public void setUser(User user){
		this.username = username;
		this.password = password;
		this.id = user.id;
		this.location = location;
		this.mood = mood;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getMood() {
		return mood;
	}
	public void setMood(String mood) {
		this.mood = mood;
	}
}
