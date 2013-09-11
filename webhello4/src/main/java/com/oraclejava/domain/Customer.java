package com.oraclejava.domain;

import com.oraclejava.web.CustomerCommand;

public class Customer {
	private int custId;
	private String custName;
	private String custAddr;
	private String custEmail;
	
	public Customer() {
	}
	public Customer(CustomerCommand command) {
		this.custId = command.getCustId();
		this.custName = command.getCustName();
		this.custAddr = command.getCustAddr();
		this.custEmail = command.getCustEmail();
	}
	
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
