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
import com.auctionminister.data.SaHeaderData;
import com.auctionminister.data.UserSmallData;

/**
 * @author wggray
 */
public class SaLoadUnsavedAction extends BaseAction {

	private static final long serialVersionUID = 876036661012891349L;
	private String itemId = "";
	private String isSecure = "S";
	private SaHeaderData soHeader = null;
	private List soLines = null;
	private List customerList = null;
	
	public SaLoadUnsavedAction() {
		super();
	}

	public String execute() throws Exception {
		
		this.startSession();
		
		try {	
									
			UserSmallData oUserID = this.verifyLogin();
						
			SaHeaderData search = new SaHeaderData();
			search.setUserId(oUserID.getUserId());
			search.setSaved(0);
				
			soHeader = (SaHeaderData) session.selectOne("GetOpenSoHeaderListByStatus", search);
			
			if (soHeader != null){
				//load lines
				soLines = session.selectList("GetOpenSoLineList", soHeader);
			}
				
			customerList = session.selectList("GetCustomerListSmall", oUserID);
				
		} catch (Exception e ) {
			addActionError(e.toString());
			return ERROR;
		} finally {
			this.endSession();
		}
		
		return SUCCESS;
	}

	/**
	 * @return Returns the customerList.
	 */
	public List getCustomerList() {
		return customerList;
	}
	/**
	 * @param customerList The customerList to set.
	 */
	public void setCustomerList(List customerList) {
		this.customerList = customerList;
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
	/**
	 * @return Returns the saHeader.
	 */
	public SaHeaderData getSoHeader() {
		return soHeader;
	}
	/**
	 * @param saHeader The saHeader to set.
	 */
	public void setSoHeader(SaHeaderData saHeader) {
		this.soHeader = saHeader;
	}
	/**
	 * @return Returns the saLines.
	 */
	public List getSoLines() {
		return soLines;
	}
	/**
	 * @param saLines The saLines to set.
	 */
	public void setSoLines(List saLines) {
		this.soLines = saLines;
	}
	
	
	/**
	 * @return Returns the itemId.
	 */
	public String getItemId() {
		return itemId;
	}
	/**
	 * @param itemId The itemId to set.
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
}
