package com.app.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Dto.AuctionBidRequestDto;
import com.app.Dto.AuctionDetailsRequestDto;
import com.app.Dto.AuctionJoinRequestDto;
import com.app.Dto.AuctionMessageDto;
import com.app.Dto.AuctionMessageTypeEnum;
import com.app.Dto.Response;
import com.app.Dto.ResponseTypeError;
import com.app.entity.Auction;
import com.app.entity.BidInformation;
import com.app.entity.Users;
import com.app.serviceimpl.RealtimeAuctionHandlerServiceImpl;

@RestController
@CrossOrigin
@Transactional
@RequestMapping("/app")
public class RealTimeBiddingController {
	@Autowired
	private SimpMessagingTemplate template;
	
	@Autowired
	private RealtimeAuctionHandlerServiceImpl auctionHandlerService;
	

	@MessageMapping("/rt-auction/join/{productId}")
	@SendTo("/rt-product/auction-updates/{productId}")
	@SuppressWarnings("unchecked")
	public AuctionMessageDto<?> joinAuction(@DestinationVariable("productId")long productId,AuctionJoinRequestDto auctionJoinRequest) {
		try {
			Response<Users> response = (Response<Users>)auctionHandlerService.joinAuction(productId, auctionJoinRequest);
			if(response.getStatus() == ResponseTypeError.SUCCESS)
				return new AuctionMessageDto<Users>(response.getData(),AuctionMessageTypeEnum.NEW_USER_JOINED,auctionJoinRequest.getUserId(),new Timestamp(System.currentTimeMillis()));
			return new AuctionMessageDto<String>(response.getData().toString(),AuctionMessageTypeEnum.AUCTION_ERROR,auctionJoinRequest.getUserId(),new Timestamp(System.currentTimeMillis()));
		} catch (Exception e) {
			e.printStackTrace();
			return new AuctionMessageDto<String>(e.getMessage(),AuctionMessageTypeEnum.AUCTION_ERROR,auctionJoinRequest.getUserId(),new Timestamp(System.currentTimeMillis()));
		}
	}

	@MessageMapping("/rt-auction/bid/{productId}")
	@SendTo("/rt-product/auction-updates/{productId}")
	@SuppressWarnings("unchecked")
	public AuctionMessageDto<?> bid(@DestinationVariable("productId")long productId,AuctionBidRequestDto auctionBidRequest){
		try {
			Response<BidInformation> response = (Response<BidInformation>) auctionHandlerService.addBid(productId, auctionBidRequest);
			if(response.getStatus() == ResponseTypeError.SUCCESS)
				return new AuctionMessageDto<BidInformation>(response.getData(),AuctionMessageTypeEnum.BID_MADE,auctionBidRequest.getUserId(),new Timestamp(System.currentTimeMillis()));
			return new AuctionMessageDto<String>(response.getData().toString(),AuctionMessageTypeEnum.AUCTION_ERROR,auctionBidRequest.getUserId(),new Timestamp(System.currentTimeMillis()));
		} catch (Exception e) {
			e.printStackTrace();
			return new AuctionMessageDto<String>(e.getMessage(),AuctionMessageTypeEnum.AUCTION_ERROR,auctionBidRequest.getUserId(),new Timestamp(System.currentTimeMillis()));
		}
	}
	
	
	

	@MessageMapping("/rt-auction/getDetails/{productId}")
	@SendTo("/rt-product/auction-updates/{productId}")
	@SuppressWarnings("unchecked")
	public AuctionMessageDto<?> getDetails(@DestinationVariable("productId")long productId,AuctionDetailsRequestDto auctionDetailsRequest){
		try {
			Response<Auction> response = (Response<Auction>) auctionHandlerService.getAuctionDetails(productId);
			if(response.getStatus() == ResponseTypeError.SUCCESS)
				return new AuctionMessageDto<Auction>(response.getData(),AuctionMessageTypeEnum.DETAILS_SHARED,auctionDetailsRequest.getUserId(),new Timestamp(System.currentTimeMillis()));
			return new AuctionMessageDto<String>("Some error occurred",AuctionMessageTypeEnum.AUCTION_ERROR,auctionDetailsRequest.getUserId(),new Timestamp(System.currentTimeMillis()));
		} catch (Exception e) {
			e.printStackTrace();
			return new AuctionMessageDto<String>(e.getMessage(),AuctionMessageTypeEnum.AUCTION_ERROR,auctionDetailsRequest.getUserId(),new Timestamp(System.currentTimeMillis()));
		}
	}
	

//	@Scheduled(fixedRate = 1000)
	public void sendCurrentTime() {
		try {
		 List<Auction> auctions = auctionHandlerService.getAllAuctions();
		 AuctionMessageDto<String> dto = new AuctionMessageDto<String>();
		 dto.setContent(null);
		 dto.setMessageTime(new Timestamp(System.currentTimeMillis()));
		 dto.setMessageType(AuctionMessageTypeEnum.TIME_UPDATES);
		 for(Auction auction : auctions)
			 this.template.convertAndSend("/rt-product/auction-updates/"+auction.getProduct().getId(),dto);
		}
		catch(Exception e) {
			 e.printStackTrace();
		}
	}
	
	@Scheduled(fixedRate = 1000)
	public void checkAndSendBidStartNotification() {
		try {
			 List<Auction> auctions = auctionHandlerService.getAllAuctions();
			 AuctionMessageDto<String> dto = new AuctionMessageDto<String>();
			 dto.setContent(null);
			 dto.setMessageTime(new Timestamp(System.currentTimeMillis()));
			 dto.setMessageType(AuctionMessageTypeEnum.AUCTION_STARTED);
			 for(Auction auction : auctions) {
				 if(auction.getStartTime().getSecond() == System.currentTimeMillis())
					 this.template.convertAndSend("/rt-product/auction-updates/"+auction.getProduct().getId(),dto);
			 }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Scheduled(fixedRate = 1000)
	public void checkAndSendBidEndNotification() {
		try {
			 List<Auction> auctions = auctionHandlerService.getAllAuctions();
			 AuctionMessageDto<String> dto = new AuctionMessageDto<String>();
			 dto.setContent(null);
			 dto.setMessageTime(new Timestamp(System.currentTimeMillis()));
			 dto.setMessageType(AuctionMessageTypeEnum.AUCTION_ENDED);
			 for(Auction auction : auctions) {
				 if(auction.getEndtTime().getSecond() == System.currentTimeMillis()) {
					auctionHandlerService.markAuctionAsCompleted(auction.getProduct().getId());
				 	this.template.convertAndSend("/rt-product/auction-updates/"+auction.getProduct().getId(),dto);
				 	
				 }
			 }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	

}