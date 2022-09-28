package com.app.serviceimpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.Auction;
import com.app.entity.BidInformation;
import com.app.entity.Product;
import com.app.entity.Users;
import com.app.repository.AuctionRepository;
import com.app.service.IAuctionService;
@Service
public class AuctionServiceImpl implements IAuctionService {
	@Autowired
	private AuctionRepository arepo;
	

	@Override
	public Auction getById(long id) {
		
		return null;
	}

	@Override
	public List<Auction> getAllActive() {
		List<Auction> a= arepo.findAll();
		return a;
	}

	@Override
	public Auction addNewAuction(Auction auction) {
	
		auction.setUsers(new java.util.HashSet<Users>());
		auction.setBiddings(new java.util.ArrayList<BidInformation>());
		return arepo.save(auction);
	}

	@Override
	public Auction updateAuction(long id, Auction auction) {
		if(arepo.existsById(id))
			return arepo.save(auction);
		return null;
	}

	@Override
	public String deleteAuction(long id) {
		if(arepo.existsById(id)) {
			Auction u = arepo.getById(id);
			arepo.deleteById(id);
			return "Delete Auction";
		}
		return "Auction not active";
	}

	@Override
	public Auction getByProduct(Product product) {
		Auction u = arepo.findByProduct(product);
		if(u != null)
			return u;
		return null;
	}

}
