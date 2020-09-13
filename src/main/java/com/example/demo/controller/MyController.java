package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.AccountConstants;
import com.example.demo.bean.AccountErrorResponse;
import com.example.demo.bean.AccountRequest;
import com.example.demo.bean.AccountResponse;
import com.example.demo.exception.ApplicationException;
import com.example.demo.service.AccountService;

@RestController
public class MyController {

	@Autowired
	AccountService accountService;
	
	@RequestMapping(value = "/balance", method = RequestMethod.GET)
	public ResponseEntity<Double> myAccountBalance(@RequestParam String account_id) {
		ResponseEntity<Double> response= null;
		try {
			Double balance= accountService.getBalance(account_id);
			response=new ResponseEntity<Double>(balance, HttpStatus.OK);
		} catch (ApplicationException e) {
			System.out.println(e);
			response = new ResponseEntity<Double>(0d, HttpStatus.NOT_FOUND);
		}
		return response;
	}

	/*
	@RequestMapping(value = "/create",produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<AccountResponse> createAccount(@RequestParam Integer id, @RequestParam Double amount) {
		ResponseEntity<AccountResponse> response= null;
		AccountResponse accountResponse = null;
		try {
			accountResponse= accountService.createAccount(id, amount);
			response=new ResponseEntity<AccountResponse>(accountResponse, HttpStatus.CREATED);
		} catch (ApplicationException e) {
			response = new ResponseEntity<AccountResponse>(accountResponse, HttpStatus.NOT_FOUND);
		}
		return response;
	}*/
	@RequestMapping(value = "/event",produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<?> depositIntoAccount(@RequestBody AccountRequest request) {
		ResponseEntity<?> response= null;
		AccountResponse accountResponse = null;
		try {
			String event = request.getType();
			if(AccountConstants.EVENT_DEPOSIT.equals(event)) {
				accountResponse= accountService.depositAccount(request.getDestination(), request.getAmount());
			}else if(AccountConstants.EVENT_WITHDRAW.equals(event)) {
				accountResponse= accountService.withdrawAccount(request.getOrigin(), request.getAmount());
			} else if(AccountConstants.EVENT_TRANSFER.equals(event)) {
				accountResponse= accountService.transferAccount(request.getOrigin(), request.getAmount(),request.getDestination());
			}
			
			response=new ResponseEntity<AccountResponse>(accountResponse, HttpStatus.CREATED);
		} catch (ApplicationException e) {
			response = new ResponseEntity<Double>(0d, HttpStatus.NOT_FOUND);
		}
		return response;
	}
	
	/*
	@RequestMapping(value = "/deposit",produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<AccountResponse> depositIntoAccount(@RequestParam Integer id, @RequestParam Double amount) {
		ResponseEntity<AccountResponse> response= null;
		AccountResponse accountResponse = null;
		try {
			accountResponse= accountService.depositAccount(id, amount);
			response=new ResponseEntity<AccountResponse>(accountResponse, HttpStatus.CREATED);
		} catch (ApplicationException e) {
			response = new ResponseEntity<AccountResponse>(accountResponse, HttpStatus.NOT_FOUND);
		}
		return response;
	}
	
	
	@RequestMapping(value = "/withdraw",produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<AccountResponse> withdrawFromAccount(@RequestParam Integer id, @RequestParam Double amount) {
		ResponseEntity<AccountResponse> response= null;
		AccountResponse accountResponse = null;
		try {
			accountResponse= accountService.withdrawAccount(id, amount);
			response=new ResponseEntity<AccountResponse>(accountResponse, HttpStatus.CREATED);
		} catch (ApplicationException e) {
			response = new ResponseEntity<AccountResponse>(accountResponse, HttpStatus.NOT_FOUND);
		}
		return response;
	}
	*/
	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public ResponseEntity<String> resetAll() {
		ResponseEntity<String> response= null;
		try {
			if(accountService.resetAll()) {
				response= new ResponseEntity<String>(HttpStatus.OK);
			}
		} catch (ApplicationException e) {
			response = new ResponseEntity<String>("NOT_OK", HttpStatus.NOT_FOUND);
		}
		return response;
	}
}

