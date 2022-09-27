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
package com.auctionminister.action.inventory;

import java.util.List;

import com.auctionminister.action.system.BaseAction;
import com.auctionminister.data.UserSmallData;

/**
 * @author wggray
 */
public class ItemClassLookupAction extends BaseAction {


	private static final long serialVersionUID = -3933291017070775550L;
	private String sIsSecure = "S";
	private List itemClassList = null;
	private String sStatus = null;
	
	public ItemClassLookupAction() {
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
				itemClassList = session.selectList("GetItemClassList", oUserID);
				
				
			} catch (Exception e ) {
				addActionError(e.toString());
				return INPUT;
			} finally {
				this.endSession();
			}
			
			return SUCCESS;
	}

	/**
	 * @return Returns the itemClassList.
	 */
	public List getItemClassList() {
		return itemClassList;
	}
	/**
	 * @param itemClassList The itemClassList to set.
	 */
	public void setItemClassList(List itemClassList) {
		this.itemClassList = itemClassList;
	}
	
	
	/**
	 * @return Returns the sStatus.
	 */
	public String getStatus() {
		return sStatus;
	}
	/**
	 * @param status The sStatus to set.
	 */
	public void setStatus(String status) {
		sStatus = status;
	}
}
