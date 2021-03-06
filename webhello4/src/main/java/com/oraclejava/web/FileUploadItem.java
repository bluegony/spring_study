package com.oraclejava.web;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadItem implements Serializable {

	private static final long serialVersionUID = -4468678989479694266L;
	
	private String name;
	
	private MultipartFile file;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	

}
