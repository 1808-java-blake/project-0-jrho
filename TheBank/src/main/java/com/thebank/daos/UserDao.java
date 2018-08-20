package com.thebank.daos;


import com.thebank.beans.User;

public interface UserDao {
	//public static final UserDao currentUserDao = UserSerializer.us;
	
	public static final UserDao currentUserDao = new UserDaoJdbc();
	//public static Map<String,List<String>> user_history = new HashMap<String,List<String>>();
	
	void createUser(User u);
	void updateUser(User u);
	User findByUsernameAndPassword(String username, String password);
	void deleteUser(User u);
	User bringUser(String username);
	void deposit(User u,double money);
	void withdraw(User u, double money);
	User checkBalance(User u);

}
