package com.chitmaster.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name="USER_DETAILS")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	
	@ManyToOne
	@JoinColumn(name="emailId")
	private Register register;

	@OneToOne
	@JoinColumn(name="chitGroupId")
	private ChitGroup chitGroup;
	
	private boolean isHost;
	
	private boolean completedBidding;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Register getRegister() {
		return register;
	}

	public void setRegister(Register register) {
		this.register = register;
	}

	public ChitGroup getChitGroup() {
		return chitGroup;
	}

	public void setChitGroup(ChitGroup chitGroup) {
		this.chitGroup = chitGroup;
	}

	public boolean isHost() {
		return isHost;
	}

	public void setHost(boolean isHost) {
		this.isHost = isHost;
	}

	public boolean isCompletedBidding() {
		return completedBidding;
	}

	public void setCompletedBidding(boolean completedBidding) {
		this.completedBidding = completedBidding;
	}
}
