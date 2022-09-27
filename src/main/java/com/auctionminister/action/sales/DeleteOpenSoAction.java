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
import com.auctionminister.data.SaHeaderData;
import com.auctionminister.data.UserSmallData;

/**
 * @author wggray
 */
public class DeleteOpenSoAction extends BaseAction {

	private static final long serialVersionUID = -5182649056917263512L;
	private String isSecure = "S";
	private long soNumber = 0;
	private long docId = 0;
	private String batchId = "";
	
	/**
	 * 
	 */
	public DeleteOpenSoAction() {
		super();
	}

	public String execute() throws Exception {
		
		this.startSession();
		
		try {	
				UserSmallData oUserID = this.verifyLogin();
				SaHeaderData so = new SaHeaderData();
				so.setDocId(docId);
				so.setSoNumber(soNumber);
				so.setBatchId(batchId);
				so.setUserId(oUserID.getUserId());
				
				if (soNumber != 0 && docId != 0 && batchId!=null){
					session.update("DeleteOpenSoLines", Long.valueOf(soNumber));
					session.update("DeleteOpenSoHeader", so);
					session.commit();
				}
				
		} catch (Exception e ) {
			session.rollback();
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
	 * @return Returns the docId.
	 */
	public long getDocId() {
		return docId;
	}
	/**
	 * @param docId The docId to set.
	 */
	public void setDocId(long docId) {
		this.docId = docId;
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
}
