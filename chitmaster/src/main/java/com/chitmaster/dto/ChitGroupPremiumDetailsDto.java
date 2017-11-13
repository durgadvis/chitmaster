package com.chitmaster.dto;

import java.util.Date;
import java.util.List;

import com.chitmaster.entity.PremiumDetails;
import com.chitmaster.entity.User;

public class ChitGroupPremiumDetailsDto {

	private int chitGroupId;
	private String groupName;
	private double chitValue;
	private double commissionPercent;
	private double minPercentBidding;
	private double maxPercentBidding;
	private Date dateofBidding;
	private Date startDate;
	private Date endDate;
	
	private List<PremiumDetails> listPremiumDetails;
	
	public int getChitGroupId() {
		return chitGroupId;
	}
	public void setChitGroupId(int chitGroupId) {
		this.chitGroupId = chitGroupId;
	}
	
	public String getName() {
		return groupName;
	}
	public void setName(String name) {
		this.groupName = name;
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
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public List<PremiumDetails> getListPremiumDetails() {
		return listPremiumDetails;
	}
	public void setListPremiumDetails(List<PremiumDetails> listPremiumDetails) {
		this.listPremiumDetails = listPremiumDetails;
	}
	
	
}
