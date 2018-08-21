package com.thebank.daos;

import java.util.List;

import com.thebank.beans.History;

public interface HistoryDao {
	public static final HistoryDao currentHistoryDao = new HistoryDaoJdbc();
	
	int createHistroy(History h);
	List<History> findByUserId(int id);
	

}
