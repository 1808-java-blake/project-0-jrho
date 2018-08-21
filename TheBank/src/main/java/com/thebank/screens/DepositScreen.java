package com.thebank.screens;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.thebank.beans.History;
import com.thebank.beans.User;
import com.thebank.daos.HistoryDao;
import com.thebank.daos.UserDao;
import com.thebank.util.AppState;

public class DepositScreen implements Screen {
	private Logger log=Logger.getRootLogger();
	private Scanner scan = new Scanner(System.in);
	private AppState state =AppState.state;
	private User u =state.getCurrentUser();
	private UserDao ud = UserDao.currentUserDao;
	private HistoryDao hd = HistoryDao.currentHistoryDao;
	private static final DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
	

	@Override
	public Screen start() {
		if(u==null) {
			return new LoginScreen();
		}
		
		try {
			
			System.out.println("How much would you like to deposit?");
			double userBalance = u.getTotBalance();
			double amount = scan.nextDouble();
			userBalance = userBalance + amount;
			
			ud.updateUser(userBalance, u.getUsername());
			u.setTotBalance(userBalance);
			state.setCurrentUser(u);
			
			LocalDateTime thisTime = LocalDateTime.now();
			String time = new String(dateTime.format(thisTime) + " ");
			
			History h = new History();
			h.setType("Deposit");
			h.setDate(time);
			h.setUserId(u.getId());
			h.setMoney(amount);
			int transactionId = hd.createHistroy(h);
			if(transactionId == 0) {
				log.error("failed to create history");
				return new LoginScreen();
			}
			h.setId(transactionId);
			log.info("created " + h);
			
		} catch (NumberFormatException e) {
			System.out.println("Invalid number");
		}
		
		return new HomeScreen();
	}

}
