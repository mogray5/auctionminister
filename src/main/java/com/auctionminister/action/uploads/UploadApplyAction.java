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
package com.auctionminister.action.uploads;

import com.auctionminister.action.system.BaseAction;
import com.auctionminister.core.UpApplyPoBatch;
import com.auctionminister.core.UpApplySaleBatch;
import com.auctionminister.data.UserSmallData;

/**
 * @author wggray
*/
public class UploadApplyAction extends BaseAction {

	private static final long serialVersionUID = 3587529076427959763L;
	private String isSecure = "S";
	
	private long batchId = 0;
	
	public UploadApplyAction() {
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
				
				//apply sales
				UpApplySaleBatch saleApply = new UpApplySaleBatch(session, 
						batchId, oUserID.getUserId());
				
				saleApply.Apply();
				
				//apply purchases
				UpApplyPoBatch poApply = new UpApplyPoBatch(session,
						batchId, oUserID.getUserId());
				
				poApply.Apply();
				
		} catch (Exception e ) {
			addActionError(e.toString());
			return ERROR;
		} finally {
			this.endSession();
		}
		
		return SUCCESS;
	}

	/**
	 * @return Returns the batchId.
	 */
	public long getBatchId() {
		return batchId;
	}
	/**
	 * @param batchId The batchId to set.
	 */
	public void setBatchId(long batchId) {
		this.batchId = batchId;
	}
}
