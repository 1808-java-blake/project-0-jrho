package com.thebank.screens;

import java.util.Scanner;

import com.thebank.beans.User;
import com.thebank.daos.UserDao;

public class LoginScreen implements Screen{
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;
	public static String fileName = "";
	

	@Override
	public Screen start() {
		//log.debug("started login screen");
		System.out.println("Enter Username or type Register to sign up: ");
		
		String username = scan.nextLine();
		fileName = username;
		
		//log.trace("username = " + username);
		
		if ("register".equalsIgnoreCase(username)) 
		{
			return new RegisterUser();
		}
		
		System.out.println("Enter Password: ");
		String password = scan.nextLine();
		
		//log.debug("received users credentials");
		User currentUser = ud.findByUsernameAndPassword(username, password);
		if (currentUser != null) {
			//log.info("user succefully logged in");
			return new HomeScreen();
		}

		System.out.println("unable to login");
		return this;
	}

}
