package main.java.dto;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkDto {
	@SerializedName("BOOKMARK_ID")
	private int id;
	@SerializedName("X_SWIFI_MGR_NO")
	private String xMgrNo;
	@SerializedName("BOOKMARK_GROUP_ID")
	private int groupId;
	@SerializedName("MAKE_DATE")
	private String makeDate;
	@SerializedName("IS_DELETE")
	private boolean isDelete;

	private String bookmarkName;
	private String wifiName;

	public BookmarkDto(int id, String xMgrNo, int groupId, String makeDate, boolean isDelete) {
		super();
		this.id = id;
		this.xMgrNo = xMgrNo;
		this.groupId = groupId;
		this.makeDate = makeDate;
		this.isDelete = isDelete;
	}
}
