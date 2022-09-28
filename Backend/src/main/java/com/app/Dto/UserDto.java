package com.app.Dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.app.entity.Auction;
import com.fasterxml.jackson.annotation.JsonProperty;


public class UserDto {
	@JsonProperty("id")
	private Integer id;
	@NotEmpty(message = "First name must be supplied")
	@Length(min = 4, max = 30, message = "Invalid First name length")

	private String firstName;

	@NotBlank(message = "Last name must be supplied")
	private String lastName;

	@NotBlank
	@Email(message = "Invalid Email")
	private String email;
	
	private String address;
	
	private String password;
	
	private String contact;
	
	
	


	/*
	 * public UserDto(
	 * 
	 * @NotEmpty(message = "First name must be supplied") @Length(min = 4, max = 30,
	 * message = "Invalid First name length") String firstName,
	 * 
	 * @NotBlank(message = "Last name must be supplied") String lastName,
	 * 
	 * @NotBlank @Email(message = "Invalid Email") String email,
	 * 
	 * @NotBlank(message = "GEnder must be Neede") String gender, String address,
	 * String password, String contact) { super(); this.firstName = firstName;
	 * this.lastName = lastName; this.email = email; this.gender = gender;
	 * this.address = address; this.password = password; this.contact = contact; }
	 */

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String name) {
		this.firstName = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}
