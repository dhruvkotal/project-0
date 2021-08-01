package com.example.exception;

@SuppressWarnings("serial")
public class AccountNotFoundException extends Exception{
	public AccountNotFoundException(String s) {
		super(s);
	}
	
}
