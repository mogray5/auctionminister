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

import java.util.List;

import com.auctionminister.action.system.BaseAction;
import com.auctionminister.data.CustomerData;
import com.auctionminister.data.UserSmallData;
import com.auctionminister.providers.SQLMapProvider;

/**
 * @author wggray
 */
public class SaLoadSaleItemsAction extends BaseAction {

	private static final long serialVersionUID = -2453250593956929642L;
	private String isSecure = "S";
	
	private List saleItemList = null;
	private long customerId = 0;
	private String caller = "";

	public SaLoadSaleItemsAction() {
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
				customer.setUserId(oUserID.getUserId());
				customer.setCustomerId(customerId);
				
				if (customerId>0){
					saleItemList = session.selectList("GetClosedSoLinesByCustomer", customer);
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
	 * @return Returns the caller.
	 */
	public String getCaller() {
		return caller;
	}
	/**
	 * @param caller The caller to set.
	 */
	public void setCaller(String caller) {
		this.caller = caller;
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
	 * @return Returns the saleItemList.
	 */
	public List getSaleItemList() {
		return saleItemList;
	}
	/**
	 * @param saleItemList The saleItemList to set.
	 */
	public void setSaleItemList(List saleItemList) {
		this.saleItemList = saleItemList;
	}
}
