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
public class VendorSmallData implements Serializable {

	private int vendorId = 0;
	private String vendorName = null;
	private long userId = 0;
	
	/**
	 * 
	 */
	public VendorSmallData() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return Returns the vendorId.
	 */
	public int getVendorId() {
		return vendorId;
	}
	/**
	 * @param vendorId The vendorId to set.
	 */
	public void setVendorId(int vendorId) {
		this.vendorId = vendorId;
	}
	/**
	 * @return Returns the vendorName.
	 */
	public String getVendorName() {
		return vendorName;
	}
	/**
	 * @param vendorName The vendorName to set.
	 */
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	
	
	/**
	 * @return Returns the userId.
	 */
	public long getUserId() {
		return userId;
	}
	/**
	 * @param userId The userId to set.
	 */
	public void setUserId(long userId) {
		this.userId = userId;
	}
}
