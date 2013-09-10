package com.oraclejava.domain;

public class Customer {
	private int custId;
	private String custName;
	private String custAddr;
	private String custEmail;
	
	public Customer(){	
	}
	public Customer(CustomerCommand command){
		this.custId = command.getPId();
		this.custName = command.getPName();
		this.custAddr = command.getPAddr();
		this.custEmail = command.getPEmail();
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
