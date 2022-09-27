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
package com.auctionminister.action.sales;

import com.auctionminister.action.system.BaseAction;
import com.auctionminister.data.CustomerData;
import com.auctionminister.data.UserSmallData;
import com.auctionminister.providers.SQLMapProvider;

/**
 * @author wggray
 */
public class SaEditCustomerAction extends BaseAction {

	private static final long serialVersionUID = 8171932523249434070L;
	private String isSecure = "S";
	
	private long customerId = 0;
	private String custName="";
	private String custEmail = "";
	private String contactPhone = "";
	private String shippingAddress="";
	
	/**
	 * 
	 */
	public SaEditCustomerAction() {
		super();
	}

	/**
	 * @return Returns the isSecure.
	 */
	public String getIsSecure() {
		return isSecure;
	}
	/**
	 * @param isSecure The isSecure to set.
	 */
	public void setIsSecure(String isSecure) {
		this.isSecure = isSecure;
	}
	
	public String execute() throws Exception {
		
		this.startSession();
		
		try {	
			
				UserSmallData oUserID = this.verifyLogin();
				
				CustomerData customer = new CustomerData();
				
				customer.setContactPhone(contactPhone);
				customer.setCustEmail(custEmail);
				customer.setCustName(custName);
				customer.setShippingAddress(shippingAddress);
				customer.setUserId(oUserID.getUserId());
				
				if (customerId > 0) {
					customer.setCustomerId(customerId);
					session.update("EditCustomer", customer);
				} else {
					session.update("AddCustomer", customer);
				}
				session.commit();
				
		} catch (Exception e ) {
			session.rollback();
			addActionError(e.toString());
			return ERROR;
		} finally {
			this.endSession();
		}
		
		return SUCCESS;
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
		return shippingAddress;
	}
	/**
	 * @param shippingAddress The shippingAddress to set.
	 */
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
}
