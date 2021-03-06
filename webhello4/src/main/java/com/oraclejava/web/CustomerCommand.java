package com.oraclejava.web;

import java.io.Serializable;

public class CustomerCommand implements Serializable {
	
	private static final long serialVersionUID = -8301889979888294021L;

	private int custId;
	private String custName;
	private String custAddr;
	private String custEmail;
	
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustAddr() {
		return custAddr;
	}
	public void setCustAddr(String custAddr) {
		this.custAddr = custAddr;
	}
	public String getCustEmail() {
		return custEmail;
	}
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
	
	
}
