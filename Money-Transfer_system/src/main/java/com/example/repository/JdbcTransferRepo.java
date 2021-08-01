package com.example.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.example.db.ConnectionFactory;
import com.example.exception.NotEnoughBalanceException;
import com.example.model.Transfer;

public class JdbcTransferRepo implements TransferRepo {
	private static Logger logger = Logger.getLogger("app1");

	@Override
	public void transfer(double amount, String fromAcc, String toAcc) {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			con = ConnectionFactory.getConnection();
			String sq = "select balance from accounts where acc = " + fromAcc;
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sq);
			rs.next();
			double bal = rs.getDouble("balance");
			if (bal < amount) {
				logger.error("Not enogh balance");
				throw new NotEnoughBalanceException("You Do not have enough balance for this Transfer");
			}

			String sql = "Update accounts set balance =  balance - ? where acc =?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDouble(1, amount);
			ps.setString(2, fromAcc);
			logger.info("Debited from " + fromAcc);
			String sql2 = "Update accounts set balance =  balance + ? where acc =?";
			int rowCount = ps.executeUpdate();
			if (rowCount == 1) {
				logger.info("Debit acc success");
			}
			rowCount = 0;
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setDouble(1, amount);
			ps2.setString(2, toAcc);
			rowCount = ps2.executeUpdate();
			if (rowCount == 1) {
				logger.info("Debit acc success");
			}
			rowCount = 0;
			String sql3 = "insert into transfer (amount,fromAcc,toAcc) values(?,?,?);";
			PreparedStatement ps3 = con.prepareStatement(sql3);
			ps3.setDouble(1, amount);
			ps3.setString(2, fromAcc);
			ps3.setString(3, toAcc);
			rowCount = ps3.executeUpdate();
			if (rowCount == 1) {
				logger.info("transfer table insert success");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.error("SQL exception in Finally of transfer");
				e.printStackTrace();
			}
		}
	}

	@Override
	public void transactions() {
		// TODO Auto-generated method stub
		Connection con = null;

		try {
			con = ConnectionFactory.getConnection();
			String sql = "select * from transfer";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Transfer transfer = new Transfer();
				transfer.setId(rs.getInt("id"));
				transfer.setAmount(rs.getDouble("amount"));
				transfer.setFromAcc(rs.getString("fromAcc"));
				transfer.setToAcc(rs.getString("toAcc"));
				System.out.println(transfer.toString());
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("SQL exception in Finally of transaction");
			}
		}

	}

}
