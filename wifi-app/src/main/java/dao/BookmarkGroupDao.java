package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import main.java.dto.BookmarkGroupDto;
import main.java.util.ConnectionUtil;

public class BookmarkGroupDao {
	public BookmarkGroupDao() {}
	
	public List<BookmarkGroupDto> selectBookmark() throws ClassNotFoundException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		List<BookmarkGroupDto> list = new ArrayList<>();
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select *" +
					" from bookmark_group where is_delete = 0 order by ORDER_NO;";
			preparedStatement = connection.prepareStatement(sql);

			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				BookmarkGroupDto bookMarkGroup = new BookmarkGroupDto( 
						Integer.parseInt(rs.getString("BOOKMARK_GROUP_ID")),
						rs.getString("NAME"),
						Integer.parseInt(rs.getString("ORDER_NO")),
						rs.getString("MAKE_DATE"),
						rs.getString("UPDATE_DATE"),
						Boolean.parseBoolean(rs.getString("IS_DELETE"))
						);

				list.add(bookMarkGroup);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(connection, preparedStatement, rs);
		}
		return list;
	}
	
	public int insertBookMarkGroup(String groupName , String orderNo) throws ClassNotFoundException {
		if(!orderNo.matches("\\d+")) return -1;
		if(Integer.parseInt(orderNo) < 0) return -1;
		Connection connection = null;
		
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionUtil.getConnection();
			if(!searchOrderNo(orderNo)) {
				return -1;
			}
			
			String sql = "insert into bookmark_group (NAME, ORDER_NO, MAKE_DATE, IS_DELETE) " +
					" values (?, ?, ?, FALSE);";
		
			String time = LocalDateTime.now().withNano(0).toString();
				
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1,groupName);
			preparedStatement.setString(2, orderNo);
			preparedStatement.setString(3, time);
			
	
			int affected = preparedStatement.executeUpdate();
			
			return affected;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(connection, preparedStatement, null);
		}
	}
	
	public boolean searchOrderNo(String orderNo) throws ClassNotFoundException {
		if(!orderNo.matches("\\d+")) return false;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionUtil.getConnection();
			
			String sql = "select * " +
					" from bookmark_group where order_no = ? ;";
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1,orderNo);
			
			rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(connection, preparedStatement, rs);
		}
	
	}
	
	public int updateBookMarkGroup(String id, String name,String orderNoChange, String orderNo) throws ClassNotFoundException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		if(!orderNoChange.matches("\\d+")) return -1;
		try {			
			connection = ConnectionUtil.getConnection();
			
			if(!orderNoChange.equals(orderNo) && !searchOrderNo(orderNoChange)) {
				return -1;
			}
			String sql = "UPDATE bookmark_group set NAME = ?, ORDER_NO = ?, UPDATE_DATE = ? where bookmark_group_id = ?;";
			preparedStatement = connection.prepareStatement(sql);

			String time = LocalDateTime.now().withNano(0).toString();
			
			preparedStatement.setString(1,name);
			preparedStatement.setString(2,orderNoChange);
			preparedStatement.setString(3,time);
			preparedStatement.setString(4,id);
			
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
	
	public int deleteBookMarkGroup(String id) throws ClassNotFoundException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {			
			connection = ConnectionUtil.getConnection();
			String orderNo = null;
			String sql = "UPDATE bookmark_group set ORDER_NO = ?, is_delete = 1 where bookmark_group_id = ?;";
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1,orderNo);
			preparedStatement.setString(2,id);
			
			int affected = preparedStatement.executeUpdate();
			
			if(affected < 1) return affected;
			else {
				BookmarkDao bookmarkDao = new BookmarkDao();
				bookmarkDao.deleteBookMark(Integer.parseInt(id));
			}
			
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
