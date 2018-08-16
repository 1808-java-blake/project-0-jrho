package com.thebank.screens;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.thebank.beans.User;
import com.thebank.daos.UserDao;
import com.thebank.daos.UserSerializer;

public class AdminScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private UserDao ud = UserDao.currentUserDao;
	private String choice="";

	@Override
	public Screen start() {
		System.out.println("You have logged in as Admin");
		//System.out.println("Select 1 to view all users, Select 2 to enter user name");
//		choice = scan.nextLine();	
//		
//			if(choice.equals("1")) {
//				
//				try(ObjectInputStream ois = new ObjectInputStream(
//						new FileInputStream("src/main/resources/users/all_users.txt"))){
//					String newList = "";
//					newList = ois.readUTF();
//					System.out.println(newList);
//						
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//					
//			}
				
				System.out.println("Enter user name to see options:");
				String username = scan.nextLine();
				User u = ud.bringUser(username);
				if(u==null) {
					System.out.println("Invalid username");
					return this;
				}
				System.out.println("Select 1 to view "+u.getUsername()+"'s"+ " admpersonal information");
				System.out.println("Select 2 to view "+u.getUsername()+"'s"+" acount information");
				System.out.println("Select 3 to view "+u.getUsername()+"'s"+" transaction history");
				System.out.println("Select 4 to delete the user");
				System.out.println("Select 5 to go back main screen");
				String selection = scan.nextLine();
				
				switch(selection) {
				
				case "1": 
					System.out.println(u.toString());
					break;
				case "2":
					System.out.println(u.getFirstName()+" "+u.getLastName()+"'s"+"The Account number is "+u.getAccountNumber()+" and total balance is $"+u.getTotBalance());
					break;
				case "3":
					System.out.println(UserSerializer.transHistory.toString());
					break;
				case "4":
					ud.deleteUser(u);
					System.out.println("The user is deleted.");
					break;
				case "5":
					return new LoginScreen();
				default:
					break;
				}
			
			
			
			System.out.println("Select 1 to return admin option, 2 to return login page");
			String newSelection = scan.nextLine();
			if(newSelection.equals("1")) {
				return new AdminScreen();
			}
			else if(newSelection.equals("2")) {
				return new LoginScreen();
			}
			else {
				System.out.println("Invalid number");
				return this;
				
			}
	}//end start
}
			
			
	

			
	


