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
import com.auctionminister.data.UserSmallData;
import com.auctionminister.params.SearchParams;
import com.auctionminister.providers.SQLMapProvider;

/**
 * @author wggray
 */
public class SaCustomerLookupAction extends BaseAction {

	private static final long serialVersionUID = -7545581160388574939L;
	private String isSecure = "S";
	private List custList = null;
	
	private String customerSearch = null;
	private String caller = "";
	
	public SaCustomerLookupAction() {
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
									
			if (customerSearch==null || customerSearch.length()==0){
			
				custList = session.selectList("GetCustomerList", oUserID);
				
			} else {
				SearchParams params = new SearchParams();
				params.setUserId(oUserID.getUserId());
				params.setSearchVal(customerSearch + "%");
				custList = session.selectList("GetCustomerListByName", params);
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
	 * @return Returns the custList.
	 */
	public List getCustList() {
		return custList;
	}
	/**
	 * @param custList The custList to set.
	 */
	public void setCustList(List custList) {
		this.custList = custList;
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
	 * @return Returns the customerSearch.
	 */
	public String getCustomerSearch() {
		return customerSearch;
	}
	/**
	 * @param customerSearch The customerSearch to set.
	 */
	public void setCustomerSearch(String customerSearch) {
		this.customerSearch = customerSearch;
	}
}
