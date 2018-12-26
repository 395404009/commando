package com.wl.pojo;

public class Queseval {
	private String qid;
	private String eid;
	private Evaluates evaluates;
	private Users users;
	public Queseval() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Queseval(String qid, String eid, Evaluates evaluates, Users users) {
		super();
		this.qid = qid;
		this.eid = eid;
		this.evaluates = evaluates;
		this.users = users;
	}
	public String getQid() {
		return qid;
	}
	public void setQid(String qid) {
		this.qid = qid;
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public Evaluates getEvaluates() {
		return evaluates;
	}
	public void setEvaluates(Evaluates evaluates) {
		this.evaluates = evaluates;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	@Override
	public String toString() {
		return "Queseval [qid=" + qid + ", eid=" + eid + ", evaluates="
				+ evaluates + ", users=" + users + "]";
	}
	

}
