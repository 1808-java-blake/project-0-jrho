package com.thebank.screens;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.thebank.beans.Account;
import com.thebank.beans.History;
import com.thebank.beans.User;
import com.thebank.daos.HistoryDao;
import com.thebank.daos.UserDao;
import com.thebank.util.AppState;
import com.thebank.util.ConnectionUtil;

public class AdminScreen implements Screen {
	private Scanner scan = new Scanner(System.in);
	private AppState state =AppState.state;
	private User u =state.getCurrentUser();
	private UserDao ud = UserDao.currentUserDao;
	private HistoryDao hd = HistoryDao.currentHistoryDao;
	private ConnectionUtil cu =ConnectionUtil.cu;

	@Override
	public Screen start() {
		
		System.out.println("You have logged in as Admin");
				
		System.out.println("Enter user name to see options:");
		
		
		String username = scan.nextLine();
		User userInfo = ud.findByUsername(username);
		//int number = state.getCurrentUser().getId();
		int userId = userInfo.getId();
		System.out.println("Select 1 to view "+userInfo.getUsername()+"'s"+ " personal information");
		System.out.println("Select 2 to view "+userInfo.getUsername()+"'s"+" acount information");
		System.out.println("Select 3 to view "+userInfo.getUsername()+"'s"+" transaction history");
		System.out.println("Select 5 to go back main screen");
		String selection = scan.nextLine();
				
		switch(selection) {
				
		case "1": 
			System.out.println(userInfo.toString());
			break;
		case "2":
			try(Connection conn=cu.getConnection()){
				PreparedStatement ps = conn.prepareStatement(
						"SELECT * FROM accounts WHERE user_id =?");
				ps.setInt(1, userId);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					Account a = new Account();
					a.setAccountNumber(rs.getInt("account_number"));
					a.setAccountId(rs.getInt("account_id"));
					a.setUserId(rs.getInt("user_id"));
					System.out.println("Account number:" + a.getAccountNumber());
					System.out.println("Account ID: "+a.getAccountId());
					System.out.println("User ID: "+a.getUserId());
					System.out.println("Account balance:" + userInfo.getTotBalance());
				}
				
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			
			break;
		case "3":
			List<History> history = hd.findByUserId(userId);
			history.stream().forEach((each) -> {
				System.out.println("Date: " + each.getDate() + " Type: "+ each.getType() + "Amount: "+each.getMoney()+ " user_id: "+each.getUserId() );
			});
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
			
			
	

			
	


