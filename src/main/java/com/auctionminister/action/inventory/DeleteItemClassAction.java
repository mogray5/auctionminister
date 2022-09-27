
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
public class DeleteItemClassAction extends BaseAction {

	private static final long serialVersionUID = 914610438864624230L;
	private String sIsSecure = "S";
	private long classId = 0;
	private String sStatus = null;
	
	public DeleteItemClassAction() {
		super();
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
	
	
	public String execute() throws Exception {
		
		this.startSession();
		
		try {	
				UserSmallData oUserID = this.verifyLogin();

				if (classId==0){
					
					addActionError("Unable to delete item class:  Class ID " + 
							Long.toString(classId) + 
							" not found.");
					return INPUT;
					
				} else {
					
					ItemClassData search = new ItemClassData();
					search.setUserId(oUserID.getUserId());
					search.setClassId(classId);
					
					session.update("DeleteItemClassA", search);
					session.update("DeleteItemClassB", search);
					session.commit();
				}				
				
			} catch (Exception e ) {
				addActionError(e.toString());
				return ERROR;
			} finally {
				this.endSession();
			}
			
			return SUCCESS;	
	}
}
