package com.oraclejava.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.oraclejava.spring.service.HelloService;
import com.oraclejava.spring.service.Sms;

@Component
public class HelloServiceImpl implements HelloService {

	@Autowired
	@Qualifier("sktelecom")
	private Sms sms;
	
	public void hello(){
        System.out.println( "Hello World!" );
        sms.send("aaa");
	}

	
}
