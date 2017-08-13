package com.chitmaster.dto;

import java.util.Date;
import java.util.List;

public class ChitGroupDto {
	
	private String name;
	private List<String> emailIdList;
	private double chitValue;
	private double commissionPercent;
	private double minPercentBidding;
	private double maxPercentBidding;
	private Date dateofBidding;
	private Date startDate;
	private Date endDate;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getEmailIdList() {
		return emailIdList;
	}
	public void setEmailIdList(List<String> emailIdList) {
		this.emailIdList = emailIdList;
	}
	public double getChitValue() {
		return chitValue;
	}
	public void setChitValue(double chitValue) {
		this.chitValue = chitValue;
	}
	public double getCommissionPercent() {
		return commissionPercent;
	}
	public void setCommissionPercent(double commissionPercent) {
		this.commissionPercent = commissionPercent;
	}
	public double getMinPercentBidding() {
		return minPercentBidding;
	}
	public void setMinPercentBidding(double minPercentBidding) {
		this.minPercentBidding = minPercentBidding;
	}
	public double getMaxPercentBidding() {
		return maxPercentBidding;
	}
	public void setMaxPercentBidding(double maxPercentBidding) {
		this.maxPercentBidding = maxPercentBidding;
	}
	public Date getDateofBidding() {
		return dateofBidding;
	}
	public void setDateofBidding(Date dateofBidding) {
		this.dateofBidding = dateofBidding;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
