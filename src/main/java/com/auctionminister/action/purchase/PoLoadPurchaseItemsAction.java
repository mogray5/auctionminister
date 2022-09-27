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
import com.auctionminister.data.VendorData;

/**
 * @author wggray
 */
public class PoLoadPurchaseItemsAction extends BaseAction {

	private static final long serialVersionUID = -1308879938760722728L;
	private String isSecure = "S";
	
	private List vendorItemList = null;
	private int vendorId = 0;
	private String caller = "";
	
	public PoLoadPurchaseItemsAction() {
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
						
				if (oUserID == null){
					addActionError("Login could not be verified.");
					return ERROR;
				}
				
				VendorData vendor = new VendorData();
				vendor.setUserId(oUserID.getUserId());
				vendor.setVendorId(vendorId);
				
				if (vendorId>0){
					vendorItemList = session.selectList("GetClosedPoLinesByVendor", vendor);
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
	 * @return Returns the vendorItemList.
	 */
	public List getVendorItemList() {
		return vendorItemList;
	}
	/**
	 * @param vendorItemList The vendorItemList to set.
	 */
	public void setVendorItemList(List vendorItemList) {
		this.vendorItemList = vendorItemList;
	}
}
