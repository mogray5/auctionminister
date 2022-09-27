package com.auctionminister.params;

import java.io.Serializable;

/**
 * Params for saving ESI refresh token
 */
public class UploadKeyParams implements Serializable {

	private static final long serialVersionUID = 2455748740397904431L;

	private long userId = 0;
	private String refreshToken;
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}
