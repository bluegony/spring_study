package com.oraclejava.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FileUploadCommand implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7498972029420208948L;
	
	private List<FileUploadItem> uploadItems;
	
	public FileUploadCommand(){
		uploadItems = new ArrayList<FileUploadItem>();
	}
	
	public List<FileUploadItem> getUploadItems() {
		return uploadItems;
	}
	public void setUploadItems(List<FileUploadItem> uploadItems) {
		this.uploadItems = uploadItems;
	}
	
}
