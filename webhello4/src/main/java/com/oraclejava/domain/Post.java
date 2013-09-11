package com.oraclejava.domain;

public class Post {
	private int seq;
	private String zipcode;
	private String sido;
	private String gugun;
	private String dong;
	private String ri;
	private String stBunji;
	private String edBunji;
	
	public Post() {
		
	}
	public Post(int seq, String zipcode, String sido, String gugun, String dong, String ri, String stBunji, String edBunji) {
		this.seq = seq;
		this.zipcode = zipcode;
		this.sido = sido;
		this.gugun = gugun;
		this.dong = dong;
		this.ri = ri;
		this.stBunji = stBunji;
		this.edBunji = edBunji;
	}
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getSido() {
		return sido;
	}
	public void setSido(String sido) {
		this.sido = sido;
	}
	public String getGugun() {
		return gugun;
	}
	public void setGugun(String gugun) {
		this.gugun = gugun;
	}
	public String getDong() {
		return dong;
	}
	public void setDong(String dong) {
		this.dong = dong;
	}
	public String getRi() {
		return ri;
	}
	public void setRi(String ri) {
		this.ri = ri;
	}
	public String getStBunji() {
		return stBunji;
	}
	public void setStBunji(String stBunji) {
		this.stBunji = stBunji;
	}
	public String getEdBunji() {
		return edBunji;
	}
	public void setEdBunji(String edBunji) {
		this.edBunji = edBunji;
	}
	
	
}
