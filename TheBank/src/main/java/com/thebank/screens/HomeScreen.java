package com.thebank.screens;

import java.util.List;
import java.util.Scanner;

import com.thebank.beans.Account;
import com.thebank.beans.History;
import com.thebank.beans.User;
import com.thebank.daos.AccountDao;
import com.thebank.daos.HistoryDao;
import com.thebank.daos.UserDao;
import com.thebank.util.AppState;




public class HomeScreen implements Screen {

	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;
	private HistoryDao hd = HistoryDao.currentHistoryDao;
	private AccountDao ad = AccountDao.currentAccountDao;
	private AppState state =AppState.state;
	private User u = state.getCurrentUser();
	//private User u = LoginScreen.newUser;
	

	public Screen start() {
		
		int number = state.getCurrentUser().getId();
		String theName = LoginScreen.fileName;
		
		Account currentAccount = new Account();
		
		
	
		
		System.out.println("Please chose from following options:");
		System.out.println("Enter 1 to deposit money");
		System.out.println("Enter 2 to withdraw money");
		System.out.println("Enter 3 to view balance");
		System.out.println("Enter 4 to view transaction history");
		System.out.println("Enter 9 to go back to log-in screen");
		String selection = scan.nextLine();
		switch (selection) {
		case "1":

			return new DepositScreen();
			
		case "2":
			return new WithdrawScreen();
			
		case "3":
			System.out.println("View balance selected");
			System.out.println("Your total balance =$"+u.getTotBalance());
			return new HomeScreen();
		case "4":
			List<History> history = hd.findByUserId(number);
			history.stream().forEach((each) -> {
				System.out.println("Date: " + each.getDate() + " Type: "+ each.getType() + "Amount: "+each.getMoney()+ " user_id: "+each.getUserId() );
			});
			break;
			
		case "9":
			System.out.println("Exit and Going back to login screen");
			return new LoginScreen();
		default:
			break;
		
		}
		
		System.out.println("Go back to user menu: 1, otherwise 2 to exit");
		if(scan.nextLine().equals("1")) {
			return new HomeScreen();
		}
		else
		{
			System.out.println("Have a nice day");
			return new LoginScreen();
		}
	

		
	}
	

}
