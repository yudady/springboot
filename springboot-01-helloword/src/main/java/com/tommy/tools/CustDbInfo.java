package com.tommy.tools;

public class CustDbInfo {

	private String id;

	private String name;

	private String status;

	private String dbName;

	private String dbUser;

	private String dbPwd;

	private String dbHost;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName == null ? null : dbName.trim();
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser == null ? null : dbUser.trim();
	}

	public String getDbPwd() {
		return dbPwd;
	}

	public void setDbPwd(String dbPwd) {
		this.dbPwd = dbPwd == null ? null : dbPwd.trim();
	}

	public String getDbHost() {
		return dbHost;
	}

	public void setDbHost(String dbHost) {
		this.dbHost = dbHost == null ? null : dbHost.trim();
	}

	@Override
	public String toString() {
		return "CustDbInfo{" + "id='" + id + '\'' + ", name=" + name + ", status='" + status + '\''
			+ ", dbName='" + dbName + '\'' + ", dbUser='" + dbUser + '\'' + ", dbPwd='" + dbPwd + '\''
			+ ", dbHost='" + dbHost + '\'' + '}';
	}
}