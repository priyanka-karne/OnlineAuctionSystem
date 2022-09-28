package com.app.service;

import java.util.List;

import com.app.Dto.AuctionBidRequestDto;
import com.app.Dto.AuctionJoinRequestDto;
import com.app.Dto.Response;
import com.app.entity.Auction;
import com.app.entity.BidInformation;

public interface IRealTimeAuctionHandlerService {
Response<?> joinAuction(long productId,AuctionJoinRequestDto auctionJoinRequest);
	
	Response<?> addBid(long productId,AuctionBidRequestDto auctionBidRequest);
	
	List<Auction> getAllAuctions();
	
	BidInformation getAuctionWinner(long productId);
	
	Response<?> getAuctionDetails(long productId);
	
	void markAuctionAsCompleted(long productId);
}
