package com.thebank.daos;

import java.util.List;

import com.thebank.beans.Account;



public interface AccountDao  {
	
	public static final AccountDao currentAccountDao = new AccountDaoJdbc();
	
	int createAccount(Account a,int userId);
	
	List<Account> findByUserId(int userId);
	void deposit(Account a, double money);
	void withdraw(Account a, double money);
	void updateAccount(Account a);
	void random_account(Account a);

}
