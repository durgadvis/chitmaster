package com.chitmaster.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PREMIUM_DETAILS")
public class PremiumDetails {
	@Id
	@GeneratedValue
	private int premiumId;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="chitGroupId")
	private ChitGroup chitGroup;
	
	private double monthlyPremium;
	
	private double monthlyBiddingDiscountAmount;
	
	private double totalAmountToBePaid;
	
	private boolean premiumPaid;
	
	private Date dateOfPayment;
	
	public int getPremiumId() {
		return premiumId;
	}
	
	public void setPremiumId(int premiumId) {
		this.premiumId = premiumId;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public ChitGroup getChitGroup() {
		return chitGroup;
	}
	public void setChitGroup(ChitGroup chitGroup) {
		this.chitGroup = chitGroup;
	}
	public double getMonthlyPremium() {
		return monthlyPremium;
	}
	public void setMonthlyPremium(double monthlyPremium) {
		this.monthlyPremium = monthlyPremium;
	}
	public double getMonthlyBiddingDiscountAmount() {
		return monthlyBiddingDiscountAmount;
	}
	public void setMonthlyBiddingDiscountAmount(double monthlyBiddingDiscountAmount) {
		this.monthlyBiddingDiscountAmount = monthlyBiddingDiscountAmount;
	}
	public double getTotalAmountToBePaid() {
		return totalAmountToBePaid;
	}
	public void setTotalAmountToBePaid(double totalAmountToBePaid) {
		this.totalAmountToBePaid = totalAmountToBePaid;
	}
	public boolean isPremiumPaid() {
		return premiumPaid;
	}
	public void setPremiumPaid(boolean premiumPaid) {
		this.premiumPaid = premiumPaid;
	}
	public Date getDateOfPayment() {
		return dateOfPayment;
	}
	public void setDateOfPayment(Date dateOfPayment) {
		this.dateOfPayment = dateOfPayment;
	}
}
