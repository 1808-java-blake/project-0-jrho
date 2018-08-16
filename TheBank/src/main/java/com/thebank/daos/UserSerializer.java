package com.thebank.daos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.thebank.beans.User;

public class UserSerializer implements UserDao {
	//private Logger log = Logger.getRootLogger();
	public static final UserSerializer us = new UserSerializer();
	public static List<String>transHistory = new ArrayList<>();
	public static List<String>theUsers = new ArrayList<>();
	public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	public static LocalDateTime now = LocalDateTime.now();
	private String theFile="";
	@Override
	public void createUser(User u) {
		if(u==null) {
			return;
		}
		File f = new File("src/main/resources/users/"+u.getUsername()+".txt");
		//System.out.println(f.getName());
		if(f.exists()) {
			return;
		}
		try {
			f.createNewFile();
		}
		catch(IOException e) {
			e.printStackTrace();
			return;
		}
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/resources/users/"+u.getUsername()+".txt"))){
			oos.writeObject(u);
			
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		
		theUsers.add(u.getUsername());
		//System.out.println(theUsers);
//		File f2 = new File("src/main/resources/users/"+u.getUsername()+"_transHistory.txt");
//		//System.out.println(f.getName());
//		if(f2.exists()) {
//			return;
//		}
//		try {
//			f2.createNewFile();
//		}
//		catch(IOException e) {
//			e.printStackTrace();
//			return;
//		}
//		try {
//			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/resources/users/all_users.txt"));
//			oos.writeUTF(theUsers.toString());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		File f3 = new File("src/main/resources/users/"+u.getUsername()+"_transHistory.txt");
//		//System.out.println(f.getName());
//		if(f3.exists()) {
//			return;
//		}
//		try {
//			f3.createNewFile();
//		}
//		catch(IOException e) {
//			e.printStackTrace();
//			return;
//		}
//		try {
//			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/resources/users/"+u.getUsername()+"_transHistory.txt"));
//			oos.writeUTF(transHistory.toString());
//			oos.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		// verify that what was passed in is not null
		if (username == null || password == null) {
			return null;
		}
		try (ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream("src/main/resources/users/" + username + ".txt"))) {

			User u = (User) ois.readObject(); // retrieve the user if it can
			// verify that the password matches
			if (password.equals(u.getPassword())) {
				return u;
			} else {
				return null;
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User bringUser(String username) {
		if(username ==null) {
			return null;
		}
		try (ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream("src/main/resources/users/" + username + ".txt"))) {
			User newUser = (User)ois.readObject();
			return newUser;
		}
		
		catch(FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e1) {
			
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
		
		
	}

	@Override
	public void deleteUser(User u) {
		if(u== null) {
			System.out.println("There is no user");
			return;
		}
		File f = new File("src/main/resources/users/"+u.getUsername()+".txt");
		
		if(f.exists()==true) {
			f.delete();
			//System.out.println("The user is deleted");
		}
		
	}

	@Override
	public void updateUser(User u) {
		deleteUser(u);
		createUser(u);
	}

	@Override
	public void deposit(User u,double money) {
		
		if(money<0) {
			System.out.println("Invalid number");
			return;
		}
		double tot=0;
		tot = u.getTotBalance();
		System.out.println("The previous total balance = $"+tot );
		System.out.println("Incoming deposit= $"+ money);
		System.out.println("The new balance = $"+ (tot+money));
		double newResult= tot+money;
		u.setTotBalance(newResult);
//		List<String> transHistory = new ArrayList<>();
		
		transHistory.add("Deposit Amount: $"+money+" at "+dtf.format(now));
		//System.out.println(transHistory.toString());
		us.updateUser(u);
		
		
	
		
		
	}

	@Override
	public User checkBalance(User u) {
		double bal = 0;
		bal = u.getTotBalance();
		
		return u;
	}

	@Override
	public void withdraw(User u, double money) {
		double tot = 0;
		double newResult=0;
		tot = u.getTotBalance();
		double withdrawMoney = 0;
		
		withdrawMoney = money*(-1);
		
		
		if(tot+money<0) {
			System.out.println("You can't withdraw money greater than your total balance");
		}
		else {
			newResult = tot+withdrawMoney;
			System.out.println("The previous total balance = $"+tot );
			System.out.println("Incoming withdrawal= $"+ money);
			System.out.println("Your new balacne = $"+newResult);
		}
		u.setTotBalance(newResult);
		transHistory.add("Withdraw Amount: $"+money+" at "+dtf.format(now));
		//System.out.println(transHistory.toString());
		us.updateUser(u);
		
	}
}


	

	
