package com.chitmaster.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateDeserializer;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

@Entity
@Table(name="CHIT_GROUP")
public class ChitGroup {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int chitGroupId;
	private String chitGroupName;
	private double chitValue;
	private double commissionPercent;
	private double minPercentBidding;
	private double maxPercentBidding;
	private Date dateofBidding;
	private Date startDate;
	private Date endDate;
	private boolean isGroupActive;
	private int numberOfMembers;
	
	public int getChitGroupId() {
		return chitGroupId;
	}
	
	public void setChitGroupId(int chitGroupId) {
		this.chitGroupId = chitGroupId;
	}
	
	public String getChitGroupName() {
		return chitGroupName;
	}
	
	public void setChitGroupName(String chitGroupName) {
		this.chitGroupName = chitGroupName;
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
	@JsonSerialize(using=DateSerializer.class)
	public Date getDateofBidding() {
		return dateofBidding;
	}
	@JsonDeserialize(using=DateDeserializer.class)
	public void setDateofBidding(Date dateofBidding) {
		this.dateofBidding = dateofBidding;
	}
	
	@JsonSerialize(using=DateSerializer.class)
	public Date getStartDate() {
		return startDate;
	}
	
	@JsonDeserialize(using=DateDeserializer.class)
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@JsonSerialize(using=DateSerializer.class)
	public Date getEndDate() {
		return endDate;
	}
	
	@JsonDeserialize(using=DateDeserializer.class)
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public boolean isGroupActive() {
		return isGroupActive;
	}
	
	public void setGroupActive(boolean isGroupActive) {
		this.isGroupActive = isGroupActive;
	}
	
	public int getNumberOfMembers() {
		return numberOfMembers;
	}
	
	public void setNumberOfMembers(int numberOfMembers) {
		this.numberOfMembers = numberOfMembers;
	}

}
