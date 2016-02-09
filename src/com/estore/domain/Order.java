package com.estore.domain;

import java.sql.Date;

/*
create table orders(
    id           varchar(50) primary key,
    money        double,
    ordertime    datetime,
    receiverinfo varchar(255),
    paystatus    int,
    user_id      int,        

--  foreign key
    foreign key(user_id) references users(id) 
);
*/


public class Order {
	private String id;
	private double money;
	private Date   ordertime;
	private String receiverinfo;
	private int    paystatus;
	private int    user_id;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public String getReceiverinfo() {
		return receiverinfo;
	}
	public void setReceiverinfo(String receiverinfo) {
		this.receiverinfo = receiverinfo;
	}
	public int getPaystatus() {
		return paystatus;
	}
	public void setPaystatus(int paystatus) {
		this.paystatus = paystatus;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	
	
	
	

}
