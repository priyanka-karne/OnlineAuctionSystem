package com.app.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.ToString;

@Entity
@Table(name="session")

@ToString

public class Auction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	 
	@JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate date ;
     
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endtTime;
    
    private Double startingAmount;
    
    
  

@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Users> users;

    @OneToMany(mappedBy = "event")
	private List<BidInformation> biders;	
    @OneToOne(mappedBy = "auction")
	private Product product;

	public Auction() {
		super();
	}

	
	public Auction(long id, LocalDate date, LocalDateTime startTime, LocalDateTime endtTime, Double startingAmount,
			 List<BidInformation> biders, Product product) {
		super();
		this.id = id;
		this.date = date;
		this.startTime = startTime;
		this.endtTime = endtTime;
		this.startingAmount = startingAmount;
//		this.users = users;
		this.biders = biders;
		this.product = product;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Set<Users> getUsers() {
		return users;
	}


	public void setUsers(Set<Users> users) {
		this.users = users;
	}

	public List<BidInformation> getBiders() {
		return biders;
	}

	public void setBiders(List<BidInformation> biders) {
		this.biders = biders;
	}


	

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndtTime() {
		return endtTime;
	}

	public void setEndtTime(LocalDateTime endtTime) {
		this.endtTime = endtTime;
	}

	public Double getStartingAmount() {
		return startingAmount;
	}

	public void setStartingAmount(Double startingAmount) {
		this.startingAmount = startingAmount;
	}

	

	public List<BidInformation> getBiddings() {
		return biders;
	}

	public void setBiddings(List<BidInformation> biddings) {
		this.biders = biddings;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
    

}
