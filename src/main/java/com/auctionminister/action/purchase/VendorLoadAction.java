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

import com.auctionminister.action.system.BaseAction;
import com.auctionminister.data.UserSmallData;
import com.auctionminister.data.VendorData;
import com.auctionminister.data.VendorSmallData;

/**
 * @author wggray
 */
public class VendorLoadAction extends BaseAction  {

	private static final long serialVersionUID = 302284010472087802L;
	private String sIsSecure = "S";
	private int vendorId = -1;
	private VendorData vendorData = null;
	
	public VendorLoadAction() {
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
				VendorSmallData search = new VendorSmallData();
				search.setUserId(oUserID.getUserId());
				search.setVendorId(vendorId);
				
				vendorData = (VendorData)session.selectOne("GetVendorById", search);
				
				if (vendorData==null){
					vendorData= new VendorData();
					vendorData.setUserId(oUserID.getUserId());
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
	 * @return Returns the vendorData.
	 */
	public VendorData getVendorData() {
		return vendorData;
	}
	/**
	 * @param vendorData The vendorData to set.
	 */
	public void setVendorData(VendorData vendorData) {
		this.vendorData = vendorData;
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
}
