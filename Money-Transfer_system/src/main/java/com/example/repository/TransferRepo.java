package com.example.repository;

import com.example.model.Transfer;

@SuppressWarnings("unused")
public interface TransferRepo {

	void transfer(double amount, String fromAcc, String toAcc);

	void transactions();
}
