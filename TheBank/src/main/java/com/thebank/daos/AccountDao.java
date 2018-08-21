package com.thebank.daos;

import java.util.List;

import com.thebank.beans.Account;



public interface AccountDao  {
	
	public static final AccountDao currentAccountDao = new AccountDaoJdbc();
	
	int createAccount(Account a,int userId);
	
	List<Account> findByUserId(int userId);
	void random_account(Account a);

}
