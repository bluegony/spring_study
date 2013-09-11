package com.oraclejava.domain;

import java.io.Serializable;
import java.net.InetAddress;

public class ChatMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6372307718994560629L;
	
	private String name;
	
	private String message;

	private String ip;
	
	private String time;
	
	public ChatMessage(){
		
	}
	
	public ChatMessage(String name, String message){
		this.name = name;
		this.message = message;
//		this.ip = InetAddress.getLocalHost().getHostAddress();
//		this.time = System.currentTimeMillis();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
