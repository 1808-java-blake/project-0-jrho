package com.thebank.beans;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4103401716905471419L;
	
	
	
	private User u = new User();
	private int accountNumber;
	private double accountBalance;
	private List<TransHistory> accountHistory = new ArrayList<TransHistory>();
	public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	public static LocalDateTime now = LocalDateTime.now();
	
	public void deposit(double money) {
		 double tot =0;
		tot = accountBalance+ money;
		String date = dtf.format(now);
		TransHistory t = new TransHistory(accountBalance, money+" deposited to", accountNumber, date);
		u.getTh().add(t);
		setTotalBalance(tot);
	}
	
	public void withdraw(double money) {
		if(accountBalance>=money) {
			double tot =0;
			tot =accountBalance-money;
			String date = dtf.format(now);
			TransHistory t = new TransHistory(accountBalance, money+" withdraw from ", accountNumber, date);
			u.getTh().add(t);
			setTotalBalance(tot);
		}
	}
	public double getTotalBalance() {
		return accountBalance;
		
	}
	public void setTotalBalance(double money) {
		this.accountBalance = money;
		
	}
	
	

}
