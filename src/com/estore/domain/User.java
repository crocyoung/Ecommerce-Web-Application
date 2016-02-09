package com.estore.domain;

/*
create table users(
    id          int primary key auto_increment,
    name        varchar(40),
    password    varchar(60),
    email       varchar(40),
    nickname    varchar(40),
    status      int,
    regisittime datetime,
    activecode  varchar(100)
);
 */

public class User {
	private int id;
	private String username;
	private String password;
	private String email;
	private String nickname;
	//private int    status;
	//private Date   registtime;
	//private String activecode;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
