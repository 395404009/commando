package com.wl.pojo;

public class Questions {
	private String qid;
	private String question;
	private String answer;
	private String subanswer;
	public Questions() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Questions(String qid, String question, String answer,
			String subanswer) {
		super();
		this.qid = qid;
		this.question = question;
		this.answer = answer;
		this.subanswer = subanswer;
	}

	public String getSubanswer() {
		return subanswer;
	}
	public void setSubanswer(String subanswer) {
		this.subanswer = subanswer;
	}
	public String getQid() {
		return qid;
	}
	public void setQid(String qid) {
		this.qid = qid;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "Questions [qid=" + qid + ", question=" + question + ", answer="
				+ answer + ", subanswer=" + subanswer + "]";
	}
	

}
