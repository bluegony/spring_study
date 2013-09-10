package com.oraclejava.domain;

public class ZipCode {
	private int seq;
	private String zipCode;
	private String sido;
	private String gugun;
	private String dong;
	private String ri;
	private String stBunji;
	private String edBunji;
	
	public ZipCode(){
		
	}
	
	public ZipCode(int seq, String arr[]){
		this.seq = seq;
		this.zipCode = arr[0];
		this.sido = arr[1];
		this.gugun = arr[2];
		this.dong = arr[3];
		this.ri = arr[4];
		this.stBunji = arr[5];
		this.edBunji = arr[6];
		
	}
	
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
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
