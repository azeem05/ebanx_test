package com.example.demo.bean;

public class AccountOriginResponse extends AccountResponse{
	public AccountOriginResponse(String id, Double balance) {
		this.origin=new Account(id,balance);
	}
	
	Account origin;

	public Account getOrigin() {
		return origin;
	}

	public void setOrigin(Account origin) {
		this.origin = origin;
	}

}
