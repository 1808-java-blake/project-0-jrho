package com.thebank.beans;

import java.io.Serializable;

public class TransHistory implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3458476383110835953L;
	private double total;
	private String history;
	private int accountNumber;
	private String date;
	public TransHistory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TransHistory(double total, String history, int accountNumber, String date) {
		super();
		this.total = total;
		this.history = history;
		this.accountNumber = accountNumber;
		this.date = date;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getHistory() {
		return history;
	}
	public void setHistory(String history) {
		this.history = history;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountNumber;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((history == null) ? 0 : history.hashCode());
		long temp;
		temp = Double.doubleToLongBits(total);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		TransHistory other = (TransHistory) obj;
		if (accountNumber != other.accountNumber)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (history == null) {
			if (other.history != null)
				return false;
		} else if (!history.equals(other.history))
			return false;
		if (Double.doubleToLongBits(total) != Double.doubleToLongBits(other.total))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TransHistory [total=" + total + ", history=" + history + ", accountNumber=" + accountNumber + ", date="
				+ date + "]";
	}
	
	

}
