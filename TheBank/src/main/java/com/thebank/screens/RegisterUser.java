package com.thebank.screens;

import java.util.Random;
import java.util.Scanner;

import com.thebank.beans.Account;
import com.thebank.beans.User;
import com.thebank.daos.UserDao;
import com.thebank.daos.UserSerializer;



public class RegisterUser implements Screen {
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;
	public static Account account = new Account();
	
	
	

	@Override
	public Screen start() {
		User u = new User();
		
		System.out.println("Enter new username");
		u.setUsername(scan.nextLine());
		System.out.println("Enter password");
		u.setPassword(scan.nextLine());
		System.out.println("Enter first name");
		u.setFirstName(scan.nextLine());
		System.out.println("Enter last name");
		u.setLastName(scan.nextLine());
		System.out.println("Enter age");
		String age = scan.nextLine();
		
		try {
			u.setAge(Integer.valueOf(age));
			
		} catch (NumberFormatException e) {
			System.out.println("Invalid number");
		}
		
		
		
		
		//setting random account number to user
		Random rand = new Random();
		int randomAccount  = 100000000+rand.nextInt(900000000);
		
		u.setAccountNumber(randomAccount);
	
		
		System.out.println("would you like to start with deposit? 1: Yes, 2: No");
		
		int selection = Integer.valueOf(scan.nextLine());
		if(selection ==1) {
			System.out.println("Enter Money");
			double number =0;
			number = Double.valueOf(scan.nextLine());
			try {
				//account.setTotalBalance(number);
				u.setTotBalance(number);
				UserSerializer.theUsers.add(u.getUsername());
				//u.setAccount(account);
				ud.createUser(u);
				//System.out.println(u);
			}
			catch(NumberFormatException e) {
				System.out.println("Invalid Number");
			}
		}
		
		else
		{
			double number=0;
			u.setTotBalance(number);
			System.out.println(u);
			//UserSerializer.theUsers.add(u.getUsername());
			ud.createUser(u);
			return new LoginScreen();
		}
		
		
		return new LoginScreen();
	}

}
