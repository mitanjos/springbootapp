package com.practice.awsapp.bean;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="mfnavlist")
public class NAVBean implements Comparable<NAVBean>{
	@Id
	private String id;
	
	private String amfiId;
	
	private String payoutISIN;
	private String reInvISIN;
	private String name;
	private Double navVal;
	private Double repurchasePrice;
	private Double salePrice;
	private String priceDate;
	private Date date;
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
	public Double getNavVal() {
		return navVal;
	}
	public void setNavVal(Double navVal) {
		this.navVal = navVal;
	}
	public Double getRepurchasePrice() {
		return repurchasePrice;
	}
	public void setRepurchasePrice(Double repurchasePrice) {
		this.repurchasePrice = repurchasePrice;
	}
	public Double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}
	public String getPriceDate() {
		return priceDate;
	}
	public void setPriceDate(String priceDate) {
		this.priceDate = priceDate;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public int compareTo(NAVBean o) {
		if(o!=null && o.getAmfiId()!=null) {
			if(o.getAmfiId().equals(amfiId)) {
				if(o.getDate()!=null)
					return o.getDate().compareTo(date);
			}
			return o.getAmfiId().compareTo(amfiId);
		}
		return 0;
	}
}
