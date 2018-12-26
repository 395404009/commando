package com.wl.pojo;

public class Userques {
	private String userid;
	private String qid;
	private Users users;
	private Questions questions;
	public Userques() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Userques(String userid, String qid, Users users, Questions questions) {
		super();
		this.userid = userid;
		this.qid = qid;
		this.users = users;
		this.questions = questions;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getQid() {
		return qid;
	}
	public void setQid(String qid) {
		this.qid = qid;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public Questions getQuestions() {
		return questions;
	}
	public void setQuestions(Questions questions) {
		this.questions = questions;
	}
	@Override
	public String toString() {
		return "Userques [userid=" + userid + ", qid=" + qid + ", users="
				+ users + ", questions=" + questions + "]";
	}

}
