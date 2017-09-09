package com.practice.awsapp.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="mfnavlist")
public class NAVBean {
	@Id
	private String id;
	
	private String amfiId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAmfiId() {
		return amfiId;
	}

	public void setAmfiId(String amfiId) {
		this.amfiId = amfiId;
	}
	
	
}
