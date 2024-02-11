package main.java.dto;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryDto {
	@SerializedName("ID")
	private int id;
	@SerializedName("LAT")
	private String lat;
	@SerializedName("LNT")
	private String lnt;
	@SerializedName("WORK_DATE")
	private String workDate;
	@SerializedName("IS_DELETE")
	private boolean isDelete;
}
