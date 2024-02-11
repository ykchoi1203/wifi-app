package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import main.java.dto.HistoryDto;
import main.java.util.ConnectionUtil;

public class HistoryDao {
	public HistoryDao() {}
	
	public List<HistoryDto> getHistory() throws ClassNotFoundException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		List<HistoryDto> list = new ArrayList<>();
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select * from history where is_delete = false order by WORK_DTTM desc limit 20;";
			preparedStatement = connection.prepareStatement(sql);
			
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				int id = Integer.parseInt(rs.getString("ID"));
				String lat = rs.getString("LAT");
				String lnt = rs.getString("LNT");
				String date = rs.getString("WORK_DTTM");
				boolean isDelete = Boolean.parseBoolean(rs.getString("IS_DELETE"));
				HistoryDto history = new HistoryDto(id, lat, lnt, date, isDelete);
				list.add(history);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(connection, preparedStatement, rs);
		}
		
		return list;
	}
	
	public int insertHistory(String lat, String lnt) throws ClassNotFoundException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = ConnectionUtil.getConnection();

			String sql = "insert into history (LAT, LNT, WORK_DTTM, IS_DELETE) " +
					" values (?, ?, ?, FALSE)";
		
			String time = LocalDateTime.now().withNano(0).toString();
				
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1,lat);
			preparedStatement.setString(2, lnt);
			preparedStatement.setString(3, time);
			
	
			int affected = preparedStatement.executeUpdate();
			
			return affected;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(connection, preparedStatement, null);
		}
	}
	
	public int deleteHistory(String id) throws ClassNotFoundException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "UPDATE history set is_delete = 1 where id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,id);
			int affected = preparedStatement.executeUpdate();
			
			return affected;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(connection, preparedStatement, null);
		}
		
		return 0;
	}
}
