package com.oraclejava.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.oraclejava.spring.service.HelloService;
import com.oraclejava.spring.service.Sms;


public class HelloServiceImpl implements HelloService {


	private Sms sms;
	
	public Sms getSms() {
		return sms;
	}

	public void setSms(Sms sms) {
		this.sms = sms;
	}

	public void hello(){
        System.out.println( "Hello World!" );
        sms.send("aaa");
	}

	
}
