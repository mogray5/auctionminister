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

import com.auctionminister.action.system.BaseAction;
import com.auctionminister.data.ItemClassData;
import com.auctionminister.data.UserSmallData;

/**
 * @author wggray
 */
public class ItemClassLoadAction extends BaseAction {

	private static final long serialVersionUID = 5106584920551168514L;
	private String sIsSecure = "S";
	private long classId = -1;
	private ItemClassData itemClassData = null;
	
	public ItemClassLoadAction() {
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
				ItemClassData search = new ItemClassData();
				search.setUserId(oUserID.getUserId());
				search.setClassId(classId);
				
				itemClassData = (ItemClassData)session.selectOne("GetItemClassById", search);
				
				
			} catch (Exception e ) {
				addActionError(e.toString());
				return ERROR;
			} finally {
				this.endSession();
			}
			
			return SUCCESS;
	}
	
	/**
	 * @return Returns the classId.
	 */
	public long getClassId() {
		return classId;
	}
	/**
	 * @param classId The classId to set.
	 */
	public void setClassId(long classId) {
		this.classId = classId;
	}
	
	
	/**
	 * @return Returns the itemClassData.
	 */
	public ItemClassData getItemClassData() {
		return itemClassData;
	}
	/**
	 * @param itemClassData The itemClassData to set.
	 */
	public void setItemClassData(ItemClassData itemClassData) {
		this.itemClassData = itemClassData;
	}
}
