package com.example.model;

public class Account {

	private String acc;
	private Double balance;

	public String getAcc() {
		return acc;
	}

	public void setAcc(String acc) {
		this.acc = acc;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Accounts [acc=" + acc + ", balance=" + balance + "]";
	}

	public Account(String acc, Double balance) {
		super();
		this.acc = acc;
		this.balance = balance;
	}

	public Account() {
		super();
	}

}
