package com.chitmaster.dto;

public class BiddingDetailsDto {
	
	private int chitGroupId;
	private String userId;
	private double bidValue;
	
	public int getChitGroupId() {
		return chitGroupId;
	}
	public void setChitGroupId(int chitGroupId) {
		this.chitGroupId = chitGroupId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public double getBidValue() {
		return bidValue;
	}
	public void setBidValue(double updatedBidValue) {
		this.bidValue = updatedBidValue;
	}
	
	

}
