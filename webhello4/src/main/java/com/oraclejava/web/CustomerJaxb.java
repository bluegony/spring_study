package com.oraclejava.web;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.oraclejava.domain.Customer;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder={
		"custId", "custName", "custAddr", "custEmail"
}) // XmlType annotation에서 순서를 정의하지 않으면 xml document내에서 어떤 순서로 정리될 지 모른다.
@XmlRootElement
public class CustomerJaxb {
	private int custId = -1;
	private String custName = null;
	private String custAddr = null;
	private String custEmail = null;

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

	CustomerJaxb() {
	}

	CustomerJaxb( CustomerCommand cmd ) {
		custId = cmd.getCustId();
		custName = cmd.getCustName();
		custAddr = cmd.getCustAddr();
		custEmail = cmd.getCustEmail();
	}
	CustomerJaxb( Customer cmd ) {
		custId = cmd.getCustId();
		custName = cmd.getCustName();
		custAddr = cmd.getCustAddr();
		custEmail = cmd.getCustEmail();
	}
}
