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
import com.auctionminister.data.ItemData;
import com.auctionminister.data.UserSmallData;
import com.auctionminister.params.SearchParams;

/**
 * @author wggray
 */
public class ItemLoadAction extends BaseAction {

	private static final long serialVersionUID = -1381045415967124578L;
	private String sItemId = "";
	private ItemData oItem = null;
	private String sIsSecure = "S";
	private List itemClassList = null;
	private List vendorList = null;
	private String needClassList = null;
	private String needVendorList = null;
	
	/**
	 * 
	 */
	public ItemLoadAction() {
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
				SearchParams search = new SearchParams();
				search.setUserId(oUserID.getUserId());
				search.setSearchVal(sItemId);
				
				oItem = (ItemData)session.selectOne("GetItem", search);
				
				if (oItem==null){
					oItem= new ItemData();
					oItem.setItemTypeId(1);
				}
				
				itemClassList = session.selectList("GetItemClassList", oUserID);
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
	 * @return Returns the sItemId.
	 */
	public String getItemId() {
		return sItemId;
	}
	/**
	 * @param itemId The sItemId to set.
	 */
	public void setItemId(String itemId) {
		sItemId = itemId;
	}
	
	
	/**
	 * @return Returns the oItem.
	 */
	public ItemData getItem() {
		return oItem;
	}
	/**
	 * @param item The oItem to set.
	 */
	public void setItem(ItemData item) {
		oItem = item;
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
	 * @return Returns the needClassList.
	 */
	public String getNeedClassList() {
		return needClassList;
	}
	
	/**
	 * @param needClassList The needClassList to set.
	 */
	public void setNeedClassList(String needClassList) {
		this.needClassList = needClassList;
	}
	
	/**
	 * @return Returns the needVendorList.
	 */
	public String getNeedVendorList() {
		return needVendorList;
	}
	
	/**
	 * @param needVendorList The needVendorList to set.
	 */
	public void setNeedVendorList(String needVendorList) {
		this.needVendorList = needVendorList;
	}
	
	
}
