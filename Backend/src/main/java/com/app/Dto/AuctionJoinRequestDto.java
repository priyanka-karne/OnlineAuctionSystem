package com.app.Dto;

import java.security.Timestamp;

public class AuctionJoinRequestDto {
private long userId;
	
	private String userName;
	
	private Timestamp joiningTime;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Timestamp getJoiningTime() {
		return joiningTime;
	}

	public void setJoiningTime(Timestamp joiningTime) {
		this.joiningTime = joiningTime;
	}


}
