package com.app.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="product")

@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column( nullable = false)
	private double baseAmount;
	@Column(length = 150, nullable = false)
	private String details;
	
	@Lob
	private String imageURL;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "session_id", referencedColumnName = "id")
	private Auction auction;
	
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user", referencedColumnName = "id")
	private Users user;
	public Product() {
		super();
	}

	
	public Users getUser() {
		return user;
	}


	public void setUser(Users user) {
		this.user = user;
	}


	public Product(double baseAmount, String details, String imageURL, Auction auction) {
		super();
		
		this.baseAmount = baseAmount;
		this.details = details;
		this.imageURL = imageURL;
		this.auction = auction;
	}


	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Auction getAuction() {
		return auction;
	}


	public void setAuction(Auction auction) {
		this.auction = auction;
	}


	public double getBaseAmount() {
		return baseAmount;
	}

	public void setBaseAmount(double baseAmount) {
		this.baseAmount = baseAmount;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

}
