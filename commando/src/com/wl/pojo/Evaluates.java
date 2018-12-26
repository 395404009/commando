package com.wl.pojo;

public class Evaluates {
    private String eid;
    private String context;
    private String time;
	public Evaluates() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Evaluates(String eid, String context, String time) {
		super();
		this.eid = eid;
		this.context = context;
		this.time = time;
	}

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}

	@Override
	public String toString() {
		return "Evaluates [eid=" + eid + ", context=" + context + ", time="
				+ time + "]";
	}
	
    
}
