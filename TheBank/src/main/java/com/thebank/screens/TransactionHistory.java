package com.thebank.screens;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import com.thebank.beans.User;
import com.thebank.daos.UserDao;
import com.thebank.daos.UserSerializer;

public class TransactionHistory implements Screen {
	
	List<String>newArr = UserSerializer.transHistory;
	private UserDao ud = UserDao.currentUserDao;

	@Override
	public Screen start() {
		
		String theName = LoginScreen.fileName;
		User currentUser = new User();
		currentUser = ud.bringUser(theName);
		System.out.println(currentUser);
		Object getArr= new Object();
		if(currentUser==null) {
			return null;
		}
		File f = new File("src/main/resources/users/"+currentUser.getUsername()+"_history.txt");
		System.out.println(f.getName());
		if(f.exists()) {
			return null;
		}
		try {
			f.createNewFile();
		}
		catch(IOException e) {
			e.printStackTrace();
			return null;
		}
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/resources/users/"+currentUser.getUsername()+"_history.txt"))){
			oos.writeObject(newArr);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		
		try (ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream("src/main/resources/users/" + currentUser.getUsername() +"_history.txt"))) {
			getArr = ois.readObject();
			
			
			
			
		}
		
		catch(FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e1) {
			
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		for(int i=0; i<newArr.size();i++) {
//			System.out.println("this is in the array");
//			System.out.println(newArr.get(i));
//		}
		System.out.println(getArr.toString());
		return new HomeScreen();
	}
	

}
