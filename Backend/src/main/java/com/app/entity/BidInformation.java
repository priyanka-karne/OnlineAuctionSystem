package com.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.ToString;

@Entity
@Table(name="bidding_Details")

@ToString
public class BidInformation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
    private long id;
	
	
	@ManyToOne
	private Users users;
	@Column(name="biddingprice")
	private Double amount;
	
	@ManyToOne
    @JoinColumn(name="session_id", nullable=false)
    private Auction event;
	
	
	public BidInformation() {
		super();
	}



	public BidInformation(long id, Users users, Double amount, Auction event) {
		super();
		this.id = id;
		this.users = users;
		this.amount = amount;
		this.event = event;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public Users getUsers() {
		return users;
	}



	public void setUsers(Users users) {
		this.users = users;
	}



	public Auction getEvent() {
		return event;
	}



	public void setEvent(Auction event) {
		this.event = event;
	}



	public Users getUser() {
		return users;
	}

	public void setUser(Users users) {
		this.users = users;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
