package com.zhuwei.domain;

import java.util.Date;

public class Database {
	private String id;
	private String name;
	private String path;
	private Date backuptime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Date getBackuptime() {
		return backuptime;
	}
	public void setBackuptime(Date backuptime) {
		this.backuptime = backuptime;
	}
	
	
}
