package com.example;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.example.repository.AccountRepo;
import com.example.repository.JdbcAccountRepo;
import com.example.repository.JdbcTransferRepo;
import com.example.repository.TransferRepo;

public class App {
	public static void main(String[] args) {
		Logger logger = Logger.getLogger("app1");
		TransferRepo txnRepo = new JdbcTransferRepo();
		AccountRepo accRepo = new JdbcAccountRepo();
		Scanner scan = new Scanner(System.in);
		int choice;
		String fromAcc, toAcc;
		double amount;
		char cho;
		do {
		System.out.println("\n-------------------");
		System.out.println("WELCOME TO CONSOLE BASED MONEY TRANSFER SYSYTEM");
		System.out.println("-------------------\n");
		System.out.println("1. Load account.");
		System.out.println("2. Transfer.");
		System.out.println("3. View all transactions.");
		System.out.println("4. Exit.");
		System.out.print("\nEnter your choice : ");
		choice = scan.nextInt();
		scan.nextLine();
		switch (choice) {
		case 1:
			logger.info("reached switch case 1");
			System.out.println("Enter account number : ");
			fromAcc = scan.nextLine();
			accRepo.loadAccount(fromAcc);
			break;
		case 2:
			logger.info("reached switch case 2");
			System.out.println("Enter From Account's number ");
			fromAcc = scan.nextLine();
			System.out.println("Enter To Account's number ");
			toAcc = scan.nextLine();
			System.out.println("Enter Amount to be transfered ");
			amount = scan.nextDouble();
			txnRepo.transfer(amount, fromAcc, toAcc);
			break;
		case 3:
			logger.info("reached switch case 3");
			txnRepo.transactions();
			break;
		case 4:
			logger.info("reached switch case 4");
			
			System.exit(1);
			
			break;

		default:
			System.out.println("Wrong choice !");
		}

		System.out.println("continue(y/n)?");
		cho=scan.next().charAt(0);
		}while(cho=='y');
			

		scan.close();
	}

}
