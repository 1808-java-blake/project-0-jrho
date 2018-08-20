package com.thebank.util;

import com.thebank.beans.User;

public class AppState {
	
	public static final AppState state = new AppState();
	private User currentUser;
	
	private AppState() {
		
	}
	public User getCurrentUser() {
		return currentUser;
	}
	public void setCurrentUser(User currentUser)
	{
		this.currentUser = currentUser;
	}

}


