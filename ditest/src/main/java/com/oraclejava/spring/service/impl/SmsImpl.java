package com.oraclejava.spring.service.impl;

import org.springframework.stereotype.Component;

import com.oraclejava.spring.service.Sms;

@Component("sktelecom")
public class SmsImpl implements Sms {

	public void send(String contents) {
		// TODO Auto-generated method stub

        System.out.println( "send " +contents+"!!");

	}

}
