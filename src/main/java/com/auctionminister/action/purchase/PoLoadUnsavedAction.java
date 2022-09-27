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
import com.auctionminister.data.ItemData;
import com.auctionminister.data.PoHeaderData;
import com.auctionminister.data.UserSmallData;

/**
 * @author wggray
 */
public class PoLoadUnsavedAction extends BaseAction {

	private static final long serialVersionUID = -1241425226998027029L;
	private String itemId = "";
	private String isSecure = "S";
	private PoHeaderData poHeader = null;
	private List poLines = null;
	private List vendorList = null;
	
	/**
	 * 
	 */
	public PoLoadUnsavedAction() {
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
			PoHeaderData search = new PoHeaderData();
			search.setUserId(oUserID.getUserId());
			search.setSaved(0);
				
			poHeader = (PoHeaderData) session.selectOne("GetOpenPoHeaderListByStatus", search);
			
			if (poHeader != null){
				//load lines
				poHeader.setUserId(oUserID.getUserId());
				poLines = session.selectList("GetOpenPoLineList", poHeader);
			}
				
			vendorList = session.selectList("GetVendorListSmall", oUserID);
				
		} catch (Exception e ) {
			addActionError(e.toString());
			return ERROR;
		} finally {
			this.endSession();
		}
		
		return SUCCESS;
	}

	
	
	/**
	 * @return Returns the poLines.
	 */
	public List getPoLines() {
		return poLines;
	}
	/**
	 * @param poLines The poLines to set.
	 */
	public void setPoLines(List poLines) {
		this.poLines = poLines;
	}
	
	/**
	 * @return Returns the poHeader.
	 */
	public PoHeaderData getPoHeader() {
		return poHeader;
	}
	/**
	 * @param poHeader The poHeader to set.
	 */
	public void setPoHeader(PoHeaderData poHeader) {
		this.poHeader = poHeader;
	}
	
	/**
	 * @return Returns the vendorList.
	 */
	public List getVendorList() {
		return vendorList;
	}
	/**
	 * @param vendorList The vendorList to set.
	 */
	public void setVendorList(List vendorList) {
		this.vendorList = vendorList;
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
