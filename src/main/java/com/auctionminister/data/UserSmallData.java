/*
 * Copyright (c) 2013 Wayne Gray All rights reserved
 * 
 * This file is part of Auction Minister.
 * 
 * Auction Minister is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Auction Minister is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Auction Minister.  If not, see <http://www.gnu.org/licenses/>.
*/
package com.auctionminister.data;

import java.io.Serializable;

/**
 * @author wggray
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UserSmallData implements Serializable {

	private static final long serialVersionUID = 6073677070800001150L;
	private long lUserID = -1;
	private String sUserName = "";
	private String sFirstName = "";
	private String sLastName = "";
	private String refreshToken = "";
	
	public UserSmallData() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return Returns the lUserID.
	 */
	public long getUserId() {
		return lUserID;
	}
	/**
	 * @param userID The lUserID to set.
	 */
	public void setUserId(long userID) {
		lUserID = userID;
	}
	/**
	 * @return Returns the sFirstName.
	 */
	public String getFirstName() {
		return sFirstName;
	}
	/**
	 * @param firstName The sFirstName to set.
	 */
	public void setFirstName(String firstName) {
		sFirstName = firstName;
	}
	/**
	 * @return Returns the sLastName.
	 */
	public String getLastName() {
		return sLastName;
	}
	/**
	 * @param lastName The sLastName to set.
	 */
	public void setLastName(String lastName) {
		sLastName = lastName;
	}
	/**
	 * @return Returns the sUserName.
	 */
	public String getUserName() {
		return sUserName;
	}
	/**
	 * @param userName The sUserName to set.
	 */
	public void setUserName(String userName) {
		sUserName = userName;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}
