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
public class UpLoadAssignSoAction extends BaseAction {

	
	private static final long serialVersionUID = 5033446566609353478L;
	private String isSecure = "S";
	
	private UploadData tranLine = null;
	private long trxIndex = 0;
	
	public UpLoadAssignSoAction() {
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
				search.setTrxIndex(trxIndex);
				search.setUserId(oUserID.getUserId());
				
				tranLine = (UploadData)session.selectOne("GetPaypalTran", search);
				if (tranLine==null){
					addActionError("Error loading Paypal Transaction");
					return ERROR;
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
	 * @return Returns the tranLine.
	 */
	public UploadData getTranLine() {
		return tranLine;
	}
	/**
	 * @param tranLine The tranLine to set.
	 */
	public void setTranLine(UploadData tranLine) {
		this.tranLine = tranLine;
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
}
