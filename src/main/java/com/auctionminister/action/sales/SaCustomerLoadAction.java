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
import com.auctionminister.data.ItemData;
import com.auctionminister.data.UserSmallData;
import com.auctionminister.providers.SQLMapProvider;

public class SaCustomerLoadAction extends BaseAction {

	private static final long serialVersionUID = -941888257409139997L;
	private String isSecure = "S";
	
	private long customerId =0;
	private CustomerData customer = null;
	
	public SaCustomerLoadAction() {
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
				
				CustomerData search = new CustomerData();
				search.setUserId(oUserID.getUserId());
				search.setCustomerId(customerId);
				
				customer = (CustomerData)session.selectOne("GetCustomer", search);
				
				if (customer==null){
					customer = new CustomerData();
				}
				
				
		} catch (Exception e ) {
			addActionError(e.toString());
			return ERROR;
		} finally {
			this.endSession();
		}
		
		return SUCCESS;
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
	 * @return Returns the customer.
	 */
	public CustomerData getCustomer() {
		return customer;
	}
	/**
	 * @param customer The customer to set.
	 */
	public void setCustomer(CustomerData customer) {
		this.customer = customer;
	}
}
