package com.example.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.security.auth.login.AccountNotFoundException;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.example.db.ConnectionFactory;
import com.example.model.Account;

public class JdbcAccountRepo implements AccountRepo {

	private static Logger logger = Logger.getLogger("app1");

	@Override
	public void loadAccount(String acNum) {
		// TODO Auto-generated method stub
		Account a1 = new Account();
		Connection con = null;
		try {
			con = ConnectionFactory.getConnection();
			String sql = "Select * from accounts where acc = " + acNum;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()==false)
			{
				logger.error("Account not found");
				throw new AccountNotFoundException(acNum + " is not available in db");
			}
			
			a1.setAcc(rs.getString("acc"));
			a1.setBalance(rs.getDouble("balance"));
			System.out.println(a1.toString());
			logger.info("Load acc success");
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("SQL excep in Finally of Load acc");
			e.printStackTrace();
		} finally {
			try {
				con.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public Void Update(Account account) {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			con = ConnectionFactory.getConnection();
			String sql = "Update accounts set balance =  ? where acc =?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDouble(1, account.getBalance());
			ps.setString(2, account.getAcc());
			int rowCount = ps.executeUpdate();
			if (rowCount == 1) {
				logger.info("Update acc success");
			}


		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("SQL excep in Finally of Update");
				e.printStackTrace();
			}
		}
		return null;
	}

}
