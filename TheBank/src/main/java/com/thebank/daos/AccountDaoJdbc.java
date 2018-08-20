package com.thebank.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import com.thebank.beans.Account;
import com.thebank.util.ConnectionUtil;

public class AccountDaoJdbc implements AccountDao {
	private Logger log = Logger.getRootLogger();
	private ConnectionUtil cu = ConnectionUtil.cu;
	
	@Override
	public int createAccount(Account a,int userId) {
		try(Connection conn = cu.getConnection())
			{
					PreparedStatement ps = conn.prepareStatement(
							"INSERT INTO accounts (account_number, account_balance,user_id) VALUES (?,?,?)",
							new String[] {"account_id"});
					ps.setInt(1, a.getAccountNumber());
					ps.setDouble(2, a.getBalance());
					//ps.setArray(2, a.getHistroy());
					ps.setInt(3, userId);
					
					int recordsCreated = ps.executeUpdate();
					log.trace(recordsCreated + "records created");
					
					ResultSet rs = ps.getGeneratedKeys();
					if(rs.next()) {
						log.trace("generated account id is" + rs.getInt("account_id"));
						return rs.getInt("account_id");
					}
			} catch (SQLException e) {
				log.error(e.getMessage());
				for(StackTraceElement ste: e.getStackTrace()) {
					log.error(ste);
				}
				log.warn("failed to create new user");
			}
		return 0;
	}

	@Override
	public List<Account> findByUserId(int userId) {
		
		try(Connection conn = cu.getConnection()){
			List<Account> accounts = new ArrayList<>();
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM accounts WHERE user_id=?");
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Account a = new Account();
				a.setAccountNumber(rs.getInt("account_number"));
				a.setBalance(rs.getDouble("balance"));
				a.setUserId(rs.getInt("user_id"));
				accounts.add(a);
			}
			return accounts;
		} catch(SQLException e) {
			e.printStackTrace();
		}


		return null;
	}

	@Override
	public void deposit(Account a, double money) {
		if(money<0) {
			System.out.println("Invalid input");
			return;
		}
		a.setBalance(a.getBalance()+money);
		updateAccount(a);
		
		
	}

	@Override
	public void withdraw(Account a, double money) {
		if(money<0) {
			System.out.println("Invalid input");
			return;
		}
		a.setBalance(a.getBalance()-money);
		updateAccount(a);
		
	}

	@Override
	public void updateAccount(Account a) {
		try(Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(
					"UPDATE accounts SET balance =? WHERE user_id=?");
			ps.setDouble(1, a.getBalance());
			ps.setInt(2, a.getUserId());
			int recordsChanged = ps.executeUpdate();
			log.trace(recordsChanged + "records updtaed");
			
					
			
		} catch (SQLException e) {
			log.error(e.getMessage());
			for(StackTraceElement ste: e.getStackTrace()) {
				log.error(ste);
			}
			log.warn("failed to update account");
		}
		
		
	}

	@Override
	public void random_account(Account a) {
		Random rand = new Random();
		int randomAccount = 100000000 + rand.nextInt(90000000);
		a.setAccountNumber(randomAccount);
		
	}

}

