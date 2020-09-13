package com.example.demo.bean;

public class Account {

	public Account(String Id){
		this.id=Id;
	}
	
	public Account(String Id, Double balance) {
		this.id=Id;
		this.balance=balance;
	}
	String id;
	Double balance;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean ret=false;
		if(obj instanceof Account == false) {
			ret=false;
		}
		if(((Account)obj).getId().equals(this.id))
			ret=true;
		
		return ret;
	}
}
