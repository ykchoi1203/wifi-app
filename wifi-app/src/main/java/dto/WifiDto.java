package main.java.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WifiDto {
	private String Distance;
	private String X_SWIFI_MGR_NO;
	private String X_SWIFI_WRDOFC;
	private String X_SWIFI_MAIN_NM;
	private String X_SWIFI_ADRES1;
	private String X_SWIFI_ADRES2;
	private String X_SWIFI_INSTL_FLOOR;
	private String X_SWIFI_INSTL_TY;
	private String X_SWIFI_INSTL_MBY;
	private String X_SWIFI_SVC_SE;
	private String X_SWIFI_CMCWR;
	private String X_SWIFI_CNSTC_YEAR;
	private String X_SWIFI_INOUT_DOOR;
	private String X_SWIFI_REMARS3;
	private String LAT;
	private String LNT;
	private String WORK_DTTM;
	
	public WifiDto(String x_SWIFI_MGR_NO, String x_SWIFI_WRDOFC, String x_SWIFI_MAIN_NM, String x_SWIFI_ADRES1,
			String x_SWIFI_ADRES2, String x_SWIFI_INSTL_FLOOR, String x_SWIFI_INSTL_TY, String x_SWIFI_INSTL_MBY,
			String x_SWIFI_SVC_SE, String x_SWIFI_CMCWR, String x_SWIFI_CNSTC_YEAR, String x_SWIFI_INOUT_DOOR,
			String x_SWIFI_REMARS3, String lAT, String lNT, String wORK_DTTM) {
		super();
		X_SWIFI_MGR_NO = x_SWIFI_MGR_NO;
		X_SWIFI_WRDOFC = x_SWIFI_WRDOFC;
		X_SWIFI_MAIN_NM = x_SWIFI_MAIN_NM;
		X_SWIFI_ADRES1 = x_SWIFI_ADRES1;
		X_SWIFI_ADRES2 = x_SWIFI_ADRES2;
		X_SWIFI_INSTL_FLOOR = x_SWIFI_INSTL_FLOOR;
		X_SWIFI_INSTL_TY = x_SWIFI_INSTL_TY;
		X_SWIFI_INSTL_MBY = x_SWIFI_INSTL_MBY;
		X_SWIFI_SVC_SE = x_SWIFI_SVC_SE;
		X_SWIFI_CMCWR = x_SWIFI_CMCWR;
		X_SWIFI_CNSTC_YEAR = x_SWIFI_CNSTC_YEAR;
		X_SWIFI_INOUT_DOOR = x_SWIFI_INOUT_DOOR;
		X_SWIFI_REMARS3 = x_SWIFI_REMARS3;
		LAT = lAT;
		LNT = lNT;
		WORK_DTTM = wORK_DTTM;
	}
	
}
