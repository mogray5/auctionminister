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

/**
 * @author wggray
 */
public class SaBatchLookupAction extends BaseAction {

	private static final long serialVersionUID = 7498475263335807762L;
	private String isSecure = "S";
	
	private List batchList = null;
	
	public SaBatchLookupAction() {
		super();
	}

	public String execute() throws Exception {
		
		this.startSession();
		
		try {	
				UserSmallData oUserID = this.verifyLogin();
				batchList = session.selectList("GetOpenSoBatchList", Long.valueOf(oUserID.getUserId()));
				
		} catch (Exception e ) {
			addActionError(e.toString());
			return ERROR;
		} finally {
			this.endSession();
		}
		
		return SUCCESS;
	}
	
	

	/**
	 * @return Returns the batchList.
	 */
	public List getBatchList() {
		return batchList;
	}
	/**
	 * @param batchList The batchList to set.
	 */
	public void setBatchList(List batchList) {
		this.batchList = batchList;
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
}
