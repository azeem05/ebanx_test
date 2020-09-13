package com.example.demo.bean;

public class AccountDestinationResponse extends AccountResponse{
	public AccountDestinationResponse(String id, Double balance) {
		this.destination=new Account(id,balance);
	}
	
	Account destination;

	public Account getDestination() {
		return destination;
	}

	public void setDestination(Account destination) {
		this.destination = destination;
	}


}
