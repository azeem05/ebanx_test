package com.example.demo.bean;

public class AccountErrorResponse extends AccountResponse{
String error;

public AccountErrorResponse(String error) {
	this.error=error;
}
public String getError() {
	return error;
}

public void setError(String error) {
	this.error = error;
}

}
