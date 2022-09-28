package com.app.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Dto.*;
import com.app.entity.Product;
import com.app.entity.Users;
import com.app.serviceimpl.*;

@RestController
@CrossOrigin("http://localhost:3000")
@Transactional
@RequestMapping("user")
public class UserController {
	@Autowired
	private UserService uservice;
	
	@GetMapping
	public ResponseEntity<?> getAllUser() throws Exception
	{
		List<Users> list=uservice.getAllUser();
		return Response.success(list);
	}
	
	@PostMapping("/adduser")
	public ResponseEntity<?>  insertUser(@RequestBody Users user) throws Exception
	{
		System.out.println("User insertion  " + user);
		Users s=uservice.addNewUser(user);
		return Response.success(s);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") long id) throws Exception {
		UserDto result = uservice.getUserById(id);
		if(result == null)
			return Response.error("User not found");
		return Response.success(result);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteSeller(@PathVariable("id") long userId) throws Exception {
		System.out.println("in get User ");
		String d=uservice.deleteUser(userId);
		return Response.success(d);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateAuction(@PathVariable ("id") long id,@RequestBody Users users)
	{
		uservice.updateUser(id, users);
		return Response.success(users);
	}
	@PostMapping("/login")
	public ResponseEntity<?> validateUser(@RequestBody LoginDto ldto)
	{
		System.out.println(" In Login Dto");
		Users u=uservice.ValidateUSer(ldto.getEmail(), ldto.getPassword());
		if(u != null)
		{
		return Response.success(u);	
		}
		return Response.error(null);
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> saveUserDetails(@RequestBody UserDto rdto )
	{
		System.out.println(" In Register Dto");
		String r= uservice.saveUserDetail(rdto);
		 return Response.success(r);
		
	}

}
