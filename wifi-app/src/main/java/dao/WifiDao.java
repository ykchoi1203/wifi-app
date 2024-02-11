package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.dto.WifiDto;
import main.java.util.ConnectionUtil;


public class WifiDao {
	public WifiDao() {}
	
	public int wifiInsert(List<WifiDto> wifiList) throws ClassNotFoundException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = ConnectionUtil.getConnection();

			String sql = "insert into wifi (X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2, "
					+ " X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR, "
					+ "X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM) " +
					" values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
			for(int i=0; i<wifiList.size(); i++) {
				
				preparedStatement = connection.prepareStatement(sql);
				if(!isAready(wifiList.get(i).getX_SWIFI_MGR_NO(), connection)) {
					continue;
				}
				preparedStatement.setString(1, wifiList.get(i).getX_SWIFI_MGR_NO());
				preparedStatement.setString(2, wifiList.get(i).getX_SWIFI_WRDOFC());
				preparedStatement.setString(3, wifiList.get(i).getX_SWIFI_MAIN_NM());
				preparedStatement.setString(4, wifiList.get(i).getX_SWIFI_ADRES1());
				preparedStatement.setString(5, wifiList.get(i).getX_SWIFI_ADRES2());
				preparedStatement.setString(6, wifiList.get(i).getX_SWIFI_INSTL_FLOOR());
				preparedStatement.setString(7, wifiList.get(i).getX_SWIFI_INSTL_TY());
				preparedStatement.setString(8, wifiList.get(i).getX_SWIFI_INSTL_MBY());
				preparedStatement.setString(9, wifiList.get(i).getX_SWIFI_SVC_SE());
				preparedStatement.setString(10, wifiList.get(i).getX_SWIFI_CMCWR());
				preparedStatement.setString(11, wifiList.get(i).getX_SWIFI_CNSTC_YEAR());
				preparedStatement.setString(12, wifiList.get(i).getX_SWIFI_INOUT_DOOR());
				preparedStatement.setString(13, wifiList.get(i).getX_SWIFI_REMARS3());
				preparedStatement.setString(14, wifiList.get(i).getLAT());
				preparedStatement.setString(15, wifiList.get(i).getLNT());
				preparedStatement.setString(16, wifiList.get(i).getWORK_DTTM());
	
				preparedStatement.addBatch();
				
				if (i % 10000 == 0) {
                	preparedStatement.executeBatch();
                	
                	preparedStatement.clearBatch();
                	
                }
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			ConnectionUtil.close(connection, preparedStatement, null);
		}
		
		return wifiList.size();
	}
	
	public boolean isAready(String wifiNo, Connection connect) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			connection = connect;
			String sql = "select * from wifi where X_SWIFI_MGR_NO = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, wifiNo);
			
			rs = preparedStatement.executeQuery();
			
			if(rs.next()) {
				return false;
			} else return true;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(!preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		
		return false;
	}
	
	public List<WifiDto> selectNearWifi(String LAT, String LNT) throws ClassNotFoundException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		List<WifiDto> list = new ArrayList<>();
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select ROUND(SQRT(POWER(111.045 * (CAST(wi.LAT AS REAL) - ?), 2) + POWER(111.045 * (? - CAST(wi.LNT AS REAL)) * COS(CAST(wi.LAT AS REAL) / 57.3),2)), 6) as distance_difference, wi.* " +
					" from wifi as wi " +
					" ORDER BY distance_difference " + 
					" LIMIT 20;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,LAT);
			preparedStatement.setString(2, LNT);
			
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				WifiDto wifi = new WifiDto(
						rs.getString("distance_difference"),
						rs.getString("X_SWIFI_MGR_NO"),
						rs.getString("X_SWIFI_WRDOFC"),
						rs.getString("X_SWIFI_MAIN_NM"),
						rs.getString("X_SWIFI_ADRES1"),
						rs.getString("X_SWIFI_ADRES2"),
						rs.getString("X_SWIFI_INSTL_FLOOR"),
						rs.getString("X_SWIFI_INSTL_TY"),
						rs.getString("X_SWIFI_INSTL_MBY"),
						rs.getString("X_SWIFI_SVC_SE"),
						rs.getString("X_SWIFI_CMCWR"),
						rs.getString("X_SWIFI_CNSTC_YEAR"),
						rs.getString("X_SWIFI_INOUT_DOOR"),
						rs.getString("X_SWIFI_REMARS3"),
						rs.getString("LAT"),
						rs.getString("LNT"),
						rs.getString("WORK_DTTM"));
				list.add(wifi);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			 ConnectionUtil.close(connection, preparedStatement, rs);
		}
		
		return list;
	}
	
	public WifiDto selectDetailWifi(String mgrNo) throws ClassNotFoundException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			connection = ConnectionUtil.getConnection();
			String sql = "select *" +
					" from wifi " +
					" WHERE X_SWIFI_MGR_NO = ?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,mgrNo);

			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				WifiDto wifi = new WifiDto(
						rs.getString("X_SWIFI_MGR_NO"),
						rs.getString("X_SWIFI_WRDOFC"),
						rs.getString("X_SWIFI_MAIN_NM"),
						rs.getString("X_SWIFI_ADRES1"),
						rs.getString("X_SWIFI_ADRES2"),
						rs.getString("X_SWIFI_INSTL_FLOOR"),
						rs.getString("X_SWIFI_INSTL_TY"),
						rs.getString("X_SWIFI_INSTL_MBY"),
						rs.getString("X_SWIFI_SVC_SE"),
						rs.getString("X_SWIFI_CMCWR"),
						rs.getString("X_SWIFI_CNSTC_YEAR"),
						rs.getString("X_SWIFI_INOUT_DOOR"),
						rs.getString("X_SWIFI_REMARS3"),
						rs.getString("LAT"),
						rs.getString("LNT"),
						rs.getString("WORK_DTTM"));

				return wifi;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionUtil.close(connection, preparedStatement, rs);
		}
		return null;
	}
}
