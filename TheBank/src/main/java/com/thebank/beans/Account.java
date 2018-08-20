package com.thebank.beans;

import java.util.ArrayList;
import java.util.List;

public class Account {
	
	private int accountId;
	private int accountNumber;
	private double balance;
	private int userId;
	private List<String> histroy = new ArrayList<String>();
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(int accountId, int accountNumber, double balance, int userId, List<String> histroy) {
		super();
		this.accountId = accountId;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.userId = userId;
		this.histroy = histroy;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public List<String> getHistroy() {
		return histroy;
	}
	public void setHistroy(List<String> histroy) {
		this.histroy = histroy;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
		result = prime * result + accountNumber;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((histroy == null) ? 0 : histroy.hashCode());
		result = prime * result + userId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountId != other.accountId)
			return false;
		if (accountNumber != other.accountNumber)
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (histroy == null) {
			if (other.histroy != null)
				return false;
		} else if (!histroy.equals(other.histroy))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountNumber=" + accountNumber + ", balance=" + balance
				+ ", userId=" + userId + ", histroy=" + histroy + "]";
	}
	
	


}
