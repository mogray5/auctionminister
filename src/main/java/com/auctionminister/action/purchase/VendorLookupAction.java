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
package com.auctionminister.action.purchase;

import java.util.List;

import com.auctionminister.action.system.BaseAction;
import com.auctionminister.data.UserSmallData;
import com.auctionminister.params.SearchParams;

/**
 * @author wggray
 */
public class VendorLookupAction extends BaseAction {

	private static final long serialVersionUID = -6679849438644444571L;
	private String sIsSecure = "S";
	private List lstItems = null;
	private String vendorSearch = "";
	private String caller="";
	
	public VendorLookupAction() {
		super();
	}

	/**
	 * @return Returns the sIsSecure.
	 */
	public String getIsSecure() {
		return sIsSecure;
	}
	/**
	 * @param isSecure The sIsSecure to set.
	 */
	public void setIsSecure(String isSecure) {
		sIsSecure = isSecure;
	}
	
	public String execute() throws Exception {
		
		this.startSession();
		
		try {	
				UserSmallData oUserID = this.verifyLogin();

				if (vendorSearch==null || vendorSearch.length()==0){

					lstItems = session.selectList("GetVendorList", oUserID);
				} else {
					
					SearchParams params = new SearchParams();
					params.setUserId(oUserID.getUserId());
					params.setSearchVal(vendorSearch + "%");
					lstItems = session.selectList("GetVendorListByName", params);
				}
				
				
			} catch (Exception e ) {
				addActionError(e.toString());
				return ERROR;
			} finally {
				this.endSession();
			}
			
			return SUCCESS;
	}
	
	public List getVendorList(){
		return lstItems;
	}
	
	
	/**
	 * @return Returns the lstItems.
	 */
	public List getLstItems() {
		return lstItems;
	}
	/**
	 * @param lstItems The lstItems to set.
	 */
	public void setLstItems(List lstItems) {
		this.lstItems = lstItems;
	}
	/**
	 * @return Returns the vendorSearch.
	 */
	public String getVendorSearch() {
		return vendorSearch;
	}
	/**
	 * @param vendorSearch The vendorSearch to set.
	 */
	public void setVendorSearch(String vendorSearch) {
		this.vendorSearch = vendorSearch;
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
}
