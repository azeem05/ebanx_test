package com.example.demo.bean;

public class AccountTransferResponse extends AccountResponse{
	public AccountTransferResponse(String id, Double balance) {
		this.origin=new Account(id,balance);
	}
	
	public AccountTransferResponse() {
		// TODO Auto-generated constructor stub
	}

	Account origin;
	Account destination;

	public Account getDestination() {
		return destination;
	}

	public void setDestination(Account destination) {
		this.destination = destination;
	}

	public Account getOrigin() {
		return origin;
	}

	public void setOrigin(Account origin) {
		this.origin = origin;
	}

}
