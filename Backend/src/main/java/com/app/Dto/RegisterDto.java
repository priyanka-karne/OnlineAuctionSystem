package com.app.Dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterDto {
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

	
	@NotBlank(message = "GEnder must be Neede")
	private String gender;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}
	

}
