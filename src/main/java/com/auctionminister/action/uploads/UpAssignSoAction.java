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
import com.auctionminister.data.UploadData;
import com.auctionminister.data.UserSmallData;
import com.auctionminister.params.UploadParams;

/**
 * @author wggray
 */
public class UpAssignSoAction extends BaseAction {
	
	private static final long serialVersionUID = 3019851048300278818L;
	private String isSecure = "S";
	
	private long soNumber = 0;
	private long trxIndex = 0;
	private String itemId = "";
	
	public UpAssignSoAction() {
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
						
				UploadParams search = new UploadParams();
				search.setUserId(oUserID.getUserId());
				search.setTrxIndex(trxIndex);
				
				UploadData tranline = (UploadData)session.selectOne("GetTranLine", search);
				
				if (tranline!=null){
					tranline.setSoNumber(soNumber);
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
	 * @return Returns the soNumber.
	 */
	public long getSoNumber() {
		return soNumber;
	}
	/**
	 * @param soNumber The soNumber to set.
	 */
	public void setSoNumber(long soNumber) {
		this.soNumber = soNumber;
	}
	/**
	 * @return Returns the trxIndex.
	 */
	public long getTrxIndex() {
		return trxIndex;
	}
	/**
	 * @param trxIndex The trxIndex to set.
	 */
	public void setTrxIndex(long trxIndex) {
		this.trxIndex = trxIndex;
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