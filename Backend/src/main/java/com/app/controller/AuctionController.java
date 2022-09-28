package com.app.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Dto.Response;
import com.app.entity.Auction;
import com.app.entity.Product;
import com.app.entity.Users;
import com.app.serviceimpl.AuctionServiceImpl;

@RestController
@CrossOrigin("http://localhost:3000")
@Transactional
@RequestMapping("auction")
public class AuctionController {
	
	@Autowired
	private AuctionServiceImpl aservice;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findAuctionById(@PathVariable("id") long id) {
		Auction result = aservice.getById(id);
		 
		if(result == null)
			return Response.error("Auction not active");
		return Response.success(result);
	}
	
	@PostMapping("/addauction")
	public ResponseEntity<?>  addAuction(@RequestBody Auction auction) 
	{
		System.out.println("Auction insertion  " + auction);
		Auction s=this.aservice.addNewAuction(auction);
		return Response.success(s);
	}
	
	
	@GetMapping
	public ResponseEntity<?> getAllAuction() 
	{
		List<Auction> list=aservice.getAllActive();
		return Response.success(list);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAuction(@PathVariable("id") long auctionid) {
		System.out.println("in get Product ");
		String d=aservice.deleteAuction(auctionid);
		return Response.success(d);
	}
	
	@PutMapping("/updateAuction")
	public ResponseEntity<?> updateAuction(@PathVariable ("id") long id,@RequestBody Auction auction)
	{
		aservice.updateAuction(id, auction);
		return Response.success(auction);
	}
	

}
