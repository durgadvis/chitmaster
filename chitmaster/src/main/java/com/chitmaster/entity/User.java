package com.chitmaster.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table (name="USER_DETAILS")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	
	@OneToOne
	@JoinColumn(name="emailId")
	private Register register;

	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="USER_CHITGROUP", joinColumns=@JoinColumn(name="userId"), inverseJoinColumns=@JoinColumn(name="chitGroupId"))
	private List<ChitGroup> chitGroups = new ArrayList<ChitGroup>();
	
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

	public List<ChitGroup> getChitGroups() {
		return chitGroups;
	}

	public void setChitGroups(List<ChitGroup> chitGroups) {
		this.chitGroups = chitGroups;
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
