package com.thebank.daos;


import com.thebank.beans.User;

public interface UserDao {
	//public static final UserDao currentUserDao = UserSerializer.us;
	
	public static final UserDao currentUserDao = UserSerializer.us;
	void createUser(User u);
	void updateUser(User u);
	User findByUsernameAndPassword(String username, String password);
	void deleteUser(User u);
	void deposit(User u,double money);
	User checkBalance(User u);
	void withdraw(User u, double money);
	User bringUser(String username);

}
