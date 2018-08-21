package com.thebank.screens;

import java.util.Scanner;

import com.thebank.beans.Account;
import com.thebank.beans.User;
import com.thebank.daos.AccountDao;
import com.thebank.daos.UserDao;



public class RegisterUser implements Screen {
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;
	private AccountDao ad = AccountDao.currentAccountDao;
	//private AppState state = AppState.state;
	//private Logger log = Logger.getRootLogger();
	//public static Account account = new Account();
	
	@Override
	public Screen start() {
		User u = new User();
		Account a = new Account();
		
		System.out.println("Enter new username");
		String username = scan.nextLine();
		u.setUsername(username);
		System.out.println("Enter password");
		String password = scan.nextLine();
		u.setPassword(password);
		System.out.println("Enter first name");
		u.setFirstName(scan.nextLine());
		System.out.println("Enter last name");
		u.setLastName(scan.nextLine());
		System.out.println("Enter age");
		String age = scan.nextLine();
		
		try {
			u.setAge(Integer.valueOf(age));
			//ud.createUser(u);
			
			
			
		} catch (NumberFormatException e) {
			System.out.println("Invalid number");
			return null;
		}
		
		//set random account number
		ad.random_account(a);
		System.out.println("Do you like to start with deposit? 1: deposit 2: menu");
		String selection = scan.nextLine();
		if(selection.equals("1")) {
			System.out.println("Enter deposit amount");
			String amount = scan.nextLine();
			double money = Double.valueOf(amount);
//			a.setBalance(money);
//			System.out.println("new balance " +a.getBalance() );
			
			u.setTotBalance(money);
			System.out.println("new balance" +u.getTotBalance());
			ud.createUser(u);
			
			
			
			
			int userId = ud.findByUsernameAndPassword(username, password).getId();
			//ad.findByUserId(userId);
			//a.setUserId(u.getId());
			//System.out.println(a.getUserId());
			ad.createAccount(a,userId);
		}
		else
		{
			int userId = ud.findByUsernameAndPassword(username, password).getId();
			ud.createUser(u);
			a.setBalance(0);
			//a.setUserId(u.getId());
			//create account
			ad.createAccount(a,userId);
		}
		
		
		
		
		
		return new LoginScreen();
	}
	


		
		
		
}


