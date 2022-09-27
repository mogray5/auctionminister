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

import com.auctionminister.action.system.BaseAction;
import com.auctionminister.core.AmSo;
import com.auctionminister.data.SaBatchData;
import com.auctionminister.data.UserSmallData;
import com.auctionminister.params.SaLookupParams;

/**
 * @author wggray
 */
public class AddOpenSoAction extends BaseAction {

	private static final long serialVersionUID = -1407153785946918603L;
	private String isSecure = "S";
	
	//form data
	private String batchId = "";
	private long customerId = 0;
	
	public AddOpenSoAction() {
		super();
	}

	public String execute() throws Exception {
		
		long docId;
		float fee = 0;
		this.startSession();
		
		try {	
				UserSmallData oUserID = this.verifyLogin();
				AmSo soAdd = new AmSo();
				SaLookupParams search = new SaLookupParams();
				search.setUserId(oUserID.getUserId());
				search.setBatchId(batchId);
				
				docId = soAdd.AddBatch(batchId, customerId, oUserID, session);
				
				SaBatchData batch = (SaBatchData)session.selectOne("GetOpenSoBatch", search);
				
				if (batch!=null){
					soAdd.AddHeader(session, oUserID, customerId, docId,fee);
				} else {
					addActionError("batch is null");
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
	 * @return Returns the batchId.
	 */
	public String getBatchId() {
		return batchId;
	}
	/**
	 * @param batchId The batchId to set.
	 */
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	/**
	 * @return Returns the customerId.
	 */
	public long getCustomerId() {
		return customerId;
	}
	/**
	 * @param customerId The customerId to set.
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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
