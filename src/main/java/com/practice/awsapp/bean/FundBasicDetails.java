package com.practice.awsapp.bean;

import org.springframework.data.annotation.Id;

public class FundBasicDetails {
	@Id
	private String id;
	
	private String amfiId;
	
	private String payoutISIN;
	private String reInvISIN;
	private String name;
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
	public String getPayoutISIN() {
		return payoutISIN;
	}
	public void setPayoutISIN(String payoutISIN) {
		this.payoutISIN = payoutISIN;
	}
	public String getReInvISIN() {
		return reInvISIN;
	}
	public void setReInvISIN(String reInvISIN) {
		this.reInvISIN = reInvISIN;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
