package com.oraclejava.domain;

import java.io.Serializable;

public class CustomerCommand implements Serializable {

	public int getPId() {
		return pId;
	}
	public void setPId(int pId) {
		this.pId = pId;
	}
	public String getPName() {
		return pName;
	}
	public void setPName(String pName) {
		this.pName = pName;
	}
	public String getPAddr() {
		return pAddr;
	}
	public void setPAddr(String pAddr) {
		this.pAddr = pAddr;
	}
	public String getPEmail() {
		return pEmail;
	}
	public void setPEmail(String pEmail) {
		this.pEmail = pEmail;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -6623192312677653466L;

	private int pId;
	private String pName;
	private String pAddr;
	private String pEmail;
	

}
