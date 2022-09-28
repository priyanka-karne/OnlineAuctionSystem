package com.app.Dto;

import java.sql.Timestamp;

import com.app.entity.BidInformation;
import com.app.entity.Users;

public class AuctionMessageDto<T> {
private T content;

	
	private AuctionMessageTypeEnum messageType;
	
	private long userId;
	
	private Timestamp messageTime;

	

	

	public T getContent() {
		return content;
	}






	public void setContent(T content) {
		this.content = content;
	}






	public long getUserId() {
		return userId;
	}






	public void setUserId(long userId) {
		this.userId = userId;
	}






	public AuctionMessageDto(T content, AuctionMessageTypeEnum messageType, long userId, Timestamp messageTime) {
		super();
		this.content = content;
		this.messageType = messageType;
		this.userId = userId;
		this.messageTime = messageTime;
	}






	public AuctionMessageDto() {
		super();
	}






	public Timestamp getMessageTime() {
		return messageTime;
	}
	
	public void setMessageTime(Timestamp messageTime) {
		this.messageTime = messageTime;
	}

	public AuctionMessageTypeEnum getMessageType() {
		return messageType;
	}

	public void setMessageType(AuctionMessageTypeEnum messageType) {
		this.messageType = messageType;
	}

	@Override
	public String toString() {
		return "AuctionMessageDto [content=" + content + ", messageType=" + messageType + ", userId=" + userId
				+ ", messageTime=" + messageTime + "]";
	}
}
