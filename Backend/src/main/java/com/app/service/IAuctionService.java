package com.app.service;

import java.util.List;

import com.app.entity.Auction;
import com.app.entity.Product;

public interface IAuctionService {
Auction getById(long id);
	
	public List<Auction> getAllActive() ;
	
public	Auction addNewAuction(Auction auction);
	
public	Auction updateAuction(long id,Auction auction);
	
public	String deleteAuction(long id); 

public	Auction getByProduct(Product product);

}
