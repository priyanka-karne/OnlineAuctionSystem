package com.app.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.Dto.AuctionBidRequestDto;
import com.app.Dto.AuctionJoinRequestDto;
import com.app.Dto.Response;
import com.app.Dto.ResponseTypeError;
import com.app.Dto.UserDto;
import com.app.entity.Auction;
import com.app.entity.BidInformation;
import com.app.entity.Product;
import com.app.entity.Users;
import com.app.service.IRealTimeAuctionHandlerService;
@Service
public class RealtimeAuctionHandlerServiceImpl implements IRealTimeAuctionHandlerService {

	@Autowired
	private ProductServiceImpl proservice;
	@Autowired
	private UserService userservice;
	@Autowired
	private AuctionServiceImpl auctionservice;
	
	private HashMap<Long,Auction> auctionsMap = new HashMap<Long,Auction>();
	
	
	@Override
	public Response<?> joinAuction(long productId, AuctionJoinRequestDto auctionJoinRequest) {
		checkForAuctionExistence(productId);
		UserDto user = userservice.getUserById(auctionJoinRequest.getUserId());
		if(user != null) {
			Auction auction = auctionsMap.get(productId);
			if(auction.getUsers() == null)
				auction.setUsers(new java.util.HashSet<Users>());
			if(auction.getBiddings() == null)
				auction.setBiddings(new java.util.ArrayList<BidInformation>());
			if(auction.getUsers().contains(user))
				return new Response (ResponseTypeError.SUCCESS,user);
			return new Response<String>(ResponseTypeError.ERROR,"User already added as auction participant");
		}
		return new Response<String>(ResponseTypeError.ERROR,"No such user exists");
	}

	@Override
	public Response<?> addBid(long productId, AuctionBidRequestDto auctionBidRequest) {
		checkForAuctionExistence(productId);
		Auction auction = auctionsMap.get(productId);
		if(auction.getUsers() == null)
			auction.setUsers(new java.util.HashSet<Users>());
		if(auction.getBiddings() == null)
			auction.setBiddings(new java.util.ArrayList<BidInformation>());
		UserDto user = userservice.getUserById(auctionBidRequest.getUserId());
		if(user == null)
			return new Response<String>(ResponseTypeError.ERROR,"No such user exists");
		if(auction.getUsers().contains(user)){
			BidInformation bidInformation = new BidInformation();
			bidInformation.setAmount(auctionBidRequest.getBidAmount());
//			bidInformation.setUser(user);
			if(!auction.getBiddings().contains(bidInformation)) {
				Double maxBidding = getMaxBidding(auction.getBiddings());
				if(maxBidding >= auctionBidRequest.getBidAmount())
					return new Response<String>(ResponseTypeError.ERROR,"Bid has to be greater than max bid");
				auction.getBiddings().add(bidInformation);
				return new Response<BidInformation>(ResponseTypeError.SUCCESS,bidInformation);
			}
			return new Response<String>(ResponseTypeError.ERROR,"User Bid Already Registered");
		}
		return new Response<String>(ResponseTypeError.ERROR,"No such user registered for auction");
	}

	@Override
	public List<Auction> getAllAuctions() {
		List<Auction> auctions = new ArrayList<Auction>();
		if(auctionsMap.size() == 0) {
			auctions = auctionservice.getAllActive();
			for(Auction auction : auctions)
				auctionsMap.put(auction.getProduct().getId(),auction);
		}
		for(Map.Entry<Long, Auction> entry : auctionsMap.entrySet()) 
			auctions.add(entry.getValue());
		return auctions;
	}

	@Override
	public BidInformation getAuctionWinner(long productId) {
		Product product = proservice.getProductById(productId);
		if(product != null) {
			Auction auction = auctionservice.getByProduct(product);
			if(auction.getUsers() == null)
				auction.setUsers(new java.util.HashSet<Users>());
			if(auction.getBiddings() == null)
				auction.setBiddings(new java.util.ArrayList<BidInformation>());
			List<BidInformation> list = auction.getBiddings();
			BidInformation maxBid = list.get(0);
			for(BidInformation bid : list){
				if(bid.getAmount() > maxBid.getAmount())
					maxBid = bid;
			}
			return maxBid;
		}
		return null;
	}

	@Override
	public Response<?> getAuctionDetails(long productId) {
		checkForAuctionExistence(productId);
		return new Response<Auction>(ResponseTypeError.SUCCESS,auctionsMap.get(productId));
	
	}

	@Override
	public void markAuctionAsCompleted(long productId) {
		checkForAuctionExistence(productId);
		Auction auction = auctionsMap.get(productId);
		if(auction.getUsers() == null)
			auction.setUsers(new java.util.HashSet<Users>());
		if(auction.getBiddings() == null)
			auction.setBiddings(new java.util.ArrayList<BidInformation>());
		auctionservice.updateAuction(auction.getId(), auction);
		auctionsMap.remove(productId);
	}
	
	private Double getMaxBidding(List<BidInformation> bidding) {
		Double max = Double.MIN_VALUE;
		for(BidInformation bid : bidding) 
			max = Double.max(bid.getAmount(),max);
		return max;
	}
	
	private void checkForAuctionExistence(long productId){
		if(auctionsMap.containsKey(productId))
			return;
		Product product = proservice.getProductById(productId);
		if(product != null)
			auctionsMap.put(productId,auctionservice.getByProduct(product));
	}


	
}
