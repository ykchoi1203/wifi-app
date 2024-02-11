package main.java.dto;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkGroupDto {
	@SerializedName("BOOKMARK_GROUP_ID")
	private int id;
	@SerializedName("NAME")
	private String name;
	@SerializedName("ORDER_NO")
	private int orderNo;
	@SerializedName("MAKE_DATE")
	private String makeDate;
	@SerializedName("UPDATE_DATE")
	private String updateDate;
	@SerializedName("IS_DELETE")
	private boolean isDelete; 
}
