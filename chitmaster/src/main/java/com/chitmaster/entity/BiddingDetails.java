package com.chitmaster.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="BIDDING_DETAILS")
public class BiddingDetails {
	@Id
	@GeneratedValue
	private int biddingId;

	@ManyToOne
	@JoinColumn(name="chitGroupId")
	private ChitGroup chitGroup;
	
	@ManyToOne
	@JoinColumn(name="winnerUserId")
	private User winnerUser;
	
	private Date dateOfBidding;

	private double biddingPercentWon;

	private double totalDiscountAmount;
	
	private double commissionAmount;

	public int getBiddingId() {
		return biddingId;
	}
	
	public void setBiddingId(int biddingId) {
		this.biddingId = biddingId;
	}
	
	public ChitGroup getChitGroup() {
		return chitGroup;
	}
	public void setChitGroup(ChitGroup chitGroup) {
		this.chitGroup = chitGroup;
	}
	public User getWinnerUser() {
		return winnerUser;
	}
	public void setWinnerUser(User winnerUser) {
		this.winnerUser = winnerUser;
	}
	public Date getDateOfBidding() {
		return dateOfBidding;
	}
	public void setDateOfBidding(Date dateOfBidding) {
		this.dateOfBidding = dateOfBidding;
	}
	public double getBiddingPercentWon() {
		return biddingPercentWon;
	}
	public void setBiddingPercentWon(double biddingPercentWon) {
		this.biddingPercentWon = biddingPercentWon;
	}
	public double getTotalDiscountAmount() {
		return totalDiscountAmount;
	}
	public void setTotalDiscountAmount(double totalDiscountAmount) {
		this.totalDiscountAmount = totalDiscountAmount;
	}
	public double getCommissionAmount() {
		return commissionAmount;
	}
	public void setCommissionAmount(double commissionAmount) {
		this.commissionAmount = commissionAmount;
	}
}
