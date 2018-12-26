package com.wl.pojo;

public class Users {
	private String userid;
	private String username;
	private String pwd;
	private String usex;
	private String photofile;
	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Users(String userid, String username, String pwd, String usex,
			String photofile) {
		super();
		this.userid = userid;
		this.username = username;
		this.pwd = pwd;
		this.usex = usex;
		this.photofile = photofile;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getUsex() {
		return usex;
	}
	public void setUsex(String usex) {
		this.usex = usex;
	}
	public String getPhotofile() {
		return photofile;
	}
	public void setPhotofile(String photofile) {
		this.photofile = photofile;
	}
	@Override
	public String toString() {
		return "Users [userid=" + userid + ", username=" + username + ", pwd="
				+ pwd + ", usex=" + usex + ", photofile=" + photofile + "]";
	}

}
