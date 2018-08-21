package com.thebank.screens;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.thebank.beans.User;
import com.thebank.daos.UserDao;
import com.thebank.util.AppState;

public class LoginScreen implements Screen{
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;
	private Logger log = Logger.getRootLogger();
	private AppState state = AppState.state;
	public static String fileName;
	public static User newUser = new User();

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
		
		else if(username.equals("admin")) {
				return new AdminScreen();
			}
		
		System.out.println("Enter Password: ");
		String password = scan.nextLine();
		
		//log.debug("received users credentials");
		 User currentUser = ud.findByUsernameAndPassword(username, password);
		if (currentUser != null) {
			state.setCurrentUser(currentUser);
			log.info("user succefully logged in");
			log.info("welcome" + currentUser);
			//newUser = currentUser;
			return new HomeScreen();
		}

		System.out.println("unable to login");
		return this;
	}

}
