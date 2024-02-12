package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import main.java.dto.BookmarkDto;
import main.java.util.ConnectionUtil;

public class BookmarkDao {
	public BookmarkDao() {}
	
	public int insertBookMark(String bookmarkId, String mgrNo) throws ClassNotFoundException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = ConnectionUtil.getConnection();

			String sql = "insert into bookmark (X_SWIFI_MGR_NO, BOOKMARK_GROUP_ID, MAKE_DATE, IS_DELETE) " +
					" values (?, ?, ?, FALSE)";
		
			String time = LocalDateTime.now().withNano(0).toString();
				
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, mgrNo);
			preparedStatement.setString(2, bookmarkId);
			preparedStatement.setString(3, time);
			
	
			int affected = preparedStatement.executeUpdate();
			
			return affected;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(connection, preparedStatement, null);
		}
	}
	
	public List<BookmarkDto> selectBookmarkList() throws ClassNotFoundException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		List<BookmarkDto> list = null;
		try {
			connection = ConnectionUtil.getConnection();
			
			String sql = "select bm.* ,  wifi.X_SWIFI_MAIN_NM as wifi_name, bmg.name as bmg_name " +
					" from bookmark as bm join wifi on wifi.X_SWIFI_MGR_NO = bm.X_SWIFI_MGR_NO "
					+ " join bookmark_group as bmg on bmg.BOOKMARK_GROUP_ID = bm.BOOKMARK_GROUP_ID"
					+ " where bm.is_delete = 0 order by bm.make_date desc limit 20;";
			
			preparedStatement = connection.prepareStatement(sql);
			
			
			rs = preparedStatement.executeQuery();
			list = new ArrayList<>();
			
			while(rs.next()) {
				BookmarkDto bookmark = new BookmarkDto(
						Integer.parseInt(rs.getString("BOOKMARK_ID")),
						rs.getString("bmg_name"),
						Integer.parseInt(rs.getString("BOOKMARK_GROUP_ID")),
						rs.getString("MAKE_DATE"),
						Boolean.parseBoolean(rs.getString("IS_DELETE")),
						rs.getString("bmg_name"),
						rs.getString("wifi_name")
						);
				list.add(bookmark);
			}
			
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(connection, preparedStatement, rs);
		}
	}
	
	public int deleteBookMark(String bookmarkId) throws ClassNotFoundException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "UPDATE bookmark set is_delete = 1 where bookmark_id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,bookmarkId);
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
	
	public int deleteBookMark(int id) throws ClassNotFoundException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {			
			connection = ConnectionUtil.getConnection();
			String sql = "UPDATE bookmark set is_delete = 1 where bookmark_group_id = ?;";
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1,Integer.toString(id));
			
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
