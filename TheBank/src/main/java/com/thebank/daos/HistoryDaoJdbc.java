package com.thebank.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.thebank.beans.History;
import com.thebank.util.ConnectionUtil;

public class HistoryDaoJdbc implements HistoryDao {
	private Logger log = Logger.getRootLogger();
	private ConnectionUtil cu = ConnectionUtil.cu;

	@Override
	public int createHistroy(History h) {
		try(Connection conn = cu.getConnection()){
			PreparedStatement ps = conn.prepareStatement(
					"INSERT INTO history (type,money, date, user_id) VALUES (?,?,?,?)",
					new String[] {"history_id"});
			ps.setString(1, h.getType());
			ps.setDouble(2, h.getMoney());
			ps.setString(3, h.getDate());
			ps.setInt(4, h.getUserId());
			int recordsCreated = ps.executeUpdate();
			log.trace(recordsCreated + " records created");
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				log.trace("history id is" + rs.getInt("history_id"));
				return rs.getInt("history_id");
			}
		} catch (SQLException e) {
			log.error(e.getMessage());
			for(StackTraceElement ste: e.getStackTrace()) {
				log.error(ste);
			}
			log.warn("failed to create new user");
		}
		return 0;
	
	}

	@Override
	public List<History> findByUserId(int id) {
		try (Connection conn = cu.getConnection()){
			List<History> transactions = new ArrayList<>();
			PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM history WHERE user_id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				History list = new History();
				list.setId(rs.getInt("history_id"));
				list.setType(rs.getString("type"));
				list.setMoney(rs.getDouble("money"));
				list.setDate(rs.getString("date"));
				list.setUserId(rs.getInt("user_id"));
				transactions.add(list);
			}	
			return transactions;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
