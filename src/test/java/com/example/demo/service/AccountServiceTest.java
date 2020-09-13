package com.example.demo.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestComponent;

import com.example.demo.exception.ApplicationException;

@SpringBootTest
class AccountServiceTest {

	@Mock
	AccountService service;
	
	@Test
	void getBalanceTest1( ) throws ApplicationException {
		String id="100";
		System.out.println("Id="+id);
		System.out.println("Balance=="+service.getBalance(id));
	}

	@Test
	void getBalanceTest2( ) throws ApplicationException {
		String id="1234";
		System.out.println("Id="+id);
		System.out.println("Balance=="+service.getBalance(id));
	}
}
