package com.example.demo.service;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.bean.Account;
import com.example.demo.bean.AccountDestinationResponse;
import com.example.demo.bean.AccountOriginResponse;
import com.example.demo.bean.AccountResponse;
import com.example.demo.bean.AccountTransferResponse;
import com.example.demo.bean.AccountUtil;
import com.example.demo.exception.ApplicationException;
import com.example.demo.AccountConstants;

@Service
public class AccountService {
	
	@Autowired
	AccountUtil accountUtil;
	
	public Double getBalance(String Id) throws ApplicationException {		
		return getAccount(Id).getBalance();
	}
	
	public AccountResponse createAccount(String id,Double amount) throws ApplicationException {
		Account account=new Account(id,amount);
		account= accountUtil.createAccount(account);
		return new AccountDestinationResponse(id,amount);
	}
	
	public AccountResponse depositAccount(String id,Double amount) throws ApplicationException {
		accountUtil.depositAccount(id, amount);
		Account account=getAccount(id);
		AccountResponse response=new AccountDestinationResponse(account.getId(),account.getBalance());
		return response;
	}
	
	public AccountResponse withdrawAccount(String id,Double amount) throws ApplicationException {
		accountUtil.withdrawAccount(id, amount);
		Account account=getAccount(id);
		AccountResponse response=new AccountOriginResponse(account.getId(),account.getBalance());
		return response;
	}
	
	public boolean resetAll() throws ApplicationException {
		accountUtil.resetAll();
		return true;
	}

	
	private Account getAccount(String id) throws ApplicationException {
		if(StringUtils.isEmpty(id)) {
			throw new ApplicationException(AccountConstants.INVALID_ACCOUNT_NUMBER);
		}
		return accountUtil.getAccount(new Account(id));
	}

	public AccountResponse transferAccount(String origin, Double amount, String destination) throws ApplicationException {
		if(StringUtils.isEmpty(origin) || StringUtils.isEmpty(destination))
			throw new ApplicationException(AccountConstants.INVALID_ACCOUNT_NUMBER);
		
		if(origin.equals(destination)) {
			throw new ApplicationException(AccountConstants.SAME_ACCOUNT_TRANSFER);
		}
		AccountTransferResponse response = new AccountTransferResponse();
		AccountOriginResponse withDrawnAccount = (AccountOriginResponse)withdrawAccount(origin, amount);
		response.setOrigin(withDrawnAccount.getOrigin());
		AccountDestinationResponse depositAccount = (AccountDestinationResponse) depositAccount(destination, amount);
		response.setDestination(depositAccount.getDestination());				
		return response;
	}

}
