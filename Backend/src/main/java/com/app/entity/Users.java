package com.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="user")

public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(length = 128, nullable = false,unique=true)
	private String email;
	
	@Column(length = 128, nullable = false)
	private String password;
	
	@Column(name="firstName" ,length = 45, nullable = false)
	private String firstName;
	
	@Column(name="lastName" ,length = 45, nullable = false)
	private String lastName;
	
	@Column(name="address",length=128,nullable = false)
	private String address;
	
     @Column(name="contact")
	private String contact;
	
	@ManyToOne
    @JoinColumn(name="session_id")
    private Auction user;
	

	@OneToOne(mappedBy = "user")
	private Product product;
	

	public Users() {
		super();
	}

	


	public Users(long id, String email, String password, String firstName, String lastName, String address,
			String contact) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.contact = contact;

	
	}




	public long getId() {
		return id;
	}




	public void setId(long id) {
		this.id = id;
	}




	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getContact() {
		return contact;
	}


	public void setContact(String contact) {
		this.contact = contact;
	}
}
