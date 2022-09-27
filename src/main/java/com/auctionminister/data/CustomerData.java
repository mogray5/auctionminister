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
public class CustomerData implements Serializable {

	private long userId = 0;
	private long customerId = 0;
	private String custName = "";
	private String custEmail = "";
	private String contactPhone = "";
	private String ShippingAddress = "";
	private String buyerId = "";
	
	/**
	 * 
	 */
	public CustomerData() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return Returns the contactPhone.
	 */
	public String getContactPhone() {
		return contactPhone;
	}
	/**
	 * @param contactPhone The contactPhone to set.
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	/**
	 * @return Returns the custEmail.
	 */
	public String getCustEmail() {
		return custEmail;
	}
	/**
	 * @param custEmail The custEmail to set.
	 */
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
	/**
	 * @return Returns the custName.
	 */
	public String getCustName() {
		return custName;
	}
	/**
	 * @param custName The custName to set.
	 */
	public void setCustName(String custName) {
		this.custName = custName;
	}
	/**
	 * @return Returns the customerId.
	 */
	public long getCustomerId() {
		return customerId;
	}
	/**
	 * @param customerId The customerId to set.
	 */
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	/**
	 * @return Returns the shippingAddress.
	 */
	public String getShippingAddress() {
		return ShippingAddress;
	}
	/**
	 * @param shippingAddress The shippingAddress to set.
	 */
	public void setShippingAddress(String shippingAddress) {
		ShippingAddress = shippingAddress;
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
	
	
	/**
	 * @return Returns the buyerId.
	 */
	public String getBuyerId() {
		return buyerId;
	}
	/**
	 * @param buyerId The buyerId to set.
	 */
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
}
