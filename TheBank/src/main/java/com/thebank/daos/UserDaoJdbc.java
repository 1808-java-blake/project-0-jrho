package com.thebank.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.thebank.beans.User;
import com.thebank.util.ConnectionUtil;

public class UserDaoJdbc implements UserDao {
	private ConnectionUtil cu = ConnectionUtil.cu;
	private Logger log = Logger.getRootLogger();
	
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Override
	public void createUser(User u) {
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO users (username, pass, firstname, lastname,age,balance) VALUES (?,?,?,?,?,?)");
			ps.setString(1, u.getUsername());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getFirstName());
			ps.setString(4, u.getLastName());
			ps.setInt(5, u.getAge());
			ps.setDouble(6, u.getTotBalance());
			int recordsCreated = ps.executeUpdate();
			log.trace(recordsCreated + "records created");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			for(StackTraceElement ste: e.getStackTrace())
			{
				log.warn("failed to create new user");
			}
			
		}
		
		
	}

	@Override
	public void updateUser(User u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		try (Connection conn = cu.getConnection()) {
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM users WHERE username=? and pass=?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
					
			if(rs.next()) {
				User u = new User();
				u.setAge(rs.getInt("age"));
				u.setFirstName(rs.getString("firstname"));
				u.setLastName(rs.getString("lastname"));
				u.setUsername(rs.getString("username"));
				u.setId(rs.getInt("user_id"));
				
				return u;
			} else {
				log.warn("failed to find user with provided credentials from the db");
				return null;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteUser(User u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User bringUser(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deposit(User u, double money) {
		if(money<0) {
			System.out.println("Invalid number");
			return;
		}
		
		double tot=0;
		tot = u.getTotBalance();
		System.out.println("The previous total balance =$"+tot);
		
		double newResult = tot+money;
		u.setTotBalance(newResult);
		System.out.println("New total balance =$" +newResult);
		
	}

	@Override
	public void withdraw(User u, double money) {
		if(money<0) {
			System.out.println("Invalid number");
			return;
		}
		double tot=0;
		tot = u.getTotBalance();
		System.out.println("The previous total balance =$" + tot);
		
		double newResult = tot-money;
		if(newResult<0) {
			System.out.println("withdrawal money is greater than current balance");
			return;
		}
		u.setTotBalance(newResult);
		System.out.println("New total balance =$" +newResult);
	}

	@Override
	public User checkBalance(User u) {
		// TODO Auto-generated method stub
		return null;
	}

}






//package com.revature.daos;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//import org.apache.log4j.Logger;
//
//import com.revature.beans.User;
//import com.revature.util.ConnectionUtil;
//
//public class UserDaoJdbc implements UserDao {
//	private ConnectionUtil cu = ConnectionUtil.cu;
//	private Logger log = Logger.getRootLogger();
//	
//	static {
//		try {
//			Class.forName("org.postgresql.Driver");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//
//	@Override
//	public void createUser(User u) {
//		try (Connection conn = cu.getConnection()) {
//			PreparedStatement ps = conn.prepareStatement(
//					"INSERT INTO app_users (username, pass, age, firstname, lastname) VALUES (?,?,?,?,?)");
//			ps.setString(1, u.getUsername());
//			ps.setString(2, u.getPassword());
//			ps.setInt(3, u.getAge());
//			ps.setString(4, u.getFirstName());
//			ps.setString(5, u.getLastName());
//			int recordsCreated = ps.executeUpdate();
//			log.trace(recordsCreated + " records created");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			log.error(e.getMessage());
//			for(StackTraceElement ste: e.getStackTrace()) {
//				log.error(ste);
//			}
//			log.warn("failed to create new user");
//		}
//
//	}
//
//	@Override
//	public User findByUsernameAndPassword(String username, String password) {
//		try (Connection conn = cu.getConnection()) {
//			// don't do this
////			Statement s = conn.createStatement();
////			ResultSet rs = s.executeQuery("SELECT * FROM app_users WHERE username='" + 
////							username + "' AND pass='" + password + "'");
//			
//			// do this
//			PreparedStatement ps = conn.prepareStatement(
//					"SELECT * FROM app_users WHERE username=? and pass=?");
//			ps.setString(1, username);
//			ps.setString(2, password);
//			ResultSet rs = ps.executeQuery();
//					
//			if(rs.next()) {
//				User u = new User();
//				u.setAge(rs.getInt("age"));
//				u.setFirstName(rs.getString("firstname"));
//				u.setLastName(rs.getString("lastname"));
//				u.setUsername(rs.getString("username"));
//				u.setId(rs.getInt("user_id"));
//				return u;
//			} else {
//				log.warn("failed to find user with provided credentials from the db");
//				return null;
//			}
//
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	@Override
//	public void updateUser(User u) {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void deleteUser(User u) {
//		// TODO Auto-generated method stub
//
//	}
//
//}
