package com.oraclejava.web;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.oraclejava.domain.Customer;

@XmlRootElement(name="CustomerRoot")
public class CustomerListJaxb {
	private List<CustomerJaxb> list;

	public CustomerListJaxb(){
		
	}
	
	public CustomerListJaxb(List<Customer> customerList){
		list = new ArrayList<CustomerJaxb>();
		for (Customer c : customerList) {
			list.add(new CustomerJaxb(c));
		}
	}
	
	public List<CustomerJaxb> getList() {
		return list;
	}

	public void setList(List<CustomerJaxb> list) {
		this.list = list;
	}
}
