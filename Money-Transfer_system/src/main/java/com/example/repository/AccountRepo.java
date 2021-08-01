package com.example.repository;

import com.example.model.Account;

public interface AccountRepo {

	void loadAccount(String acNum);

	Void Update(Account account);

}
