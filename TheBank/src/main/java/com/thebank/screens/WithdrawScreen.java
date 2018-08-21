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

public class WithdrawScreen implements Screen {
	private Logger log = Logger.getRootLogger();
	private Scanner scan = new Scanner(System.in);
	private AppState state = AppState.state;
	private User u = state.getCurrentUser();
	private UserDao ud = UserDao.currentUserDao;
	private HistoryDao hd = HistoryDao.currentHistoryDao;
	private static final DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");

	@Override
	public Screen start() {
		if(u == null) {
			return new LoginScreen();
		}
		try {
			
			System.out.println("Enter withdrawl amount");
			double balance = u.getTotBalance();
			double money = scan.nextDouble();
			balance = balance - money;
			if(balance<0) {
				System.out.println("Invalid amount");
				return new HomeScreen();
			}
			ud.updateUser(balance, u.getUsername());
			u.setTotBalance(balance);
			state.setCurrentUser(u);
			
			LocalDateTime thisTime = LocalDateTime.now();
			String time = new String(dateTime.format(thisTime) + " ");
			
			History h = new History();
			h.setType("Withdrawal");
			h.setMoney(money);
			h.setDate(time);
			h.setUserId(u.getId());
			int transactionId = hd.createHistroy(h);
			if(transactionId == 0) {
				log.error("failed to create history");
				return new LoginScreen();
			}
			h.setId(transactionId);
			log.info("created " + h);
			
		} catch (NumberFormatException e) {
			System.out.println("Invalid number");
		} catch (NullPointerException e) {
			System.out.println("h");
			e.printStackTrace();
		}
		
		
		return new HomeScreen();
	}

}
