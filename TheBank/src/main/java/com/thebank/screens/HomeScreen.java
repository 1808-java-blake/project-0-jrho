package com.thebank.screens;

import java.util.Scanner;

import com.thebank.beans.User;
import com.thebank.daos.UserDao;



public class HomeScreen implements Screen {

	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;
	

	public Screen start() {
		
		int count = 0;
		String theName = LoginScreen.fileName;
		User currentUser = new User();
		currentUser = ud.bringUser(theName);
		System.out.println(currentUser);
		
		while(count==0)
		{
			
	
		System.out.println("Please chose from following options:");
		System.out.println("Enter 1 to deposit money");
		System.out.println("Enter 2 to withdraw money");
		System.out.println("Enter 3 to view balance");
		System.out.println("Enter 4 to view transaction history");
		System.out.println("Enter 5 to delete the user");
		System.out.println("Enter 9 to go back to log-in screen");
		String selection = scan.nextLine();
		switch (selection) {
		case "1":
			System.out.println("Enter how much u want to deposit");
			double newMoney =Double.valueOf(scan.nextLine());
			ud.deposit(currentUser, newMoney);
			count=1;
			
			break;
		case "2":
			System.out.println("Withdraw selected");
			System.out.println("Enter how much u want to withdraw");
			double withdrawMoney = Double.valueOf(scan.nextLine());
			ud.withdraw(currentUser,withdrawMoney);
			System.out.println("your new balance = "+currentUser.getTotBalance());
			count=1;
			break;
			
		case "3":
			System.out.println("View balance selected");
			double num2 = currentUser.getTotBalance();
			System.out.println("Total balance ="+num2);
			count=1;
			break;
		case "4":
			Screen hs = new TransactionHistory();
			hs.start();
			count=1;
			break;
		case "9":
			System.out.println("Exit and Going back to login screen");
			return new LoginScreen();
		default:
			break;
		
		}
		System.out.println("Go back to menu: 1, otherwise 2 to exit");
		if(scan.nextLine().equals("1")) {
			return new HomeScreen();
		}
		else
		{
			System.out.println("Have a nice day");
		}
	}

		return null;
	}
	

}
