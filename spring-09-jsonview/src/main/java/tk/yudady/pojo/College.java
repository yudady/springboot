package tk.yudady.pojo;

import com.fasterxml.jackson.annotation.JsonView;

import tk.yudady.config.Profile;

public class College {

	@JsonView(Profile.PublicView.class)
	private String colName;
	@JsonView(Profile.FriendsView.class)
	private String colLocation;

	public College(String colName, String colLocation) {
		this.colName = colName;
		this.colLocation = colLocation;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public String getColLocation() {
		return colLocation;
	}

	public void setColLocation(String colLocation) {
		this.colLocation = colLocation;
	}
}
