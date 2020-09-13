package com.example.demo.bean;

public class Destination {

	Integer Id;
	Double balance;
	public Destination(Integer Id,Double balance) {
		this.Id=Id;
		this.balance=balance;
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
}
