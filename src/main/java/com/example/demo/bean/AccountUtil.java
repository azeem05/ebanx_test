package com.example.demo.bean;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.example.demo.AccountConstants;
import com.example.demo.exception.ApplicationException;

@Component
public class AccountUtil {
	
	private ArrayList<Account> accountList= new ArrayList<Account>();
	
	public Account createAccount(Account account) throws ApplicationException {
		if(accountList.contains(account))
			throw new ApplicationException(AccountConstants.ACCOUNT_ALREADY_EXISTS);
			accountList.add(account);
			return account;
	}
	
	public Account getAccount(Account account) throws ApplicationException {
		Account resAcc=null;
		if(!accountList.contains(account))
			throw new ApplicationException(AccountConstants.ACCOUNT_NOT_EXISTS);
			for(Account acc: accountList) {
				if(acc.equals(account))
					resAcc=acc;
			}
			return resAcc;
	}
	
	public Account depositAccount(String id,Double amount) throws ApplicationException {
		System.out.println("Deposit into account="+id);
		Account resAcc=null;
		if(amount<=0) {
			throw new ApplicationException(AccountConstants.INVALID_DEPOSIT_AMOUNT);
		}
		Account givenAccount = new Account(id,amount);
		System.out.println("Size=="+accountList.size());
		if(!accountList.contains(givenAccount)) {
			accountList.add(givenAccount);
			System.out.println("Size now=="+accountList.size());
			return givenAccount;
		}
		
			for(Account acc: accountList) {
				if(acc.equals(givenAccount)) {					
					acc.setBalance(acc.getBalance()+amount);
					System.out.println("Balance in the account="+acc.getBalance());
					resAcc=acc;
					break;
				}
			}
			return resAcc;
	}
	
	public Account withdrawAccount(String id,Double amount) throws ApplicationException {
		System.out.println("Withdraw from account="+id);
		Account resAcc=null;
		Account givenAccount = new Account(id);
		if(!accountList.contains(givenAccount))
			throw new ApplicationException(AccountConstants.ACCOUNT_NOT_EXISTS);
			for(Account acc: accountList) {
				if(acc.equals(givenAccount)) {
					if(acc.getBalance() < amount) {
						throw new ApplicationException(AccountConstants.INVALID_WITHDRAW_AMOUNT);	
					}
					System.out.println("Withdrawn from account="+id);
					acc.setBalance(acc.getBalance()-amount);
					System.out.println("Balance in the account="+acc.getBalance());
					resAcc=acc;
					break;
				}
			}
			return resAcc;
	}
	
	public boolean resetAll() throws ApplicationException {
		accountList.clear();
		return true;
	}

}
