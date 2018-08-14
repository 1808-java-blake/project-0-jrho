package com.thebank.screens;

import java.util.Scanner;

import com.thebank.beans.User;
import com.thebank.daos.UserDao;



public class RegisterUser implements Screen {
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;
	
	
	

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
		
		
		System.out.println("would you like to deposit? 1: Yes, 2: No");
		int count =0;
		count = Integer.valueOf(scan.nextLine());
		if(count ==1) {
			System.out.println("Enter Money");
			double number =0;
			number = Double.valueOf(scan.nextLine());
			try {
				u.setTotBalance(number);
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
			ud.createUser(u);
			return new LoginScreen();
		}
		
		
		return new LoginScreen();
	}

}
